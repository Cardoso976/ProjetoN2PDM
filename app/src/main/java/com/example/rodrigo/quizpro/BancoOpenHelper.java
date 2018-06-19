package com.example.rodrigo.quizpro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BancoOpenHelper extends SQLiteOpenHelper {

    public BancoOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "CREATE TABLE perguntas(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pergunta TEXT," +
                "altA TEXT," +
                "altB TEXT," +
                "altC TEXT," +
                "altD TEXT," +
                "altCorreta TEXT," +
                "respondido INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
