package com.yjt.demo_soundrecorderutils;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private String mFilePath;
	private static int REQ_1=1;
	Button btn_record;
	Button btn_play;
	TextView tv_path;
	UPlayer mplayer;
	//public MediaPlayer mPlayer; 
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//mFilePath=Environment.getExternalStorageDirectory().getPath();
		//mFilePath=mFilePath+"/SoundRecorder/"+"record1.mp3";
		
        
		initShow();
	}
	
	private void initShow(){
		btn_record=(Button)findViewById(R.id.btn_record);
		tv_path=(TextView)findViewById(R.id.tv_path);
		btn_play=(Button)findViewById(R.id.btn_play);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void startRecord(View view){
		Intent intent=new Intent(MediaStore.Audio.Media.RECORD_SOUND_ACTION);
		//Uri recorduri=Uri.fromFile(new File(mFilePath));
		//intent.putExtra(MediaStore.EXTRA_OUTPUT,recorduri);
		startActivityForResult(intent, REQ_1);
		
		
	}
	public void startPlay(View view) {
		if(mFilePath!=null){
			mplayer=new UPlayer(mFilePath);
			mplayer.start();
		}
		
	}
	public void stopPlay(View view){
		if(mFilePath!=null){
			mplayer.stop();
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode==RESULT_OK){
			if(requestCode==REQ_1){
				//Uri uri = data.getData();
				mFilePath = UPlayer.getAudioFilePathFromUri(data.getData(),this);
               tv_path.setText(mFilePath);
				
			}
			
		}
		
	}
//	public boolean start() {
//		mPlayer=new MediaPlayer();
//        try {    
//             //设置要播放的文件  
//             mPlayer.setDataSource(mFilePath);  
//             mPlayer.prepare();  
//             //播放  
//             mPlayer.start();         
//         }catch(Exception e){  
//             Log.e("TAG", "prepare() failed");    
//         }  
//  
//        return false;  
//    }
	 
//    public boolean stop() {  
//        mPlayer.stop();  
//        mPlayer.release();  
//        mPlayer = null;  
//        return false;  
//    } 
//	private String getAudioFilePathFromUri(Uri uri) {
//        Cursor cursor = getContentResolver()
//                .query(uri, null, null, null, null);
//        cursor.moveToFirst();
//        int index = cursor.getColumnIndex(MediaStore.Audio.AudioColumns.DATA);
//        return cursor.getString(index);
//    }
	
	
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
}
