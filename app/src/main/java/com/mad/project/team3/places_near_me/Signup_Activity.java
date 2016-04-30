package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Signup_Activity extends AppCompatActivity {
    long dbstatus;
    boolean stat_fname=true;
    boolean stat_lname=true;
    boolean stat_sex = true;
    boolean stat_country=true;
    boolean stat_dob=false;
    boolean stat_username=false;
    boolean stat_password=false;
    boolean stat_email=false;
    String User_Creation_Email_MSg="Welcome,\n\nThank You for registering to the app.\n\nRegards,\nTeam3\nMAD";

    String fname;
    String lname;
    String dob;
    String country;
    String Sex;
    String username;
    String password;
    String email;
    EditText e_fname, e_lname, e_dob, e_country, e_email, e_username, e_password;
    RadioGroup radio_g;
    RadioButton radio_b;
    SqliteController controller = new SqliteController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    public void add_user(View view) {

try {



    e_fname = (EditText) findViewById(R.id.editText);
    fname = e_fname.getText().toString();
    //Toast.makeText(Signup_Activity.this, "fname="+fname, Toast.LENGTH_LONG).show();
    e_lname = (EditText) findViewById(R.id.editText2);
    lname = e_lname.getText().toString();
    // Toast.makeText(Signup_Activity.this, "lname="+lname, Toast.LENGTH_LONG).show();
    e_dob = (EditText) findViewById(R.id.editText3);
    dob = e_dob.getText().toString();
    // Toast.makeText(Signup_Activity.this, "dob="+dob, Toast.LENGTH_LONG).show();
    e_country = (EditText) findViewById(R.id.editText4);
    country = e_country.getText().toString();
    // Toast.makeText(Signup_Activity.this, "country="+country, Toast.LENGTH_LONG).show();
    e_email = (EditText) findViewById(R.id.editText7);
    email = e_email.getText().toString().trim();
    String emailPattern = "^\\S+@\\S+$";
    String pat_dob="^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";
    String pat_username= "^[a-z0-9_-]{3,15}$";
    String pat_password="^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

    // Toast.makeText(Signup_Activity.this, "email="+email, Toast.LENGTH_LONG).show();
    e_username = (EditText) findViewById(R.id.editText5);
    username = e_username.getText().toString();

    //Toast.makeText(Signup_Activity.this, "username="+username, Toast.LENGTH_LONG).show();
    e_password = (EditText) findViewById(R.id.editText6);
    password = e_password.getText().toString();
    // Toast.makeText(Signup_Activity.this, "password="+password, Toast.LENGTH_LONG).show();
    radio_g = (RadioGroup) findViewById(R.id.radioGroup2);
    int selected_id = radio_g.getCheckedRadioButtonId();
    radio_b = (RadioButton) findViewById(selected_id);
    Sex = radio_b.getText().toString();
    //Toast.makeText(Signup_Activity.this, "sex="+Sex, Toast.LENGTH_LONG).show();


// fname validation

    if (null == fname || fname.length() == 0)
    {
        stat_fname=false;
        Toast.makeText(this, "Please Enter fname Properly", Toast.LENGTH_LONG).show();

    }

    //lname validation
     if (null == lname || lname.length() == 0)
    {
        stat_lname=false;
        Toast.makeText(this, "Please Enter lname Properly", Toast.LENGTH_LONG).show();

    }


    // dob validation


    if (dob.matches(pat_dob))
    {
        stat_dob=true;
    }

    if (stat_dob==false)
    {
        Toast.makeText(this, "Please Enter dob Properly", Toast.LENGTH_LONG).show();

    }


// country validation

    if (null == country || country.length() == 0)
    { stat_country=false;
        Toast.makeText(this, "Please Enter country Properly", Toast.LENGTH_LONG).show();

    }

    //email validation


     if (email.matches(emailPattern))
    {
        stat_email=true;
        //Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
    }

    if (stat_email==false)
    {
        Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
    }


// sex validation
    if (null == Sex || Sex.length() == 0) {
        stat_sex=false;
        Toast.makeText(this, "Please Select a sex ", Toast.LENGTH_LONG).show();
    }

        // username validation
        // A username should be between 2 and 25 characters long.
                //It may contain characters, numbers and the ., -, _ symbols.
    if (username.matches(pat_username))
    {
        stat_username=true;
    }
    if (stat_username==false)
    {
        Toast.makeText(getApplicationContext(), "Please Enter username Properly", Toast.LENGTH_LONG).show();

    }
    String test_username=controller.check_existing_username(username);
    if (test_username==null)
    {
        Toast.makeText(this, "Username already picked:Pick new username", Toast.LENGTH_LONG).show();
        stat_username=false;

    }


String pass_suggestion="";
    //password validation Rules
/*
^                 # start-of-string
(?=.*[0-9])       # a digit must occur at least once
(?=.*[a-z])       # a lower case letter must occur at least once
(?=.*[A-Z])       # an upper case letter must occur at least once
(?=.*[@#$%^&+=])  # a special character must occur at least once you can replace with your special characters
(?=\\S+$)          # no whitespace allowed in the entire string
.{4,}             # anything, at least six places though
$                 # end-of-string
*/
    if (password.matches(pat_password))
    {
        stat_password=true;
    }
    if (stat_password==false){

        Toast.makeText(getApplicationContext(), "Please Enter password Properly", Toast.LENGTH_LONG).show();

    }

    //Final Validation

if (stat_fname==true&&stat_lname==true&&stat_dob==true&&stat_country==true&&stat_sex==true&&stat_username==true&&stat_password==true&&stat_email==true) {
     dbstatus=controller.insertuser(fname, lname, dob, country, Sex, email, username, password);

    if  (dbstatus==-1)
    {
        Toast.makeText(Signup_Activity.this, "User already Exists", Toast.LENGTH_SHORT).show();
    }
    else {
        Toast.makeText(this, "User Registered Successfully", Toast.LENGTH_LONG).show();
        new MyTask().execute();
        Intent i = new Intent(Signup_Activity.this, Signup_Success.class);
        startActivity(i);
    }


}

}
catch (Exception e)
{
    Toast.makeText(this, "Please select Sex", Toast.LENGTH_LONG).show();
}

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



    public void login(View view)
    {
       Intent i = new Intent(Signup_Activity.this, Login_Page.class);
        startActivity(i);


    }
    private class MyTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                GMailSender sender = new GMailSender("myapp123zzz@gmail.com", "googleapp");
                sender.sendMail("Register Email",
                        User_Creation_Email_MSg,
                        "myapp123zzz@gmail.com",email
                        );

            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
            return null;
        }

    }
}
