package com.example.nicog.test.model;;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class PackJugadaEntrante 
{
    @Expose
    private int id;
    @Expose
    private ArrayList<Jugada> arrJugadasRealizadas;
    @Expose
    private Tarjeta tarjeta;

    public PackJugadaEntrante() 
    {
        this.id = -1;
        this.arrJugadasRealizadas = new ArrayList<Jugada>();
        this.tarjeta = new Tarjeta();
    }

    public PackJugadaEntrante(int id, ArrayList<Jugada> arrJugadasRealizadas, Tarjeta tarjeta) {
        this.id = id;
        this.arrJugadasRealizadas = arrJugadasRealizadas;
        this.tarjeta = tarjeta;
    }
    
    //<editor-fold desc="GYS">
    public int getId()
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public ArrayList<Jugada> getArrJugadasRealizadas() 
    {
        return arrJugadasRealizadas;
    }

    public void setArrJugadasRealizadas(ArrayList<Jugada> arrJugadasRealizadas) {
        this.arrJugadasRealizadas = arrJugadasRealizadas;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }
    
    
    //</editor-fold>
    
    @Override
    public String toString() {
        return "PackJugadaEntrante{" + "id=" + id + ", arrJugadasRealizadas=" + arrJugadasRealizadas + ", tarjeta=" + tarjeta + '}';
    }
    public String toJSON ()
    {
        Gson  gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String JSON = gson.toJson(this);
        
        return JSON;
    }


}
