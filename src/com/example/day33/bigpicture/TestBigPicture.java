package com.example.day33.bigpicture;

import com.example.day33.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class TestBigPicture extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b1_bigpicture);
		findViewById(R.id.b1_load).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b1_load:
			//����ͼƬʱ��Ҫʹ�õ��Ĳ�������װ�����������
			Options opt = new Options();
			//��Ϊ���������ڴ棬ֻ��ȡͼƬ���
			opt.inJustDecodeBounds = true;
			//λͼ����
			BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/world201401.jpg", opt);
			//�õ�ͼƬ���
			int imageWidth = opt.outWidth;
			int imageHeight = opt.outHeight;
			//�õ���Ļ���
			Display dp = getWindowManager().getDefaultDisplay();
			int screenWidth = dp.getWidth();
			int screenHeight = dp.getHeight();
			//�������ű���
			int scale = 1;
			int scaleWidth = imageWidth / screenWidth;
			int scaleHeight = imageHeight / screenHeight;
			if(scaleWidth >= scaleHeight && scaleWidth >= 1){
				scale = scaleWidth;
			}else if(scaleWidth < scaleHeight && scaleHeight >= 1){
				scale = scaleHeight;
			}
			//�������ű���
			opt.inSampleSize = scale;

			opt.inJustDecodeBounds= false;
			Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/world201401.jpg", opt);
			ImageView iv = (ImageView) findViewById(R.id.b1_image);
			iv.setImageBitmap(bitmap);
			
			break;

		default:
			break;
		}
	}

}
