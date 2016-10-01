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
		// ��ʼ��������
		initDrawing();

		// ���û�����ɫ
		findViewById(R.id.b4_red).setOnClickListener(this);
		findViewById(R.id.b4_green).setOnClickListener(this);
		findViewById(R.id.b4_blue).setOnClickListener(this);
		findViewById(R.id.b4_brush).setOnClickListener(this);
		findViewById(R.id.b4_save).setOnClickListener(this);

		// ���ô�������
		iv.setOnTouchListener(new OnTouchListener() {
			// ������Ļ�ǣ������¼�����ʱ�˷�������
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				switch (action) {
				// �û���ָ������Ļ
				case MotionEvent.ACTION_DOWN:
					Log.d("OnTouchListener", "������Ļ");
					startX = (int) event.getX();
					startY = (int) event.getY();
					break;
				// �û���ָ���ڻ���
				case MotionEvent.ACTION_MOVE:
					Log.d("OnTouchListener", "���ڻ���");
					int x = (int) event.getX();
					int y = (int) event.getY();
					Log.d("event.xy", x + ";" + y);
					canvas.drawLine(startX, startY, x, y, paint);
					// ÿ�λ������֮�󣬱��λ��ƵĽ��������Ϊ�´εĳ�ʼ����
					startX = x;
					startY = y;
					iv.setImageBitmap(bmCopy);

					break;
				// �û���ָ�뿪��Ļ
				case MotionEvent.ACTION_UP:
					Log.d("OnTouchListener", "�뿪��Ļ");
					break;
				default:
					break;
				}
				// ��Ϊtrue������ϵͳ��������¼�����������,����,false:��������ʱϵͳ��Ѵ����¼����ݸ�ImageView�ĸ��ڵ�
				return true;
			}
		});
	}

	public void initDrawing() {
		// ���ػ�����ı���ͼ
		bmSrc = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
		// ��������ͼ�ĸ���,��ֽ
		bmCopy = Bitmap.createBitmap(bmSrc.getWidth(), bmSrc.getHeight(),
				bmSrc.getConfig());
		// ��
		paint = new Paint();
		// ��
		canvas = new Canvas(bmCopy);
		// ����
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
			
			//����SD�������㲥
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
