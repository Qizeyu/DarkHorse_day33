package com.example.day33.clothing;

import com.example.day33.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.ImageView;

public class TestClothing extends Activity {
	ImageView iv;

	Bitmap bmCopy;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b5_clothing);

		Bitmap bmSrc = BitmapFactory.decodeResource(getResources(),
				R.drawable.awaiyi);
		// 创建外衣图副本
		bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(),
				bmSrc.getConfig());
		Paint paint = new Paint();
		Canvas canvas = new Canvas(bmCopy);
		canvas.drawBitmap(bmSrc, new Matrix(), paint);

		iv = (ImageView) findViewById(R.id.b5_up);
		iv.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_MOVE:
					int x = (int) event.getX();
					int y = (int) event.getY();

					for (int i = -5; i <= 5; i++) {
						for (int j = -5; j <= 5; j++) {
							if (Math.sqrt(i * i + j * j) <= 5) {
								if (x + i < bmCopy.getWidth()
										&& y +j < bmCopy.getHeight() && x+i >= 0 && y+j >= 0) {
									// 把用户划过的坐标置为透明色,改变指定的像素颜色
									bmCopy.setPixel(x + i, y + j,
											Color.TRANSPARENT);
									iv.setImageBitmap(bmCopy);
								}
							}
						}
					}
					break;
				default:
					break;
				}
				return true;
			}
		});
	}
}
