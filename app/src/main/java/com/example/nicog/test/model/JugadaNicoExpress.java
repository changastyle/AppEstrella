package com.example.nicog.test.model;

public class JugadaNicoExpress
{
    private int numeroApostado;
    private double dineroApostado;

    public JugadaNicoExpress()
    {
        numeroApostado = 0;
        dineroApostado = 0.0;
    }

    public JugadaNicoExpress(int numeroApostado, double dineroApostado)
    {
        this.numeroApostado = numeroApostado;
        this.dineroApostado = dineroApostado;
    }

    //*GYS*//
    public int getNumeroApostado() {
        return numeroApostado;
    }

    public void setNumeroApostado(int numeroApostado) {
        this.numeroApostado = numeroApostado;
    }

    public double getDineroApostado() {
        return dineroApostado;
    }

    public void setDineroApostado(double dineroApostado) {
        this.dineroApostado = dineroApostado;
    }

    @Override
    public String toString() {
        return "JugadaNicoExpress{" +
                "numeroApostado=" + numeroApostado +
                ", dineroApostado=" + dineroApostado +
                '}';
    }
}
