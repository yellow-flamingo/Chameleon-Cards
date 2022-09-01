package com.android.chameleoncards;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        username = (EditText) findViewById(R.id.username2);
        password = (EditText) findViewById(R.id.password2);
        btnlogin = (Button) findViewById(R.id.btnlogin2);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();

                if (user.equals("") || pass.equals("")) {
                    Toast.makeText(AdminActivity.this, "Please fill out all the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (user.equals("Adm!n123") && pass.equals("Adm!n456")) {
                        Intent intent = new Intent(getApplicationContext(), NewUserActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(AdminActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}