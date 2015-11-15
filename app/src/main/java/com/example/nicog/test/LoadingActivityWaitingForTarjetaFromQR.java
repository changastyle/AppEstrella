package com.example.nicog.test;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.media.MediaPlayer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.nicog.test.Threads.PedirDatosTarjeta;
import com.example.nicog.test.controller.Controller;

public class LoadingActivityWaitingForTarjetaFromQR extends Activity
{

    private MediaPlayer mp;
    private ImageView imagen;
    private TextView  textViewNumeroLeidoLoadingActivity;
    private int numeroEscaneadoConElQR;

    Animation animRotate;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_activity_waiting_for_tarjeta_from_qr);
        int numeroEscaneadoConElQR = (Integer)(getIntent().getSerializableExtra("numeroEscaneadoConElQR"));
        this.numeroEscaneadoConElQR = numeroEscaneadoConElQR;
        textViewNumeroLeidoLoadingActivity = (TextView) findViewById(R.id.textViewNumeroLeidoLoadingActivity);
        textViewNumeroLeidoLoadingActivity.setText(String.valueOf(numeroEscaneadoConElQR));
        imagen = (ImageView) findViewById(R.id.imagen);

        mp = MediaPlayer.create(this, R.raw.tic);
        mp.start();

        animRotate = AnimationUtils.loadAnimation(this, R.anim.rotate_animation_for_clock);
        imagen.startAnimation(animRotate);




        PedirDatosTarjeta pedirDatosTarjeta = new PedirDatosTarjeta(this,numeroEscaneadoConElQR);
        pedirDatosTarjeta.execute();


        Controller.addActivity(this);

    }
    public void stopMusic()
    {
        this.mp.stop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //return Controller.ManejadorCliente.menuOperaciones(item, this);
        return false;
    }

    /*GYS*/
    public int getNumeroEscaneadoConElQR()
    {
        return numeroEscaneadoConElQR;
    }

    public void setNumeroEscaneadoConElQR(int numeroEscaneadoConElQR)
    {
        this.numeroEscaneadoConElQR = numeroEscaneadoConElQR;
    }
}
