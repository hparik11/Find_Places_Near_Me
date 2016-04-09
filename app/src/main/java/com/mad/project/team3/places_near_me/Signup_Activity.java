package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class Signup_Activity extends AppCompatActivity {

    String fname ;
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

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        //return true;
    }
*/
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
    email = e_email.getText().toString();
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


    if (null == fname || fname.length() == 0) {
        Toast.makeText(this, "Please Enter fname Properly", Toast.LENGTH_LONG).show();

    } else if (null == lname || lname.length() == 0) {
        Toast.makeText(this, "Please Enter lname Properly", Toast.LENGTH_LONG).show();

    } else if (null == dob || dob.length() == 0) {
        Toast.makeText(this, "Please Enter dob Properly", Toast.LENGTH_LONG).show();

    } else if (null == country || country.length() == 0) {
        Toast.makeText(this, "Please Enter country Properly", Toast.LENGTH_LONG).show();

    } else if (null == email || email.length() == 0) {
        Toast.makeText(this, "Please Enter email Properly", Toast.LENGTH_LONG).show();

    } else if (null == Sex || Sex.length() == 0) {
        Toast.makeText(this, "Please Enter sex Properly", Toast.LENGTH_LONG).show();

    } else if (null == username || username.length() == 0) {
        Toast.makeText(this, "Please Enter username Properly", Toast.LENGTH_LONG).show();

    } else if (null == password || password.length() == 0) {
        Toast.makeText(this, "Please Enter password Properly", Toast.LENGTH_LONG).show();

    } else {
        controller.insertuser(fname, lname, dob, country, Sex, email, username, password);
        Toast.makeText(this, "User Added to Database", Toast.LENGTH_LONG).show();
        Intent i = new Intent(Signup_Activity.this, Signup_Success.class);
        startActivity(i);
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
}
