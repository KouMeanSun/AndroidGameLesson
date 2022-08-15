package com.gmy.gamelesson.game.Chapter18;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gmy.gamelesson.R;

public class LoadSurfaceView extends SurfaceView
implements SurfaceHolder.Callback {

	GL_Demo myActivity;
	
	int screenWidth = 320;		//��Ļ����
	int screenHeight = 480;		//��Ļ�߶�
	
	int picWidth=112;			//���ذ�ťͼƬ����
	int picHeight=40;			//���ذ�ťͼƬ�߶�
	
	Bitmap bgAbout;				//����ͼƬ
	
	public LoadSurfaceView(GL_Demo myActivity) {		
		super(myActivity);
		
		this.myActivity=myActivity;
		
		initBitmap();			//����ͼƬ
		
		this.getHolder().addCallback(this);		//���ûص�����
		
	} 
	public void initBitmap()	//��ʼ��ͼƬ
	{ 
		//���ر���ͼƬ
		bgAbout=BitmapFactory.decodeResource(getResources(), R.mipmap.load);
	}
	
	//����ͼ��
	@Override
	public void draw(Canvas canvas)
	{
		super.draw(canvas);
		canvas.drawBitmap(bgAbout, 0, 0,null);	//������ͼƬ
	}
	
	//view�ı��ʱ�����
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	
	//view������ʱ�����
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		
		Canvas canvas=null;		//����
		
		try
		{
			canvas=holder.lockCanvas();		//��ͼ֮ǰ����������
			
			synchronized(holder)
			{
				draw(canvas);					//����onDraw()����
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
				holder.unlockCanvasAndPost(canvas);		//��ͼ���֮�����������
			}
		}
	}

	//view���ٵ�ʱ�����
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		
	}
	
	 @Override
	   public boolean onKeyDown(int keyCode,KeyEvent event)		//Ϊ�������Ӽ���
	   { 
			  switch(keyCode)
			  {     
				   case KeyEvent.KEYCODE_BACK:					//������·��ؼ�
						
				   myActivity.setMenuView();					//�л������˵�����
				
				   return true;
			  }
			  
		   return false;										//false��������������ϵͳ����
	   }
}
