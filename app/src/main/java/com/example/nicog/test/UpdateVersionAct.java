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
import android.widget.ImageButton;

import com.example.nicog.test.controller.Controller;

public class UpdateVersionAct extends Activity implements View.OnClickListener
{
    Button btnActualizarApp;
    ImageButton imgViejito;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_version);

        btnActualizarApp = (Button) findViewById(R.id.btnActualizarApp);btnActualizarApp.setOnClickListener(this);
        imgViejito = (ImageButton) findViewById(R.id.imgViejito); imgViejito.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_version, menu);
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
        if (id == R.id.update_exit_setting)
        {
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        Intent intentToBrowser = new Intent(Intent.ACTION_VIEW);
        intentToBrowser.setData(Uri.parse(Controller.getURLDescargarNuevaVersion()));
        startActivity(intentToBrowser);
    }
}
