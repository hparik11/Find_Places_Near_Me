package com.mad.project.team3.places_near_me;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlaceSqliteController extends SQLiteOpenHelper {
    private static final String LOGCAT = null;

    /** Version number of the database */
    private static int VERSION = 3;

    /** Field 1 of the table locations, which is the primary key */
    public static final String FIELD_ROW_ID = "_id";
    public static final String FIELD_LNG = "lng";
    public static final String FIELD_LAT = "lat";
    public static final String FIELD_ID = "id";
    public static final String FIELD_ICN = "icon";
    public static final String FIELD_NAM = "name";
    public static final String FIELD_VCT = "vicinity";
    public static final String FIELD_PID = "placeid";
    public static final String FIELD_PHN = "phone";
    public static final String FIELD_RAT = "rating";
    public static final String FIELD_WEB = "website";

    /** A constant, stores the the table name */
    private static final String DATABASE_TABLE = "Places_details";

    /** An instance variable for SQLiteDatabase */
    SQLiteDatabase mDB;


    Context context;
    /** Constructor */
    public PlaceSqliteController(Context context) {
        super(context, "places.db", null, VERSION);
        Log.d("TTTTTTTT", "TTTTTT");
        this.context = context;
    }

    /** This is a callback method, invoked when the method getReadableDatabase() / getWritableDatabase() is called
     * provided the database does not exists
     * */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Log.d("TTTTTTTT","TTTTTT");
        String sql = "create table " + DATABASE_TABLE + " ( " +
                FIELD_ROW_ID + " integer primary key autoincrement , " +
                FIELD_LNG + " double not null, " +
                FIELD_LAT + " double not null, " +
                FIELD_ID + " text not null," +
                FIELD_ICN + " text," +
                FIELD_NAM + " text not null," +
                FIELD_VCT + " text," +
                FIELD_PID + " text," +
                FIELD_PHN + " text," +
                FIELD_RAT + " text," +
                FIELD_WEB + " text" +
                ")";

        db.execSQL(sql);
        Log.d(LOGCAT, "Table Created");
    }

    /** Inserts a new location to the table locations */
    public long insert(ContentValues contentValues){
        long rowID = mDB.insert(DATABASE_TABLE, null, contentValues);
        return rowID;
    }

    /** Deletes all locations from the table */
    public int del(){
        int cnt = mDB.delete(DATABASE_TABLE, null , null);
        return cnt;
    }

    /** Returns all the locations from the table */
    public Cursor getAllLocations(){
        return mDB.query(DATABASE_TABLE, new String[] { FIELD_ROW_ID, FIELD_LNG,  FIELD_LAT ,
                                                        FIELD_ID,   FIELD_ICN,   FIELD_NAM,
                                                        FIELD_VCT,  FIELD_PID, FIELD_PHN,
                                                        FIELD_RAT, FIELD_WEB } ,
                null, null, null, null, null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS " + DATABASE_TABLE;
        database.execSQL(query);
        onCreate(database);
    }

    public void insertplace(Double lng,Double lat,String id,String icn,String nam,String vct, String pid,String phn, String rat, String web) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("lng",lng);
        values.put("lat", lat);
        values.put("id", id);
        values.put("icon", icn);
        values.put("name", nam);
        values.put("vicinity", vct);
        values.put("placeid", pid);
        values.put("phone", phn);
        values.put("rating", rat);
        values.put("website", web);
        database.insert(DATABASE_TABLE, null, values);
        database.close();

        Log.d(LOGCAT, "insertion completed");

    }

    public void cleardb()
    {   SQLiteDatabase database = this.getWritableDatabase();
        String query="delete from " + DATABASE_TABLE;
        database.execSQL(query);
    }

}

