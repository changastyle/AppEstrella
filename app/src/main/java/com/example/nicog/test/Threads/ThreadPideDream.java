package com.example.nicog.test.Threads;

import com.example.nicog.test.Util.JSONWS;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.Dream;

/**
 * Created by nicog on 10/11/2015.
 */
public class ThreadPideDream extends Thread
{
    private Dream dreamAllenar;
    private int numeroDream;
    public ThreadPideDream(int numeroDream)
    {
        this.dreamAllenar = null;
        this.numeroDream = numeroDream;
    }
    @Override
    public void run()
    {
        super.run();
        String strRespuesta = JSONWS.sendData(Controller.getURLWSDreams(), "numeroDream", String.valueOf(numeroDream));
        dreamAllenar = (Dream) JSONWS.solamenteConvertirDatosJSONaObjetoJava(strRespuesta, Dream.class);
    }

    public Dream getDreamAllenar()
    {
        return dreamAllenar;
    }

    public void setDreamAllenar(Dream dreamAllenar)
    {
        this.dreamAllenar = dreamAllenar;
    }

    public int getNumeroDream()
    {
        return numeroDream;
    }

    public void setNumeroDream(int numeroDream)
    {
        this.numeroDream = numeroDream;
    }
}
