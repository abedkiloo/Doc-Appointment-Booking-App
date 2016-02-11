package com.example.abedx.docbook;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

public class RegisterActivity extends AppCompatActivity {
    private static  Spinner spinnerDocPatient;
    private static EditText editYourName, editUserName, editEmail, editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        editYourName = (EditText) findViewById(R.id.editTextPersonalNames);
        editUserName = (EditText) findViewById(R.id.editTextREGUserName);
        editEmail = (EditText) findViewById(R.id.editTextREGEmail);
        editPassword = (EditText) findViewById(R.id.editTextREGPassword);
        spinnerDocPatient=(Spinner)findViewById(R.id.spinnerDocPatient);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
