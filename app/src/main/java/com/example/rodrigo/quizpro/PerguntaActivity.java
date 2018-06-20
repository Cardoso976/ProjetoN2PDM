package com.example.rodrigo.quizpro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class PerguntaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);

        TextView txtPergunta = findViewById(R.id.txtPergunta);
        RadioButton altA = (RadioButton)findViewById(R.id.altA);
        RadioButton altB = (RadioButton)findViewById(R.id.altB);
        RadioButton altC = (RadioButton)findViewById(R.id.altC);
        RadioButton altD = (RadioButton)findViewById(R.id.altD);

        Intent it = getIntent();

        txtPergunta.setText(it.getStringExtra("pergunta"));
        altA.setText(it.getStringExtra("altA"));
        altB.setText(it.getStringExtra("altB"));
        altC.setText(it.getStringExtra("altC"));
        altD.setText(it.getStringExtra("altD"));

    }
    public void responder(View view){
        Intent intent = getIntent();
        int idRespostaCorreta = 0;
        boolean acertou = false;

        String a = intent.getStringExtra("altA");
        String b = intent.getStringExtra("altB");
        String c = intent.getStringExtra("altC");
        String d = intent.getStringExtra("altD");
        String ok = intent.getStringExtra("altCorreta");

        if (ok.equalsIgnoreCase(a)){idRespostaCorreta = R.id.altA;}
        else if (ok.equalsIgnoreCase(b)){idRespostaCorreta = R.id.altB;}
        else if (ok.equalsIgnoreCase(c)){idRespostaCorreta = R.id.altC;}
        else if (ok.equalsIgnoreCase(d)){idRespostaCorreta = R.id.altD;}

        TextView txtPergunta = (TextView) findViewById(R.id.txtPergunta);
        RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroup);

        if (radioGroup.getCheckedRadioButtonId() == idRespostaCorreta){
            Toast.makeText(this, "Resposta certa!",Toast.LENGTH_LONG).show();
            acertou = true;
        }else{
            Toast.makeText(this, "Resposta errada!", Toast.LENGTH_LONG).show();
        }
        Intent it = new Intent();

        it.putExtra("acertou", acertou);
        setResult(RESULT_OK, it);

        finish();
    }
}
