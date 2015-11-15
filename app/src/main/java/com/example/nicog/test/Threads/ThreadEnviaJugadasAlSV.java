package com.example.nicog.test.Threads;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.nicog.test.ActivitiDeResultados;
import com.example.nicog.test.WaitingForResultsAct;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.JugadaNicoExpress;

import java.util.ArrayList;

/**
 * Created by nicog on 2/11/2015.
 */
public class ThreadEnviaJugadasAlSV extends AsyncTask
{
    private  Activity activityQueMeLlamo;
    private String respuestaDelSV;

    public ThreadEnviaJugadasAlSV(Activity activity)
    {
        this.activityQueMeLlamo = activity;
    }
    @Override
    protected Object doInBackground(Object[] params)
    {
        Intent intentToWaitingAct = new Intent(activityQueMeLlamo, WaitingForResultsAct.class);
        activityQueMeLlamo.startActivity(intentToWaitingAct);
        respuestaDelSV = Controller.enviarJugadasAlServer();

        System.out.println("respuestaDelSV: "+respuestaDelSV);
        return respuestaDelSV;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        Intent intentToResultAct = new Intent(activityQueMeLlamo, ActivitiDeResultados.class);
        intentToResultAct.putExtra("JSON", respuestaDelSV);
        activityQueMeLlamo.startActivity(intentToResultAct);
        activityQueMeLlamo.finish();
    }
}
