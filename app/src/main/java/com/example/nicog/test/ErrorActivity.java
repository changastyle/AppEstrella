package com.example.nicog.test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.nicog.test.controller.Controller;

public class ErrorActivity extends Activity implements View.OnClickListener
{
    private Button btnReintentarConexion,btnContactoDesarrollador;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);


        btnReintentarConexion = (Button) findViewById(R.id.btnReintentarConexion);
        btnReintentarConexion.setOnClickListener(this);
        btnContactoDesarrollador = (Button) findViewById(R.id.btnContactoDesarrollador);
        btnContactoDesarrollador.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_error, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.error_exit_setting)
        {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;

        if(botonPresionado == btnReintentarConexion)
        {
            reintentarConexion();
        }
        else if(botonPresionado == btnContactoDesarrollador)
        {
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getResources().getString(R.string.telfonoDesarrollador)));
            startActivity(intent);
        }
    }
    public void reintentarConexion()
    {
        Intent intentFirstActivity = new Intent(this,MainActivity.class);
        Controller.setFalloLaConexion(false);

        this.startActivity(intentFirstActivity);

        this.finish();
    }
}
