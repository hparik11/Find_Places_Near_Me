package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Changepassword_screen extends AppCompatActivity {
EditText emailtext;
String mail_body;
        String email_string;


        SqliteController controller=new SqliteController(this);
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);

                setContentView(R.layout.activity_changepassword_screen);
                Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);


        }
        public void callpasswordupdate(View view)
        {
                Intent i = new Intent(Changepassword_screen.this, Passwordupdated.class);
                startActivity(i);
        }
        public void validate_email_and_send_email(View view){
                String []result;
                emailtext=(EditText)findViewById(R.id.email);
                 email_string=emailtext.getText().toString();
                result=controller.validate_email_and_send_email(email_string);
                if (result==null){
                        Toast.makeText(this, "The Entered email does not exist", Toast.LENGTH_LONG).show();
                }
                else {
                       //oast.makeText(this, "Email Validated ", Toast.LENGTH_LONG).show();
                        mail_body="The Username is "+result[0]+"The Password is "+result[1];
                       //oast.makeText(this, "email body is "+mail_body, Toast.LENGTH_LONG).show();
                        new MyTask().execute();
                        Toast.makeText(this, "Please check your email for Login details", Toast.LENGTH_LONG).show();


                }}

        private class MyTask extends AsyncTask<Void,Void,Void>{

                @Override
                protected Void doInBackground(Void... params) {
                        try {
                                GMailSender sender = new GMailSender("myapp123zzz@gmail.com", "googleapp");
                                sender.sendMail("Password Reset",
                                        mail_body,
                                        "myapp123zzz@gmail.com",
                                        email_string);

                        } catch (Exception e) {
                                Log.e("SendMail", e.getMessage(), e);
                        }
                        return null;
                }

        }

        }

