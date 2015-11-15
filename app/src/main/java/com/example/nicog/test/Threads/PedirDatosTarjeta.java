package com.example.nicog.test.Threads;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.nicog.test.LoadingActivityWaitingForTarjetaFromQR;
import com.example.nicog.test.PickAGame;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.Tarjeta;

public class PedirDatosTarjeta extends AsyncTask
{
    Tarjeta tarjeta;
    LoadingActivityWaitingForTarjetaFromQR activityPadre;
    int numeroSerieTarjeta;

    public PedirDatosTarjeta(LoadingActivityWaitingForTarjetaFromQR activityPadre , int numeroSerieTarjeta)
    {
        this.activityPadre = activityPadre;
        this.numeroSerieTarjeta = numeroSerieTarjeta;
    }
    @Override
    protected Object doInBackground(Object[] params)
    {
        tarjeta = Controller.pedirDatosTarjeta(numeroSerieTarjeta);
        Intent intentToPickAGame = new Intent(activityPadre , PickAGame.class);

        activityPadre.startActivity(intentToPickAGame);

        return tarjeta;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        Intent intentToPickAGame = new Intent(activityPadre , PickAGame.class);
        intentToPickAGame.putExtra("tarjeta",tarjeta.toJSON());
        activityPadre.stopMusic();
        activityPadre.startActivity(intentToPickAGame);
        activityPadre.finish();
    }
}
