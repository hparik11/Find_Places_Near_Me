package com.mad.project.team3.places_near_me;

/**
 * Created by Vishwanath on 4/28/2016.
 */
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by shara on 11/3/2015.
 */
public class UserSession {

    private SharedPreferences session;

    public UserSession(Context cntx) {
        // TODO Auto-generated constructor stub
        session = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setSession(String id) {
        session.edit().putString("SessionID", id).commit();
    }

    public void setSessionPWD(String pwd){
        session.edit().putString("SessionPWD", pwd).commit();
    }

    public String getSession() {
        String sID = session.getString("SessionID","");
        return sID;
    }

    public String getSessionPWD(){
        String sPWD = session.getString("SessionPWD","");
        return sPWD;
    }

    public SharedPreferences deleteSession() {
        //String sID = session.getString("SessionID","");
        //session = null;
        session.edit().remove("SessionID").commit();
        session.edit().remove("SessionPWD").commit();
        session.edit().clear();
        session.edit().commit();

        return session;
    }
}
