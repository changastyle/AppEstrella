package com.example.nicog.test;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicog.test.Threads.ThreadEnviaJugadasAlSV;
import com.example.nicog.test.Util.AdaptadorDeJugadas;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.Jugada;


import java.util.ArrayList;

public class ManagerNicoExpress extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener
{
    private Button btnTerminar ;
    ImageButton btnAgregarJugada;
    private TextView txtSaldoManager;
    private AdaptadorDeJugadas adaptador = new AdaptadorDeJugadas(this,Controller.getArrJugadasVolatiles());
    Animation animRotate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_nico_express);


        /*final Animation animTranslate = AnimationUtils.loadAnimation(this, R.anim.anim_translate);
        final Animation animAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        final Animation animScale = AnimationUtils.loadAnimation(this, R.anim.anim_scale);*/
        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation_for_buttons);

        btnTerminar = (Button) findViewById(R.id.btnTerminar);btnTerminar.setOnClickListener(this);
        btnAgregarJugada = (ImageButton) findViewById(R.id.btnAgregarJugada);
        txtSaldoManager = (TextView) findViewById(R.id.txtSaldoManager);
        GridView gridView = (GridView) findViewById(R.id.gridView);

        ArrayList<Button> arrDeBotones = new ArrayList<Button>();
        gridView.setAdapter(adaptador);
        gridView.setOnItemClickListener(this);

        if(Controller.getTarjetaVinculada() != null)
        {
            txtSaldoManager.setText("Saldo: $" + Controller.getTarjetaVinculada().calcularSaldoActualDeLaTarjeta() );
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
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_manager_nico_express, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.manager_exit_setting)
        {
            Controller.killAll();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;

        if(botonPresionado == btnTerminar)
        {
            System.out.println("Enviando Jugadas:" + Controller.getArrJugadasVolatiles().size());

            /*MediaPlayer mp = MediaPlayer.create(this, R.raw.tambores);
            mp.start();*/

            botonPresionado.startAnimation(animRotate);



            //Controller.enviarJugadasAlServer();
            ThreadEnviaJugadasAlSV threadEnviaJugadasAlSV = new ThreadEnviaJugadasAlSV(this);
            threadEnviaJugadasAlSV.execute();
        }

    }
    public void btnAgregarJugada_click(View v)
    {
        int maximoJugadas = 5;
        if(Controller.getArrJugadasVolatiles().size() < maximoJugadas)
        {
            Intent intentToInput = new Intent(this, InputJugadasAct.class);
            this.startActivity(intentToInput);
        }
        else
        {
            Toast.makeText(this,"Maximo "+  maximoJugadas +" jugadas.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        System.out.println("POSICION:" + position);
        /*
        Jugada jAUX = Controller.getArrJugadasVolatiles().get(v.getId() - 1);
        System.out.println("ABRISTE LA JUGADA:" + v.getId() + " -> " + jAUX.toString() );

        if(jAUX != null)
        {
            Controller.removeJugadaVolatil(jAUX);
        }*/
        /*
        Intent intentToInputAct = new Intent(context, InputJugadasAct.class);
        intentToInputAct.putExtra("jugada", jAUX.toJSON() );
        context.startActivity(intentToInputAct);*/
    }
}
