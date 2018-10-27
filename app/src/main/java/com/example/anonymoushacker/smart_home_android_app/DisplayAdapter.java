package com.example.anonymoushacker.smart_home_android_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class DisplayAdapter extends ArrayAdapter<String> {

    Activity context;

    int resource;

    List<String> objects;

    public DisplayAdapter(Activity context, int resource, List<String> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        //LayoutInflater build a layout (in this case item.xml) to a Java object so that Android could use
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null); //this.resource = item.xml

        //set up variable with layout item.xml
        TextView digital = row.findViewById(R.id.digital);
        TextView analog = row.findViewById(R.id.analog);
        Switch digitalSwitch = row.findViewById(R.id.digitalSwitch);
        Button analogButton = row.findViewById(R.id.analogView);

        return row;
    }
}
