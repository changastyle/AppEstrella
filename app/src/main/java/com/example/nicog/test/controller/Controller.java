package com.example.nicog.test.controller;

import android.app.Activity;

import com.example.nicog.test.Threads.ThreadPideDream;
import com.example.nicog.test.Util.JSONWS;
import com.example.nicog.test.model.*;
import com.google.gson.Gson;

import java.util.ArrayList;

public class Controller
{
    //PARAMETROS QUE DEBEN SER CARGADOS POR OTRO LADO DESPUES:
    private final static String versionActual = "1.0.5";
    //public static final String direccionIPWS = "192.168.5.189";
    //public static final String direccionIPWS = "192.168.1.103";
    public static final String direccionIPWS = "ngrossi.ddns.net";
    private static boolean falloLaConexion = false;
    private final static String URLWSJuegos = "http://"+ direccionIPWS +":8080/AppEstrellaServer/WS/juegos.jsp";
    private final static String URLWSVersiones = "http://" + direccionIPWS + ":8080/AppEstrellaServer/WS/consultarUltimaVersion.jsp";
    private final static String URLDescargarNuevaVersion = "http://" + "ngrossi.ddns.net" + "/AppEstrellaServer/";      //NO CAMBIAR.
    private final static String URLWSTarjetas = "http://" + direccionIPWS + ":8080/AppEstrellaServer/WS/pedirDatosTarjeta.jsp";
    private final static String URLWSDreams = "http://" + direccionIPWS + ":8080/AppEstrellaServer/WS/dreams.jsp";

    //LISTA ACTIVITIES:
    private static ArrayList<Activity> listaActivitys = new ArrayList<Activity>();
    private static boolean modoTablet = false;
    //LogicaDeNegocios:
    private static ArrayList<Juego> arrDeJuegos = new ArrayList<Juego>();
    private static java.util.ArrayList<Jugada> arrJugadasNicoExpress = new java.util.ArrayList<Jugada>();
    private static ArrayList<Jugada> arrJugadasVolatiles = new ArrayList<Jugada>();
    private static Tarjeta tarjetaVinculada = null;
    public static ArrayList<Juego> pedirJuegos()
    {
        ArrayList<Juego> arr = JSONWS.dameArrayJava(URLWSJuegos, Juego.class);
        arrDeJuegos = arr;  //THIS.arrDeJuegos = arr;
        return arr;
    }
    public static boolean soyUnaVersionActualizada()
    {
        boolean actualizada = false;
        System.out.println("Consultando Nuevas versiones de la App:");
        VersionDeLaApp versionDeLaApp = (VersionDeLaApp) JSONWS.dameObjetoJava(URLWSVersiones, VersionDeLaApp.class);

        System.out.println("Version de la app:" + versionDeLaApp);
        if(versionDeLaApp != null)
        {
            if(versionDeLaApp.getNumeroDeVersion().equalsIgnoreCase(versionActual))
            {
                System.out.println("Version correcta!.");
                actualizada = true;
            }
            else
            {
                System.out.println("Version mas nueva existente:" + versionDeLaApp.getNumeroDeVersion());
                actualizada = false;
            }
        }
        else
        {
             falloLaConexion();
        }
    return actualizada;
    }

    private static void falloLaConexion()
    {
        falloLaConexion = true;
    }

    public static Tarjeta pedirDatosTarjeta(int numeroTarjeta)
    {
        String datosRecibidos =  JSONWS.sendData(URLWSTarjetas,"numeroSerie",String.valueOf(numeroTarjeta));
        Tarjeta tarjeta = (Tarjeta) JSONWS.solamenteConvertirDatosJSONaObjetoJava(datosRecibidos,Tarjeta.class);
        tarjetaVinculada = tarjeta;
        return tarjeta;
    }

