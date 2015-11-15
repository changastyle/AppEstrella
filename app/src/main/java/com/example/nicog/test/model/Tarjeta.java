package com.example.nicog.test.model;;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tarjetas")
public class Tarjeta 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Expose 
    private int id;
    @Expose 
    private int numeroSerie;
    @Expose 
    private Date fechaAlta;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "tarjeta" , fetch = FetchType.EAGER)
    @Expose 
    private List<DetalleTarjeta> arrSaldos = new ArrayList<DetalleTarjeta>();
    

    public Tarjeta() 
    {
        arrSaldos = new ArrayList<DetalleTarjeta>();
        numeroSerie = 0;
        fechaAlta = new Date();
    }

    public Tarjeta(int id, int numeroSerie, Date fechaAlta) 
    {
        this.id = id;
        this.numeroSerie = numeroSerie;
        this.fechaAlta = fechaAlta;
        this.arrSaldos = new ArrayList<DetalleTarjeta>();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public List<DetalleTarjeta> getArrSaldos() {
        return arrSaldos;
    }

    public void setArrSaldos(List<DetalleTarjeta> arrSaldos) {
        this.arrSaldos = arrSaldos;
    }

    public String imprimirSaldos()
    {
        String salida ="";
        for(DetalleTarjeta saldosDeTarjeta : arrSaldos)
        {
            
            salida += saldosDeTarjeta.toString() + "\n";
        }
        return salida;
    }
    public void addDetalle(DetalleTarjeta detalleTarjeta)
    {
        this.arrSaldos.add(detalleTarjeta);
    }
    public double calcularSaldoActualDeLaTarjeta()
    {
        double saldo = 0;
        for(DetalleTarjeta saldosDeTarjeta : arrSaldos)
        {
            if(saldosDeTarjeta.isPositivo())
            {
                saldo += saldosDeTarjeta.getCantidad();
            }
            else
            {
                saldo -= saldosDeTarjeta.getCantidad();
            }
            
        }
        return saldo;
    }

    @Override
    public String toString() {
        return "Tarjeta{" + "id=" + id + ", numeroSerie=" + numeroSerie + ", fechaAlta=" + fechaAlta + ", arrSaldos=" + imprimirSaldos() + "\n|SALDO ACTUAL = " + calcularSaldoActualDeLaTarjeta() + '}';
    }
    public String toJSONSaldos()
    {
        String salida = ",[";
        
        for(DetalleTarjeta saldosDeTarjeta: arrSaldos)
        {
            salida += "tarjeta:{}";
        }
        
        return salida;
    }
    
    

    public String toJSON ()
    {
        Gson  gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        
        String JSON = gson.toJson(this);
        
        return JSON;
    }
    
    
    
}
