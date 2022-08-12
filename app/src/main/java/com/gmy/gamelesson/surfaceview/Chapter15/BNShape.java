package com.gmy.gamelesson.surfaceview.Chapter15;
import javax.microedition.khronos.opengles.GL10;

public abstract class BNShape {
    boolean hiFlag=false;

    public abstract void drawSelf(GL10 gl);
    public abstract float[] findMinMax();
    public abstract float[] findMid();
    public abstract void setHilight(boolean flag);
}
