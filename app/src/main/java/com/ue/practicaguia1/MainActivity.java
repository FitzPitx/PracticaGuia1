package com.ue.practicaguia1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText cant_dinero;
    private EditText tasa_interes;
    private TextView resultado_dias;
    private SeekBar barra_dias;
    private Button calcular;
    private Button limpiar;
    private TextView pago_total;
    private Button imprimir;
    double pago;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//llamada del metodo para para que busque los componentes de inicio
        initializeVariables();
        imprimir.setOnClickListener(this::resultCalculo);

        resultado_dias.setText(barra_dias.getProgress()+"/"+barra_dias.getMax());
        barra_dias.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = progress + 5;
                resultado_dias.setText(barra_dias.getProgress()+"/"+barra_dias.getMax());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Llamada del metodo para pasar datos al siguiente activity

    }

    //Metodos para el calculo del interes
    public void calculo(View view){
        final byte PERCENT = 100;
        final long INTERES_DIA = 36000;

        String cant_dinero_str = this.cant_dinero.getText().toString();
        String tasa_interes_str = this.tasa_interes.getText().toString();

        if (cant_dinero_str.isEmpty() || tasa_interes_str.isEmpty()){
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        float cant_dinero = Float.parseFloat(this.cant_dinero.getText().toString());
        float tasa_interes = Float.parseFloat(this.tasa_interes.getText().toString()) / PERCENT;
        float resultado_dias = Float.parseFloat(String.valueOf(this.barra_dias.getProgress()));

        //Formula del interes simple en dias I = Co * i/36000 * n
        double pago = (cant_dinero * (tasa_interes/INTERES_DIA) * resultado_dias);
        this.pago = pago;
        Toast.makeText(this, "Calculo hecho, por favor presione impresión", Toast.LENGTH_SHORT).show();
    }

    public void limpiar(View view){
        cant_dinero.setText("");
        tasa_interes.setText("");
        barra_dias.setProgress(0);
    }

    private void resultCalculo(View view) {
        String cant_dinero_str = this.cant_dinero.getText().toString();
        String tasa_interes_str = this.tasa_interes.getText().toString();

        if (cant_dinero_str.isEmpty() || tasa_interes_str.isEmpty()){
            Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        //Metodo para pasar la información
        Intent pasarInfo = new Intent(this,resultado_calculadora.class ); //paquete donde estoy y hacia donde voy
        pasarInfo.putExtra("calculo", pago);
        final byte PERCENT = 100;
        float cant_dinero = Float.parseFloat(this.cant_dinero.getText().toString());
        float tasa_interes = Float.parseFloat(this.tasa_interes.getText().toString()) / PERCENT;
        float resultado_dias = Float.parseFloat(String.valueOf(this.barra_dias.getProgress()));
        pasarInfo.putExtra("cant_dinero", cant_dinero);
        pasarInfo.putExtra("tasa_interes", tasa_interes);
        pasarInfo.putExtra("resultado_dias", resultado_dias);
        startActivity(pasarInfo);
    }
    private void initializeVariables(){
        //Conexion entre la parte visual y logica
        cant_dinero = findViewById(R.id.et_cantidad_dinero);
        tasa_interes = findViewById((R.id.et_tasa_interes));
        resultado_dias = findViewById(R.id.tv_resultado_dias);
        barra_dias = findViewById(R.id.sb_dias);
        calcular = findViewById(R.id.btn_calcular);
        limpiar = findViewById(R.id.btn_limpiar);
        imprimir = findViewById(R.id.btn_impresion);
    }
}