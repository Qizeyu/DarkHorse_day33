package com.example.day33.imagecopy;

import com.example.day33.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
import android.widget.ImageView;

public class TestImageCopy extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b2_imagecopy);
		//这个对象是只读的
		Bitmap bmSrc =BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/imagecopy.jpg");
		//创建图片副本
		//1.在内存中创建一个与原图一模一样大小的bitmap对象,创建与原图大小一致的白纸
		Bitmap bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
		//2.创建画笔对象
		Paint paint = new Paint();
		//3.创建画板对象
		Canvas canvas = new Canvas(bmCopy);
		//4.开始作画，把原图的内容绘制在白纸上
		canvas.drawBitmap(bmSrc, new Matrix(), paint);
		
		ImageView src = (ImageView) findViewById(R.id.b2_src);
		ImageView copy = (ImageView) findViewById(R.id.b2_copy);
		src.setImageBitmap(bmSrc);
		copy.setImageBitmap(bmCopy);
	}

}
