package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class Home_Screen_Main_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText uname;

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    protected  UserSession s;
    public static final int logout_menu = Menu.FIRST+1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation_drawer);

        s=new UserSession(this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

        TextView tv = (TextView) findViewById(R.id.txtView);
        tv.setSelected(true);

        uname = (EditText) findViewById(R.id.username);
        if (uname != null) {
            uname.setText(Login_Page.global_uname);
        }
        PagerAdapter adapter = new ImageAdapter(this);
        viewPager.setAdapter(adapter);

        Fragment1 fragment = new Fragment1();
        android.support.v4.app.FragmentTransaction fragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        // TODO Auto-generated method stub
        boolean result = super.onCreateOptionsMenu(menu);
        menu.add(0, logout_menu, 0,  "Logout");
        return result;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case logout_menu:
                if(s != null) {
                    s.deleteSession();
                    // super.onDestroy();
                    finish();
                    Intent logout = new Intent(Home_Screen_Main_Activity.this, Login_Page.class);
                    logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(logout);
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent(Home_Screen_Main_Activity.this, GooglePlacesActivity.class);
        //Intent intent1 = new Intent(Home_Screen_Main_Activity.this, List_Items_Details.class);

        if (id == R.id.nav_home) {



        } else if (id == R.id.nav_hotel) {

            intent.putExtra("Category", "Restaurants");
            startActivity(intent);
            setResult(1, intent);


        } else if (id == R.id.nav_atm) {

            intent.putExtra("Category","ATM");
            startActivity(intent);
            setResult(1, intent);



        } else if (id == R.id.nav_gas) {

            intent.putExtra("Category","Gas Station");
            startActivity(intent);
            setResult(1, intent);

        } else if (id == R.id.nav_library) {
            intent.putExtra("Category", "Library");
            startActivity(intent);
            setResult(1, intent);


        }else if (id == R.id.nav_custom) {

            intent.putExtra("Category", "");
            startActivity(intent);
            setResult(1, intent);



        }
        else if (id == R.id.nav_share) {
            new MyTask().execute();


        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(Home_Screen_Main_Activity.this, Change_Pasword_User.class);
            startActivity(i);
            setResult(1, i);

        }
        else if (id == R.id.nav_logout){

            if(s != null) {
                s.deleteSession();
                // super.onDestroy();
                finish();
                Intent logout = new Intent(Home_Screen_Main_Activity.this, Login_Page.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logout);
                return true;
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private class MyTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                GMailSender sender = new GMailSender("myapp123zzz@gmail.com", "googleapp");
                sender.sendMail("Register Email",
                        "Hello User",
                        "myapp123zzz@gmail.com",Signup_Activity.email
                );

            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }
            return null;
        }

    }
}
