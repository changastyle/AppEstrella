package com.example.nicog.test;

import android.app.Activity;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicog.test.Util.JSONWS;
import com.example.nicog.test.controller.Controller;
import com.example.nicog.test.model.Dream;
import com.example.nicog.test.model.Jugada;
import com.example.nicog.test.model.JugadaNicoExpress;

import java.util.ArrayList;

public class InputJugadasAct extends Activity implements View.OnClickListener{

    private Button btnNumero , btnSueño , btn7 ,btn8 ,btn9 , btn4 ,btn5 , btn6, btn3, btn2,btn1,btn0,btnBorrar,btnCancelar, btnMas, btnMenos,btnFinalizar,btnProximaJugada;
    private java.util.ArrayList<Button> arrDeBotonesConListener;
    private TextView txtDineroApostado;
    private String textoPantalla = "";
    private int indexDineroApostado = 0;
    private Jugada jugadaAsociada;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nico_express_game);

        arrDeBotonesConListener = new ArrayList<Button>();
        btnNumero  = (Button) findViewById(R.id.btnNumero); arrDeBotonesConListener.add(btnNumero);
        btnSueño  = (Button) findViewById(R.id.btnSueño);arrDeBotonesConListener.add(btnSueño);
        btn0 = (Button) findViewById(R.id.btn0);arrDeBotonesConListener.add(btn0);
        btn1 = (Button) findViewById(R.id.btn1);arrDeBotonesConListener.add(btn1);
        btn2 = (Button) findViewById(R.id.btn2);arrDeBotonesConListener.add(btn2);
        btn3 = (Button) findViewById(R.id.btn3);arrDeBotonesConListener.add(btn3);
        btn4 = (Button) findViewById(R.id.btn4);arrDeBotonesConListener.add(btn4);
        btn5 = (Button) findViewById(R.id.btn5);arrDeBotonesConListener.add(btn5);
        btn6 = (Button) findViewById(R.id.btn6);arrDeBotonesConListener.add(btn6);
        btn7 = (Button) findViewById(R.id.btn7);arrDeBotonesConListener.add(btn7);
        btn8 = (Button) findViewById(R.id.btn8);arrDeBotonesConListener.add(btn8);
        btn9 = (Button) findViewById(R.id.btn9);arrDeBotonesConListener.add(btn9);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);arrDeBotonesConListener.add(btnBorrar);
        btnCancelar= (Button) findViewById(R.id.btnCancelar);arrDeBotonesConListener.add(btnCancelar);
        btnMas = (Button) findViewById(R.id.btnMas);arrDeBotonesConListener.add(btnMas);
        btnMenos = (Button) findViewById(R.id.btnMenos);arrDeBotonesConListener.add(btnMenos);
        btnFinalizar = (Button) findViewById(R.id.btnFinalizar);arrDeBotonesConListener.add(btnFinalizar);
        btnProximaJugada = (Button) findViewById(R.id.btnFinalizar);arrDeBotonesConListener.add(btnFinalizar);
        txtDineroApostado = (TextView) findViewById(R.id.txtDineroApostado);
        for(Button boton : arrDeBotonesConListener)
        {
            boton.setOnClickListener(this);
        }

        //RECIBO LA JUGADA EN CASO DE ESTAR PREVIAMENTE CARGADA:
        String strJugadaRecibida = getIntent().getStringExtra("jugada");

        if(strJugadaRecibida != null)
        {
            if(!strJugadaRecibida.trim().equalsIgnoreCase(""))
            {
                System.out.println("strJugadaRecibida: " + strJugadaRecibida);
                try
                {
                    jugadaAsociada = (Jugada)JSONWS.solamenteConvertirDatosJSONaObjetoJava(strJugadaRecibida,Jugada.class);
                }
                catch (Exception e)
                {
                    Toast.makeText(this,"Error recibiendo jugada previa", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if(jugadaAsociada != null)
        {
            textoPantalla = jugadaAsociada.getNumeroApostado();
            indexDineroApostado = (int)(jugadaAsociada.getDineroApostado());
            Dream sueño = Controller.llenameElSueño(Integer.parseInt(jugadaAsociada.getNumeroApostado()));
            btnSueño.setText(String.valueOf(sueño.getNombre()));
            btnNumero.setText(jugadaAsociada.getNumeroApostado());
            System.out.println("indexDineroApostado: " +indexDineroApostado);
        }
        else
        {
            indexDineroApostado = Controller.porDefectoNicoExpress();
        }
        txtDineroApostado.setText(String.valueOf("$" + indexDineroApostado + ",00") );
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_nico_express_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
        Button botonPresionado = (Button) v;

        if(botonPresionado == btn0 || botonPresionado == btn1 || botonPresionado == btn2|| botonPresionado == btn3|| botonPresionado == btn4|| botonPresionado == btn5|| botonPresionado == btn6|| botonPresionado == btn7|| botonPresionado == btn8|| botonPresionado == btn9)
        {
            if(textoPantalla.trim().length()<3)
            {
                textoPantalla += botonPresionado.getText();
            }
        }
        else if(botonPresionado == btnBorrar)
        {
            if(textoPantalla.trim().length()> 1)
            {
                textoPantalla = textoPantalla.substring(0,textoPantalla.length() - 1 );
            }
            else
            {
                textoPantalla = "";
            }

        }
        else if(botonPresionado == btnCancelar)
        {
            this.finish();
        }
        else if(botonPresionado == btnMas)
        {
            if(indexDineroApostado < Controller.maximoNicoExpress())
            {
                indexDineroApostado ++;
            }
        }
        else if(botonPresionado == btnMenos)
        {
            if(indexDineroApostado > Controller.minimoNicoExpress() +1)
            {
                indexDineroApostado --;
            }
        }
        else if(botonPresionado == btnFinalizar)
        {
            Intent intentToManager = new Intent(this,ManagerNicoExpress.class);

            if( !btnNumero.getText().toString().equalsIgnoreCase("- - -") && btnNumero.getText().toString().length() >= 1)
            {
                String strNumeroApostado = btnNumero.getText().toString();

                Jugada nuevaJugada = new Jugada();
                nuevaJugada.setNumeroApostado(strNumeroApostado);
                nuevaJugada.setDineroApostado(indexDineroApostado);
                Controller.addJugadaVolatil(nuevaJugada);
                this.startActivity(intentToManager);
            }
            else
            {
                Toast.makeText(this,"Por favor juege a un numero.",Toast.LENGTH_SHORT).show();
            }
        }
        else if(botonPresionado == btnSueño)
        {
            Intent intentToDreams = new Intent(this, ActivityDreams.class);
            //pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
            startActivityForResult(intentToDreams, 1);
        }
        //EN 1 debido a que el sueño empieza con 2 cifras!!.
        if( textoPantalla.trim().length() > 1 && textoPantalla.trim().length()< 4)
        {
            Dream sueño = Controller.llenameElSueño(Integer.parseInt(textoPantalla));
            btnSueño.setText(String.valueOf(sueño.getNombre()));
        }
        else
        {
            btnSueño.setText(String.valueOf("***"));
        }

        txtDineroApostado.setText(String.valueOf("$" + indexDineroApostado + ",00"));
        btnNumero.setText(String.valueOf(textoPantalla));
    }
}
