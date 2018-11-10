package com.example.anonymoushacker.smart_home_android_app;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class DeviceStatus extends AsyncTask<Void, Void, String> {
    String urlString = "https://smarthome-thesis-bku.herokuapp.com/app/app.return";
    String devicesStatus;

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }

    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    protected String doInBackground(Void... values) {
        try {
            URL url = new URL(urlString);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader buffer = new BufferedReader(inputStreamReader);
            devicesStatus = buffer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return devicesStatus;
    }
}
