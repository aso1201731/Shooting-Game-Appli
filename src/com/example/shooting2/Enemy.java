package com.example.shooting2;

import android.graphics.Point;

public class Enemy {
	int ex = 70, ey = 10; //位置情報
	double sx, sy; //速度
	public int imgNo;
	boolean EnemyFlg = true;
	int size, size2;
	static int viewW, viewH;
	int esize;


	//座標
	public void init(float initx, float inity, double sx, double sy) {
		ex = (int) initx;
		ey = (int) inity;
		this.sx = sx;
		this.sy = sy;


	}



	//移動メソッド
	public void move(int speed, int rotate) {
		Point point = Math2D.CircumferencePoint(ex, ey, speed, rotate);
		ex =  + point.x;
		ey =  + point.y;


		//画面外の出たら弾が死ぬ
		if(ex > viewW + esize || ex < -esize) {
			EnemyFlg = false;
		}
		if(ey > viewH + esize || ey < -esize) {
			EnemyFlg = false;
		}
	}

}
