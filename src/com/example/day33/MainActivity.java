package com.example.day33;

import com.example.day33.bigpicture.TestBigPicture;
import com.example.day33.camera.TestCamera;
import com.example.day33.clothing.TestClothing;
import com.example.day33.drawing.TestDrawing;
import com.example.day33.effects.TestImageEffects;
import com.example.day33.imagecopy.TestImageCopy;
import com.example.day33.music.TestMusic;
import com.example.day33.surfaceview.TestVideoSurfaceView;
import com.example.day33.video.TestVideoView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		findViewById(R.id.day33_b1).setOnClickListener(this);
		findViewById(R.id.day33_b2).setOnClickListener(this);
		findViewById(R.id.day33_b3).setOnClickListener(this);
		findViewById(R.id.day33_b4).setOnClickListener(this);
		findViewById(R.id.day33_b5).setOnClickListener(this);
		findViewById(R.id.day33_b6).setOnClickListener(this);
		findViewById(R.id.day33_b7).setOnClickListener(this);
		findViewById(R.id.day33_b8).setOnClickListener(this);
		findViewById(R.id.day33_b9).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.day33_b1:
			startActivity(new Intent(MainActivity.this, TestBigPicture.class));
			break;
		case R.id.day33_b2:
			startActivity(new Intent(MainActivity.this, TestImageCopy.class));
			break;
		case R.id.day33_b3:
			startActivity(new Intent(MainActivity.this, TestImageEffects.class));
			break;
		case R.id.day33_b4:
			startActivity(new Intent(MainActivity.this, TestDrawing.class));
			break;
		case R.id.day33_b5:
			startActivity(new Intent(MainActivity.this, TestClothing.class));
			break;
		case R.id.day33_b6:
			startActivity(new Intent(MainActivity.this, TestMusic.class));
			break;
		case R.id.day33_b7:
			startActivity(new Intent(MainActivity.this, TestVideoSurfaceView.class));
			break;
		case R.id.day33_b8:
			startActivity(new Intent(MainActivity.this, TestVideoView.class));
			break;
		case R.id.day33_b9:
			startActivity(new Intent(MainActivity.this, TestCamera.class));
			break;
		default:
			break;
		}
	}
}
