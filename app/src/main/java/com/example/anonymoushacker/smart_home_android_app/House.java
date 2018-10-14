package com.example.anonymoushacker.smart_home_android_app;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class House extends AsyncTask<String, Void, String> {
    String urlString = "http://192.168.1.22:3000/";
    String userIdQuerystring, queryStringURL, eachHouse, lineHouse;

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected void onPostExecute(String aVoid) {
        super.onPostExecute(aVoid);
    }

    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    protected String doInBackground(String... values) {

        try {
            userIdQuerystring = values[0];
            eachHouse = values[1];
            queryStringURL = urlString + "app.house?userid="+userIdQuerystring+"&housename="+eachHouse;
            queryStringURL = queryStringURL.replaceAll(" ", "%20");

            URL url = new URL(queryStringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader buffer = new BufferedReader(inputStreamReader);
            lineHouse = buffer.readLine();
        } catch (Exception e) {
        e.printStackTrace();
        }
        return lineHouse;
    }
}
