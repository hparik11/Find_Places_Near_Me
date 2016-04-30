package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class User_details_Admin extends AppCompatActivity {
    private List<User_Attribute> ulist = new ArrayList<>();
    private RecyclerView recyclerView;
    private Recycler_Adaptor mAdapter;
    SqliteController controller=new SqliteController(this);
    public static String user_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details__admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new Recycler_Adaptor(ulist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        prepareuserData();


    }
    private void prepareuserData() {
        Cursor c = controller.get_each_user_details(Admin_Console.u_email);
        c.moveToFirst();
        String uname=c.getString(c.getColumnIndex("uname"));
        String fname=c.getString(c.getColumnIndex("firstname"));
        String lname=c.getString(c.getColumnIndex("lastname"));
        String dob=c.getString(c.getColumnIndex("dob"));
        String country=c.getString(c.getColumnIndex("country"));
        String sex=c.getString(c.getColumnIndex("sex"));
        String status=c.getString(c.getColumnIndex("status"));
        if (status.equals("Active"))
        {
            user_status="Deactivate user";
        }
        else {
            user_status = "Activate USer";
        }
        //set the button data here
        Button p1_button = (Button)findViewById(R.id.button5);
        p1_button.setText(user_status);

        User_Attribute udetails = new User_Attribute("First Name", fname);
        ulist.add(udetails);
        udetails = new User_Attribute("Last Name", lname);
        ulist.add(udetails);
        udetails = new User_Attribute("DOB", dob);
        ulist.add(udetails);
        udetails = new User_Attribute("Country", country);
        ulist.add(udetails);
        udetails = new User_Attribute("Sex", sex);
        ulist.add(udetails);
        udetails = new User_Attribute("Email",Admin_Console.u_email );
        ulist.add(udetails);
        udetails = new User_Attribute("UserName", uname);
        ulist.add(udetails);
        udetails = new User_Attribute("Status", status);
        ulist.add(udetails);



        mAdapter.notifyDataSetChanged();

    }
    public void OnButtonPress(View view){
        int noofrows=controller.updateuser(Admin_Console.u_email);

        Intent i = new Intent(User_details_Admin.this, User_details_Admin.class);
        startActivity(i);
    }
    public void back(View view){
        Intent i = new Intent(User_details_Admin.this, Admin_Console.class);
        startActivity(i);
    }
}
