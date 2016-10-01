package com.example.day33.camera;

import java.io.File;

import com.example.day33.R;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Toast;

public class TestCamera extends Activity implements OnClickListener {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.b9_camera);
		findViewById(R.id.b9_photograph).setOnClickListener(this);
		findViewById(R.id.b9_shooting).setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.b9_photograph:
			//启动系统提供的拍照activity
			Intent photograph = new Intent();
			photograph.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			photograph.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"photograph.jpg")));
			startActivityForResult(photograph, 10);
			break;
		case R.id.b9_shooting:
			//启动系统提供的拍照activity
			Intent shooting = new Intent();
			shooting.setAction(MediaStore.ACTION_VIDEO_CAPTURE);
			shooting.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),"shooting.3gp")));
			shooting.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
			startActivityForResult(shooting, 20);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case 10:
			Toast.makeText(TestCamera.this, "拍照成功", 0).show();
			break;
		case 20:
			Toast.makeText(TestCamera.this, "摄像成功", 0).show();
			break;
		default:
			break;
		}
	}
	
}
