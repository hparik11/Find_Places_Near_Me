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

public class Login_Page extends AppCompatActivity {

    EditText uname;
    EditText pwd;
    SqliteController controller = new SqliteController(this);
   // Button signin;
    //Button changepwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__page);
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

    public void changepwd(View view)
    {
        Intent i = new Intent(Login_Page.this, Changepassword_screen.class);
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

        else if (username.equals("admin") && password.equals("admin1"))
        {
            Intent i = new Intent(Login_Page.this, Home_Screen_Main_Activity.class);
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

                 Toast.makeText(this, "User Validated", Toast.LENGTH_LONG).show();
                 Intent i = new Intent(Login_Page.this, Home_Screen_Main_Activity.class);
                 startActivity(i);
                 //intent to harsh Map module}
             }
        }
    }
    }


