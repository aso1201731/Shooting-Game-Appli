package com.example.shooting2;

import android.graphics.Point;


public class Bullet {
	int bx, by; //弾の位置情報
	int speed, rotate;
	static int viewW, viewH;//画面の幅と高さ
	boolean isLive = false;//弾の生死
	int size;




	//移動メソッド
	public void move() {
		Point point = Math2D.CircumferencePoint(bx, by, speed, rotate);
		bx =  point.x;
		by =  point.y;


		//画面外の出たら弾が死ぬ
		if(bx > viewW + size || bx < -size) {
			isLive = false;
		}
		if(by > viewH +size || by < -size) {
			isLive = false;
		}
	}


}
