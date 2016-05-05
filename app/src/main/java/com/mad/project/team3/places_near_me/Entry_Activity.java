package com.mad.project.team3.places_near_me;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

public class Entry_Activity extends AppCompatActivity {
    ImageButton img_login;
    LoginButton img_fblogin;
    ImageButton img_signup;
    ImageButton map;
    private TextView mTextDetails;
    private CallbackManager mCallbackManager;
    private AccessTokenTracker tracker;
    private ProfileTracker profileTracker;

    SqliteController controller = new SqliteController(this);
    private FacebookCallback<LoginResult> mCallback=new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken=loginResult.getAccessToken();
            Profile profile=Profile.getCurrentProfile();
            Intent intent=new Intent(Entry_Activity.this,Home_Screen_Main_Activity.class);
            startActivity(intent);

            if(profile!=null)
            {

            }

        }
        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_entry_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        LoginButton loginButton=(LoginButton)findViewById(R.id.imageButton2);
        loginButton.setBackgroundResource(R.drawable.fblogin);
        loginButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        // loginButton.setCompoundDrawablePadding(0);
        //loginButton.setPadding(0, 0, 0, 0);
        loginButton.setText("");
        loginButton.setMaxHeight(5000);
        loginButton.setMaxWidth(150);

        mCallbackManager= CallbackManager.Factory.create();
        tracker=new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };
        profileTracker=new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                // displaymessage(newProfile);
            }
        };
        tracker.startTracking();
        profileTracker.startTracking();
    }
    public void login_with_rotate(View v)  {
        img_login=(ImageButton)findViewById(R.id.imageButton);
        /*img_login.setBackgroundResource(R.drawable.loginbackground);
        img_login.setCropToPadding(true);*/
        Animation startRotateAnimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);

        img_login.startAnimation(startRotateAnimation);

        Intent i = new Intent(Entry_Activity.this, Login_Page.class);
        startActivity(i);

    }
    public void fblogin_with_rotate(View v)
    {
        if(CheckNetwork.isInternetAvailable(Entry_Activity.this)) //returns true if internet available
        {
            img_fblogin=(LoginButton)findViewById(R.id.imageButton2);
            Animation startRotateAnimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
            img_fblogin.startAnimation(startRotateAnimation);
            LoginButton loginButton=(LoginButton)v.findViewById(R.id.imageButton2);

            // loginButton.setReadPermissions("user_friends");
            loginButton.setReadPermissions(Arrays.asList(
                    "public_profile", "email", "user_birthday", "user_friends"));
            // loginButton.setFragment();
            loginButton.registerCallback(mCallbackManager, mCallback);
            //do something. loadwebview.
        }
        else
        {
            Toast.makeText(Entry_Activity.this,"No Internet Connection",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onResume() {
        super.onResume();
        LoginButton loginButton=(LoginButton)findViewById(R.id.imageButton2);

        // loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
        // loginButton.setFragment();
        loginButton.registerCallback(mCallbackManager, mCallback);

    }

    @Override
    public void onStop() {
        super.onStop();
        tracker.stopTracking();
        profileTracker.stopTracking();
    }

    public void signup_with_rotate(View v)  {
        img_signup=(ImageButton)findViewById(R.id.imageButton4);
        Animation startRotateAnimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        img_signup.startAnimation(startRotateAnimation);
        Intent i = new Intent(Entry_Activity.this, Signup_Activity.class);
        startActivity(i);

    }
    public void map_with_rotate(View v)
    {
        map=(ImageButton)findViewById(R.id.imageButton3);
        Animation startRotateAnimation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);
        map.startAnimation(startRotateAnimation);
        Intent i = new Intent(Entry_Activity.this, Home_Screen_Main_Activity.class);
        startActivity(i);

    }



}
