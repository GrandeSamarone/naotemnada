package com.example.fulanoeciclano.naotemnada.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import com.example.fulanoeciclano.naotemnada.Modelo.Wifi_modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fulanoeciclano on 13/02/2018.
 */

public class WifiDAO extends SQLiteOpenHelper {
    View convertView;

    public WifiDAO(Context context) {
        super(context, "Wifi", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE db_wifi (id,INTEGER PRIMARY KEY,nome_do_wifi TEXT NOT NULL,senha_do_wifi DOUBLE NOT NULL);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int antigaversao, int novaversao) {
        String sql = "DROP TABLE IF EXISTS db_wifi";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insere(String nome_wifi_selecionado,String Cad_senha_selecionado) {
        SQLiteDatabase db =getWritableDatabase();

        ContentValues dados = new ContentValues();
        dados.put("nome_do_wifi",nome_wifi_selecionado);
        dados.put("senha_do_wifi", String.valueOf(Cad_senha_selecionado));

        db.insert("db_wifi",null,dados);

    }

    public List<Wifi_modelo> retornarwifi() {
        String sql = "SELECT * FROM db_wifi;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Wifi_modelo>  modelos = new ArrayList<Wifi_modelo>();

        while (c.moveToNext()){
            Wifi_modelo modelo = new Wifi_modelo();
            modelo.setId(c.getLong(c.getColumnIndex("id")));
            modelo.setNome_wifi(c.getString(c.getColumnIndex("nome_do_wifi")));

            modelos.add(modelo);
        }
        c.close();

        return  modelos;
    }
}