package com.example.fulanoeciclano.naotemnada.fragments;


import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.fulanoeciclano.Wifi.Wifi;
import com.example.fulanoeciclano.naotemnada.Adapter.ListAdapter;
import com.example.fulanoeciclano.naotemnada.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Busca_WifiFragment extends Fragment implements
        Wifi.WiFiListener ,AdapterView.OnItemClickListener {
    private Wifi wiFi;
    private ListView list;
    private List<ScanResult> scans;

    private ListAdapter listAdapter;


    public Busca_WifiFragment() {
        list =getView().findViewById(R.id.list);
        list.setOnItemClickListener(this);

        //botao para buscar WIFI
        FloatingActionButton fab = (FloatingActionButton) getView().findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Toast.makeText(this, "Buscando....", Toast.LENGTH_LONG).show();
                //Inicia a Busca...
                wiFi = Wifi.startScanWIFI(getActivity(), Busca_WifiFragment.this);
                /// / Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_busca__wifi, container, false);

    }
    // COnfiguraçao do wifi clicado
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Configuro uma rede baseada nos dados encontrados.
        WifiConfiguration wifiConfiguration = new WifiConfiguration();
        wifiConfiguration.BSSID = scans.get(position).BSSID;
        wifiConfiguration.SSID = "\"" + scans.get(position).SSID + "\"";
        wifiConfiguration.preSharedKey = "\"mpt211992manaus\"";

        //Conecto na nova rede criada.
        WifiManager wifiManager = wiFi.getWifiManager(getActivity());
        int netId = wifiManager.addNetwork(wifiConfiguration);
        wifiManager.saveConfiguration();
        wifiManager.disconnect();
        wifiManager.enableNetwork(netId, true);
        wifiManager.reconnect();
    }


    //resultado do WIFI
    @Override
    public void onResultScan(Context arg0, Intent arg1, List<ScanResult> results) {
        scans = results;
        listAdapter = new ListAdapter(getActivity(), scans);

        list.setAdapter(listAdapter);
    }

}
