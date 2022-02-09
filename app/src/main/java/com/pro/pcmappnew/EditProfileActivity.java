package com.pro.pcmappnew;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pro.pcmappnew.dao.DAOupdate;
import com.pro.pcmappnew.utils.User;

import java.util.HashMap;

public class EditProfileActivity extends AppCompatActivity {
    private FirebaseUser user;
    private String userId;
    private Button btnChange;
    private Button buttonUpdate;
    private EditText editTextEmail;
    private EditText editTextFullname;
    private EditText editTextPassword;
    private EditText editTextPhonenumber;
    public static  final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        DAOupdate dao = new DAOupdate();
        user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        userId = user.getUid();
        editTextPhonenumber = (EditText) findViewById(R.id.editTextPhonenumber);
        editTextEmail= (EditText) findViewById(R.id.editTextEmail);
        editTextFullname = (EditText) findViewById(R.id.editTextFullname);

        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    String fullname = userProfile.fullname;
                    String email = userProfile.email;
                    String phonenumber = userProfile.phonenumber;

                    editTextEmail.setText(email);
                    editTextPhonenumber.setText(phonenumber);
                    editTextFullname.setText(fullname);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        buttonUpdate = (Button) findViewById(R.id.buttonUpdate);
        buttonUpdate.setOnClickListener(view -> {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("fullname", editTextFullname.getText().toString());
            hashMap.put("phonenumber", editTextPhonenumber.getText().toString());

            dao.update(userId,hashMap).addOnSuccessListener(suc->{
                Toast.makeText(this,"Profile Updated",Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er ->{
                Toast.makeText(this,""+er.getMessage(),Toast.LENGTH_SHORT).show();
            });
        });


        btnChange = (Button) findViewById(R.id.btn_reset_password);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfileActivity.this, ChangePasswordActivity.class));
            }
        });
    }
}