   /* public static String dameSueño(int numero)
    {

        String arrSuenios[] = {"Huevos", "Agua", "Niño", "San Cono", "La Cama", "Gato", "Perro", "Revolver", "Incendio", "Arroyo", "La leche", "Palito", "Soldado", "La yeta", "Borracho", "Niña bonita", "Anillo", "Desgracia", "Sangre", "Pescado", "La fiesta", "La mujer", "El loco", "Mariposa", "Caballo", "Gallina", "La misa", "El peine", "El cerro", "San Pedro", "Santa Rosa", "La luz", "Dinero", "Cristo", "Cabeza", "Pajarito", "Manteca", "Dentista", "Aceite", "Lluvia", "Cura", "Cucho", "Zapatilla", "Balcón", "La cárcel", "El vino", "Tomates", "Muerto", "Muerto habla", "La carne", "El pan", "Serrucho", "Madre", "El barco", "La vaca", "Los gallegos", "La caída", "Jorabajo", "Ahogado", "Planta", "Virgen", "Escopeta", "Inundacion", "Casamiento", "Llanto", "Cazador", "Lombrices", "Víbora", "Sobrinos", "Vicios", "Muerto sueño", "Excrementos", "Sorpresa", "Hospital", "Negros", "Payaso", "Llamas", "Las piernas", "Ramera", "Ladrón", "La bocha", "Flores", "Pelea", "Mal tiempo", "Iglesia", "Linterna", "Humo", "Piojos", "El Papa", "La rata", "El miedo", "Excusado", "Médico", "Enamorado", "Cementerio", "Anteojos", "Marido", "La mesa", "Lavandera", "Hermanos"};
        if (numero > -1 && numero < 100)
        {
            return arrSuenios[numero];
        }
        else if (numero > 99 && numero < 1000)
        {
            String strAux = "" + numero;

            int nuevoNumero = Integer.parseInt(strAux.substring(strAux.length() - 2, strAux.length()));

            return arrSuenios[nuevoNumero];
        } else
        {
            return "" + numero;
        }
    }*/

    public static int maximoNicoExpress()
    {
        return 10;
    }

    public static int minimoNicoExpress()
    {
        return 0;
    }

    public static int porDefectoNicoExpress()
    {
        return 5;

    }

    public static void addActivity(Activity activity)
    {
        listaActivitys.add(activity);
    }

    public static void killAll()
    {
        if (listaActivitys.size() > 0)
        {
            for (Activity activity : listaActivitys)
            {
                if(activity != null)
                {
                    activity.finish();
                }
            }
        }
    }

    public static void agregarJugadaDeNicoExpress(Jugada jugadaNueva)
    {
        arrJugadasNicoExpress.add(jugadaNueva);
    }

    public static void addJugadaVolatil(Jugada jugadaNicoExpress)
    {
        arrJugadasVolatiles.add(jugadaNicoExpress);
    }

    public static void removeJugadaVolatil(Jugada jugadaAremover)
    {
        int contador = 0;
        for (Jugada jugadaBucle : arrJugadasVolatiles)
        {
            if (jugadaBucle.getNumeroApostado() == jugadaAremover.getNumeroApostado())
            {
                arrJugadasVolatiles.remove(contador);
                System.out.println("REMUEVO:" + jugadaBucle.toJSON());
            }
            contador++;
        }
    }

    public static void limpiarJugadaVolatiles()
    {
        arrJugadasVolatiles = new ArrayList<Jugada>();
    }

