package com.kerala.foodex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kerala.foodex.model.User;

public class LogIn extends AppCompatActivity {

    EditText phoneNumberEditText, passwordEditText;
    Button logInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        phoneNumberEditText = (EditText)findViewById(R.id.editPhoneNumber);
        passwordEditText = (EditText)findViewById(R.id.editPassword);
        logInButton = (Button)findViewById(R.id.logInButton);

        /*FirebaseApp.initializeApp(LogIn.this);*/
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog logInProgressDialog = new ProgressDialog(LogIn.this);
                logInProgressDialog.setMessage("please wait..");
                logInProgressDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(phoneNumberEditText.getText().toString()).exists()){
                            //Get User Information
                            logInProgressDialog.dismiss();
                            User user = dataSnapshot.child(phoneNumberEditText.getText().toString()).getValue(User.class);
                            if(user.getPassword().equals(passwordEditText.getText().toString())){
                                Toast.makeText(LogIn.this,"Password Verified", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(LogIn.this, "Sign In failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            logInProgressDialog.dismiss();

                            Toast.makeText(LogIn.this, "User not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
