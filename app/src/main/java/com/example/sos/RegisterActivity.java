package com.example.sos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sos.modal.User;
import com.example.sos.sql.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private EditText textInputEditTextName;
    private EditText textInputEditTextEmail;
    private EditText textInputEditTextPassword;
    private EditText textInputEditTextConfirmPassword;

    private Button appCompatButtonRegister;

    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {

        textInputEditTextName = (EditText) findViewById(R.id.editName);
        textInputEditTextEmail = (EditText) findViewById(R.id.editEmail);
        textInputEditTextPassword = (EditText) findViewById(R.id.editPassword);
        textInputEditTextConfirmPassword = (EditText) findViewById(R.id.editConfirmPassword);

        appCompatButtonRegister = (Button) findViewById(R.id.buttonSignUp);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DatabaseHelper(activity);
        user = new User();

    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        postDataToSQLite();
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {

        if (android.util.Patterns.EMAIL_ADDRESS.matcher(textInputEditTextEmail.getText().toString()).matches() &&
                !textInputEditTextEmail.getText().toString().equals("") &&
                textInputEditTextPassword.getText().toString().equals(textInputEditTextConfirmPassword.getText().toString()) &&
                textInputEditTextPassword.getText().toString().length() >= 6 &&
                !textInputEditTextName.getText().toString().equals("") &&
                !databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Toast.makeText(RegisterActivity.this, "Sign Up successful!", Toast.LENGTH_LONG).show();
            emptyInputEditText();
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        }

        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(textInputEditTextEmail.getText().toString()).matches()){
            Toast.makeText(RegisterActivity.this, "Please enter valid email address", Toast.LENGTH_LONG).show();
        }

        else if(textInputEditTextEmail.getText().toString().equals("")){
            Toast.makeText(RegisterActivity.this, "Plesse enter youe email address", Toast.LENGTH_LONG).show();
        }

        else if (!textInputEditTextPassword.getText().toString().equals(textInputEditTextConfirmPassword.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "Password not match", Toast.LENGTH_LONG).show();
        }

        else if (textInputEditTextPassword.getText().toString().length() < 6) {
            Toast.makeText(RegisterActivity.this, "Password minimum contain 6 character", Toast.LENGTH_LONG).show();
        }

        else if (textInputEditTextName.getText().toString().equals("")) {
            Toast.makeText(RegisterActivity.this, "Please enter your name", Toast.LENGTH_LONG).show();
        }

         else {
            // Snack Bar to show error message that record already exists
            Toast.makeText(RegisterActivity.this, "Account already exists!", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}