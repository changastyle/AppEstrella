package com.example.nicog.test.Threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.nicog.test.R;
import com.example.nicog.test.model.Juego;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by nicog on 4/11/2015.
 */
public class ThreadCargaImagenJuego extends AsyncTask
{
    private Juego juego;
    private ImageView imgJuego;
    private Bitmap bitmap;

    public ThreadCargaImagenJuego(ImageView imgJuego, Juego juego)
    {
        this.imgJuego = imgJuego;
        this.juego = juego;
    }

    @Override
    protected void onPreExecute()
    {
        imgJuego.setImageResource(R.drawable.relojarena);
    }
    @Override
    protected Object doInBackground(Object[] params)
    {
        try
        {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(juego.getUrlIcono()).getContent());
        }
        catch(Exception e)
        {
            System.out.println("Error: trayendo imagen de la URL:" + juego.getUrlIcono() + ". " + e.toString());
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Object o)
    {
        imgJuego.setImageBitmap(bitmap);
        imgJuego.setBackgroundResource(R.drawable.gradiente_gris_oscuro);

        /*ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(left, top, right, bottom);
        yourbutton.setLayoutParams(params);*/


    }
}
