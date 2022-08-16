package com.gmy.gamelesson.game.Chapter19;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.gmy.gamelesson.R;

public class SoundSurfaceView extends SurfaceView
implements SurfaceHolder.Callback {
	GL_Demo myActivity;			//activity������
	int screenWidth = 480;		//��Ļ���
	int screenHeight = 320;		//��Ļ�߶�
	Bitmap background;			//����ͼƬ

	public SoundSurfaceView(GL_Demo myActivity) {
		super(myActivity);						//���ø��෽��
		this.myActivity=myActivity;
		initBitmap();							//��ʼ��ͼƬ
		this.getHolder().addCallback(this);		//���ûص�����
	}
	
	public void initBitmap(){
		//��ʼ��ͼƬ
		background=BitmapFactory.decodeResource(getResources(), R.mipmap.soundbg);
	}
	
	@Override
	public void draw(Canvas canvas){
		super.draw(canvas);
	    canvas.drawBitmap(background, 0, 0, null);//���Ʊ���
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();//�����Ļ�����ص�x����
		int y = (int) event.getY();//�����Ļ�����ص�y����
		switch(event.getAction()){
			case MotionEvent.ACTION_DOWN:		//����ʱ	
//				if(x<32&&x>0&&y<screenHeight&&y>screenHeight-32){
//					myActivity.isSound=true;	//���Ƿ񲥷�������־��Ϊtrue
//					myActivity.mpBack.start();	//������Ϸ��������
//					myActivity.setMenuView();	//�л����˵���
//				}
//				else if(x<screenWidth&&x>screenWidth-32				//�ر�����
//				    	  &&y<screenHeight&&y>screenHeight-32)
//				{
//					myActivity.isSound=false;	//���Ƿ񲥷�������־��Ϊfalse
//					myActivity.mpBack.pause();	//��ͣ��Ϸ��������
//					myActivity.setMenuView();	//�л������˵���
//				}

				myActivity.isSound=true;	//���Ƿ񲥷�������־��Ϊtrue
				myActivity.mpBack.start();	//������Ϸ��������
				myActivity.setMenuView();

				break;
		}
		return true;
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Canvas canvas=null;				//����
		try{
			canvas=holder.lockCanvas();//��������
			synchronized(holder){
				draw(canvas);			//�ػ�
			}
		}catch(Exception e){e.printStackTrace();}
		finally{
			if(canvas!=null){
				holder.unlockCanvasAndPost(canvas);//���ƺ����
			}
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {}		//view���ٵ�ʱ�����
	
	 @Override
	 public boolean onKeyDown(int keyCode,KeyEvent event)		//Ϊ������Ӽ���
	 { 
		  switch(keyCode)
		  {     
			   case KeyEvent.KEYCODE_BACK:						//������·��ؼ�
				   	myActivity.setMenuView();					//�л������˵�����				   	
				   	return true;
			   case KeyEvent.KEYCODE_DPAD_CENTER:				//��������м�
				   	myActivity.isSound=false;					//���Ƿ񲥷�������־��Ϊfalse
					myActivity.mpBack.pause();					//��ͣ��Ϸ��������
					myActivity.setMenuView();					//�л������˵���
					return true;
		  }
		  return false;											//������������ϵͳ����
	 }
}