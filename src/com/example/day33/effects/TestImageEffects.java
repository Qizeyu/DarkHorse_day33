package com.example.day33.effects;

import com.example.day33.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class TestImageEffects extends Activity implements OnClickListener {
	Bitmap bmSrc;
	Bitmap bmCopy;
	Paint paint;
	Canvas canvas;
	Matrix mt;
	ImageView src;
	ImageView copy;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b3_effects);
		src = (ImageView) findViewById(R.id.b3_src);
		copy = (ImageView) findViewById(R.id.b3_copy);
		findViewById(R.id.b3_drawLine).setOnClickListener(this);
		findViewById(R.id.b3_translate).setOnClickListener(this);
		findViewById(R.id.b3_scale).setOnClickListener(this);
		findViewById(R.id.b3_rotate).setOnClickListener(this);
		findViewById(R.id.b3_mirror).setOnClickListener(this);
		findViewById(R.id.b3_inverted).setOnClickListener(this);
		paintEffects();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b3_drawLine:
			paintEffects();
			// 画线
			canvas.drawBitmap(bmSrc, mt, paint);
			canvas.drawLine(10, 10, 110, 110, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_translate:
			paintEffects();
			// 平移
			mt.setTranslate(20, 40);
			canvas.drawBitmap(bmSrc, mt, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_scale:
			paintEffects();
			// 缩放，水平和垂直方向的缩放比例，1为不变
			// mt.setScale(2, 0.5f);
			mt.setScale(0.5f, 0.5f, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
			canvas.drawBitmap(bmSrc, mt, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_rotate:
			paintEffects();
			// 旋转
			mt.setRotate(45, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
			canvas.drawBitmap(bmSrc, mt, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_mirror:
			paintEffects();
			// 镜面
			mt.setScale(-1, 1);
			mt.setTranslate( bmCopy.getWidth(), 0);
			canvas.drawBitmap(bmSrc, mt, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_inverted:
			paintEffects();
			// 倒影
			mt.setScale(1, -1);
			mt.postTranslate(0, bmCopy.getHeight());
			canvas.drawBitmap(bmSrc, mt, paint);
			copy.setImageBitmap(bmCopy);
			break;
		default:
			break;
		}
	}
	public void paintEffects(){
		// 这个对象是只读的
				bmSrc = BitmapFactory.decodeFile(Environment
						.getExternalStorageDirectory() + "/imagecopy.jpg");
				// 创建图片副本
				// 1.在内存中创建一个与原图一模一样大小的bitmap对象,创建与原图大小一致的白纸
				bmCopy = Bitmap.createBitmap(bmSrc.getWidth(),
						bmSrc.getHeight(), bmSrc.getConfig());
				// 2.创建画笔对象
				paint = new Paint();
				// 3.创建画板对象
				canvas = new Canvas(bmCopy);
				// 4.开始作画，把原图的内容绘制在白纸上
				mt = new Matrix();

				src.setImageBitmap(bmSrc);
				copy.setImageBitmap(bmCopy);
	}
}
