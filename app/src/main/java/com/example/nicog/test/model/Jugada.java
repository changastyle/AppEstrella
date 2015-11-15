package com.example.nicog.test.model;;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="jugadas")
public class Jugada 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Expose
    private int id;
    @Column(name="numeroApostado")
    @Expose
    private String numeroApostado;
    @Column(name="dineroApostado")
    @Expose
    private double dineroApostado;
    @Expose
    private int fkTarjeta;

    public Jugada() 
    {
        this.id = -1;
        this.numeroApostado = "";
        this.dineroApostado = 0.0;
        this.fkTarjeta = -1;
    }

    public Jugada(String numeroApostado, double dineroApostado) 
    {
        this.numeroApostado = numeroApostado;
        this.dineroApostado = dineroApostado;
    }
    
    //GYS:

    public String getNumeroApostado() {
        return numeroApostado;
    }

    public void setNumeroApostado(String numeroApostado) {
        this.numeroApostado = numeroApostado;
    }

    public double getDineroApostado() {
        return dineroApostado;
    }

    public void setDineroApostado(double dineroApostado) {
        this.dineroApostado = dineroApostado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkTarjeta() {
        return fkTarjeta;
    }

    public void setFkTarjeta(int fkTarjeta) {
        this.fkTarjeta = fkTarjeta;
    }

    @Override
    public String toString()
    {
        return "Jugada{" + "id=" + id + ", numeroApostado=" + numeroApostado + ", dineroApostado=" + dineroApostado + ", fkTarjeta=" + fkTarjeta + '}';
    }
    public String toJSON()
    {
        Gson  gson = new Gson();
        
        String JSON = gson.toJson(this);
        
        return JSON;
    }
    
    
    
    
}
