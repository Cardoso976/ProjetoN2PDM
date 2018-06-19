package com.example.rodrigo.quizpro;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int erros = 0, acertos = 0;
    private ArrayList<Pergunta> perguntas;
    private ArrayAdapter<Pergunta> adapter;
    private Toast toasty;
    private long lastBackPressTime = 0;
    private BancoOpenHelper openHelper;
    private SQLiteDatabase context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        perguntas = new ArrayList<>();

        DAO repository = new DAO(this);
        repository.GetPerguntas();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, perguntas);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Pergunta perguntaClick = perguntas.get(posicao);
                if (!perguntaClick.isRespondido()){
                    repository.getPergunta(posicao);
                    perguntaClick.setRespondido(true);
                    adapter.notifyDataSetChanged();
                } else {
                    toasty();
                }

            }
        });

    }

    public void toasty(){
        Toast.makeText(this,"Pergunta já foi respondida",Toast.LENGTH_LONG).show();
    }

    public void getPergunta(int position){
        Intent intent = new Intent(this,PerguntaActivity.class);

        Pergunta pergunta = new Pergunta();
        DAO repository = new DAO(this);
        pergunta = repository.getPergunta(position);

        intent.putExtra("pergunta", pergunta.getPergunta());
        intent.putExtra("altA", pergunta.getAltA());
        intent.putExtra("altB", pergunta.getAltB());
        intent.putExtra("altC", pergunta.getAltC());
        intent.putExtra("altD", pergunta.getAltD());
        intent.putExtra("altCorreta", pergunta.getAltCorreta());

        startActivityForResult(intent,0);
        //view.setEnabled(false);

    }
}
