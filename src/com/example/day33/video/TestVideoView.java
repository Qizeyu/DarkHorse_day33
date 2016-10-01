package com.example.day33.video;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

import com.example.day33.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class TestVideoView extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b8_video);
		//检查vitamio引擎是否安装，如果没有，帮你安装
		if(!LibsChecker.checkVitamioLibs(this)){return;}
		
		
		VideoView vv = (VideoView) findViewById(R.id.b8_video);
		vv.setVideoPath("sdcard/bnsdmm.mp4");
		vv.start();
		//添加控制器
		vv.setMediaController(new MediaController(this));
	}

}
