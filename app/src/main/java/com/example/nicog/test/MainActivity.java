package com.example.nicog.test;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.nicog.test.Threads.ComprobarNuevasVersiones;

public class MainActivity extends Activity
{
    ImageView imagenTrebol;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagenTrebol = (ImageView) findViewById(R.id.imgTrebol);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        imagenTrebol.startAnimation(animation1);

        //Sonido de moneditas:
        mp = MediaPlayer.create(this, R.raw.coin);
        mp.start();


        //Networking y control de version de la App.
        ComprobarNuevasVersiones comprobarNuevasVersiones = new ComprobarNuevasVersiones(this);
        comprobarNuevasVersiones.execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.exit_setting)
        {
            this.finish();
        }
        else if(id == R.id.action_change_ip)
        {
            Intent intentToChangeIP = new Intent(this,ChangeIPAct.class);
            this.startActivity(intentToChangeIP);
        }

        return super.onOptionsItemSelected(item);
    }

    public ImageView getImagenTrebol()
    {
        return imagenTrebol;
    }

    public void setImagenTrebol(ImageView imagenTrebol)
    {
        this.imagenTrebol = imagenTrebol;
    }

    public MediaPlayer getMp()
    {
        return mp;
    }

    public void setMp(MediaPlayer mp)
    {
        this.mp = mp;
    }
}
