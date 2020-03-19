package com.kerala.foodex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnSignUp, btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignUp = (Button)findViewById(R.id.signUp);
        btnLogIn = (Button)findViewById(R.id.logIn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIn = new Intent(MainActivity.this, SignUp.class);
                startActivity(logIn);
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logIn = new Intent(MainActivity.this, LogIn.class);
                startActivity(logIn);
            }
        });
    }
}
