package net.junsun.l22_ble_scanning;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by jsun on 5/28/2016.
 */
public class BleDeviceArrayAdaptor extends ArrayAdapter<MyBleDevice> {
    private List<MyBleDevice> devices;

    public BleDeviceArrayAdaptor(Context context, int resource, List<MyBleDevice> devices) {
        super(context, resource, devices);
        this.devices = devices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        View row = convertView;
        if (convertView == null) {
            // This is an expensive operation! Avoid and reuse as much as possible.
            row = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
        }

        TextView firstRow = (TextView) row.findViewById(android.R.id.text1);
        TextView secondRow = (TextView) row.findViewById(android.R.id.text2);

        if (devices.get(position).name != null)
            firstRow.setText(devices.get(position).name);
        else
            firstRow.setText("(null)");
        secondRow.setText("MAC:"
                + devices.get(position).address
                + ", RSSI:"
                + devices.get(position).rssi);
        return row;
    }

}
