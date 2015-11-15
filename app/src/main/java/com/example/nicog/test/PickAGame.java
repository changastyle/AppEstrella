package com.example.nicog.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicog.test.Threads.ThreadPideJuegos;
import com.example.nicog.test.Util.AdaptadorDeJuegos;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.Juego;
import com.example.nicog.test.model.Tarjeta;

import java.util.ArrayList;

public class PickAGame extends Activity implements View.OnClickListener
{
    TextView txt ;
    private Button btnVincularTarjeta;
    private TextView txtSaldo;
    GridView gridViewGames;
    private ArrayList<Juego> arrayDeJuegos;
    private ProgressBar progressBarTemporal;
    private LinearLayout linearLayoutDondeVaElGridView , wraperHeaderSaldo;
    private RelativeLayout wraperVincularTarjeta;
    private AdaptadorDeJuegos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_a_game_activity);

        wraperHeaderSaldo = (LinearLayout) findViewById(R.id.wraperHeaderSaldo);
        wraperVincularTarjeta = (RelativeLayout) findViewById(R.id.wraperVincularTarjeta);
        txtSaldo = (TextView) findViewById(R.id.txtSaldo);
        btnVincularTarjeta = (Button) findViewById(R.id.btnVincularTarjeta);
        btnVincularTarjeta.setOnClickListener(this);

            if( Controller.getTarjetaVinculada() != null)
            {
                wraperVincularTarjeta.setVisibility(View.GONE);
                wraperHeaderSaldo.setVisibility(View.VISIBLE);

                btnVincularTarjeta.setText("Tarjeta nro: #" +Controller.getTarjetaVinculada().getNumeroSerie());
                txtSaldo.setText("Saldo: $" + Controller.getTarjetaVinculada().calcularSaldoActualDeLaTarjeta());
            }
            else
            {
                wraperVincularTarjeta.setVisibility(View.VISIBLE);
                wraperHeaderSaldo.setVisibility(View.GONE);

                btnVincularTarjeta.setText(R.string.btnTarjetaNoVinculada);
                txtSaldo.setText(R.string.txtSaldoNoVinculado);
            }

            gridViewGames = (GridView) findViewById(R.id.gridViewGames);
            adaptador = new AdaptadorDeJuegos(this,Controller.getArrDeJuegos());
            gridViewGames.setAdapter(adaptador);

            //PIDO QUE JUEGOS TIENE DISPONIBLE EL SV:
           ThreadPideJuegos threadPideJuegos = new ThreadPideJuegos(this);
            threadPideJuegos.execute();

            Controller.addActivity(this);

    }

    public void tePasoLosJuegos(ArrayList<Juego> arrJuegos)
    {
        /*gridViewGames = (GridView) findViewById(R.id.gridViewGames);
        linearLayoutDondeVaElGridView.removeView(progressBarTemporal);
        arrayDeJuegos = arrJuegos;
        System.out.println("TRAJE " + arrayDeJuegos.size() + "JUEGOS:");*/
        for(Juego juego : Controller.getArrDeJuegos())
        {
            Juego juegoAux = new Juego(juego.getId(), juego.getNombre(),juego.getUrlIcono());
            arrayDeJuegos.add(juegoAux);
            System.out.println("Juego: " + juegoAux.toString());
        }

        System.out.println("arrayDeJuegos: " + arrayDeJuegos.size());
        adaptador = new AdaptadorDeJuegos(this,arrayDeJuegos);
        System.out.println("adaptador.size = " + adaptador.getCount());
        gridViewGames.setAdapter(adaptador);
    }
    public void TengoUnaTarjetaNueva(Tarjeta tarjeta)
    {
        btnVincularTarjeta.setText(tarjeta.getNumeroSerie());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pick_a_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.pick_exit)
        {
            Controller.killAll();
        }
        else if (id == R.id.pick_descargar_qr)
        {
            bajarQRDroid();
        }
        else if(id == R.id.pick_modo_tablet)
        {
            modoTablet();
        }

        return super.onOptionsItemSelected(item);
    }
    private void bajarQRDroid()
    {
        Intent intentToQRDroid = new Intent(android.content.Intent.ACTION_VIEW);
        intentToQRDroid.setData(Uri.parse("https://play.google.com/store/apps/details?id=com.google.zxing.client.android&hl=es-419"));
        startActivity(intentToQRDroid);
    }

    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;

        if(botonPresionado == btnVincularTarjeta)
        {
            vincularTarjeta();
        }
        // PARA LOS JUEGOS EL ESUCHA DE BOTONES ESTA EN EL ADAPTER!!!.
    }
    public void abrirJuego(String nombreJuego)
    {
        Intent intentToJuego = new Intent(this,ManagerNicoExpress.class);
        this.startActivity(intentToJuego);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == 0)
        {

            if (resultCode == RESULT_OK)
            {
                int numeroEscaneado = Integer.parseInt(intent.getStringExtra("SCAN_RESULT"));

                Intent intentToLoadingActivity = new Intent(this,LoadingActivityWaitingForTarjetaFromQR.class);
                intentToLoadingActivity.putExtra("numeroEscaneadoConElQR", numeroEscaneado);
                startActivity(intentToLoadingActivity);
            }
            else if (resultCode == RESULT_CANCELED)
            {
                Toast.makeText(this,"Error al leer codigo QR",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void vincularTarjeta()
    {
        try
        {
            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            Toast.makeText(this,"Apunta y dispara!",Toast.LENGTH_SHORT).show();
            startActivityForResult(intent, 0);
            //this.finish();
        }
        catch(Exception e)
        {
            Toast.makeText(this,"Necesitas un lector de codigos QR!",Toast.LENGTH_LONG).show();
            bajarQRDroid();

        }


    }
    private void modoTablet()
    {
        Controller.setModoTablet(true);
        Intent intentToModoTablet = new Intent(this,LoadingActivityWaitingForTarjetaFromQR.class);
        intentToModoTablet.putExtra("numeroEscaneadoConElQR", 4545878);
        this.startActivity(intentToModoTablet);

        Toast.makeText(this, "Modo Tablet Iniciado!",Toast.LENGTH_SHORT).show();
        this.finish();

    }


    /*GYS**/

    public TextView getTxt()
    {
        return txt;
    }

    public void setTxt(TextView txt)
    {
        this.txt = txt;
    }

    public AdaptadorDeJuegos getAdaptador()
    {
        return adaptador;
    }

    public void setAdaptador(AdaptadorDeJuegos adaptador)
    {
        this.adaptador = adaptador;
    }

    public LinearLayout getLinearLayoutDondeVaElGridView()
    {
        return linearLayoutDondeVaElGridView;
    }

    public void setLinearLayoutDondeVaElGridView(LinearLayout linearLayoutDondeVaElGridView)
    {
        this.linearLayoutDondeVaElGridView = linearLayoutDondeVaElGridView;
    }

    public ProgressBar getProgressBarTemporal()
    {
        return progressBarTemporal;
    }

    public void setProgressBarTemporal(ProgressBar progressBarTemporal)
    {
        this.progressBarTemporal = progressBarTemporal;
    }

    public ArrayList<Juego> getArrayDeJuegos()
    {
        return arrayDeJuegos;
    }

    public void setArrayDeJuegos(ArrayList<Juego> arrayDeJuegos)
    {
        this.arrayDeJuegos = arrayDeJuegos;
    }

    public GridView getGridViewGames()
    {
        return gridViewGames;
    }

    public void setGridViewGames(GridView gridViewGames)
    {
        this.gridViewGames = gridViewGames;
    }

    public Button getBtnVincularTarjeta()
    {
        return btnVincularTarjeta;
    }

    public void setBtnVincularTarjeta(Button btnVincularTarjeta)
    {
        this.btnVincularTarjeta = btnVincularTarjeta;
    }
}
