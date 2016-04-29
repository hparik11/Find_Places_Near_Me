package com.mad.project.team3.places_near_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Change_Pasword_User extends AppCompatActivity {
    SqliteController controller = new SqliteController(this);
    EditText oldpwd, newpwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__pasword__user);

    }
    public void clicked(View view){
        String pat_password="^((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
        oldpwd = (EditText) findViewById(R.id.oldpwd);
        newpwd = (EditText) findViewById(R.id.newpwd);
        String s_oldpwd = oldpwd.getText().toString();
        String s_newpwd = newpwd.getText().toString();
        if (s_newpwd.matches(pat_password)) {
            boolean check_old_pwd = controller.validate_user1(Login_Page.global_uname, s_oldpwd);
            if (check_old_pwd == true) {

                Toast.makeText(this, "Password Validated", Toast.LENGTH_LONG).show();
                int rows = controller.update_password(Login_Page.global_uname, s_newpwd);
                if (rows > 0) {
                    Toast.makeText(this, "Password Updated", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Change_Pasword_User.this,Login_Page.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(this, "Password not  Updated", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Old Password Incorrect", Toast.LENGTH_LONG).show();
            }
        }
        else {Toast.makeText(this, "New Password Format Incorrect", Toast.LENGTH_LONG).show();}



    }
}
