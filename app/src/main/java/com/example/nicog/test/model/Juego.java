package com.example.nicog.test.model;;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.google.gson.Gson;

@Entity
@Table(name="juegos")
public class Juego implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int id;
    private String nombre;
    private String urlIcono;

    public Juego() 
    {
        this.id = id;
        this.nombre = nombre;
        this.urlIcono = urlIcono;
    }

    public Juego(int id, String nombre, String urlIcono) 
    {
        this.id = id;
        this.nombre = nombre;
        this.urlIcono = urlIcono;
    }

    
    //<editor-fold desc="GYS:">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlIcono() {
        return urlIcono;
    }

    public void setUrlIcono(String urlIcono) {
        this.urlIcono = urlIcono;
    }
    
    //</editor-fold>

    @Override
    public String toString()
    {
        return "Juego{" + "id=" + id + ", nombre=" + nombre + ", urlIcono=" + urlIcono + '}';
    }
    
    public String toJSON()
    {
        Gson  gson = new Gson ();
        
        String JSON = gson.toJson(this);
        
        return JSON;
    }
    
    
}
