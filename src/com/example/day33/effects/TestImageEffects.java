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
			// ����
			canvas.drawBitmap(bmSrc, mt, paint);
			canvas.drawLine(10, 10, 110, 110, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_translate:
			paintEffects();
			// ƽ��
			mt.setTranslate(20, 40);
			canvas.drawBitmap(bmSrc, mt, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_scale:
			paintEffects();
			// ���ţ�ˮƽ�ʹ�ֱ��������ű�����1Ϊ����
			// mt.setScale(2, 0.5f);
			mt.setScale(0.5f, 0.5f, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
			canvas.drawBitmap(bmSrc, mt, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_rotate:
			paintEffects();
			// ��ת
			mt.setRotate(45, bmCopy.getWidth() / 2, bmCopy.getHeight() / 2);
			canvas.drawBitmap(bmSrc, mt, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_mirror:
			paintEffects();
			// ����
			mt.setScale(-1, 1);
			mt.setTranslate( bmCopy.getWidth(), 0);
			canvas.drawBitmap(bmSrc, mt, paint);
			copy.setImageBitmap(bmCopy);
			break;
		case R.id.b3_inverted:
			paintEffects();
			// ��Ӱ
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
		// ���������ֻ����
				bmSrc = BitmapFactory.decodeFile(Environment
						.getExternalStorageDirectory() + "/imagecopy.jpg");
				// ����ͼƬ����
				// 1.���ڴ��д���һ����ԭͼһģһ����С��bitmap����,������ԭͼ��Сһ�µİ�ֽ
				bmCopy = Bitmap.createBitmap(bmSrc.getWidth(),
						bmSrc.getHeight(), bmSrc.getConfig());
				// 2.�������ʶ���
				paint = new Paint();
				// 3.�����������
				canvas = new Canvas(bmCopy);
				// 4.��ʼ��������ԭͼ�����ݻ����ڰ�ֽ��
				mt = new Matrix();

				src.setImageBitmap(bmSrc);
				copy.setImageBitmap(bmCopy);
	}
}
