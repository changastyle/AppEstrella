package com.example.nicog.test.model;;

import com.google.gson.Gson;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="versionesdelaapp")
public class VersionDeLaApp 
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String numeroDeVersion;
    private Date timestamp;

    public VersionDeLaApp() {
        this.id = -1;
        this.numeroDeVersion = "";
        this.timestamp = new Date(0);
    }
    public VersionDeLaApp(int id, String numeroDeVersion, Date timestamp) {
        this.id = id;
        this.numeroDeVersion = numeroDeVersion;
        this.timestamp = timestamp;
    }
    //<editor-fold desc="GYS">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroDeVersion() {
        return numeroDeVersion;
    }

    public void setNumeroDeVersion(String numeroDeVersion) {
        this.numeroDeVersion = numeroDeVersion;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "VersionDeLaApp{" + "id=" + id + ", numeroDeVersion=" + numeroDeVersion + ", timestamp=" + timestamp + '}';
    }
    public String toJSON()
    {
        Gson  gson = new Gson ();
        
        String JSON = gson.toJson(this);
        
        return JSON;
    }
    
    
    
}
