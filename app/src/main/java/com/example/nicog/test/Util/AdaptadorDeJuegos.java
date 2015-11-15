package com.example.nicog.test.Util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.nicog.test.ManagerNicoExpress;
import com.example.nicog.test.PickAGame;
import com.example.nicog.test.R;
import com.example.nicog.test.Threads.ThreadCargaImagenJuego;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.Juego;

import java.util.ArrayList;

public class AdaptadorDeJuegos extends BaseAdapter implements View.OnClickListener
{
    private Context context;
    private final ArrayList<Juego> arrDeJuegos;

    public AdaptadorDeJuegos(Context context, ArrayList<Juego> arrDeJuegos)
    {
        this.context = context;
        this.arrDeJuegos = arrDeJuegos;
    }

    @Override
    public int getCount()
    {
        return arrDeJuegos.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.arrDeJuegos.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View wrapper;

        if (convertView == null)
        {
            wrapper = new View(context);
            wrapper = inflater.inflate(R.layout.layout_para_juegos, null);

            Juego juegoActual = arrDeJuegos.get(position);

            wrapper.setId(juegoActual.getId());
            wrapper.setOnClickListener(this);

            ImageView imgJuego = (ImageView) wrapper.findViewById(R.id.imgJuego);
            imgJuego.setId(juegoActual.getId());
            imgJuego.setOnClickListener(this);

            ThreadCargaImagenJuego threadCargaImagenJuego = new ThreadCargaImagenJuego(imgJuego,juegoActual);
            threadCargaImagenJuego.execute();
            /*try
            {
                Bitmap bitmap = threadCargaImagenJuego.getBitmap();
                System.out.println("BITMAP = " + bitmap);
                imgJuego.setImageBitmap(bitmap);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }*/
        }
        else
        {
            wrapper = (View) convertView;
        }

        return wrapper;
    }

    @Override
    public void onClick(View v)
    {
        View viewPresionada = (View) v;
        System.out.println("View Presionado = " + viewPresionada.getId() );

        abrirActivityJuegos(viewPresionada.getId());
    }
    public void abrirActivityJuegos(int idDelJuegoAabrir)
    {
        Class activityAabrir = PickAGame.class;

        if(Controller.getTarjetaVinculada() != null)
        {
            if(idDelJuegoAabrir == 1)
            {
                activityAabrir = ManagerNicoExpress.class;
            }
            else
            {

            }
            Intent intentToGame = new Intent(context,activityAabrir);
            context.startActivity(intentToGame);
        }
        else
        {
            Toast.makeText(context, "Debe vincular tarjeta primero!", Toast.LENGTH_SHORT).show();
        }
    }
}
