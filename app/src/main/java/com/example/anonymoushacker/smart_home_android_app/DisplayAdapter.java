package com.example.anonymoushacker.smart_home_android_app;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import java.util.ArrayList;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.ViewHolder> {

    ArrayList<String> deviceType, deviceName;
    Context context;

    private final static int digitalNumber = 1;
    private final static int analogNumber = 2;

    public DisplayAdapter(Context context, ArrayList<String> deviceType, ArrayList<String> deviceName) {
        this.deviceType = deviceType;
        this.deviceName = deviceName;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if (deviceType.get(position).equals("analog"))
            return analogNumber;
        else if (deviceType.get(position).equals("digital"))
            return digitalNumber;
        return 0;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
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


    public void onBindViewHolder (ViewHolder viewHolder, int position) {
        if (deviceType.get(position).equals("analog")) {
            viewHolder.analogText.setText(deviceName.get(position));
        }
        else
            if (deviceType.get(position).equals("digital")) {
            viewHolder.digitalText.setText(deviceName.get(position));
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

    public ViewHolder (View itemView) {
        super(itemView);
        itemview = itemView;
        digitalText = itemView.findViewById(R.id.digital);
        analogText = itemView.findViewById(R.id.analog);
        digitalSwitch = itemView.findViewById(R.id.digitalSwitch);
        analogButton = itemView.findViewById(R.id.analogView);
    }

    }
}
