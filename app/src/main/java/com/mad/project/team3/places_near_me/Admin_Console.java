package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Admin_Console extends AppCompatActivity {
    SqliteController controller = new SqliteController(this);
    public static String u_email;
    public static String u_long_email;
    AdminCursorAdapter aca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__console);
        Cursor cursor = controller.getAll();

        ListView lvItems = (ListView) findViewById(R.id.display_listview);
        startManagingCursor(cursor);
             aca = new AdminCursorAdapter(this, cursor, 0);
        lvItems.setAdapter(aca);
        aca.changeCursor(cursor);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView txtview = (TextView) view.findViewById(R.id.t_email);
                String emails = txtview.getText().toString();
                u_email = emails;
                u_long_email=emails;
                Intent i = new Intent(Admin_Console.this, User_details_Admin.class);
                startActivity(i);

            }
        });
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick (AdapterView parent, View view, int position, long id) {
                TextView txtview1 = (TextView) view.findViewById(R.id.t_email);

                String emails = txtview1.getText().toString();
                u_long_email=emails;
                u_email=emails;

                System.out.println("Long click");
                startActionMode((android.view.ActionMode.Callback) modeCallBack);
                view.setSelected(true);

                return true;
            }
        });

    }


    private ActionMode.Callback modeCallBack = new ActionMode.Callback() {

        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        public void onDestroyActionMode(ActionMode mode) {
            mode = null;
        }

        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.setTitle("Options");
            mode.getMenuInflater().inflate(R.menu.admin_menu, menu);
            return true;

        }

        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()) {
                case R.id.edit:
                    editfunction();
                    return true;
                case R.id.delete:
                    //close the action mode
                    deletefunction();
                    //mode.finish();
                    return true;
                default:
                    mode.finish();
                    return false;
            }
        }
    };



    public void adminlogout(View view)
{finish();

    Intent i = new Intent(Admin_Console.this, Entry_Activity.class);
    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    startActivity(i);
}
    public void editfunction(){

        Intent i = new Intent(Admin_Console.this, User_details_Admin.class);
        startActivity(i);

        }

    public void deletefunction()
    {
        controller.deleteuser(u_long_email);
        Intent i = new Intent(Admin_Console.this, Admin_Console.class);
        startActivity(i);
    }
}
