package com.example.nicog.test.Util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicog.test.InputJugadasAct;
import com.example.nicog.test.PickAGame;
import com.example.nicog.test.R;
import com.example.nicog.test.model.Dream;

import java.util.ArrayList;

public class AdaptadorDeDreams extends BaseAdapter implements View.OnClickListener
{
    private Context context;
    private final ArrayList<Dream> arrDeDreams;

    public AdaptadorDeDreams(Context context, ArrayList<Dream> arrDeDreams)
    {
        this.context = context;
        this.arrDeDreams = arrDeDreams;
    }

    @Override
    public int getCount()
    {
        return arrDeDreams.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.arrDeDreams.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null)
        {
            gridView = new View(context);

            gridView = inflater.inflate(R.layout.layout_para_dreams, null);
            //Componentes del Layout Juego:
            TextView textDream = (TextView) gridView.findViewById(R.id.textDream);
            ImageView imgDream = (ImageView) gridView.findViewById(R.id.imgDream);

            Dream dreamActual = arrDeDreams.get(position);

            textDream.setText(String.valueOf(dreamActual.getNombre()));

            //Button btnJuego = (Button) gridView.findViewById(R.id.btnJuego);
            //btnJuego.setId(arrDeDreams.get(position).getId());
            //btnJuego.setOnClickListener(this);

//            headerJuego.setText(String.valueOf(arrDeJuegos.get(position).getNombre()));
            //btnJuego.setText("Jugar a " + String.valueOf(arrDeJuegos.get(position).getNombre()));

            System.out.println(arrDeDreams.get(position).getNombre());
            /*if(arrDeDreams.get(position).getNombreDream().trim().toLowerCase().equalsIgnoreCase("nicoexpress"))
            {
                imgJuego.setImageResource(R.drawable.quiniela);
                System.out.println("entré");
            }
            else
            {
                imgJuego.setImageResource(R.drawable.quini6);
            }*/
        }
        else
        {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;
        System.out.println("Boton Presionado = " + botonPresionado.getId() );

        abrirActivityJuegos(botonPresionado.getId());
    }
    public void abrirActivityJuegos(int idDelJuegoAabrir)
    {
        Class activityAabrir = PickAGame.class;
        if(idDelJuegoAabrir == 1)
        {
            activityAabrir = InputJugadasAct.class;
        }
        else
        {

        }
        Intent intentToGame = new Intent(context,activityAabrir);
        context.startActivity(intentToGame);
    }
}
