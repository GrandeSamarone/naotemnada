package com.example.fulanoeciclano.naotemnada.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fulanoeciclano.naotemnada.DAO.WifiDAO;
import com.example.fulanoeciclano.naotemnada.R;
import com.example.fulanoeciclano.naotemnada.RecicleView.wifiAdapterRec;

public class GeralFragment extends Fragment {
    RecyclerView recyclerView;
    private wifiAdapterRec adapter;

    public GeralFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_geral, container, false);
    }
    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView =getView().findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        WifiDAO dao = new WifiDAO(getActivity());
        adapter = new wifiAdapterRec(dao.retornarwifi());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    public void onResume(){
        super.onResume();
        configurarRecycler();
    }


}


