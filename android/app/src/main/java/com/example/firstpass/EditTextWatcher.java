package com.example.firstpass;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EditTextWatcher implements TextWatcher {

    TextView passwordTextView;
    EditText userSecretField;
    EditText appNameField;

    PasswordService passwordService;

    EditTextWatcher(TextView passwordTextView, EditText userSecret, EditText appName) {
        super();
        this.passwordTextView = passwordTextView;
        this.userSecretField = userSecret;
        this.appNameField = appName;
        this.passwordService = new PasswordService(passwordTextView);
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String userSecret = userSecretField.getText().toString();
        String appName = appNameField.getText().toString();

        passwordService.putPassword(userSecret, appName);
    }
}
