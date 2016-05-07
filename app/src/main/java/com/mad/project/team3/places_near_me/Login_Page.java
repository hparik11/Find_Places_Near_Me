package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Page extends AppCompatActivity {

    EditText uname;
    EditText pwd;
    TextView uflag;
    SqliteController controller = new SqliteController(this);
    public static String global_uname;
    public static String global_pwd;
    protected UserSession s;
   // Button signin;
    //Button changepwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);
        s=new UserSession(this);
    if((s.getSession()!="")&& (s.getSessionPWD()!=""))
        {
            Intent i = new Intent(Login_Page.this, Home_Screen_Main_Activity.class);
            startActivity(i);
        }
       Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        uflag=(TextView)findViewById(R.id.button7);
        uflag.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);


    }
        public void changepwd(View view)
    {
        Intent i = new Intent(Login_Page.this, Changepassword_screen.class);
        startActivity(i);
    }


    public void new_user(View view){
        Intent i = new Intent(Login_Page.this, Signup_Activity.class);
        startActivity(i);
    }


    public void map_module(View view){

        // this gets called after signin button is pressed.

        uname=(EditText)findViewById(R.id.editText12);
        pwd=(EditText)findViewById(R.id.editText13);
        String username  = uname.getText().toString();
        String password=pwd.getText().toString();

        if(null == username || username.length() == 0)
        {
            Toast.makeText(this,"Please Enter UserName Properly",Toast.LENGTH_LONG).show();

        }
        else if(null == password || password.length() == 0)
        {
            Toast.makeText(this,"Please Enter Password Properly",Toast.LENGTH_LONG).show();

        }
        else if (username.equals("admin") && password.equals("admin"))
        {

            Intent i = new Intent(Login_Page.this, Admin_Console.class);
            startActivity(i);
            // intent to admin console
        }


        else {
            String dbuser = controller.validate_user(username,password);

             if (dbuser==null)
             {
                 Toast.makeText(this, "Invalid Login Please Enter Correct Login Details", Toast.LENGTH_LONG).show();
             }
            else {

                 global_pwd=password;
                         global_uname=username;
                 s.setSession(username);
                 s.setSessionPWD(password);
                 Toast.makeText(this, "User Validated", Toast.LENGTH_LONG).show();

                 Intent i = new Intent(Login_Page.this, Home_Screen_Main_Activity.class);
                 startActivity(i);
                 //intent to harsh Map module}
             }
        }
    }

    }


