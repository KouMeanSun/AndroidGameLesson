package com.gmy.gamelesson.game.Chapter16;
import java.util.List;
import static com.gmy.gamelesson.game.Chapter16.Constant.*;

public class Ball_Go_Thread extends Thread
{
    List<LogicalBall> albfc;

    public Ball_Go_Thread(List<LogicalBall> albfc)
    {
        this.albfc=albfc;
    }

    public void run(){
        while(BALL_GO_FLAG)
        {
            for(LogicalBall lb:albfc)
            {//循环控制每一个球
                lb.move();
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