    public static String enviarJugadasAlServer()
    {
        PackJugadaEntrante packJugadaEntrante = new PackJugadaEntrante();
        packJugadaEntrante.setTarjeta(tarjetaVinculada);
        packJugadaEntrante.setArrJugadasRealizadas(Controller.getArrJugadasVolatiles());
        System.out.println("arrJugadasVolatiles: " + arrJugadasVolatiles.size());

        for(Jugada j : arrJugadasVolatiles)
        {
            System.out.println(j.toJSON());
        }

        System.out.println("packJugadaEntrante.toJSON(): " + packJugadaEntrante.toJSON());
        return JSONWS.sendData("http://" + direccionIPWS + ":8080/AppEstrellaServer/WS/reciboJugadas.jsp", "jugadasEntrantes", packJugadaEntrante.toJSON());
    }
    public static Dream llenameElSueño(int numeroDelSueño)
    {
        Dream dreamRespuesta = null;

        String strRespuesta = "";
        if (numeroDelSueño > -1 && numeroDelSueño < 100)
        {
            numeroDelSueño = numeroDelSueño;
        }
        else if (numeroDelSueño > 99 && numeroDelSueño < 1000)
        {
            String strAux = "" + numeroDelSueño;
            numeroDelSueño = Integer.parseInt(strAux.substring(strAux.length() - 2, strAux.length()));
        }
        else
        {
            numeroDelSueño = -1;
        }


        if(numeroDelSueño != -1)
        {
            try
            {
                ThreadPideDream threadPideDream = new ThreadPideDream(numeroDelSueño);
                threadPideDream.start();
                threadPideDream.join();

                dreamRespuesta = threadPideDream.getDreamAllenar();
                System.out.println("dreamRespuesta:" + dreamRespuesta);
            }
            catch(Exception e)
            {
                System.out.println("ERROR: Controller.llenameElSueño(" + numeroDelSueño + ")");
                e.printStackTrace();
            }
        }


        return dreamRespuesta;
    }

    //GYS:
    public static String getDireccionIPWS()
    {
        return direccionIPWS;
    }

    public static ArrayList<Juego> getArrDeJuegos()
    {
        return arrDeJuegos;
    }

    public static void setArrDeJuegos(ArrayList<Juego> arr)
    {
        Controller.arrDeJuegos = arr;
    }

    public static String getURLWSJuegos()
    {
        return URLWSJuegos;
    }

    public static ArrayList<Jugada> getArrJugadasNicoExpress()
    {
        return arrJugadasNicoExpress;
    }

    public static void setArrJugadasNicoExpress(ArrayList<Jugada> arrJugadasNicoExpress)
    {
        Controller.arrJugadasNicoExpress = arrJugadasNicoExpress;
    }

    public static ArrayList<Activity> getListaActivitys()
    {
        return listaActivitys;
    }

    public static void setListaActivitys(ArrayList<Activity> listaActivitys)
    {
        Controller.listaActivitys = listaActivitys;
    }

    public static ArrayList<Jugada> getArrJugadasVolatiles()
    {
        return arrJugadasVolatiles;
    }

    public static void setArrJugadasVolatiles(ArrayList<Jugada> arrJugadasVolatiles)
    {
        Controller.arrJugadasVolatiles = arrJugadasVolatiles;
    }

    public static void setFalloLaConexion(boolean falloLaConexion)
    {
        Controller.falloLaConexion = falloLaConexion;
    }

    public static boolean isFalloLaConexion()
    {
        return falloLaConexion;
    }

    public static String getVersionActual()
    {
        return versionActual;
    }

    public static String getURLWSVersiones()
    {
        return URLWSVersiones;
    }

    public static String getURLDescargarNuevaVersion()
    {
        return URLDescargarNuevaVersion;
    }

    public static String getURLWSTarjetas()
    {
        return URLWSTarjetas;
    }

    public static Tarjeta getTarjetaVinculada()
    {
        return tarjetaVinculada;
    }

    public static void setTarjetaVinculada(Tarjeta tarjetaVinculada)
    {
        Controller.tarjetaVinculada = tarjetaVinculada;
    }

    public static boolean isModoTablet()
    {
        return modoTablet;
    }

    public static void setModoTablet(boolean modoTablet)
    {
        Controller.modoTablet = modoTablet;
    }

    public static String getURLWSDreams()
    {
        return URLWSDreams;
    }
}
