package com.gmy.gamelesson.game.Chapter18;

import javax.microedition.khronos.opengles.GL10;

import static com.gmy.gamelesson.game.Chapter18.Constant.*;

public class Tree
{
	Ball ball; //��������
	Column column;//��������
	
	public Tree(float scall,int leafId, int trunkId)
	{
		 ball=new Ball(scall*UNIT_SIZE,leafId);//Ϊ����ָ������
		 column=new Column(2*scall*UNIT_SIZE,0.2f*scall*UNIT_SIZE,trunkId);//Ϊ����ָ������
	}
	public void drawSelf(GL10 gl)
	{
		gl.glPushMatrix();//������ǰ����
		gl.glTranslatef(0, column.height*UNIT_SIZE/2, 0);//ƽ��
		column.drawSelf(gl);//��������
		gl.glTranslatef(0, column.height*UNIT_SIZE/2, 0);//ƽ��
		gl.glTranslatef(0,ball.radius*UNIT_SIZE/2-0.2f, 0);//ƽ��
		ball.drawSelf(gl);//��������
		gl.glPopMatrix();//�ָ�֮ǰ�����ľ���
		
	}
}