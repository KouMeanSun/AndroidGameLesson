package com.gmy.gamelesson.surfaceview.Chapter13;
import java.util.List;
import com.gmy.gamelesson.surfaceview.Chapter13.Constant.*;

import static com.gmy.gamelesson.surfaceview.Chapter13.Constant.MIN_SPEED;

public class Ball_Go_Thread  extends Thread
{
    List<LogicalBall> albfc;

    public Ball_Go_Thread(List<LogicalBall> albfc)
    {
        this.albfc=albfc;
    }

    public void run(){
        while(Constant.THREAD_FLAG)
        {
            for(LogicalBall lb:albfc)
            {//循环控制每一个球
                if(lb.vy>MIN_SPEED||lb.state==0||lb.state==1)
                {
                    lb.move();
                }
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