package com.example.adailson.formasinteracao;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    GLSurfaceView superficieDesenho = null;
    Renderizador render = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //valida a var de referencia para a superficie
        superficieDesenho = new GLSurfaceView(this);

        //Valida a var de referencia para o renderizador
        render = new Renderizador();

        //Associa o renderizador a superficie de desenho da tela.
        superficieDesenho.setRenderer(render);

        superficieDesenho.setOnTouchListener(render);

        //Publica a sup de desenho na tela do app
        setContentView(superficieDesenho);
    }
}
