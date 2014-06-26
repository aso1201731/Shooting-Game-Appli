package com.example.shooting2;

import android.graphics.Point;

public class Math2D {
	static public double TwoPointsDistance(int ax, int ay, int bx, int by){//２点間の距離
		return Math.sqrt(Math.pow(bx-ax,2)+Math.pow(by-ay,2));
	}

	static public int PartnerAngle(int Myx, int Myy, int ax, int ay){//相手の角度
		double rad = Math.atan2(ay - Myy, ax - Myx);
		return (int)(rad * 180 / Math.PI);
	}

	static public int MoveAngle(int Myx, int Myy){//移動量から角度
		double rad = Math.atan2(Myy, Myx);
		return (int)(rad * 180 / Math.PI);
	}

	static public Point CircumferencePoint(int Myx, int Myy, int r, int rec){//円周上の座標
		int X = (int)(Myx+r*Math.sin(2 * Math.PI * (rec/360.0)));
		int Y = (int)(Myy+r*Math.cos(2 * Math.PI * (rec/360.0)));
		return new Point(X,Y); //ポイントで返す
	}

	/*以下未実装
	static public Point LineIntersect(){//２線分の交点

	}

	static public Point LineIntersection(){//２線分が交差しているか

	}
	*/
}
/*
○Math2Dクラス
・2点を直線で結んだ距離を返す
TwoPointsDistance(点Aのx, 点Aのy, 点Bのx, 点Bのy)

・相手の座標の角度を返す
PartnerAngle()

・移動方向(角度)を返す
MoveAngle()

・円周上の座標を返す
CircumferencePoint()

・2つの直線の交点を返す
LineIntersect()

・2つの直線が交差しているかを返す
LineIntersection()

*/
