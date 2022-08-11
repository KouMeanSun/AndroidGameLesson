package com.gmy.gamelesson.surfaceview.Chapter13;
import java.util.ArrayList;
import static com.gmy.gamelesson.surfaceview.Chapter13.Constant3.*;

public class Ball_Go_Thread3  extends Thread
{
    ArrayList<LogicalBall3> albfc;
    //Collision cu;

    public Ball_Go_Thread3(ArrayList<LogicalBall3> albfc)
    {
        this.albfc=albfc;
        //cu=new Collision();
    }

    public void run(){
        while(Constant.THREAD_FLAG)
        {
            for(LogicalBall3 lb:albfc)
            {//循环控制每一个球
                lb.move(albfc,ANERGY_LOSE);
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