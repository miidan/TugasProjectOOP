package com.pro.pcmappnew;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pro.pcmappnew.utils.User;

public class NavigationActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private TextView textViewUserEmail;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        userId = user.getUid();
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null) {
                    String email = userProfile.fullname;
                    textViewUserEmail.setText("" + email);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

