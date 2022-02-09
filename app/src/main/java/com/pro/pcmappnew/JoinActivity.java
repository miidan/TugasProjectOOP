package com.pro.pcmappnew;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.pro.pcmappnew.utils.JoinSpreadsheetWebService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JoinActivity extends AppCompatActivity {

    private EditText nameInputField,idnumberInputField,emailInputField,phonenumberInputField,addressInputField,birthdateInputField;
    private ProgressDialog progressDialog;
    private RadioGroup rbOrderGroup;
    private RadioButton rbOrder;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();

        final JoinSpreadsheetWebService spreadsheetWebService = retrofit.create(JoinSpreadsheetWebService.class);

        nameInputField = (EditText) findViewById(R.id.join_name);
        idnumberInputField = (EditText) findViewById(R.id.join_idnumber);
        emailInputField = (EditText) findViewById(R.id.join_email);
        phonenumberInputField = (EditText) findViewById(R.id.join_phonenumber);
        birthdateInputField = (EditText) findViewById(R.id.join_birthdate);
        addressInputField = (EditText) findViewById(R.id.join_address);
        //rbOrderGroup = (RadioGroup) findViewById(R.id.rb_ordertype);


        findViewById(R.id.btn_test2).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nameInput = nameInputField.getText().toString();
                        String idnumberInput = idnumberInputField.getText().toString();
                        String emailInput = emailInputField.getText().toString();
                        String phonenumberInput = phonenumberInputField.getText().toString();
                        String addressInput = addressInputField.getText().toString();
                        String birthdateInput = birthdateInputField.getText().toString();
                        Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(nameInput, idnumberInput, emailInput, phonenumberInput, addressInput, birthdateInput);
                        completeQuestionnaireCall.enqueue(callCallback);
                        //progressDialog.setMessage("Your order has been placed");
                        //progressDialog.show();
                    }
                }
        );
    }
    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }
    };
}