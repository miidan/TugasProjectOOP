package com.pro.pcmappnew;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import com.pro.pcmappnew.dao.DAOorder;
import com.pro.pcmappnew.utils.Order;
import com.pro.pcmappnew.utils.OrderSpreadsheetWebService;
import com.pro.pcmappnew.utils.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OrderActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText nameInputField,emailInputField,phonenumberInputField,ordertypeInputField,orderdescInputField;
    private ProgressDialog progressDialog;
    private RadioGroup rbOrderGroup;
    private RadioButton rbOrder;
    private FirebaseUser user;
    private String userId;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();

        final OrderSpreadsheetWebService spreadsheetWebService = retrofit.create(OrderSpreadsheetWebService.class);
*/
        emailInputField = (EditText) findViewById(R.id.order_email);
        phonenumberInputField = (EditText) findViewById(R.id.order_phonenumber);
        //ordertypeInputField = (EditText) findViewById(R.id.order_ordertype);
        //rbOrderGroup = (RadioGroup) findViewById(R.id.rb_ordertype);
        orderdescInputField = (EditText) findViewById(R.id.order_desc);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        user = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
        userId = user.getUid();
        databaseReference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if (userProfile != null){
                    String email = userProfile.email;
                    String phonenumber = userProfile.phonenumber;
                    emailInputField.setText(email);
                    phonenumberInputField.setText(phonenumber);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        DAOorder dao = new DAOorder();
        Button btnOrder = findViewById(R.id.btn_test1);
        btnOrder.setOnClickListener(v->{
            Order order = new Order(
                    nameInputField.getText().toString(),
                    emailInputField.getText().toString(),
                    phonenumberInputField.getText().toString(),
                    spinner.getSelectedItem().toString(),
                    orderdescInputField.getText().toString());
            dao.add(order).addOnSuccessListener(suc->
            {
                Toast.makeText(OrderActivity.this, "Your Order has been placed", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(OrderActivity.this, "fail", Toast.LENGTH_SHORT).show();
            });
                       /* String nameInput = nameInputField.getText().toString();
                        String emailInput = emailInputField.getText().toString();
                        String phonenumberInput = phonenumberInputField.getText().toString();
                        String ordertypeInput = ordertypeInputField.getText().toString();
                        String orderdescInput = orderdescInputField.getText().toString();
                       // Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(nameInput, emailInput, phonenumberInput, ordertypeInput, orderdescInput);
                        //completeQuestionnaireCall.enqueue(callCallback);
                        //progressDialog.setMessage("Your order has been placed");
                        progressDial og.show(); */

        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
    /*private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }
    };*/
