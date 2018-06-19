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
        EditText edtOpA = (EditText)findViewById(R.id.edtOpA);
        EditText edtOpB = (EditText)findViewById(R.id.edtOpB);
        EditText edtOpC = (EditText)findViewById(R.id.edtOpC);
        EditText edtOpD = (EditText)findViewById(R.id.edtOpD);

        RadioButton rbA = (RadioButton)findViewById(R.id.altA);
        RadioButton rbB = (RadioButton)findViewById(R.id.altB);
        RadioButton rbC = (RadioButton)findViewById(R.id.altC);
        RadioButton rbD = (RadioButton)findViewById(R.id.altD);

        String opA = edtOpA.getText().toString();
        String opB = edtOpB.getText().toString();
        String opC = edtOpC.getText().toString();
        String opD = edtOpD.getText().toString();
        String opOk ="";

        if (rbA.isChecked()){
            opOk += "opA";
        }else if (rbB.isChecked()){
            opOk += "opB";
        }else if (rbC.isChecked()){
            opOk += "opC";
        }else if (rbD.isChecked()){
            opOk += "opD";
        }

        Pergunta pergunta = new Pergunta(edtPergunta.getText().toString(), opA, opB, opC, opD, opOk);
        DAO repository = new DAO(this);
        repository.CreatePergunta(pergunta);
        Toast.makeText(this,"Pergunta incluída com sucesso",Toast.LENGTH_LONG).show();
        finish();
    }
}
