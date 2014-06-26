package com.example.shooting2;

import java.util.ArrayList;

import android.graphics.Bitmap;

public class Animation {
	public Animation(int animationNo) {
		super();
		AnimationNo = animationNo;
	}

	int AnimationNo,Counter;

	static ArrayList<Bitmap[]> imgArr = new ArrayList<Bitmap[]>();	//アニメーション配列のリスト

	public static void add(Bitmap[] img){ imgArr.add(img);}			//リスト追加
	public static void remove(int index){}							//リスト削除
	public static Bitmap[] get(int index){ return imgArr.get(index);}//配列取得

	public Bitmap getImage(){			//画像取得
		return imgArr.get(AnimationNo)[Counter];
	}

	public int next(){
		int onf=0;
		if(Counter<imgArr.get(AnimationNo).length){
			Counter+=1;
		}else{
			Counter=0;
			onf=-1;
		}
		return onf;
	}
}

/* [[ Animationクラスをインポートして ]]
 * [[ 一行目のpackageは自分のsrcの自分のパッケージ名に変える ]]
 *
 * Bitmap[] img = { 画像1, 画像2, 画像3}; //アニメーション配列の作成
 * Animation.add(img); 					//アニメーション配列を追加
 * Animation.remove(アニメーション番号);		//アニメーション配列を削除
 * Animation.get(アニメーション番号);			//アニメーション配列取得
 * ---------------------------------------------------------
 * << 使用方法 >>
 * Animation anim = new Animation(アニメーション番号);//アニメーション作成
 *
 * anim.getImage();								//画像取得
 *
 * anim.next();									//次の画像に移動
 *
 * ↑nextはアニメーションの画像の最後に-1を返す。
 */
