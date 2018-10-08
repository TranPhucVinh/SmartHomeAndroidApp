package com.example.anonymoushacker.smart_home_android_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Dashboard extends AppCompatActivity {

    Button username, logout;
    ListView dashboardListView;

    String urlString = "http://192.168.1.22:3000/";

    String usernameQuerystring, house;

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
        dashboardListView = findViewById(R.id.dashboard);
    }

    private void handleEvent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myBundle");
        usernameQuerystring = bundle.getString("username");
        username.setText(usernameQuerystring);
        house = bundle.getString("house");

        Toast.makeText(Dashboard.this, ""+house, Toast.LENGTH_LONG).show();
    }
}
