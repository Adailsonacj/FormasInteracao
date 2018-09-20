package com.example.adailson.formasinteracao;

import javax.microedition.khronos.opengles.GL10;

public class Triangulo extends Geometria {

    private int tamanhoFixo = 200;

    public Triangulo(GL10 gl) {
        super(gl);
        float[] co1 = new float[]{
                0, 0,
                tamanhoFixo , 0,
                tamanhoFixo / 2, tamanhoFixo/2
        };
        super.setCoordenadas(co1);
    }

    @Override
    public void desenha() {
        super.getGl().glLoadIdentity();
        super.getGl().glTranslatef(super.getPosX(), super.getPosY(), 0);
        super.getGl().glRotatef(super.getAnguloRotacao(), 0, 0, 1);
        super.getGl().glScalef(super.getScaleX(), super.getScaleY(), 1);
        //super.getGl().glColor4f(1, 0, 0, 1);
        super.getGl().glVertexPointer(2, GL10.GL_FLOAT, 0, super.getBuffer());
        super.getGl().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 3);
    }
}
