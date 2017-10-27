package com.rada.labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText etUser, etPw;
    Button shared, internal, next;
    SharedPreferences.Editor editor;
    FileOutputStream fos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = (EditText) findViewById(R.id.etUser);
        etPw = (EditText) findViewById(R.id.etPw);
        shared = (Button) findViewById(R.id.shared);
        internal = (Button) findViewById(R.id.internal);
        next = (Button) findViewById(R.id.next);
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(" ", MODE_PRIVATE);
        editor = preferences.edit();

    }

    public void sharedPreferences (View view) {

        editor.putString("username", etUser.getText().toString());
        editor.putString("password", etPw.getText().toString());
        editor.commit();
        Toast.makeText(this, "Data saved in Shared Preferences!", Toast.LENGTH_SHORT).show();


    }

    public void internalStorage (View view) {

        String user = etUser.getText().toString();
        String passw = etPw.getText().toString();

        try {
            fos = openFileOutput("username.txt", Context.MODE_PRIVATE);
            fos.write(user.getBytes());


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        try {
            fos = openFileOutput("password.txt", Context.MODE_PRIVATE);
            fos.write(passw.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                fos.close();

            } catch (IOException e) {
                e.printStackTrace();

            }
        }


        Toast.makeText(this, "Data saved in Internal Storage!", Toast.LENGTH_SHORT).show();

    }

    public void submit (View view) {

        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);



    }
}
