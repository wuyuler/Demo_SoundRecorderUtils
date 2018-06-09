package com.yjt.demo_soundrecorderutils;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class UPlayer {
	private String mFilePath;
	private MediaPlayer mPlayer;
	
	
	
	public UPlayer(String mFilePath) {
		super();
		this.mFilePath = mFilePath;
	}
	
	public static String getAudioFilePathFromUri(Uri uri,Context context) {
        Cursor cursor = context.getContentResolver()
                .query(uri, null, null, null, null);
        cursor.moveToFirst();
        int index = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
        return cursor.getString(index);
    }
	
	public boolean start() {
		mPlayer=new MediaPlayer();
        try {    
             //设置要播放的文件  
             mPlayer.setDataSource(mFilePath);  
             mPlayer.prepare();  
             //播放  
             mPlayer.start();         
         }catch(Exception e){  
             Log.e("TAG", "prepare() failed");    
         }  
  
        return false;  
    }
	
	public boolean stop() { 
		try {
			mPlayer.stop();  
	        mPlayer.release();  
	        mPlayer = null; 
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
         
        return false;  
    }

	public String getmFilePath() {
		return mFilePath;
	}

	public void setmFilePath(String mFilePath) {
		this.mFilePath = mFilePath;
	} 
	
	

}
