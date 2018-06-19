package com.example.rodrigo.quizpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAO {
    private BancoOpenHelper openHelper;
    private SQLiteDatabase db;

    public DAO(Context context){
        openHelper = new BancoOpenHelper(context, "perguntas.db", null, 1);
    }

    public ArrayList<Pergunta> GetPerguntas(){
        db = openHelper.getWritableDatabase();

        ArrayList<Pergunta> perguntas = new ArrayList<>();
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

        Cursor consulta = db.query("perguntas",null,null,null,null,null,"_id");
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
        values.put("opA",pergunta.getAltA());
        values.put("opB",pergunta.getAltB());
        values.put("opC",pergunta.getAltC());
        values.put("opD",pergunta.getAltD());
        values.put("opCorreta", pergunta.getAltCorreta());
        values.put("respondido", 0);
        db.insert("perguntasQuiz",null,values);

        db.close();
    }
}
