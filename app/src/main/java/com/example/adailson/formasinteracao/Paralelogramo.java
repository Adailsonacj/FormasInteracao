package com.example.adailson.formasinteracao;

import javax.microedition.khronos.opengles.GL10;

public class Paralelogramo extends Geometria {

    private int tamanhoFixo = 200;

    public Paralelogramo(GL10 gl) {
        super(gl);
        float[] co = new float[]{
                0, 0,
                tamanhoFixo, 0,
                tamanhoFixo / 2, tamanhoFixo / 2,
                3 * tamanhoFixo / 2, tamanhoFixo / 2

        };
        super.setCoordenadas(co);
    }

    @Override
    public void desenha() {
        super.getGl().glLoadIdentity();
        super.getGl().glTranslatef(super.getPosX(), super.getPosY(), 0);
        super.getGl().glRotatef(super.getAnguloRotacao(), 0, 0, 1);
        super.getGl().glScalef(super.getScaleX(), super.getScaleY(), 1);
        super.getGl().glDrawArrays(GL10.GL_TRIANGLE_STRIP, 0, 4);
    }
}
