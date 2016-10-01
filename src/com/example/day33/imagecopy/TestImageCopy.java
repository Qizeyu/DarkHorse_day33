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
		//���������ֻ����
		Bitmap bmSrc =BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/imagecopy.jpg");
		//����ͼƬ����
		//1.���ڴ��д���һ����ԭͼһģһ����С��bitmap����,������ԭͼ��Сһ�µİ�ֽ
		Bitmap bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(), bmSrc.getConfig());
		//2.�������ʶ���
		Paint paint = new Paint();
		//3.�����������
		Canvas canvas = new Canvas(bmCopy);
		//4.��ʼ��������ԭͼ�����ݻ����ڰ�ֽ��
		canvas.drawBitmap(bmSrc, new Matrix(), paint);
		
		ImageView src = (ImageView) findViewById(R.id.b2_src);
		ImageView copy = (ImageView) findViewById(R.id.b2_copy);
		src.setImageBitmap(bmSrc);
		copy.setImageBitmap(bmCopy);
	}

}
