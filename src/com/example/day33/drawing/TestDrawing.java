package com.example.day33.drawing;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.example.day33.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;

public class TestDrawing extends Activity implements OnClickListener {
	ImageView iv;
	int startX;
	int startY;

	Bitmap bmSrc;
	Bitmap bmCopy;
	Paint paint;
	Canvas canvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b4_drawing);
		iv = (ImageView) findViewById(R.id.b4_drawing);
		// 初始化画画板
		initDrawing();

		// 设置画笔颜色
		findViewById(R.id.b4_red).setOnClickListener(this);
		findViewById(R.id.b4_green).setOnClickListener(this);
		findViewById(R.id.b4_blue).setOnClickListener(this);
		findViewById(R.id.b4_brush).setOnClickListener(this);
		findViewById(R.id.b4_save).setOnClickListener(this);

		// 设置触摸侦听
		iv.setOnTouchListener(new OnTouchListener() {
			// 触摸屏幕是，触摸事件产生时此方法调用
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				// 用户手指摸到屏幕
				case MotionEvent.ACTION_DOWN:
					Log.d("OnTouchListener", "触摸屏幕");
					startX = (int) event.getX();
					startY = (int) event.getY();
					break;
				// 用户手指正在滑动
				case MotionEvent.ACTION_MOVE:
					Log.d("OnTouchListener", "正在滑动");
					int x = (int) event.getX();
					int y = (int) event.getY();
					Log.d("event.xy", x + ";" + y);
					canvas.drawLine(startX, startY, x, y, paint);
					// 每次绘制完毕之后，本次绘制的结束坐标变为下次的初始坐标
					startX = x;
					startY = y;
					iv.setImageBitmap(bmCopy);

					break;
				// 用户手指离开屏幕
				case MotionEvent.ACTION_UP:
					Log.d("OnTouchListener", "离开屏幕");
					break;
				default:
					break;
				}
				// 置为true：告诉系统这个触摸事件由我来处理,消费,false:不处理，这时系统会把触摸事件传递给ImageView的父节点
				return true;
			}
		});
	}

	public void initDrawing() {
		// 加载画画板的背景图
		bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		// 创建背景图的副本,白纸
		bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(),
				bmSrc.getConfig());
		// 笔
		paint = new Paint();
		// 板
		canvas = new Canvas(bmCopy);
		// 绘制
		canvas.drawBitmap(bmSrc, new Matrix(), paint);

		iv.setImageBitmap(bmCopy);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b4_red:
			paint.setColor(Color.RED);
			break;
		case R.id.b4_green:
			paint.setColor(Color.GREEN);
			break;
		case R.id.b4_blue:
			paint.setColor(Color.BLUE);
			break;
		case R.id.b4_brush:
			paint.setStrokeWidth(7);
			break;
		case R.id.b4_save:
			File file = new File("sdcard/drawing.png");
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			bmCopy.compress(CompressFormat.PNG, 100, fos);
			
			//发送SD卡就绪广播
			Intent intent = new Intent();
			intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
			intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
			sendBroadcast(intent);
			break;
		default:
			break;
		}
	}

}
