package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Home_Screen_Main_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    NavigationView navigationView = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_navigation_drawer);
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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

//            intent.putExtra("Category", "");
//            startActivity(intent);
//            setResult(1, intent);

            startActivity(intent);

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

        } else if (id == R.id.nav_manage) {
            Intent i = new Intent(Home_Screen_Main_Activity.this, Change_Pasword_User.class);
            startActivity(i);
            setResult(1, i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
