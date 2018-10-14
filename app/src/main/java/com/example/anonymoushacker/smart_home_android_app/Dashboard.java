package com.example.anonymoushacker.smart_home_android_app;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Dashboard extends AsyncTask<String, Void, String> {

    String urlString = "http://192.168.1.22:3000/";
    String userIdQuerystring, queryStringURL, lineDashboard;

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
            queryStringURL = urlString + "app.dashboard?userid=" + userIdQuerystring;

            URL url = new URL(queryStringURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader buffer = new BufferedReader(inputStreamReader);
            lineDashboard = buffer.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lineDashboard;
    }
}

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dashboard);
//        declareVariable();
//        handleEvent();
//    }
//
//
//    private void handleEvent() {
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("myBundle");
//        usernameQuerystring = bundle.getString("username");
//        userIdQuerystring = bundle.getString("userid");
//        username.setText(usernameQuerystring);
//        house = bundle.getString("house");
//
//        try {
//            jsonArray = new JSONArray(house);
//            jsonObject = jsonArray.getJSONObject(0);
//
//            houseArray = jsonObject.getJSONArray("house");
//
//            for (int i=0;i<houseArray.length();i++){
//                houseName.add(houseArray.getString(i));
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        adapterHouses = new ArrayAdapter<String>(Dashboard.this,
//                android.R.layout.simple_list_item_1,
//                houseName);
//        dashboardListView.setAdapter(adapterHouses);
//
//        dashboardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                eachHouse = houseName.get(position);
////                Toast.makeText(Dashboard.this, ""+houseName.get(position), Toast.LENGTH_LONG).show();
//
//                HouseView houseView = new HouseView();
//                houseView.execute();
////                while (lineHouse == null){
////                    houseView.execute();
////                }
//
//                if (lineHouse == null) {
//                    Toast.makeText(Dashboard.this, "NUll URL", Toast.LENGTH_LONG).show();
//                }
//                else {
//                    Toast.makeText(Dashboard.this, ""+queryStringURL, Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
////        SystemClock.sleep(1000);
//
////        if (eachHouse != null)
//
//    }


////                queryStringURL = urlString + "app.house?userid=1&housename=House 001";
//

//
////                queryStringURL = urlString + "app.house?userid=1&housename=House%20001";

