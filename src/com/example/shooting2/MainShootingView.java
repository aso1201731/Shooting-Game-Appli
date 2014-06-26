package com.example.shooting2;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.Animation;

public class MainShootingView extends SurfaceView implements SurfaceHolder.Callback, Runnable{

	SurfaceHolder gholder;
	Thread thread;
	Paint paint;
	Paint textPaint;
	Paint paintlife; //ライフ
	Canvas canvas;
	Bitmap img, img2, img3; //背景画像の箱

	long startTime, endTime;
	int ScreenX, ScreenY; //画面の幅と高さ
	int x , y;//背景用
	int a , b = -980;

	int frame;

	boolean Key = false;
	boolean Bug = true;


	Player player1;

	ArrayList<Bitmap> imgArr = new ArrayList<Bitmap>();
	Bullet[] bullet = new Bullet[30];

	ArrayList<Bitmap> EneArr = new ArrayList<Bitmap>();
	Enemy[] enemy = new Enemy[5];

	EnemyBullet[] enebullet = new EnemyBullet[20];

	ArrayList<Animation> anim = new ArrayList<Animation>();



	public class Player { //内部クラス
		public int px, py; //座標
		int level;
		int life = ScreenX; //ライフ
		boolean Plive = true;
		public Player(int x, int y) { //コンストラクタ：画面が作られた時
			super();
			this.px = x;
			this.py = y;

		}
	}

	public void shoot(int speed, int rotate, int x, int y) {
		for(int i = 0; i < bullet.length; i++) {
			if(bullet[i].isLive == false) {
				bullet[i].bx = x;
				bullet[i].by = y;
				bullet[i].speed = speed;
				bullet[i].rotate = rotate;
				bullet[i].isLive = true;
				break;
			}
		}
	}



	public void EneShoot(int speed, int rotate, int x, int y) {
		for(int i = 0; i < enebullet.length; i++) {
			if(enebullet[i].EBulletFlg == false) {
				enebullet[i].bx = x;
				enebullet[i].by = y;
				enebullet[i].speed = speed;
				enebullet[i].rotate = rotate;
				enebullet[i].EBulletFlg = true;
				break;
			}
		}
	}



