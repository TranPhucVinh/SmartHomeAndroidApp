package com.example.anonymoushacker.smart_home_android_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    Button username, logout;
    ListView dashboardListView;
    ArrayAdapter<String> adapterHouses;
    ArrayList<String> houseName;

//    String urlString = "http://192.168.40.74:3000/";

    String urlString = "http://192.168.1.22:3000/";
    String queryStringURL;

    String usernameQuerystring, userIdQuerystring, house, eachHouse, lineHouse;

    JSONArray jsonArray, houseArray;
    JSONObject jsonObject;

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
        houseName = new ArrayList<>();
    }

    private void handleEvent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("myBundle");
        usernameQuerystring = bundle.getString("username");
        userIdQuerystring = bundle.getString("userid");
        username.setText(usernameQuerystring);
        house = bundle.getString("house");

        try {
            jsonArray = new JSONArray(house);
            jsonObject = jsonArray.getJSONObject(0);

            houseArray = jsonObject.getJSONArray("house");

            for (int i=0;i<houseArray.length();i++){
                houseName.add(houseArray.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapterHouses = new ArrayAdapter<String>(Dashboard.this,
                android.R.layout.simple_list_item_1,
                houseName);
        dashboardListView.setAdapter(adapterHouses);

        dashboardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                eachHouse = houseName.get(position);
                Toast.makeText(Dashboard.this, ""+houseName.get(position), Toast.LENGTH_LONG).show();

//                while (lineHouse == null) {

//                }
            }
        });

//        SystemClock.sleep(1000);
//        if (eachHouse != null) {
//            HouseView houseView = new HouseView();
//            houseView.execute();
//                if (lineHouse == null) {
//                    Toast.makeText(Dashboard.this, "NUll URL", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(Dashboard.this, ""+queryStringURL, Toast.LENGTH_LONG).show();
//                }
//        }
    }

    class HouseView extends AsyncTask<Void, Void, Void>{
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

//                queryStringURL = urlString + "app.house?userid=1&housename=House 001";

                queryStringURL = urlString + "app.house?userid="+userIdQuerystring+"&housename="+eachHouse;
                queryStringURL = queryStringURL.replaceAll(" ", "%20");

//                queryStringURL = urlString + "app.house?userid=1&housename=House%20001";
                URL url = new URL(queryStringURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader buffer = new BufferedReader(inputStreamReader);
                lineHouse = buffer.readLine();
//                while (lineHouse == null){
//                    lineHouse = buffer.readLine();
//                }
                buffer.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
