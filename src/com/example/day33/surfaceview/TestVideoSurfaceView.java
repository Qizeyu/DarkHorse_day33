package com.example.day33.surfaceview;

import java.io.IOException;

import com.example.day33.R;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.Window;

public class TestVideoSurfaceView extends Activity {
	private MediaPlayer player;
	private SurfaceHolder sh;
	private SurfaceView sv;
	
	//��¼λ��,��Ϊ��̬�ͺͽ��̹������ˣ����ⷵ�ؼ��˳�������
	static int currentPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b7_surfaceview);
		
		sv = (SurfaceView) findViewById(R.id.b7_surface);
		//�õ�SurfaceView�Ŀ�����
		sh = sv.getHolder();
		
		/*Thread t = new Thread(){

			@Override
			public void run() {
				try {
					sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						
					}
				});
			}
		};
		t.start();*/
		
		sh.addCallback(new Callback() {
			//����ʱ����
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				//ÿ��surfaceView����ʱ��ͬʱֹͣ������Ƶ
				if(player != null){
					currentPosition = player.getCurrentPosition();
					player.stop();
					player.release();
					player = null;
				}
			}
			//surfaceView����ʱ����
			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				//ÿ��surfaceView����ʱ����ȥ������Ƶ
				if(player == null){
					player = new MediaPlayer();
					player.reset();
					try {
						player.setDataSource("sdcard/sms.3gp");
						player.setDisplay(sh);
						player.prepare();
						player.start();
						player.seekTo(currentPosition);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			//�ṹ�ı�ʱ����
			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width,
					int height) {
				
			}
		});
	}
}
