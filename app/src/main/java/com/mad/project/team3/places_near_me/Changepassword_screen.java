package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Changepassword_screen extends AppCompatActivity {
EditText emailtext;
String mail_body;


        SqliteController controller=new SqliteController(this);
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_changepassword_screen);
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
        }
        public void callpasswordupdate(View view)
        {
                Intent i = new Intent(Changepassword_screen.this, Passwordupdated.class);
                startActivity(i);
        }
        public void validate_email_and_send_email(View view){
                String []result;
                emailtext=(EditText)findViewById(R.id.email);
                String email_string=emailtext.getText().toString();
                result=controller.validate_email_and_send_email(email_string);
                if (result==null){
                        Toast.makeText(this, "The Entered email does not exist", Toast.LENGTH_LONG).show();
                }
                else {
                        Toast.makeText(this, "Email Validated ", Toast.LENGTH_LONG).show();
                        mail_body="The Usernmae is "+result[0]+"The Password is"+result[1];
                        Toast.makeText(this, "email body is "+mail_body, Toast.LENGTH_LONG).show();
           //             sendemail(email_string);


                }}
               /* public void sendemail(String receipent)
        {
                try {
                        GMailSender sender = new GMailSender("srk8989@gmail.com", "vinayak!@3");
                        sender.sendMail("Password Reset",
                                mail_body,
                                "srk8989@gmail.com",
                                receipent);
                } catch (Exception e) {
                        Log.e("SendMail", e.getMessage(), e);
                }


        }*/

        }

