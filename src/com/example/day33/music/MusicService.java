package com.example.day33.music;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;

public class MusicService extends Service {
	MediaPlayer player;
	private Timer timer;
	@Override
	public IBinder onBind(Intent intent) {
		
		return new MusicController();
	}
	
	class MusicController extends Binder implements MusicInterface{

		@Override
		public void play() {
			MusicService.this.play();
		}

		@Override
		public void pause() {
			MusicService.this.pause();
		}

		@Override
		public void continuePlay() {
			MusicService.this.continuePlay();
		}

		@Override
		public void seekTo(int progress) {
			MusicService.this.seekTo(progress);
		}
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		player = new MediaPlayer();
	}

	public void play(){
		if(player==null){
			player = new MediaPlayer();
		}
		//����
		player.reset();
		try {
			player.setDataSource("sdcard/Idina Menzel - Let It Go.mp3");
//			player.setDataSource("http://10.0.2.2:8080/test/Idina%20Menzel%20-%20Let%20It%20Go.mp3");
			//ͬ��׼��
//			player.prepare();
			//���粥��ʱ����ʹ��ͬ��׼������������첽׼��
			player.prepareAsync();
//			player.start();
			player.setOnPreparedListener(new OnPreparedListener() {
				
				@Override
				public void onPrepared(MediaPlayer mp) {
					player.start();
					addTimer();
				}
			});
			
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
	public void continuePlay(){
		player.start();
	}
	public void pause(){
		player.pause();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if(player != null){
			//ֹͣ����
			player.stop();
			//�ͷ�ռ�õ���Դ,��ʱplayer�����Ѿ��ϵ���
			player.release();
			player = null;
		}
		if(timer !=null){
			timer.cancel();
			timer = null;
		}
	}
	
	public void seekTo(int progress){
		player.seekTo(progress);
	}
	
	public void addTimer(){
		
		//��ʱ��
		if(timer == null){
			timer = new Timer();
			timer.schedule(new TimerTask() {
				
				@Override
				public void run() {
					//��ȡ������ʱ��
					int duration = player.getDuration();
					//��ȡ������ǰ���Ž���
					int currentPosition =player.getCurrentPosition();
					
					Message msg = TestMusic.handler.obtainMessage();
					//�ѽ��ȷ�װ����Ϣ������
					Bundle bundle = new Bundle();
					bundle.putInt("duration", duration);
					bundle.putInt("currentPosition", currentPosition);
					msg.setData(bundle);
					TestMusic.handler.sendMessage(msg);
				}
				//��ʼ��ʱ������5���룬��һ��ִ��run�������Ժ�ÿ1000����ִ��һ��
			}, 5, 1000);
		}
	}
}
