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
		//���vitamio�����Ƿ�װ�����û�У����㰲װ
		if(!LibsChecker.checkVitamioLibs(this)){return;}
		
		
		VideoView vv = (VideoView) findViewById(R.id.b8_video);
		vv.setVideoPath("sdcard/bnsdmm.mp4");
		vv.start();
		//��ӿ�����
		vv.setMediaController(new MediaController(this));
	}

}
