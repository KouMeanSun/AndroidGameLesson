package com.gmy.gamelesson.surfaceview.Chapter13;
import static com.gmy.gamelesson.surfaceview.Chapter13.Constant2.*;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

public class LogicalBall2 {
    float vx;//球在X轴方向的速度
    float mass;//球的质量
    float timeLive=0;//球的运动总时间
    BallForDraw2 ball;	//球

    //球的起始位置
    float startX;
    float startY=0;
    float startZ=0;

    //当前位置
    float currentX;
    float currentY;
    float currentZ;

    float xAngle=0;//球转动角度

    Ball_Go_Thread bgt;
    public LogicalBall2(BallForDraw2 ball,float startX,float vx,float mass)
    {
        this.ball=ball;
        this.mass=mass;
        this.startX=startX;
        this.vx=vx;

        currentX=startX;
        currentY=startY+SCALE;
        currentZ=startZ;
    }

    public void drawSelf(GL10 gl)
    {
        gl.glEnable(GL10.GL_BLEND);//开启混合
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        gl.glPushMatrix();
        gl.glTranslatef(currentX, currentY, currentZ);
        gl.glRotatef(xAngle, 0, 0, 1);
        ball.drawSelf(gl);
        gl.glPopMatrix();
    }

    public void move(ArrayList<LogicalBall2> ballAl)
    {
        timeLive=timeLive+TIME_SPAN;
        currentX=startX+vx*timeLive;

        //计算是否碰撞
        for(int i=0;i<ballAl.size();i++)
        {
            LogicalBall2 bfcL=ballAl.get(i);
            if(bfcL!=this)
            {
                float distance=Math.abs(this.currentX-bfcL.currentX);//获取两个球心的距离
                this.xAngle=(float) -Math.toDegrees(currentX/SCALE);
                bfcL.xAngle=(float) Math.toDegrees(currentX/SCALE);
                float vPreviousA=this.vx;
                float vPreviousB=bfcL.vx;
                if(distance<2*SCALE)//判断球心距是否小于两球半径和，若小于则发生碰撞
                {
                    this.vx=vPreviousA-2*bfcL.mass*(vPreviousA-vPreviousB)/(this.mass+bfcL.mass);
                    bfcL.vx=vPreviousB-2*this.mass*(vPreviousB-vPreviousA)/(this.mass+bfcL.mass);
                    this.startX=this.currentX;
                    bfcL.startX=bfcL.currentX;
                    this.timeLive=0;
                    bfcL.timeLive=0;
                }
            }
        }
    }
}
