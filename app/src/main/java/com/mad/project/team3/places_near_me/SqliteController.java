
package com.mad.project.team3.places_near_me;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteController extends SQLiteOpenHelper {
    private static final String LOGCAT = null;
    public String table_name="user_details";
    public String col1="firstname";
    public String col2="lastname";
    public String col3="dob";
    public String col4="country";
    public String col5="sex";
    public String col6="email";
    public String col7="uname";
    public String col8="password";
    String[] myStringArray = new String[2];



    public SqliteController(Context applicationcontext) {
        super(applicationcontext, "signup.db", null, 1);
        Log.d(LOGCAT, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE user_details ( firstname TEXT, lastname TEXT,dob TEXT,country TEXT,sex TEXT,email TEXT,uname TEXT,password TEXT)";
        database.execSQL(query);
        Log.d(LOGCAT, "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS user_details";
        database.execSQL(query);
        onCreate(database);
    }

    public void insertuser(String fname,String lname,String dob,String country,String Sex ,String email, String uname,String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstname",fname);
        values.put("lastname", lname);
        values.put("dob", dob);
        values.put("country", country);
        values.put("sex", Sex);
        values.put("email",email);
        values.put("uname", uname);
        values.put("password", password);
        database.insert("user_details", null, values);
        database.close();

        Log.d(LOGCAT, "insertion completed");


    }
    public String validate_user(String UserName,String Password )
    {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT uname as un FROM user_details WHERE " + col7 + "=? AND password=?", new String[]{UserName,Password});
            c.moveToFirst();
            return c.getString(c.getColumnIndex("un"));
        }
        catch (Exception e){
            return null;
        }


    }
    public String[] validate_email_and_send_email(String email){

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT uname as un,password as pwd FROM user_details WHERE " + col6 + "=?", new String[]{email});
            c.moveToFirst();
            myStringArray[0]= c.getString(c.getColumnIndex("un"));
            myStringArray[1]=c.getString(c.getColumnIndex("pwd"));
            return myStringArray;
        }
        catch (Exception e)
        {
            myStringArray=null;
            return myStringArray;
        }



    }
    public void cleardb()
    {   SQLiteDatabase database = this.getWritableDatabase();
        String query="delete from user_details";
        database.execSQL(query);
    }


}

