package com.gmy.gamelesson.surfaceview.Chapter13;
import java.util.ArrayList;

public class Ball_Go_Thread2 extends Thread
{
    ArrayList<LogicalBall2> albfc;

    public Ball_Go_Thread2(ArrayList<LogicalBall2> albfc)
    {
        this.albfc=albfc;
    }

    public void run(){
        while(Constant.THREAD_FLAG)
        {
            for(LogicalBall2 lb:albfc)
            {//循环控制每一个球
                lb.move(albfc);
            }

            try{
                sleep(50);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}