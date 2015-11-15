package com.example.nicog.test.model;


        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;
        import com.google.gson.annotations.Expose;
        import javax.persistence.Entity;
        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.Table;

@Entity
@Table(name="dreams")
public class Dream
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose
    private int id;
    @Expose
    private int numero;
    @Expose
    private String nombre;
    @Expose
    private String url;

    public Dream()
    {
        this.id = -1;
        this.numero = -1;
        this.nombre = "";
        this.url = "";
    }
    public Dream(int id, int numero, String nombre, String url)
    {
        this.id = id;
        this.numero = numero;
        this.nombre = nombre;
        this.url = url;
    }

    //<editor-fold desc="GYS">
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    //</editor-fold>

    @Override
    public String toString() {
        return "Dream{" + "id=" + id + ", numero=" + numero + ", nombre=" + nombre + ", url=" + url + '}';
    }

    public String toJSON()
    {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return gson.toJson(this);
    }
}
