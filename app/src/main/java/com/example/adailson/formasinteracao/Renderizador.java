package com.example.adailson.formasinteracao;

import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static java.lang.Math.random;

//essa classe implementa os métodos necessários para
//utilizar a biblioteca OPENGL no desenho gráfico que
// sera apresentado na tela pela superficie de desenho
class Renderizador implements GLSurfaceView.Renderer, View.OnTouchListener {

    private GL10 gl;
    private Triangulo tri;
    private Quadrado qua;
    private Paralelogramo para;
    private float esquerda = 0;
    private float direita = 0;
    private int posX = 0;
    private int posY = 0;
    private static int larguraX;
    private static int alturaY;
    private Geometria objMove = null;
    private ArrayList<Geometria> vetorGeo = new ArrayList();

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int largura, int altura) {
        this.gl = gl;
        this.alturaY = altura;
        this.larguraX = largura;
        //É chamado quando a superfície de desenho for alterada.
        //Configurando a área de cordenadas do plano cartesiano
        //MAtriz de projeção
        gl.glMatrixMode(gl.GL_PROJECTION);
        gl.glLoadIdentity();
        //Define o espaço de trabalho.
        //volume (CUBO 3D) de renderização - Tudo que for configurado dentro desse volume aparece na tela.
        //Definindo X - Y - Z , LARGURA - ALTURA - PROFUNDIDADE
        gl.glOrthof(0.0f, largura, 0.0f, altura, -1.0f, 1.0f);
        //OPENGL aponta para nova matriz (De transformações geométricas)
        //Translação, Rotação e Escala
        //Matriz de câmera
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glViewport(0, 0, largura, altura);

        qua = new Quadrado(gl);
//        qua.setScale(1.25f, 1.25f);
        qua.setCor(1, 0, 0, 1);
        qua.registraBuffer();


    }

    @Override
    public void onDrawFrame(GL10 gl) {
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        for (int i = 0; i < vetorGeo.size(); i++) {
//            vetorGeo.get(i).setPos(posX,posY);
            vetorGeo.get(i).registraBuffer();
//            vetorGeo.get(i).setAnguloRotacao(esquerda);
            vetorGeo.get(i).desenha();
        }

        //getObjeto(posX, posY).setPos(posX, posY);
        qua.setAnguloRotacao(direita);
        qua.setPos(posX, posY);
//        qua.desenha();
        direita += 5;
        esquerda -= 5;

        if (objMove != null) {
            objMove.setPos(posX, posY);
        }

    }

    public Geometria getObjeto(int posX, int posY) {
        int pontoBase = 0;


        for (int i = 0; i < vetorGeo.size(); i++) {
            if (posX > vetorGeo.get(i).getPosX() - 100 / 2 && posY > vetorGeo.get(i).getPosY() - 100 / 2) {
                return vetorGeo.get(i);
            }


//            if (pontoBase == 50) {
//                pontoBase = 0;
//            }
//            while (pontoBase < 50) {
//                pontoBase += 1;
//                if (posX + pontoBase == vetorGeo.get(i).getPosX()) {
//                    return vetorGeo.get(i);
//                }
//            }
        }
        return null;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            posX = (int) motionEvent.getX();
            posY = alturaY - (int) motionEvent.getY();
        }
        if (objMove == null) {
            if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                posX = (int) motionEvent.getX();
                posY = alturaY - (int) motionEvent.getY();
                Quadrado qua = new Quadrado(gl);
                qua.setPos(posX, posY);
                qua.setCor((float) random(), (float) random(), (float) random(), 1);
                vetorGeo.add(qua);
            }
        }
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            posX = (int) motionEvent.getX();
            posY = alturaY - (int) motionEvent.getY();

            objMove = new Quadrado(gl);
            objMove = this.getObjeto((int) posX, posY);
        }
        return true;
    }
}
