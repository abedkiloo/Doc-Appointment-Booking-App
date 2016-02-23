package com.example.abedx.docbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    private static Spinner spinnerDocPatient;
    private static EditText editYourName, editUserName, editEmail, editPassword;
    private static ArrayAdapter docpatinetAdapter;
    private static String selection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //implemtation of login
        editYourName = (EditText) findViewById(R.id.editTextPersonalNames);
        editUserName = (EditText) findViewById(R.id.editTextREGUserName);
        editEmail = (EditText) findViewById(R.id.editTextREGEmail);
        editPassword = (EditText) findViewById(R.id.editTextREGPassword);
        spinnerDocPatient = (Spinner) findViewById(R.id.spinnerDocPatient);
        //intialize the adapter created from an string resource
        docpatinetAdapter = ArrayAdapter.createFromResource(this, R.array.DocorPatient, android.R.layout.simple_list_item_1);
        //set the spinner to be a drop down
        docpatinetAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        //let the spinner addapt the docpatinetAdapter
        spinnerDocPatient.setAdapter(docpatinetAdapter);
        spinnerDocPatient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "You will registered as " + selection, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnRegister(View view) {
        if (selection.trim().equals("Doctor")) {
            startActivity(new Intent(Register.this, DoctorsPanel.class));
        }
        else if(selection.trim().equals("Patient"))
        {
            startActivity(new Intent(Register.this,PatientLogin.class));
        }
    }
}
