package com.example.nicog.test.model;;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

public class RespuestaJugada 
{
    @Expose
    private Jugada jugadaAsociada;
    @Expose
    private double dineroGanado;

    public RespuestaJugada() {
        jugadaAsociada = new Jugada();
        dineroGanado = 0;
    }

    public RespuestaJugada(Jugada jugadaAsociada, double dineroGanado) {
        this.jugadaAsociada = jugadaAsociada;
        this.dineroGanado = dineroGanado;
    }
    

    public Jugada getJugadaAsociada() {
        return jugadaAsociada;
    }

    public void setJugadaAsociada(Jugada jugadaAsociada) {
        this.jugadaAsociada = jugadaAsociada;
    }

    public double getDineroGanado() {
        return dineroGanado;
    }

    public void setDineroGanado(double dineroGanado) {
        this.dineroGanado = dineroGanado;
    }
    public void agregarGanancia(double cuanto)
    {
        this.dineroGanado += cuanto;
    }

    @Override
    public String toString() {
        return "RespuestaJugada{" + "jugadaAsociada=" + jugadaAsociada.toString() + ", dineroGanado=" + dineroGanado + '}';
    }
    public String toJSON ()
    {
        Gson  gson = new Gson();
        
        String JSON = gson.toJson(this);
        
        return JSON;
    }
    
    
}
