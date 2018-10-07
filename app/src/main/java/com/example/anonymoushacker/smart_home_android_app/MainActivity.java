package com.example.anonymoushacker.smart_home_android_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

    EditText textUsername, textPassword;
    Button signin;

    String urlString = "http://192.168.1.22:3000/";
    String queryStringURL;
    String line, username, password;

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
//                textUsername.setText("");
//                textPassword.setText("");
                try {
                    if (line.equalsIgnoreCase("Login sucessfully")) {
                        Intent intent = new Intent(MainActivity.this,Dashboard.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("username",username);
                        intent.putExtra("myBundle", bundle);
                        startActivity(intent);
                    } else if (line.equalsIgnoreCase("Try again")) {
                        Toast.makeText(MainActivity.this, "Try again", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(MainActivity.this, "no connection", Toast.LENGTH_LONG).show();
                    }
                }
                catch (NullPointerException e){
                    Toast.makeText(MainActivity.this, "nothing here", Toast.LENGTH_LONG).show();
                }
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
            username = textUsername.getText().toString();
            password = textPassword.getText().toString();

            try {
                queryStringURL = urlString + "validate?username="+username+"&password="+password;

                URL url = new URL(queryStringURL);
                HttpURLConnection connection =  (HttpURLConnection) url.openConnection();

                connection.setRequestMethod("POST");
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
                BufferedReader buffer = new BufferedReader(inputStreamReader);
                line = buffer.readLine();

//                jsonarray = new JSONArray(line);
//                jsonObject = jsonarray.getJSONObject(0);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
}