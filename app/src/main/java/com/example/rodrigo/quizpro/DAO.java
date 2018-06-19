package com.example.rodrigo.quizpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAO {
    private OpenHelper openHelper;
    private SQLiteDatabase db;

    public DAO(Context context){
        openHelper = new OpenHelper(context, "perguntas.db", null, 1);
    }

    public ArrayList<Pergunta> GetPerguntas(){
        ArrayList<Pergunta> perguntas = new ArrayList<>();
        db = openHelper.getWritableDatabase();
        int respondido;
        Cursor consulta = db.query("perguntas",null,null,null,null,null,"pergunta");
        while(consulta.moveToNext()){
            Pergunta pergunta = new Pergunta();
            pergunta.setPergunta(consulta.getString(1));
            pergunta.setAltA(consulta.getString(2));
            pergunta.setAltB(consulta.getString(3));
            pergunta.setAltC(consulta.getString(4));
            pergunta.setAltD(consulta.getString(5));
            pergunta.setAltCorreta(consulta.getString(6));
            respondido = consulta.getInt(7);
            if (respondido == 1) pergunta.setRespondido(true);
            else pergunta.setRespondido(false);

            perguntas.add(pergunta);
        }
        db.close();
        return perguntas;
    }

    public Pergunta getPergunta(int idPergunta){
        db = openHelper.getWritableDatabase();

        Cursor consulta = db.query("perguntas",null,null,null,null,null,"pergunta");

        int respondido;
        consulta.moveToPosition(idPergunta);
        Pergunta pergunta = new Pergunta();

        pergunta.setPergunta(consulta.getString(1));
        pergunta.setAltA(consulta.getString(2));
        pergunta.setAltB(consulta.getString(3));
        pergunta.setAltC(consulta.getString(4));
        pergunta.setAltD(consulta.getString(5));
        pergunta.setAltCorreta(consulta.getString(6));
        respondido = consulta.getInt(7);

        if (respondido == 1) pergunta.setRespondido(true);
        else pergunta.setRespondido(false);

        db.close();
        return pergunta;
    }

    public void CreatePergunta(Pergunta pergunta){
        db = openHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("pergunta",pergunta.getPergunta());
        values.put("altA",pergunta.getAltA());
        values.put("altB",pergunta.getAltB());
        values.put("altC",pergunta.getAltC());
        values.put("altD",pergunta.getAltD());
        values.put("altCorreta", pergunta.getAltCorreta());
        values.put("respondido", 0);
        db.insert("perguntas",null,values);

        db.close();
    }

    public void excluir(){
        db = openHelper.getWritableDatabase();

        db.execSQL("DELETE FROM perguntas");

        db.close();
    }
}
