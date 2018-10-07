package com.example.anonymoushacker.smart_home_android_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Dashboard extends AppCompatActivity {

    Button username, logout;
    ListView dashboard;

    String urlString = "http://192.168.1.22:3000/";
    String queryStringURL, line, usernameQuerystring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        declareVariable();
        handleEvent();
    }

    private void declareVariable() {
        username = findViewById(R.id.username);
        logout = findViewById(R.id.logout);
        dashboard = findViewById(R.id.dashboard);
    }

    private void handleEvent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myBundle");
        usernameQuerystring = bundle.getString("username");
        username.setText(usernameQuerystring);
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


            try {
                queryStringURL = urlString + "app.dashboard?username="+usernameQuerystring;

                URL url = new URL(queryStringURL);
                HttpURLConnection connection =  (HttpURLConnection) url.openConnection();

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
