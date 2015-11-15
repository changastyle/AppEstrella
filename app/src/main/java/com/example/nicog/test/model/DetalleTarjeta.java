package com.example.nicog.test.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="saldos")
public class DetalleTarjeta 
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose 
    private int id;
    @Expose 
    private boolean positivo;
    @Expose 
    private double cantidad;
    @Expose 
    private Date time;
    @ManyToOne()
    @JoinColumn(name = "fkTarjeta")
    private Tarjeta tarjeta;

    public DetalleTarjeta() 
    {
        this.positivo = false;
        this.cantidad = 0;
        time = new Date(0);
        this.tarjeta = new Tarjeta();
    }

    public DetalleTarjeta( boolean positivo, double cantidad, Date time, Tarjeta tarjeta) {
        this.positivo = positivo;
        this.cantidad = cantidad;
        this.time = time;
        this.tarjeta = tarjeta;
    }
    

    //GYS:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPositivo() {
        return positivo;
    }

    public void setPositivo(boolean positivo) {
        this.positivo = positivo;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    /*public Tarjeta getTarjeta() {
        return tarjeta;
    }*/

    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    @Override
    public String toString() {
        return "SaldosDeTarjeta{" + "id=" + id + ", positivo=" + positivo + ", cantidad=" + cantidad + ", time=" + time +'}';
    }
    public String toJSON ()
    {
        Gson  gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String JSON = gson.toJson(this);
        
        return JSON;
    }
    
}
