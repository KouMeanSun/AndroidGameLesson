package com.gmy.gamelesson.game.Chapter18;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gmy.gamelesson.R;

public class WinSurfaceView extends SurfaceView
implements SurfaceHolder.Callback {

	GL_Demo myActivity;
	
	int screenWidth = 320;		//屏幕宽度
	int screenHeight = 480;		//屏幕高度
	
	int picWidth=112;			//返回按钮图片宽度
	int picHeight=40;			//返回按钮图片高度
	
	Bitmap bgAbout;				//背景图片
	Bitmap gameBack;			//返回按钮图片
	
	boolean soundActivity=true;	//绘制声音提示的界面的标志
	boolean soundMarker=false;	//是否打开声音标志
	
	public WinSurfaceView(GL_Demo myActivity) {		
		super(myActivity);
		
		this.myActivity=myActivity;
		
		initBitmap();			//加载图片
		
		this.getHolder().addCallback(this);		//设置回调方法
		
	} 
	public void initBitmap()	//初始化图片
	{ 
		//加载背景图片
		bgAbout=BitmapFactory.decodeResource(getResources(), R.mipmap.win);
		
		//加载返回按钮图片
		gameBack=BitmapFactory.decodeResource(getResources(), R.mipmap.back);
	}
	
	//绘制图形
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		canvas.drawBitmap(bgAbout, 0, 0,null);	//画背景图片
		
		//画返回按钮图片
		canvas.drawBitmap(gameBack, (screenWidth-(1*picWidth)), screenHeight-2*picHeight,null);
	}
	
	//view改变的时候调用
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	
	//view创建的时候调用
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		Canvas canvas=holder.lockCanvas();		//画图之前先锁定画布
		
		try
		{
			synchronized(holder)
			{
				draw(canvas);					//调用onDraw()方法
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas!=null)
			{
				holder.unlockCanvasAndPost(canvas);		//画图完成之后给画布解锁
			}
		}
	}

	//view销毁的时候调用
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}

	//触摸事件回调方法
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		int x = (int) event.getX();			//触点x坐标
		int y = (int) event.getY();			//触点y坐标

		switch(event.getAction())
		{
			case MotionEvent.ACTION_DOWN:
				
			if(x<(screenWidth-(1*picWidth))+picWidth&&x>(screenWidth-(1*picWidth))
	    	  &&y<screenHeight-2*picHeight+picHeight&&y>screenHeight-2*picHeight)
			{//当触点在返回按钮图片上时
				
				myActivity.msv=new MySurfaceView(myActivity);
				
				myActivity.setMenuView();		//切换到主菜单界面
				
				if(myActivity.isSound)			//是否打开了声音	
				{
					myActivity.mpBack.start();		//播放背景音乐
					
					myActivity.mpWin.pause();		//停止胜利音乐	
				}			
			}
			
			break;
			}
    	
		return super.onTouchEvent(event);
	}
	
	 @Override
	   public boolean onKeyDown(int keyCode,KeyEvent event)		//为按键添加监听
	   { 
			  switch(keyCode)
			  {     
				   case KeyEvent.KEYCODE_BACK:					//如果按下返回键
						
				   myActivity.setMenuView();					//切换到主菜单界面
				
				   return true;
			  }
			  
		   return false;										//false，其他按键交给系统处理
	   }
		   
}

