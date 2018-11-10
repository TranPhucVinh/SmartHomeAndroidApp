package com.example.anonymoushacker.smart_home_android_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    ArrayList<String> deviceType, deviceName, deviceID, deviceStatus;
    String currentDeviceStatus, idValue, statusValue;
    Context context;
    JSONArray jsonArray;
    JSONObject jsonObject;
    OkHttpClient client;
    WebSocket ws;

    private final static int digitalNumber = 1;
    private final static int analogNumber = 2;

    private final class EchoWebSocketListener extends WebSocketListener {
        private static final int NORMAL_CLOSURE_STATUS = 1000;

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            webSocket.close(NORMAL_CLOSURE_STATUS, null);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        }
    }

    public DisplayAdapter(Context context, ArrayList<String> deviceType, ArrayList<String> deviceName, String currentDeviceStatus) {
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.context = context;
        this.currentDeviceStatus = currentDeviceStatus;
        deviceID = new ArrayList<>();
        deviceStatus = new ArrayList<>();

        client = new OkHttpClient();
        Request request = new Request.Builder().url("https://smarthome-thesis-bku.herokuapp.com").build();
        EchoWebSocketListener listener = new EchoWebSocketListener();
        ws = client.newWebSocket(request, listener);
    }



    @Override
    public int getItemViewType(int position) {
        if (deviceType.get(position).equals("analog"))
            return analogNumber;
        else if (deviceType.get(position).equals("digital"))
            return digitalNumber;
        return 0;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View adapterView = null;

        switch (viewType)
        {
            case digitalNumber:
                adapterView = inflater.inflate(R.layout.digital, viewGroup, false);
                break;
            case analogNumber:
                adapterView = inflater.inflate(R.layout.analog, viewGroup, false);
                break;
        }

        ViewHolder viewHolder = new ViewHolder(adapterView);
        return viewHolder;
    }


    public void onBindViewHolder (final ViewHolder viewHolder, final int position) {
        try {
            jsonArray = new JSONArray(currentDeviceStatus);

            for (int i = 0; i < jsonArray.length(); i++) {
//                jsonObject = new JSONObject(jsonArray.getString(i));
                jsonObject = jsonArray.getJSONObject(i);
                deviceID.add(jsonObject.getString("id"));
                deviceStatus.add(jsonObject.getString("status"));
            }


        if (deviceType.get(position).equals("analog")) {
            viewHolder.analogText.setText(deviceName.get(position));
            viewHolder.analogButton.setText(deviceStatus.get(position));
        }
        else if (deviceType.get(position).equals("digital")) {
            viewHolder.digitalText.setText(deviceName.get(position));

            if (deviceStatus.get(position).equals("ON")){
                viewHolder.digitalSwitch.setChecked(true);
            }

            viewHolder.digitalSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (viewHolder.digitalSwitch.isChecked()) {
                        ws.send(deviceID.get(position)+"&LED_ON");
//                    Toast.makeText(MainActivity.this, "Checked", Toast.LENGTH_LONG).show();
                    }
                    else if (viewHolder.digitalSwitch.isChecked() == false) {
                        ws.send(deviceID.get(position)+"&LED_OFF");
//                    Toast.makeText(MainActivity.this, "Not checked", Toast.LENGTH_LONG).show();
                    }
                }
            });
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return deviceType.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView digitalText, analogText;
        public Switch digitalSwitch;
        public Button analogButton;

    public ViewHolder (final View itemView) {
        super(itemView);
        itemview = itemView;
        digitalText = itemView.findViewById(R.id.digital);
        analogText = itemView.findViewById(R.id.analog);
        digitalSwitch = itemView.findViewById(R.id.digitalSwitch);
        analogButton = itemView.findViewById(R.id.analogView);
    }

    }
}
