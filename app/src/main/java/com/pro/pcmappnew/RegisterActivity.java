package com.pro.pcmappnew;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.pro.pcmappnew.utils.User;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

   // RelativeLayout rellay1, rellay2;

    //Handler handler = new Handler();
    //Runnable runnable = new Runnable() {
     //   @Override
      //  public void run() {
     //       rellay1.setVisibility(View.VISIBLE);
    //        rellay2.setVisibility(View.VISIBLE);
    //    }
    //};
    //defining view objects
    private EditText editTextEmail;
    private EditText editTextFullname;
    private EditText editTextPassword;
    private EditText editTextPhoneNumber;
    private Button buttonSignup,btnBack;
    private TextView btnResetPassword;

    private TextView textViewSignIn;

    private ProgressDialog progressDialog;


    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //if getCurrentUser does not returns null
        if(firebaseAuth.getCurrentUser() != null){
            //that means user is already logged in
            //so close this activity
            finish();

            //and open profile activity
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

       // rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        //rellay2 = (RelativeLayout) findViewById(R.id.rellay2);

        //handler.postDelayed(runnable, 2000);
        //initializing views
        editTextFullname = (EditText) findViewById(R.id.editTextFullname);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextPhoneNumber= (EditText) findViewById(R.id.editTextPhonenumber);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);
        btnBack = (Button) findViewById(R.id.btn_back);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        buttonSignup.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void registerUser(){

        //getting email and password from edit texts
        String fullname = editTextFullname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phonenumber  = editTextPhoneNumber.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(fullname)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(phonenumber)){
            Toast.makeText(this,"Please enter phonenumber",Toast.LENGTH_LONG).show();
            return;
        }
        if(password.length()<5){
            Toast.makeText(this,"Password is too short",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            User user = new User (fullname, email, phonenumber);
                            FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "User has been registered"
                                                , Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                                    }
                                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                                }
                            });
                        }else{
                            //display some message here
                            Toast.makeText(RegisterActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view)  {

        if(view == buttonSignup){
            registerUser();
        }

        if(view == textViewSignIn){
            //open login activity when user taps on the already registered textview
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}