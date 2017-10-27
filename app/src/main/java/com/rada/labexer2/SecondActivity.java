package com.rada.labexer2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SecondActivity extends AppCompatActivity {

    TextView displayOne, displayTwo;
    Button loadShared, loadInternal, clear, back;
    SharedPreferences preferences;
    FileInputStream fis;
    FileOutputStream fos;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        displayOne = (TextView) findViewById(R.id.displayOne);
        displayTwo = (TextView) findViewById(R.id.displayTwo);
        loadShared = (Button) findViewById(R.id.loadShared);
        loadInternal = (Button) findViewById(R.id.loadInternal);
        clear = (Button) findViewById(R.id.clear);
        back = (Button) findViewById(R.id.back);
        preferences = getApplication().getSharedPreferences(" ", MODE_PRIVATE);


    }

    public void sharedPreferences (View view) {
        String user = preferences.getString("username", "");
        String pwd = preferences.getString("password", "");
        displayOne.setText("User is " + user + " and");
        displayTwo.setText("Password is " + pwd);


    }

    public void internalStorage (View view) {
        StringBuffer buffer = new StringBuffer();
        StringBuffer secondBuffer = new StringBuffer();

        int read = 0;

        try {
            fis = openFileInput("username.txt");
            while ((read = fis.read()) != -1) {
                buffer.append((char)read);

            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }

        try {
            fis = openFileInput("password.txt");
            while ((read = fis.read()) != -1) {
                secondBuffer.append((char)read);

            }
            fis.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();

        }

        displayOne.setText("User is " + buffer.toString() + " and");
        displayTwo.setText("Password is " + secondBuffer.toString());


    }

    public void clear (View view) {
        

        try {
            fos = openFileOutput("username.txt", Context.MODE_PRIVATE);
            fos.flush();


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
            fos.flush();

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

        displayOne.setText(null);
        displayTwo.setText(null);


    }

    public void back (View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);


    }

}
