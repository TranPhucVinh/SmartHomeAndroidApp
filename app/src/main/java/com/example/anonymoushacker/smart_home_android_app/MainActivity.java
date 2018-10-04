package com.example.anonymoushacker.smart_home_android_app;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    EditText textUsername, textPassword;
    Button signin;

    String urlString = "https://smarthome-thesis-bku.herokuapp.com/";
    String queryStringURL;
    String line;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declareVariable();
        handleEvent();
    }

    private void declareVariable() {

        textUsername = findViewById(R.id.username);
        textPassword = findViewById(R.id.password);
        signin = findViewById(R.id.button);
    }

    private void handleEvent() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation validation = new Validation();
                validation.execute();
                Toast.makeText(MainActivity.this, ""+line,Toast.LENGTH_LONG).show();
            }
        });
    }

    class Validation extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
        protected Void doInBackground(Void... values) {
            String username = textUsername.getText().toString();
            String password = textPassword.getText().toString();

            try {
                queryStringURL = urlString + "validate?username="+username+"&password="+password;

                URL url = new URL(queryStringURL);
                HttpsURLConnection connection =  (HttpsURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader buffer = new BufferedReader(inputStreamReader);
                line = buffer.readLine();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}

