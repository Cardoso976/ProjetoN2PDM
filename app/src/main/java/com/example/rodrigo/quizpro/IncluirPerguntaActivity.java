package com.example.rodrigo.quizpro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class IncluirPerguntaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incluir_pergunta);
    }

    public void incluir(View view){
        EditText edtPergunta = (EditText)findViewById(R.id.edtPergunta);
        EditText edtAltA = (EditText)findViewById(R.id.edtAltA);
        EditText edtAltB = (EditText)findViewById(R.id.edtAltB);
        EditText edtAltC = (EditText)findViewById(R.id.edtAltC);
        EditText edtAltD = (EditText)findViewById(R.id.edtAltD);
        EditText edtAltOk = (EditText)findViewById(R.id.edtAltOk);

        String altA = edtAltA.getText().toString();
        String altB = edtAltB.getText().toString();
        String altC = edtAltC.getText().toString();
        String altD = edtAltD.getText().toString();
        String altOk = edtAltOk.getText().toString();

        if (altOk.equalsIgnoreCase("a")){altOk = altA;}
        else if (altOk.equalsIgnoreCase("b")){altOk = altB;}
        else if (altOk.equalsIgnoreCase("c")){altOk = altC;}
        else if (altOk.equalsIgnoreCase("d")){altOk = altD;}

        Pergunta pergunta = new Pergunta(edtPergunta.getText().toString(), altA, altB, altC, altD, altOk);
        DAO repository = new DAO(this);
        repository.CreatePergunta(pergunta);
        Toast.makeText(this,"Pergunta incluída com sucesso",Toast.LENGTH_LONG).show();
        finish();
    }
}