	@Override
	public boolean onTouchEvent(MotionEvent event) { // タッチイベント
		player1.px = (int)event.getX();
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Key = true;
			player1.px = (int)event.getX();
			break;

		case MotionEvent.ACTION_MOVE:
			break;

		case MotionEvent.ACTION_UP:
			Key = false;
			break;
		case MotionEvent.ACTION_CANCEL:break;
		}
		return true;
	}



	//変更用定数
	static final int frameValue = 60;
	public MainShootingView(Context context) {// TODO 自動生成されたコンストラクター・スタブ
		super(context);
		getHolder().addCallback(this);


	}

	@Override
	public void run() {// 処理の実行
		Resources res = this.getContext().getResources();
		imgArr.add(BitmapFactory.decodeResource(res, R.drawable.jiki2));
		imgArr.add(BitmapFactory.decodeResource(res, R.drawable.bullet1));
		imgArr.add(BitmapFactory.decodeResource(res, R.drawable.ene1));
		imgArr.add(BitmapFactory.decodeResource(res, R.drawable.bakuani));
		imgArr.add(BitmapFactory.decodeResource(res, R.drawable.ic_lan));

		EneArr.add(BitmapFactory.decodeResource(res, R.drawable.ene1));
		EneArr.add(BitmapFactory.decodeResource(res, R.drawable.ene2));
		EneArr.add(BitmapFactory.decodeResource(res, R.drawable.ene3));
		EneArr.add(BitmapFactory.decodeResource(res, R.drawable.ene4));
		EneArr.add(BitmapFactory.decodeResource(res, R.drawable.ene5));
		EneArr.add(BitmapFactory.decodeResource(res, R.drawable.ene6));
		EneArr.add(BitmapFactory.decodeResource(res, R.drawable.ene7));
		EneArr.add(BitmapFactory.decodeResource(res, R.drawable.ene));


		anim.size();
		//anim.add(new Animation(0));
		//anim.add(Animation.add(BitmapFactory.decodeResource(res, R.drawable.bakuani)));


		img = BitmapFactory.decodeResource(res, R.drawable.back);
		img = Bitmap.createScaledBitmap(img, ScreenX, ScreenY, true);
		imgArr.add(img);

		//int w = img.getWidth();
		//int h = img.getWidth();

		img2 = BitmapFactory.decodeResource(res, R.drawable.kumo12);
		img2 = Bitmap.createScaledBitmap(img2, ScreenX, ScreenY, true);
		imgArr.add(img2);

		img3 = BitmapFactory.decodeResource(res, R.drawable.kumo12);
		img3 = Bitmap.createScaledBitmap(img3, ScreenX, ScreenY, true);
		imgArr.add(img3);

		while(true) {
			startTime = System.currentTimeMillis();
			canvas = gholder.lockCanvas();
			canvas.drawColor(Color.GRAY); //コメントにしたらトランザムモード 画面全体を指定した色にする
			canvas.drawText("フレームレート："+endTime, 0, 70, textPaint);
			canvas.drawText("" +Math2D.TwoPointsDistance(player1.px, player1.py, enemy[0].ex, enemy[0].ey), 200, 80, textPaint);
			canvas.drawText("座標:"+ ScreenX + "/" + ScreenY ,50, 90, textPaint);
			//Rect src = new Rect(768, 0, w+768, 0);
			//Rect dst = new Rect(0, 980, 0, 980 + h);
			canvas.drawBitmap(img, 0, 0, paint);



			//自機描写
			canvas.drawBitmap(imgArr.get(0), player1.px, player1.py, textPaint);
			//canvas.drawRect(0, 5, ScreenY, 20, paint);

			if(frame < 100) {
				frame += 1;
			} else {
				frame = 0;
			}
			if(Key == true && player1.level == 0) {
				if(frame%2 == 0) {
					int size = imgArr.get(0).getWidth();
					int size2 = imgArr.get(1).getWidth();
					int playerX = player1.px + (size  - size2) / 2;
					shoot(10, 180, playerX, player1.py );
					//shoot(10, 135, playerX, player1.py);
					//shoot(10, 225, playerX, player1.py);
				}


			}



			//弾描画
			for(int i = 0; i < bullet.length; i++) {
				if(bullet[i].isLive == true) {
					canvas.drawBitmap(imgArr.get(1), bullet[i].bx, bullet[i].by, paint);
					bullet[i].speed = bullet[i].speed + 20;
					bullet[i].move();
				}
			}

			canvas.drawRect(ScreenX, 0, 0, 0, paintlife); //ライフ描画


			if(player1.life > ScreenX){ // 自機ライフの上限設定
				player1.life = ScreenX;
			}



			//スクロール
			//canvas.drawBitmap(img2, 0, y, paint);
			y += 10;
			if(y == 980 ) {
				y = -980;
			}

			//canvas.drawBitmap(img3, 0, b, paint);
			b += 10;

			if(b == 980) {
				b = -980;
			}




			//敵の処理

			for(int i = 0; i < enebullet.length; i++) { //敵弾描画
				if(enebullet[i].EBulletFlg == true) {
					canvas.drawBitmap(imgArr.get(4),enebullet[i].bx , enebullet[i].by, paint);
					enebullet[i].speed = enebullet[i].speed;
					enebullet[i].move();

				 	//敵弾当たり判定
					enebullet[i].size = 50;
					if(Math2D.TwoPointsDistance(enebullet[i].bx, enebullet[i].by, player1.px, player1.py) < enebullet[i].size) {
						canvas.drawCircle(enebullet[i].bx, enebullet[i].by, player1.px, textPaint);

						enebullet[i].EBulletFlg = false;
					}
				}

			}

			//敵の弾

			if(enemy[0].EnemyFlg == true) {

				if(frame%4 == 0) {
					int size = EneArr.get(0).getWidth();
					int size2 = imgArr.get(4).getWidth();
					int EneBulletX = enemy[0].ex + (size  - size2) / 2;
					//EneShoot(10, 135, enebullet[0].bx, enemy[0].ey);
					//EneShoot(10,  45, enebullet[0].bx, enemy[0].ey);
					EneShoot(20,   0, enemy[0].ex, enemy[0].ey);
					EneShoot(10, -45, enemy[0].ex, enemy[0].ey);
					EneShoot(10,  45, enemy[0].ex, enemy[0].ey);
					//EneShoot(10, 180, enemy[0].ex, enemy[0].ey);
				}



			}



			//敵描写
			for(int i = 0; i < enemy.length ; i++) {
				int w = EneArr.get(enemy[i].imgNo).getWidth();//画像サイズ取得
				int h = EneArr.get(enemy[i].imgNo).getHeight();//画像サイズ取得
				Rect src = new Rect(0, 0, w, h);
				Rect dst = new Rect(enemy[i].ex, enemy[i].ey, enemy[i].ex + w*2, enemy[i].ey + h*2); //座標X, 座標Y,　座標X+サイズX, 座標Y＋サイズY

				if(enemy[i].EnemyFlg == true) {
					enemy[i].size2 = 100;
					enemy[i].size = 50;
					canvas.drawBitmap(EneArr.get(enemy[i].imgNo), src, dst, null);
					enemy[i].sy = enemy[i].sy -0.05;
					enemy[i].move(10, 0);
				}



				for(int b = 0; b < bullet.length; b++) {//弾衝突時のあたり判定
					if(Math2D.TwoPointsDistance(bullet[b].bx, bullet[b].by, enemy[i].ex, enemy[i].ey) < enemy[i].size) {
						enemy[i].EnemyFlg = false;
						if(enemy[i].EnemyFlg == false) {
							//anim.get(0);
							//anim.get(0).getImage();
							//anim.get(0).next();
							//anim.remove(0);

						}
					}
				}

			}
			gholder.unlockCanvasAndPost(canvas);

			//処理時間計測
			try {
				Thread.sleep(frameValue);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
			endTime = startTime-System.currentTimeMillis();
		}

	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { // 画面が変わったとき
		ScreenX = width;
		ScreenY = height;

		Bullet.viewW = ScreenX;
		Bullet.viewH = ScreenY;

		player1.px = ScreenX / 2;
		player1.py = ScreenY / 5 * 4;

		EnemyBullet.viewW = ScreenX;
		EnemyBullet.viewH = ScreenY;

		Enemy.viewW = ScreenX;
		Enemy.viewH = ScreenY;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {// 画面が作られた時
		gholder = getHolder();
		textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setColor(Color.WHITE);
		textPaint.setTextSize(18);


		paintlife = new Paint();
		paintlife.setStyle(Style.STROKE);
		paintlife.setStrokeWidth(50); //線の太さを設定
		paintlife.setARGB(255,100,100,255);

		paint = new Paint();
		paint.setStyle(Paint.Style.STROKE); //図形のスタイルを線に設定
		//paint.setARGB(100, 0, 255, ScreenY); //不透明な色にする トランザムの原因

		for(int i=0;i<bullet.length;i++){ //弾の配列作成
			bullet[i] = new Bullet();
		}

		for(int i = 0; i < enemy.length; i++) { //敵の配列作成
			enemy[i] = new Enemy();
		}

		for(int i = 0; i < enebullet.length; i++) {
			enebullet[i] = new EnemyBullet();
		}

		player1 = new Player(0, 0);
		player1.level = 0;
		thread = new Thread(this);
		thread.start();

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {// ゲーム終了時

	}

}
