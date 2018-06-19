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
        RadioButton opA = (RadioButton)findViewById(R.id.altA);
        RadioButton opB = (RadioButton)findViewById(R.id.altB);
        RadioButton opC = (RadioButton)findViewById(R.id.altC);
        RadioButton opD = (RadioButton)findViewById(R.id.altD);

        Intent it = getIntent();

        txtPergunta.setText(it.getStringExtra("pergunta"));
        opA.setText(it.getStringExtra("opA"));
        opB.setText(it.getStringExtra("opB"));
        opC.setText(it.getStringExtra("opC"));
        opD.setText(it.getStringExtra("opD"));

    }
    public void responder(View view){
        Intent intent = getIntent();
        int idRespostaCorreta = 0;
        boolean acertou = false;

        String altA = intent.getStringExtra("opA");
        String altB = intent.getStringExtra("opB");
        String altC = intent.getStringExtra("opC");
        String altD = intent.getStringExtra("opD");
        String altCorreta = intent.getStringExtra("opCorreta");

        if (altCorreta.equalsIgnoreCase(altA)){idRespostaCorreta = R.id.altA;}
        else if (altCorreta.equalsIgnoreCase(altB)){idRespostaCorreta = R.id.altB;}
        else if (altCorreta.equalsIgnoreCase(altC)){idRespostaCorreta = R.id.altC;}
        else if (altCorreta.equalsIgnoreCase(altD)){idRespostaCorreta = R.id.altD;}

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
