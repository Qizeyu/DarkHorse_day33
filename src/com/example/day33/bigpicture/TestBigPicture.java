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
			//解析图片时需要使用到的参数都封装在这个对象里
			Options opt = new Options();
			//不为像素申请内存，只获取图片宽高
			opt.inJustDecodeBounds = true;
			//位图工厂
			BitmapFactory.decodeFile(Environment.getExternalStorageDirectory()+"/world201401.jpg", opt);
			//拿到图片宽高
			int imageWidth = opt.outWidth;
			int imageHeight = opt.outHeight;
			//拿到屏幕宽高
			Display dp = getWindowManager().getDefaultDisplay();
			int screenWidth = dp.getWidth();
			int screenHeight = dp.getHeight();
			//计算缩放比例
			int scale = 1;
			int scaleWidth = imageWidth / screenWidth;
			int scaleHeight = imageHeight / screenHeight;
			if(scaleWidth >= scaleHeight && scaleWidth >= 1){
				scale = scaleWidth;
			}else if(scaleWidth < scaleHeight && scaleHeight >= 1){
				scale = scaleHeight;
			}
			//设置缩放比例
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
