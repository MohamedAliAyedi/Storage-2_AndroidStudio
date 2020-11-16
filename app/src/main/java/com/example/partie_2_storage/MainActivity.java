package com.example.partie_2_storage;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText mName, mPassword;
    private Button btnLogin;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mName = findViewById(R.id.etName);
        mPassword = findViewById(R.id.etPassword);
        btnLogin =  findViewById(R.id.btnLogin);
        mCheckBox = findViewById(R.id.checkbox);


        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        checkSharedPreferences();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mCheckBox.isChecked()){
                    mEditor.putString(getString(R.string.checkbox), "True");
                    mEditor.commit();

                    //save the name
                    String name = mName.getText().toString();
                    mEditor.putString(getString(R.string.name), name);
                    mEditor.commit();

                    //save the password
                    String password = mPassword.getText().toString();
                    mEditor.putString(getString(R.string.password), password);
                    mEditor.commit();

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);

                }else{
                    //set a checkbox when the application starts
                    mEditor.putString(getString(R.string.checkbox), "False");

                    mEditor.commit();

                    //save the name
                    mEditor.putString(getString(R.string.name), "");
                    mEditor.commit();

                    //save the password
                    mEditor.putString(getString(R.string.password), "");
                    mEditor.commit();
                }
            }
        });

    }

    private void checkSharedPreferences(){
        String checkbox = mPreferences.getString(getString(R.string.checkbox), "False");
        String name = mPreferences.getString(getString(R.string.name), "");
        String password = mPreferences.getString(getString(R.string.password), "");



        mName.setText(name);
        mPassword.setText(password);

        if(checkbox.equals("True")){
            mCheckBox.setChecked(true);
        }else{
            mCheckBox.setChecked(false);
        }

    }
}

