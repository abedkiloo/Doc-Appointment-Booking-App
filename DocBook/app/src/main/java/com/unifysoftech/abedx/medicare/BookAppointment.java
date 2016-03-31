package com.unifysoftech.abedx.medicare;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {
    private Button mPickDate;
    private int mYear;
    private int mMonth;
    private TextView mBodyText;
    static final int DATE_DIALOG_ID = 0;
    public static final String DEFAULT = "You Have not Login Please Login first";
    private String URL = "http://mc.rapando.co.ke/src/php/appointments.php";
    private int mDay;
    public static String KEY_patName = "patientName";
    public static String KEY_docName = "doctorName";
    public static String KEY_date = "moment";
    public static Button btnNotify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        DisplayMetrics displayMetrics=new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
//        int width=displayMetrics.widthPixels;
//        int height=displayMetrics.heightPixels;
//        getWindow().setLayout((int)(width*0.8),(int)(height*0.6));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //date picker presentation
        mPickDate = (Button) findViewById(R.id.pickDate);//button for showing date picker dialog
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        mBodyText = (TextView) findViewById(R.id.showDate);

        // display the current date
        updateDisplay();
    }

    //return date picker dialog
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);
        }
        return null;
    }

    //update month day year
    private void updateDisplay() {
        mBodyText.setText(//this is the edit text where you want to show the selected date
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(mYear).append("-")
                        .append(mMonth + 1).append("-")
                        .append(mDay).append(""));


        //.append(mMonth + 1).append("-")
        //.append(mDay).append("-")
        //.append(mYear).append(" "));
    }

    // the call back received when the user "sets" the date in the dialog
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    public void btnBookAppointment(View view) {

        SharedPreferences loadData;
        loadData = getSharedPreferences("User Data", Context.MODE_PRIVATE);
        final String youruserNmae = loadData.getString("SETUSER", DEFAULT);
        String yourPassword = loadData.getString("PASSWORD", DEFAULT);
        final String setdate = mBodyText.getText().toString();
        final String docName = "Lily%20Njeri";
        MessageClass.message(getApplicationContext(), "Your Appointment has beeen recived");

        startActivity(new Intent(getApplicationContext(), DoctorCatergoriesFragment.class));
        int mNotificationId = 001;
        Intent resultIntent = new Intent(getApplicationContext(), DoctorCatergoriesFragment.class);
        PendingIntent pIntent = PendingIntent.getActivity(getApplicationContext(), 0, resultIntent, 0);
        NotificationCompat.Builder noti = new NotificationCompat.Builder(getApplicationContext())
                .setTicker("Appointment").setContentTitle("Booked Appointment").setSmallIcon(R.mipmap.docicon).setContentInfo("Booked Appointment with " +
                        "Doctor Lily Njeri");

        PendingIntent resultPendingIntent =
                PendingIntent.getActivity(
                        getApplicationContext(),
                        0,
                        resultIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        noti.setContentIntent(resultPendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyMgr.notify(mNotificationId, noti.build());


//        final ProgressDialog progressDialog1 = ProgressDialog.show(this, "Getting Doctors For you ....", "Please wait ...", false, false);
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("APPOINT", response);
//                progressDialog1.dismiss();
//                if(response.equals("1"))
//                {
//                    MessageClass.message(getApplicationContext(),"Your Appointment has beeen recived");
//                    startActivity(new Intent(getApplicationContext(),DoctorCatergoriesFragment.class));
//                }
//                else if (response.equals("0"))
//                {
//                    MessageClass.message(getApplicationContext(),"Your Appointment has not been received");
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError volleyError) {
//                        //Log.d("StringReqErro",volleyError.toString());
//                        progressDialog1.dismiss();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//
//                params.put(KEY_docName,docName);
//                params.put(KEY_patName,youruserNmae);
//                params.put(KEY_date,setdate);
//                return params;
//            }
//        };
//        RequestQueue requestQueuesend = Volley.newRequestQueue(getApplicationContext());
//        requestQueuesend.add(stringRequest);

    }
}
