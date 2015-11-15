package com.example.nicog.test.Util;

import com.example.nicog.test.Threads.ConexionInicial;

/**
 * Created by nicog on 10/10/2015.
 */
public class ThreadTimeoutConexionInicial extends Thread
{
    private ConexionInicial conexionInicial;
    private int time;
    public ThreadTimeoutConexionInicial(ConexionInicial conexionInicial, int time)
    {
        this.conexionInicial = conexionInicial;
        this.time = time;
    }
    @Override
    public void run()
    {
        super.run();
        int contador = 0;
        while(contador <= time)
        {
            contador++;
            try
            {
                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
