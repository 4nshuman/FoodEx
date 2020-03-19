package com.kerala.foodex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kerala.foodex.model.User;

public class SignUp extends AppCompatActivity {

    EditText phoneNumberEditText,  nameEditText, passwordEditText;
    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phoneNumberEditText = (EditText)findViewById(R.id.editPhoneNumber);
        nameEditText = (EditText)findViewById(R.id.editName);
        passwordEditText = (EditText)findViewById(R.id.editPassword);
        signUpButton = (Button)findViewById(R.id.signUpButton);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("User");

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog signUpProgressDialog = new ProgressDialog(SignUp.this);
                signUpProgressDialog.setMessage("please wait..");
                signUpProgressDialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(phoneNumberEditText.getText().toString()).exists()){
                            signUpProgressDialog.dismiss();
                            Toast.makeText(SignUp.this, dataSnapshot.child(phoneNumberEditText.getText().toString()).toString(), Toast.LENGTH_LONG).show();
                        }
                        else{
                            signUpProgressDialog.dismiss();
                            User user = new User(nameEditText.getText().toString(), passwordEditText.getText().toString());
                            table_user.child(phoneNumberEditText.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sign up Successful", Toast.LENGTH_LONG).show();
                            finish();
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
