package com.example.fulanoeciclano.naotemnada.Adapter;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.fulanoeciclano.naotemnada.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fulanoeciclano on 13/02/2018.
 */

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private List<ScanResult> scans = new ArrayList<ScanResult>();
    LayoutInflater layoutInflater;

    public ListAdapter(Context context, List<ScanResult> scans){
        this.mContext = context;
        this.scans = scans;
        this.layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return scans.size();
    }

    @Override
    public Object getItem(int i) {
        return scans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {

        if (convertView == null) {
            convertView =  layoutInflater.inflate(R.layout.item_layout, container, false);
        }
        TextView ssid = (TextView) convertView.findViewById(R.id.ssid);
        //TextView bssid = (TextView) convertView.findViewById(R.id.bssid);

        ssid.setText(scans.get(position).SSID.toString());
        // bssid.setText(scans.get(position).BSSID.toString());

        return convertView;
    }
}

