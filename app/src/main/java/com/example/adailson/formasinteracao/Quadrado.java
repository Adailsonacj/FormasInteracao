package com.example.adailson.formasinteracao;

import javax.microedition.khronos.opengles.GL10;

public class Quadrado extends Geometria {

    private int tamanhoFixo = 200;

    public Quadrado(GL10 gl) {
        super(gl);
        float[] co = new float[]{
                -50, 50,
                -50, -50,
                50, 50,
                50, -50
        };
        super.setCoordenadas(co);
    }

    @Override
    public void desenha() {
        super.getGl().glLoadIdentity();
        super.getGl().glTranslatef(super.getPosX(), super.getPosY(), 0);
        super.getGl().glRotatef(super.getAnguloRotacao(), 0, 0, 1);
        super.getGl().glScalef(super.getScaleX(), super.getScaleY(), 1);
        super.getGl().glColor4f(super.red, super.green, super.blue, super.alpha);
        super.getGl().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    }
}
