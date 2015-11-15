package com.example.nicog.test;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nicog.test.Util.JSONWS;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.RespuestaConjunto;
import com.example.nicog.test.model.RespuestaJugada;

public class ActivitiDeResultados extends Activity implements View.OnClickListener
{
    private RespuestaConjunto respuestaConjunto;
    private LinearLayout listaNumerosGanadores , listaNumerosApostados;
    private TextView txtTotalGanado , txtSaldo;
    private Button btnVolverAtras;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activiti_de_resultados);
        String JSONRecibido = String.valueOf( getIntent().getExtras().get("JSON") );
        listaNumerosGanadores = (LinearLayout) findViewById(R.id.listaNumerosGanadores);
        listaNumerosApostados = (LinearLayout) findViewById(R.id.listaNumerosApostados);
        txtTotalGanado = (TextView) findViewById(R.id.txtGanado);
        btnVolverAtras = (Button) findViewById(R.id.btnVolverAtrasResultados);btnVolverAtras.setOnClickListener(this);
        txtSaldo = (TextView) findViewById(R.id.txtSaldo);

        System.out.println("JSONRecibido: " + JSONRecibido);
        if(JSONRecibido != null)
        {
            try
            {
                RespuestaConjunto respuestaConjunto = (RespuestaConjunto) JSONWS.solamenteConvertirDatosJSONaObjetoJava(JSONRecibido,RespuestaConjunto.class);
                System.out.println("respuestaConjunto:" + respuestaConjunto);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }


            //MUCHO MUY IMPORTANTE ESTAS 2 LINEAS DE ABAJO, VAN JUNTAS:
            respuestaConjunto = (RespuestaConjunto) JSONWS.solamenteConvertirDatosJSONaObjetoJava(JSONRecibido, RespuestaConjunto.class) ;
            if(respuestaConjunto != null )
            {
                if(respuestaConjunto.getArrRespuestas() != null)
                {
                    Intent intentToVentanitaEmotiva  = new Intent( this, VentanitaEmotiva.class);
                    double total = respuestaConjunto.calcularGananciaTotal();
                    intentToVentanitaEmotiva.putExtra("total", String.valueOf(total));

                    this.startActivity(intentToVentanitaEmotiva);
                }
            }

            System.out.println("respuestaConjunto:" + respuestaConjunto);

        for(String numeroSorteado : respuestaConjunto.getArrNumerosSorteados())
        {
            Button btnAux = new Button(this);
            btnAux.setText(numeroSorteado);
            Drawable img = this.getResources().getDrawable( R.drawable.btns_inputs );
            btnAux.setCompoundDrawablesWithIntrinsicBounds( img, null, null, null);
            listaNumerosGanadores.addView(btnAux);
        }
        double totalGanado = 0;
        for(RespuestaJugada respuestaJugada : respuestaConjunto.getArrRespuestas())
        {
            Button btnAux = new Button(this);
            btnAux.setText(respuestaJugada.getJugadaAsociada().getNumeroApostado());
            System.out.println(respuestaJugada.getJugadaAsociada().getNumeroApostado());
            listaNumerosApostados.addView(btnAux);
            totalGanado += respuestaJugada.getDineroGanado();
        }
        txtTotalGanado.setText("$" + totalGanado + ".-");

        if(Controller.getTarjetaVinculada() != null)
        {
            txtSaldo.setText("Saldo: $" + Controller.getTarjetaVinculada().calcularSaldoActualDeLaTarjeta());
        }
        Controller.addActivity(this);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        //this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_nico_express, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;

        if(botonPresionado == btnVolverAtras)
        {
            Intent intentToManager = new Intent(this,ManagerNicoExpress.class);
            this.startActivity(intentToManager);
        }
    }
}
