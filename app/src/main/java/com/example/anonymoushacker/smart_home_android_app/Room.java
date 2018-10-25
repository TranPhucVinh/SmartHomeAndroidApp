package com.example.anonymoushacker.smart_home_android_app;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class Room extends AsyncTask<String, Void, String> {
    String urlString = "https://smarthome-thesis-bku.herokuapp.com/app/";
    String userIdQuerystring, queryStringURL, eachHouse, eachFloor, eachRoom, lineDevice;

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
            eachFloor = values[2];
            eachRoom = values[3];
            queryStringURL = urlString + "app.room?userid="+userIdQuerystring+"&housename="+eachHouse+"&floorname="+eachFloor+"&roomname="+eachRoom;
            queryStringURL = queryStringURL.replaceAll(" ", "%20");

            URL url = new URL(queryStringURL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader buffer = new BufferedReader(inputStreamReader);
            lineDevice = buffer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineDevice;
    }
}
