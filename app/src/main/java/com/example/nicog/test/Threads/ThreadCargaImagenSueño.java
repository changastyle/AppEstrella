package com.example.nicog.test.Threads;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.example.nicog.test.R;
import com.example.nicog.test.model.Dream;

import java.io.InputStream;
import java.net.URL;

public class ThreadCargaImagenSueño extends AsyncTask
{
    private ImageView imageView;
    private Dream sueñoAsociado;
    private Bitmap bitmap;

    public ThreadCargaImagenSueño(ImageView imageView, Dream sueño)
    {
        this.imageView = imageView;
        this.sueñoAsociado = sueño;
    }
    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        imageView.setImageResource(R.drawable.relojarena);
    }

    @Override
    protected Object doInBackground(Object[] params)
    {
        try
        {
            bitmap = BitmapFactory.decodeStream((InputStream) new URL(sueñoAsociado.getUrl()).getContent());
        }
        catch(Exception e)
        {
            System.out.println("Error: trayendo imagen de la URL:" + sueñoAsociado.getUrl() + ". " + e.toString());
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        imageView.setImageBitmap(bitmap);
    }
}
