package com.example.countdown_timer;

import com.CommonModule;





import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;


public class MainActivity extends Activity {
	public CommonModule m_CM;
	public Context m_context;
	public boolean m_blnLANDSCAPE;
	public String m_StrSetValue;
	public float m_fltTotalSec;
	
	public Button m_ButStart;
	public Button m_ButStop;
	public EditText m_edtValue;
	public SeekBar m_Volumesetting_seekBar;
	
	public MediaPlayer m_MediaPlayer;
	public AudioManager audioManager;// 音量管理器
	public int maxVolume;// 最大音量
	public int currentVolume;// 当前音量	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		m_blnLANDSCAPE=false;
		m_fltTotalSec=30*60;
		m_ButStart=(Button)this.findViewById(R.id.button1);
		m_ButStop=(Button)this.findViewById(R.id.button2);

		m_edtValue=(EditText)this.findViewById(R.id.editText1);
	    m_Volumesetting_seekBar=(SeekBar)this.findViewById(R.id.seekBar1);
		
	    m_MediaPlayer= new MediaPlayer();
	    audioManager = (AudioManager) getSystemService(this.AUDIO_SERVICE);//音量管理器
	    maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);// 获得最大音量
	    currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);// 获得当前音量			
		
		int tid=android.os.Process.myTid();
		m_CM=new CommonModule(this,(Context)this,tid);
		m_CM.RawFile2SD(R.raw.m01,"countdown_timer","m01.mp3");
		m_context=(Context)this;
		m_ButStop.setEnabled(false);
		
		m_ButStart.setOnClickListener(new ButListener());
		m_ButStop.setOnClickListener(new ButListener());
	    m_Volumesetting_seekBar.setMax(maxVolume);
	    m_Volumesetting_seekBar.setProgress(currentVolume);
	    m_Volumesetting_seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener()
	    {
	        @Override
	        public void onStopTrackingTouch(SeekBar seekBar)
	        {
	                                     
	        }
	                                 
	        @Override
	        public void onStartTrackingTouch(SeekBar seekBar)
	        {
	                                     
	        }
	                                 
	        @Override
	        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
	        {
	            audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, AudioManager.FLAG_ALLOW_RINGER_MODES);
	        }
	    });
		
	}
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		//螢幕旋轉不執行onCreate
		/*
		//對應的設定
        <activity
            android:name="com.listensutra.MainActivity"
            android:label="@string/app_name"
            android:configChanges="orientation|keyboardHidden|screenSize">
		 */
	    // TODO Auto-generated method stub
	    super.onConfigurationChanged(newConfig);
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    	m_blnLANDSCAPE=true;
	    }
	    else {
	    	m_blnLANDSCAPE=false;
	    }
	}
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		switch(keyCode)
		{
			case KeyEvent.KEYCODE_BACK://返回
				m_CM.CloseAlertDialog();//出現詢問是否關閉程式視窗 2015/03/17 finish();//關閉自己
				return true;//不作任何動作
		}
		return super.onKeyDown(keyCode, event);
	}
    protected void onDestroy(){ 
		//真正作用區
		//當呼叫刪除自己之後，CM內的Timer才會被刪除 by jash at 2014/09/04
    	m_MediaPlayer.release();
        super.onDestroy();
        //Kill myself
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    @Override
    protected void onStop()
    {
    	// TODO Auto-generated method stub
    	//背景執行前最後出發的事件
    	super.onStop();
    	m_CM.cancelNotification();
    	m_CM.ShowNotification("佛號倒數計時器","顯示畫面");
    }  	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	class ButListener implements OnClickListener
	{
		public void onClick(View v)
		{

			if(v==m_ButStart)
			{
				m_ButStop.setEnabled(true);
				m_ButStart.setEnabled(false);
				m_edtValue.setEnabled(false);
				m_StrSetValue = m_edtValue.getText().toString();
				m_fltTotalSec=Integer.valueOf(m_StrSetValue)*60;
				m_CM.SetTimerStepState(1);
			}
			if(v==m_ButStop)
			{
				m_CM.SetTimerStepState(0);
				m_ButStop.setEnabled(false);
				m_ButStart.setEnabled(true);
				m_edtValue.setEnabled(true);
				m_edtValue.setText(m_StrSetValue);
				m_fltTotalSec=Integer.valueOf(m_StrSetValue)*60;

			}
		}
	}
}
