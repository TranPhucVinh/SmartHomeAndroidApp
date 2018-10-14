package com.example.anonymoushacker.smart_home_android_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    EditText textUsername, textPassword;
    Button signin, logout;

    Button dbusername, houseButton, houseUser, floorButton, floorUser;

    String username, password;

    String userIdQuerystring;
    String resultString, dashboardReturn, houseReturn, floorReturn;
    String eachHouse, eachFloor, eachRoom;

    JSONArray jsonArray, houseArray, floorArray, roomArray;
    JSONObject jsonObject;
    ListView dashboardListView, houseListView, floorListView, roomListView;
    ArrayAdapter<String> adapterHouses, adapterFloors, adapterRooms;
    ArrayList<String> houseName, floorName, roomName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declareVariable();
        handleEvent();
    }

    private void declareVariable() {

        textUsername = findViewById(R.id.houseUser);
        textPassword = findViewById(R.id.password);
        signin = findViewById(R.id.button);
        houseName = new ArrayList<>();
        floorName = new ArrayList<>();
        roomName  = new ArrayList<>();
    }

    private void handleEvent() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = textUsername.getText().toString();
                password = textPassword.getText().toString();

                Validation validation = new Validation();
                try {
                    resultString = validation.execute(username, password).get(); // get return value from doInBackground
                    if (resultString.equalsIgnoreCase("Try again")) {
                        Toast.makeText(MainActivity.this, "" + resultString, Toast.LENGTH_LONG).show();
                    } else {
                        jsonArray = new JSONArray(resultString);
                        jsonObject = jsonArray.getJSONObject(0);
                        if (jsonObject.has("message")) {
                            userIdQuerystring = jsonObject.getString("id");
                            setContentView(R.layout.activity_dashboard);

                            // declare listview after loading layout activity_dashboard
                            dashboardListView = findViewById(R.id.dashboard);
                            dbusername = findViewById(R.id.houseUser);
                            dashboardEvent();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace(); //handle it the way you like
                } catch (ExecutionException e) {
                    e.printStackTrace();//handle it the way you like
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void dashboardEvent() {
        Dashboard dashboard = new Dashboard();
        dbusername.setText(username);

        try {
            dashboardReturn = dashboard.execute(userIdQuerystring).get();
        } catch (InterruptedException e) {
            e.printStackTrace(); //handle it the way you like
        } catch (ExecutionException e) {
            e.printStackTrace();//handle it the way you like
        }

        if (dashboardReturn != null) {
            try {
                jsonArray = new JSONArray(dashboardReturn);
                jsonObject = jsonArray.getJSONObject(0);

                houseArray = jsonObject.getJSONArray("house");

                for (int i = 0; i < houseArray.length(); i++) {
                    houseName.add(houseArray.getString(i));
                }
                adapterHouses = new ArrayAdapter<String>(MainActivity.this,
                        android.R.layout.simple_list_item_1,
                        houseName);
                dashboardListView.setAdapter(adapterHouses);

            } catch (JSONException e) {
                e.printStackTrace();
            }

            dashboardListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    eachHouse = houseName.get(position);
                    houseClickEvent();
                }
            });
        }
    }

    private void houseClickEvent() {
        House house = new House();
        try {
            houseReturn = house.execute(userIdQuerystring, eachHouse).get();
        } catch (InterruptedException e) {
            e.printStackTrace(); //handle it the way you like
        } catch (ExecutionException e) {
            e.printStackTrace();//handle it the way you like
        }
        if (houseReturn != null) {
            setContentView(R.layout.house);
            houseListView = findViewById(R.id.houseListView);
            houseButton = findViewById(R.id.houseButton);
            houseUser = findViewById(R.id.houseUser);

            houseButton.setText(eachHouse);
            houseUser.setText(username);
            if (houseReturn.equalsIgnoreCase("Try again")) {
                Toast.makeText(MainActivity.this, "House currently doesn't have any floors", Toast.LENGTH_LONG).show();
            } else {
                try {
                    jsonArray = new JSONArray(houseReturn);
                    jsonObject = jsonArray.getJSONObject(0);

                    floorArray = jsonObject.getJSONArray("floor");

                    for (int i = 0; i < floorArray.length(); i++) {
                        floorName.add(floorArray.getString(i));
                    }
                    adapterFloors = new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            floorName);
                    houseListView.setAdapter(adapterFloors);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
                    houseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            eachFloor = floorName.get(position);
                            floorClickEvent();
                        }
                    });
        }
    }

    private void floorClickEvent() {
        Floor floor = new Floor();
        try {
            floorReturn = floor.execute(userIdQuerystring, eachHouse, eachFloor).get();
        } catch (InterruptedException e) {
            e.printStackTrace(); //handle it the way you like
        } catch (ExecutionException e) {
            e.printStackTrace();//handle it the way you like
        }
        if (houseReturn != null) {
            setContentView(R.layout.floor);
            floorListView = findViewById(R.id.floorListView);
            floorButton = findViewById(R.id.floorButton);
            floorUser = findViewById(R.id.floorUser);

            floorButton.setText(eachFloor);
            floorUser.setText(username);

            if (floorReturn.equalsIgnoreCase("Try again")) {
                Toast.makeText(MainActivity.this, "Floor currently doesn't have any rooms", Toast.LENGTH_LONG).show();
            } else {
                try {
                    jsonArray = new JSONArray(floorReturn);
                    jsonObject = jsonArray.getJSONObject(0);

                    roomArray = jsonObject.getJSONArray("room");

                    for (int i = 0; i < roomArray.length(); i++) {
                        roomName.add(roomArray.getString(i));
                    }
                    adapterRooms = new ArrayAdapter<String>(MainActivity.this,
                            android.R.layout.simple_list_item_1,
                            roomName);
                    floorListView.setAdapter(adapterRooms);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}