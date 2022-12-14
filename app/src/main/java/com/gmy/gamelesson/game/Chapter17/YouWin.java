package com.gmy.gamelesson.game.Chapter17;


import static com.gmy.gamelesson.game.Chapter17.Constant.*;
import static com.gmy.gamelesson.game.Chapter17.Constant.soundFlag;
import static com.gmy.gamelesson.game.Chapter17.Constant.winAndLose;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gmy.gamelesson.R;

public class YouWin extends SurfaceView implements SurfaceHolder.Callback{
	
	MainActivity activity;
	int screenWidth = 320;//??Ļ????
	int screenHeight = 480;//??Ļ?߶?
	
	Bitmap uwin;	
	Bitmap ulose;
	public YouWin(MainActivity activity) {
		super(activity);
		
		getHolder().addCallback(this);	
		
		this.activity=activity;		
		// TODO Auto-generated constructor stub
		initBitemap();
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		switch(event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			int x = (int) event.getX();
			int y = (int) event.getY();
			if(x>5&&x<35&&y>450&&y<490)
			{
				//Log.d("sss","ddfdfgdfgdgfhgghgjhjhjhjhkjkklkfdf");
				activity.toAnotherView(START_GAME);
				soundFlag=true;
				activity.initSound();
			}
			else if(x>285&&x<315&&y>450&&y<490)
			{
				//Log.d("sss","dfdf");
				activity.toAnotherView(ENTER_MENU);
				soundFlag=true;
				threadFlag=true;
			}
			break;
		}
		return true;
	}
	private void initBitemap() {
		// TODO Auto-generated method stub
		
		uwin = BitmapFactory.decodeResource(getResources(), R.mipmap.win);
		ulose=BitmapFactory.decodeResource(getResources(), R.mipmap.lose);
	}
	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.draw(canvas);
		switch(winAndLose)
		{
		case 0:canvas.drawBitmap(uwin,0,0, null);break;
		case 1:canvas.drawBitmap(ulose, 0, 0,null);break;
		}
	}		
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Canvas canvas = holder.lockCanvas();//??ȡ????
		try{
			synchronized(holder){
				draw(canvas);//????
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
