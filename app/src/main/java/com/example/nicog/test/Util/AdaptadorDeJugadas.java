package com.example.nicog.test.Util;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicog.test.InputJugadasAct;
import com.example.nicog.test.R;
import com.example.nicog.test.Threads.ThreadCargaImagenSueño;
import com.example.nicog.test.Threads.ThreadPideDream;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.Dream;
import com.example.nicog.test.model.Jugada;
import com.example.nicog.test.model.JugadaNicoExpress;

import java.util.ArrayList;

public class AdaptadorDeJugadas extends BaseAdapter
{
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<Jugada> arrDeJugadas;


    public AdaptadorDeJugadas(Context context, ArrayList<Jugada> arrDeJugadas)
    {
        super();
        this.context = context;
        this.arrDeJugadas = arrDeJugadas;

    }

    @Override
    public int getCount()
    {
        return arrDeJugadas.size();
    }

    @Override
    public Object getItem(int position)
    {
        return arrDeJugadas.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        System.out.println("getVIEW position = " + position);

        View wrapper = null;

        ImageView imagenDream = null;
        TextView txtDream = null;
        TextView txtDineroApostado = null;


            wrapper = inflater.inflate(R.layout.jugadas_realizadas, null);
            imagenDream = (ImageView) wrapper .findViewById(R.id.imgDream);
            txtDream = (TextView) wrapper.findViewById(R.id.txtDream);
            txtDineroApostado = (TextView) wrapper.findViewById(R.id.txtDineroApostado);

        Jugada jugada = arrDeJugadas.get(position);
        Dream dreamAsociado = null;
        try
        {
            dreamAsociado = Controller.llenameElSueño(Integer.parseInt(jugada.getNumeroApostado()));
            if(dreamAsociado != null)
            {
                ThreadCargaImagenSueño threadCargaImagenSueño = new ThreadCargaImagenSueño(imagenDream,dreamAsociado);
                threadCargaImagenSueño.execute();
                //imagenDream.setImageResource(R.drawable.relojarena);
                //txtDream.setText(jugada.getNumeroApostado());

                txtDream.setText(String.valueOf(Controller.llenameElSueño(Integer.parseInt(jugada.getNumeroApostado())).getNombre()));
                txtDineroApostado.setText(String.valueOf("$" + jugada.getDineroApostado()));
            }
            else
            {
                imagenDream.setImageResource(R.drawable.relojarena);
            }
        }
        catch (Exception e)
        {
            System.out.println("ERROR: AdaptadorDeJugadas.getView() +  pidiendo sueño.");
            e.printStackTrace();
        }







        /*if (convertView == null)
        {

            wrapper = new View(context);

            wrapper = inflater.inflate(R.layout.jugadas_realizadas, null);
            System.out.println("POSICION get view: " +  position + "!");
            //System.out.println("Controller.getArrJugadasVolatiles():" + Controller.getArrJugadasVolatiles());
            /*System.out.println("Controller.getArrJugadasVolatiles().get(position):" + Controller.getArrJugadasVolatiles().get(position));
            Jugada jugadaActual = Controller.getArrJugadasVolatiles().get(position);

            wrapper.setId(Controller.getArrJugadasVolatiles().size());

            ImageView  flag = (ImageView) wrapper .findViewById(R.id.flag);
            TextView txtDream = (TextView) wrapper.findViewById(R.id.txtDream);
            TextView txtDineroApostado = (TextView) wrapper.findViewById(R.id.txtDineroApostado);
            String numeroApostado = String.valueOf(jugadaActual.getNumeroApostado());

           // flag.setImageResource(R.drawable.huevos);
            Dream dreamAsociado = null;
            try
            {
                dreamAsociado = Controller.llenameElSueño(Integer.parseInt(jugadaActual.getNumeroApostado()));
            }
            catch (Exception e)
            {
                System.out.println("ERROR: AdaptadorDeJugadas.getView() +  pidiendo sueño.");
                e.printStackTrace();
            }

            if(dreamAsociado != null)
            {
                ThreadCargaImagenSueño threadCargaImagenSueño = new ThreadCargaImagenSueño(flag,dreamAsociado);
                threadCargaImagenSueño.execute();
                txtDream.setText(String.valueOf(Controller.llenameElSueño(Integer.parseInt(jugadaActual.getNumeroApostado())).getNombre()));
                txtDineroApostado.setText(String.valueOf("$" + jugadaActual.getDineroApostado()));
            }
            else
            {
               flag.setImageResource(R.drawable.relojarena);
            }
        } else
        {
            wrapper = (View) convertView;
        }*/

        return wrapper;
    }

}
