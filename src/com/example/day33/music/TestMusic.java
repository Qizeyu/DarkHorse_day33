package com.example.day33.music;

import com.example.day33.R;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class TestMusic extends Activity implements OnClickListener {
	MyServiceConn conn = new MyServiceConn();
	MusicInterface mi;
	private Intent intent;
	static SeekBar sb;

	static Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			Bundle bundle = msg.getData();
			int duration = bundle.getInt("duration");
			int currentPosition = bundle.getInt("currentPosition");
			// 刷新进度条的进度
			sb.setMax(duration);
			sb.setProgress(currentPosition);
		}

	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b6_music);
		findViewById(R.id.b6_play).setOnClickListener(this);
		findViewById(R.id.b6_continueplay).setOnClickListener(this);
		findViewById(R.id.b6_pause).setOnClickListener(this);
		findViewById(R.id.b6_exit).setOnClickListener(this);

		sb = (SeekBar) findViewById(R.id.b6_seekbar);
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.d("播放进度条", "手指抬起");
				// 根据拖动的进度改变音乐播放进度
				int progress = seekBar.getProgress();
				// 改变播放进度
				mi.seekTo(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				Log.d("播放进度条", "手指按下");
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				Log.d("播放进度条", "手指滑动");
			}
		});

		intent = new Intent(TestMusic.this, MusicService.class);
		startService(intent);
		bindService(intent, conn, BIND_AUTO_CREATE);
	}

	class MyServiceConn implements ServiceConnection {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			mi = (MusicInterface) service;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b6_play:
			mi.play();
			break;
		case R.id.b6_continueplay:
			mi.continuePlay();
			break;
		case R.id.b6_pause:
			mi.pause();
			break;
		case R.id.b6_exit:
			unbindService(conn);
			stopService(intent);
			finish();
			break;
		default:
			break;
		}
	}
}
