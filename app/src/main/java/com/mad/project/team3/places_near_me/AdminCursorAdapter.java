package com.mad.project.team3.places_near_me;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class AdminCursorAdapter extends CursorAdapter {
    public AdminCursorAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.listview_layout, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tvusername = (TextView) view.findViewById(R.id.t_username);
        TextView tvemail = (TextView) view.findViewById(R.id.t_email);
        // Extract properties from cursor
        String uname = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
        String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
        // Populate fields with extracted properties
        tvusername.setText(uname);
        tvemail.setText(email);
    }
}