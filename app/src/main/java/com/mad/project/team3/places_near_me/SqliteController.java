
package com.mad.project.team3.places_near_me;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SqliteController extends SQLiteOpenHelper {
    private static final String LOGCAT = null;
    public String table_name = "user_details";
    public String col1 = "firstname";
    public String col2 = "lastname";
    public String col3 = "dob";
    public String col4 = "country";
    public String col5 = "sex";
    public String col6 = "email";
    public String col7 = "uname";
    public String col8 = "password";
    public String col9 = "status";
    String[] myStringArray = new String[2];
    String val;


    public SqliteController(Context applicationcontext) {
        super(applicationcontext, "signup.db", null, 4);
        Log.d(LOGCAT, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query = "CREATE TABLE user_details ( firstname TEXT, lastname TEXT,dob TEXT,country TEXT,sex TEXT,email TEXT,uname TEXT,status TEXT,password TEXT,CONSTRAINT customers_pk PRIMARY KEY (email))";
        database.execSQL(query);
        Log.d(LOGCAT, "Table Created");
    }

    //
    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS user_details";
        database.execSQL(query);
        onCreate(database);
    }

    public void deleteuser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.delete(table_name, "email = ?", new String[]{email});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkstat_user(String email) {


        return true;
    }

    public int updateuser(String email) {
        if (User_details_Admin.user_status.equals("Deactivate user")) {
            val = "Inactive";
        } else {
            val = "Active";
        }
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col9, val); //These Fields should be your String values of actual column names
        return db.update(table_name, cv, col6 + " = ?", new String[]{email});


    }
    public int update_password(String username,String Password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col8, Password);
        return db.update(table_name, cv, col7 + " = ?", new String[]{username});
    }

    public long insertuser(String fname, String lname, String dob, String country, String Sex, String email, String uname, String password) {

        long status;
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("firstname", fname);
        values.put("lastname", lname);
        values.put("dob", dob);
        values.put("status", "Active");
        values.put("country", country);
        values.put("sex", Sex);
        values.put("email", email);
        values.put("uname", uname);
        values.put("password", password);

        status = database.insert("user_details", null, values);
        return status;


    }

    public Cursor getAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db == null) {
            return null;
        }
        return db.rawQuery("select uname as _id,email from user_details", null);
    }


    public String validate_user(String UserName, String Password) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM user_details WHERE " + col7 + "=? AND password=?", new String[]{UserName, Password});
            c.moveToFirst();
            String user_stat=c.getString(c.getColumnIndex("status"));
            if (user_stat.equals("Active"))
            {
                return c.getString(c.getColumnIndex("uname"));
            }
            else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }
    public String check_existing_username(String Username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM user_details WHERE " + col7 + "=?", new String[]{Username});
        if (c.getCount() > 0)
        {
            return null;
        }
        else {return "success"; }
    }

    public Cursor get_each_user_details(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM user_details WHERE " + col6 + "=?", new String[]{email});
        return c;

    }

    public String[] validate_email_and_send_email(String email) {

        try {

            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT uname as un,password as pwd FROM user_details WHERE " + col6 + "=?", new String[]{email});
            c.moveToFirst();
            myStringArray[0] = c.getString(c.getColumnIndex("un"));
            myStringArray[1] = c.getString(c.getColumnIndex("pwd"));
            return myStringArray;
        } catch (Exception e) {
            myStringArray = null;
            return myStringArray;
        }


    }


    public void cleardb() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "delete from user_details";
        database.execSQL(query);
    }

    public boolean validate_user1(String UserName, String Password) {
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM user_details WHERE " + col7 + "=? AND password=?", new String[]{UserName, Password});
            if (c.getCount() > 0)
            {
                return true;
            }

            else
            {
                return false;
            }
        }
         catch (Exception e)
        {
            return false;
        }
    }


}

