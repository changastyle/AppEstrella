package com.example.nicog.test.Threads;

        import android.os.AsyncTask;
        import android.view.View;
        import android.widget.GridView;

        import com.example.nicog.test.PickAGame;
        import com.example.nicog.test.R;
        import com.example.nicog.test.Util.AdaptadorDeJuegos;
        import com.example.nicog.test.controller.Controller;
        import com.example.nicog.test.model.Juego;

        import java.util.ArrayList;

public class ThreadPideJuegos extends AsyncTask
{
    private PickAGame pickAGame;
    private ArrayList<Juego> arrJuegos;
    private GridView gridViewGames;
    private AdaptadorDeJuegos adaptador;
    public ThreadPideJuegos(PickAGame pickAGame)
    {
        this.pickAGame = pickAGame;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        adaptador = new AdaptadorDeJuegos(pickAGame,Controller.getArrDeJuegos());
    }

    @Override
    protected Object doInBackground(Object[] params)
    {
        arrJuegos = Controller.pedirJuegos();
        return arrJuegos;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        pickAGame.findViewById(R.id.barraEsperandoJuegos).setVisibility(View.GONE);
        gridViewGames = (GridView) pickAGame.findViewById(R.id.gridViewGames);
        adaptador = new AdaptadorDeJuegos(pickAGame,Controller.getArrDeJuegos());
        gridViewGames.setAdapter(adaptador);
        //pickAGame.tePasoLosJuegos(arrJuegos);
    }

    //GYS:
    public PickAGame getPickAGame()
    {
        return pickAGame;
    }
    public void setPickAGame(PickAGame pickAGame)
    {
        this.pickAGame = pickAGame;
    }
    @Override
    public String toString()
    {
        return "ThreadPideJuegos{" +
                "pickAGame=" + pickAGame +
                '}';
    }
}
