package com.pro.pcmappnew;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pro.pcmappnew.utils.User;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private TextView textViewFullname;
    private TextView textViewPhoneNumber;
    private Button buttonLogout;
    private FirebaseUser user;
    private String userId;
    private Button btnTest;
    private Button btnUpdate;


    String fullname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if the user is not logged in
        //that means current user will return null
        if(firebaseAuth.getCurrentUser() == null) {
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //initializing views
        textViewFullname = (TextView) findViewById(R.id.textViewFullname);
        //textViewPhoneNumber = (TextView) findViewById(R.id.textViewPhoneNumber);

        user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        userId = user.getUid();
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    String fullname = userProfile.fullname;
                    textViewFullname.setText("Hello, "+fullname+".");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        //textViewFullname.setText(fullname);
       // textViewPhoneNumber.setText(""+phonenumber);



        //adding listener to button
        buttonLogout.setOnClickListener(this);
        btnTest = (Button) findViewById(R.id.btn_test);
        btnUpdate = (Button) findViewById(R.id.btn_update);


        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, MainActivity.class));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, EditProfileActivity.class));
            }
        });

    }

    @Override
    public void onClick(View view) {
        //if logout is pressed
        if(view == buttonLogout){
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, com.pro.pcmappnew.LoginActivity.class));
        }
    }
}