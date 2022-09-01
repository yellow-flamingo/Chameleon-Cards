package com.android.chameleoncards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;
    DBHelper DB;
    PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnlogin1);
        DB = new DBHelper(this);
        preferenceManager = PreferenceManager.getInstance(this);

        //DB.queryData( "CREATE TABLE IF NOT EXISTS CARDS (Id INTEGER PRIMARY KEY AUTOINCREMENT, username TEXT, image BLOB)");

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(LogInActivity.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    //Boolean checkuserpass = DB.checkUsernamePassword(user, pass);
                    Boolean checkuserpass2 = DB.queryData("select * from users where username = " +
                            "'" + user + "' and " + "password" + " = '" + pass + "'");
                    if (checkuserpass2 == true) {
                        Toast.makeText(LogInActivity.this, "Sign In Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        preferenceManager.setString("user_logged_in", user);
                        preferenceManager.setString("user_password", pass);
                    } else {
                        Toast.makeText(LogInActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}