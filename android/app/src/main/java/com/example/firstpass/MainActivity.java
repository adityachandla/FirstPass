package com.example.firstpass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userSecret;
    EditText appName;
    TextView passwordTextView;
    Button copyToClipboradButton;

    static Context appContext;


    public static Context getContext() {
        return appContext;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appContext = getApplicationContext();

        initFields();

        TextWatcher textWatcher = new EditTextWatcher(passwordTextView, userSecret, appName);
        userSecret.addTextChangedListener(textWatcher);
        appName.addTextChangedListener(textWatcher);

        copyToClipboradButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("password", passwordTextView.getText());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(MainActivity.this, "Coppied successfully", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFields() {
        userSecret = findViewById(R.id.userSecret);
        appName = findViewById(R.id.appName);
        passwordTextView = findViewById(R.id.password);
        copyToClipboradButton = findViewById(R.id.copyToClipboardButton);
    }
}
