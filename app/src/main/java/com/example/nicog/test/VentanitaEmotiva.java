package com.example.nicog.test;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class VentanitaEmotiva extends Activity
{
    private MediaPlayer mp;
    private ImageButton imagen;
    private Button btnOK;
    private TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        imagen = (ImageButton) findViewById(R.id.imgButton);
        btnOK = (Button) findViewById(R.id.btnOK);
        txtInfo = (TextView) findViewById(R.id.txtInfo);
        String strTotal = getIntent().getStringExtra("total");
        double total = Double.parseDouble(strTotal);

        if(total > 0)
        {
            txtInfo.setText("Ganaste!!");
            imagen.setBackgroundResource(R.drawable.hombre_rico);
            mp = MediaPlayer.create(this,R.raw.exito);
            mp.start();
        }
        else
        {
            txtInfo.setText("No ganador.");
            imagen.setBackgroundResource(R.drawable.xroja);
            mp = MediaPlayer.create(this,R.raw.fracaso);
            mp.start();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_win, menu);
        return true;
    }

    public void clickTerminarAct(View v)
    {
        Button botonPresionado = (Button)v;

        if(botonPresionado == btnOK)
        {
            this.finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
