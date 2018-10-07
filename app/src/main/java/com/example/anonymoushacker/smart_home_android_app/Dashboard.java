package com.example.anonymoushacker.smart_home_android_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        declareVariable();
        handleEvent();
    }

    private void declareVariable() {
    }

    private void handleEvent() {
    }
}
