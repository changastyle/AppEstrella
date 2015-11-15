package com.example.nicog.test.model;;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;

public class RespuestaConjunto 
{
    @Expose 
    private Tarjeta tarjetaAsociada;
    @Expose 
    private ArrayList<String> arrNumerosSorteados;
    @Expose 
    private ArrayList<RespuestaJugada> arrRespuestas;
    @Expose 
    private boolean saldoInsuficiente = false;

    public RespuestaConjunto() {
        this.tarjetaAsociada = new Tarjeta();
        this.arrNumerosSorteados = new ArrayList<String>();
        this.arrRespuestas = new ArrayList<RespuestaJugada>();
        this.saldoInsuficiente = false;
    }

    
    public RespuestaConjunto(Tarjeta tarjetaAsociada, ArrayList<String> arrNumerosSorteados, ArrayList<RespuestaJugada> arrRespuestas) {
        this.tarjetaAsociada = tarjetaAsociada;
        this.arrNumerosSorteados = arrNumerosSorteados;
        this.arrRespuestas = arrRespuestas;
        this.saldoInsuficiente = false;
    }

    
    
    public Tarjeta getTarjetaAsociada() {
        return tarjetaAsociada;
    }
    
    public boolean isSaldoInsuficiente() {    
        return saldoInsuficiente;
    }

    //GYS:
    public void setSaldoInsuficiente(boolean saldoInsuficiente) {
        this.saldoInsuficiente = saldoInsuficiente;    
    }

    public void setTarjetaAsociada(Tarjeta tarjetaAsociada) {
        this.tarjetaAsociada = tarjetaAsociada;
    }

    public ArrayList<String> getArrNumerosSorteados() {
        return arrNumerosSorteados;
    }

    public void setArrNumerosSorteados(ArrayList<String> arrNumerosSorteados) {
        this.arrNumerosSorteados = arrNumerosSorteados;
    }

    public ArrayList<RespuestaJugada> getArrRespuestas() {
        return arrRespuestas;
    }

    public void setArrRespuestas(ArrayList<RespuestaJugada> arrRespuestas) {
        this.arrRespuestas = arrRespuestas;
    }
    public double calcularGananciaTotal()
    {
        double total = 0 ;
        for(RespuestaJugada respuestaJugada : arrRespuestas)
        {
            total += respuestaJugada.getDineroGanado();
        }
        return total;
    }
    @Override
    public String toString() {
        return "RespuestaConjunto{" + "tarjetaAsociada=" + tarjetaAsociada + ", arrNumerosSorteados=" + arrNumerosSorteados + ", arrRespuestas=" + arrRespuestas + ", saldoInsuficiente=" + saldoInsuficiente + '}';
    }

    
    public String toJSON ()
    {
        Gson  gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String JSON = gson.toJson(this);
        
        return JSON;
    }
}
