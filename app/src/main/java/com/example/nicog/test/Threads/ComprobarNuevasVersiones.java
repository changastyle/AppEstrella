package com.example.nicog.test.Threads;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

import com.example.nicog.test.ErrorActivity;
import com.example.nicog.test.PickAGame;
import com.example.nicog.test.R;
import com.example.nicog.test.UpdateVersionAct;
import com.example.nicog.test.controller.Controller;

public class ComprobarNuevasVersiones extends AsyncTask
{
    Activity activityPadre;
    TextView txtCheckNewVersions;
    boolean appEstaActualizada= false;
    boolean falloLaConexionConElServer = false;

    public ComprobarNuevasVersiones(Activity activityPadre)
    {
        this.activityPadre = activityPadre;
    }

    @Override
    protected void onPreExecute()
    {
        txtCheckNewVersions = (TextView) activityPadre.findViewById(R.id.txtCheckNewVersions);
        txtCheckNewVersions.setText(R.string.txt_check_new_versions_2);
    }

    @Override
    protected Object doInBackground(Object[] params)
    {
        appEstaActualizada = Controller.soyUnaVersionActualizada();
        falloLaConexionConElServer = Controller.isFalloLaConexion();

        return null;
    }
    @Override
    protected void onPostExecute(Object o)
    {
        txtCheckNewVersions.setText(R.string.txt_check_new_versions_2);

        Intent intentToUpdateAct = new Intent(activityPadre,UpdateVersionAct.class);
        Intent successIntent = new Intent(activityPadre, PickAGame.class);
        Intent errorIntent = new Intent(activityPadre, ErrorActivity.class);

        if(appEstaActualizada)
        {
            activityPadre.startActivity(successIntent);
        }
        else
        {
            if(falloLaConexionConElServer)
            {
                activityPadre.startActivity(errorIntent);
            }
            else
            {
                activityPadre.startActivity(intentToUpdateAct);
            }
        }
        this.activityPadre.finish();
    }
}
