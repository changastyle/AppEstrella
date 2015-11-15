package com.example.nicog.test.Threads;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.nicog.test.ErrorActivity;
import com.example.nicog.test.PickAGame;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.Juego;

import java.util.ArrayList;

public class ConexionInicial extends Thread
{

    Activity activity;
    Intent intentTo2Activity;
    Intent intentToErrorActivity;
    Object result = null;

    public ConexionInicial(Activity activity)
    {
        this.activity = activity;
        intentTo2Activity = new Intent(activity, PickAGame.class);
        intentToErrorActivity = new Intent(activity,ErrorActivity.class);
    }

    @Override
    public void run()
    {
        super.run();
        result = Controller.pedirJuegos();
        try
        {
            join(3000);
            Intent intentToPickAGame = new Intent(activity,PickAGame.class);
            Intent intentToErrorAct = new Intent(activity,ErrorActivity.class);
            if(result != null)
            {
                activity.startActivity(intentToPickAGame);
            }
            else
            {
                activity.startActivity(intentToErrorAct);
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }



    /*@Override
    protected void onPostExecute(Object o)
    {
        /*intentTo2Activity.putExtra("data", result);

        System.out.println("DATA-->" + result);

        if(result != null )
        {
            activity.startActivity(intentTo2Activity);
        }
        else
        {
            activity.startActivity(intentToErrorActivity);
        }

        activity.finish();
    }*/

    public Object getResult()
    {
        return result;
    }

    public void setResult(Object result)
    {
        this.result = result;
    }
}
