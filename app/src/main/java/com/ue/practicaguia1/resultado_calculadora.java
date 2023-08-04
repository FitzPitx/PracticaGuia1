package com.ue.practicaguia1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.NumberFormat;

public class resultado_calculadora extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_calculadora);
        pasarInfo();
    }

    private void pasarInfo(){
        Bundle recibir = getIntent().getExtras();
        Intent intent = getIntent();
        if (recibir != null){


            // Obtén los valores pasados a través del Intent
            double pago = intent.getDoubleExtra("calculo", 0);
            float cant_dinero = intent.getFloatExtra("cant_dinero", 0f);
            float tasa_interes = intent.getFloatExtra("tasa_interes", 0f);
            float resultado_dias = intent.getFloatExtra("resultado_dias", 0f);

            NumberFormat percent = NumberFormat.getPercentInstance();
            NumberFormat currency = NumberFormat.getCurrencyInstance();
            String cantidad_format = currency.format(cant_dinero);
            String tasa_interes_format = percent.format(tasa_interes);
            String interes_diario_format = percent.format(pago);

            // Obtenemos las referencias de los TextViews en el layout
            TextView tv_Cant_Dinero = findViewById(R.id.tv_cant_dinero);
            TextView tv_Tasa_Interes = findViewById(R.id.tv_tasa_interes);
            TextView tv_Resultado_Dias = findViewById(R.id.tv_dias_total);
            TextView tv_interes_total = findViewById(R.id.tv_interes_diario);

            // Mostramos los valores en los TextViews
            tv_Cant_Dinero.setText(" " + cantidad_format);
            tv_Tasa_Interes.setText(" " + tasa_interes_format);
            tv_Resultado_Dias.setText(" "+ resultado_dias);
            tv_interes_total.setText(" "+ interes_diario_format);

        }
    }
}