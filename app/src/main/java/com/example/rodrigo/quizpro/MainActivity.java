package com.example.rodrigo.quizpro;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int erros = 0, acertos = 0;
    private ArrayList<Pergunta> perguntas;
    private ArrayAdapter<Pergunta> adapter;
    private Toast toasty;
    private long lastBackPressTime = 0;
    private OpenHelper openHelper;
    private SQLiteDatabase context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.drawable.ic_code_holo_dark);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Click sobre a navegacao", Toast.LENGTH_SHORT).show();
            }
        });

        perguntas = new ArrayList<>();

        DAO repository = new DAO(this);
        perguntas = repository.GetPerguntas();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, perguntas);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setChoiceMode(listView.CHOICE_MODE_MULTIPLE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Pergunta pergunta = perguntas.get(posicao);
                if (!pergunta.isRespondido()){
                    pergunta.setRespondido(true);
                    chamarViewPergunta(posicao);
                    adapter.notifyDataSetChanged();
                } else {
                    toasty();
                }

            }
        });

    }

    private void refreshLista(){
        DAO repository = new DAO(this);
        perguntas = repository.GetPerguntas();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, perguntas);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        DAO repository = new DAO(this);
        switch (item.getItemId()) {
            case R.id.excluir:
                repository.excluir();
                refreshLista();
                atualizaPlacar(0,0);
                Toast.makeText(MainActivity.this, "Perguntas Exluidas, placar zerado ", Toast.LENGTH_SHORT).show();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return true;
    }

    public void toasty(){
        Toast.makeText(this,"Pergunta já foi respondida",Toast.LENGTH_LONG).show();
    }

    public void chamarViewPergunta(int position){
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            if (requestCode == 0) {
                boolean repostaCorreta = data.getBooleanExtra("acertou", false);

                if (repostaCorreta) {
                    acertos++;
                } else {
                    erros++;
                }
                atualizaPlacar(erros,acertos);

            }
        }
        if (requestCode == 1) {
            DAO repository = new DAO(this);
            perguntas = repository.GetPerguntas();
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, perguntas);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            acertos = 0;
            erros = 0;
            atualizaPlacar(0,0);
            adapter.notifyDataSetChanged();
        }

    }

    public void IncluirPergunta(View view){
        Intent intent = new Intent(this,IncluirPerguntaActivity.class);
        startActivityForResult(intent,1);
        //startActivity(intent);
    }

    private void atualizaPlacar(int erros, int acertos){
        TextView txtAcertos = (TextView) findViewById(R.id.txtAcertos);
        TextView txtErros = (TextView) findViewById(R.id.txtErros);

        txtAcertos.setText("Acertos: " + acertos);
        txtErros.setText("Erros: " + erros);
    }

    @Override
    public void onBackPressed() {
        if (this.lastBackPressTime < System.currentTimeMillis() - 4000) {
            toasty = Toast.makeText(this, "Pressione voltar novamente para sair.", Toast.LENGTH_LONG);
            toasty.show();
            this.lastBackPressTime = System.currentTimeMillis();
        } else {
            if (toasty != null) {
                toasty.cancel();
            }
            super.onBackPressed();
        }

    }
}
