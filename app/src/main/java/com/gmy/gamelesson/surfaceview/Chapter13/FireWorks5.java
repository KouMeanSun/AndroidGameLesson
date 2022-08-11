package com.gmy.gamelesson.surfaceview.Chapter13;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

//代表焰火粒子系统的类
public class FireWorks5 {
    //用于绘制的各个纹理粒子组成的数组
    static ParticleForDraw5[] pfdArray;

    //所有焰火粒子的列表
    ArrayList<SingleParticle5> al=new ArrayList<>();
    //定时运动所有焰火粒子的线程
    FireWorksThread5 fwt;
    public FireWorks5(int[] texId)
    {
        //对用于绘制的各个纹理粒子进行初始化
        pfdArray=new ParticleForDraw5[]
                {
                        new ParticleForDraw5(texId[0],0.05f),
                        new ParticleForDraw5(texId[1],0.05f),
                        new ParticleForDraw5(texId[2],0.05f),
                        new ParticleForDraw5(texId[3],0.05f),
                        new ParticleForDraw5(texId[4],0.05f),
                        new ParticleForDraw5(texId[5],0.05f)
                };


        //初始化定时运动所有焰火粒子的线程并启动
        fwt=new FireWorksThread5(this);
        fwt.start();
    }

    public void drawSelf(GL10 gl)
    {
        //开启混合
        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA,GL10.GL_ONE_MINUS_SRC_ALPHA);

        int size=al.size();
        //循环扫描所有焰火粒子的列表并绘制各个粒子
        for(int i=0;i<size;i++)
        {
            try
            {
                al.get(i).drawSelf(gl);
            }
            catch(Exception e)
            {
            }
        }

        //关闭混合
        gl.glDisable(GL10.GL_BLEND);
    }
}
