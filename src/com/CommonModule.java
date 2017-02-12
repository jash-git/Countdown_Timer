package com;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EncodingUtils;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import com.example.countdown_timer.MainActivity;
import com.example.countdown_timer.R;






import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.os.SystemClock;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Process;
/*
//MediaPlayer play mp3
方法1:
// Load an audio resource from a package resource. 
MediaPlayer resourcePlayer = 
   MediaPlayer.create(this, R.raw.my_audio);

// Load an audio resource from a local file. 
MediaPlayer filePlayer = MediaPlayer.create(this, 
   Uri.parse(“file:///sdcard/localfile.mp3”));

// Load an audio resource from an online resource. 
MediaPlayer urlPlayer = MediaPlayer.create(this, 
   Uri.parse(“http://site.com/audio/audio.mp3”));

// Load an audio resource from a Content Provider. 
MediaPlayer contentPlayer = MediaPlayer.create(this, 
   Settings.System.DEFAULT_RINGTONE_URI);
注意：上述這種create後返回mediaPlayer的方式，其實已經調用了prepare方法，所以不能再調用了。

方法2:
MediaPlayer mediaPlayer = new MediaPlayer(); 
mediaPlayer.setDataSource(“/sdcard/mydopetunes.mp3”); 
mediaPlayer.prepare();
*/
/*
 	//按鈕事件標準框架紀錄
	//單一按鈕事件標準框架紀錄
	    m_bt1（按鈕物件）.setOnClickListener
	    (
	    		new View.OnClickListener() 
	    		{
	    			@Override
	    			public void onClick(View v)
	    			{
	    				
	    			}
	    		}
	    );
	//多按鈕事件標準框架紀錄
	class ButListener implements OnClickListener
	{
		public void onClick(View v)
		{
			if(v==bt[0])
			{
	
			}
			if(v==bt[1])
			{
					
			}
		}
	}	    
*/
/*
    <!-- SD卡/網路權限 -->   
	<uses-permission android:name="android.permission.INTERNET" />
	<uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
	<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
	<uses-permission android:name="android.permission.READ_PHONE_STATE" />
	<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    
    <!-- Android 線程優先順序修改 --> 
    <uses-permission android:name="android.permission.RAISED_THREAD_PRIORITY"/>    	
	
	<!-- USB 偵測_start --> 
    <permission android:name="android.hardware.usb.host" ></permission>
    
    <uses-feature
        android:name="android.hardware.usb.host"
        android:required="true" />
    <!-- USB 偵測_end -->
    
    <!-- 發送簡訊權限 -->
    <uses-permission android:name="android.permission.SEND_SMS"/>
 */
public class CommonModule
{
	private int m_intStep;//狀態控制變數
	///////////////////////////////////
	//主頁變數紀錄_start
	private MainActivity m_MA;
	private Context m_context;
	private int m_tid;
	//主頁變數紀錄_end
	///////////////////////////////////
	//通用訊息對話盒ShowMessageDialog
	public void ShowMessageDialog(String StrTitle,String StrMessage,String ButtonText)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(m_context);
		builder.setTitle(StrTitle);
		builder.setMessage(StrMessage);
		builder.setNeutralButton(ButtonText,new DialogInterface.OnClickListener()//修正風格和5TV一樣 at 2015/03/20 builder.setPositiveButton("Close",new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.cancel();
			}
		});
		AlertDialog alertdialog=builder.create();
		alertdialog.show();		
	}	
	public static void ShowMessageDialog(Context Context1,String StrTitle,String StrMessage,String ButtonText)
	{
		AlertDialog.Builder builder=new AlertDialog.Builder(Context1);
		builder.setTitle(StrTitle);
		builder.setMessage(StrMessage);
		builder.setNeutralButton(ButtonText,new DialogInterface.OnClickListener()//修正風格和5TV一樣 at 2015/03/20 builder.setPositiveButton("Close",new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				dialog.cancel();
			}
		});
		AlertDialog alertdialog=builder.create();
		alertdialog.show();		
	}
	///////////////////////////////////
	///Notification 狀態列顯示icon/ 喚回GUI
	/*
	 AndroidManifest.xml設定
		<activity android:name="ShowMessageActivity"
             android:launchMode="singleTask">
        </activity> 
	 */
	public NotificationManager notificationManager;
	///
    // 取消通知
    public void cancelNotification()
    {
    	NotificationManager notificationManager = (NotificationManager) m_context.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }		
    public static void cancelNotification(Context ct)
    {
    	NotificationManager notificationManager = (NotificationManager) ct.getSystemService(android.content.Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(0);
    }	
	public void ShowNotification(String StrTitle,String StrData)
	{
		///Notification_step02
		//http://blog.xuite.net/kaymaner/Android/233387979-%5BAndroid%5D+Notification+%E9%80%9A%E7%9F%A5
		//取得Notification服務

		 //設定當按下這個通知之後要執行的activity
		notificationManager = (NotificationManager)m_context.getSystemService(Context.NOTIFICATION_SERVICE);
		if(notificationManager!=null)
		{
			notificationManager.cancelAll();
		}
		Notification nf = new Notification(R.drawable.ic_launcher, StrTitle,System.currentTimeMillis());
		Intent i = new Intent(m_context, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(m_context, 0, i, 0);
		nf.setLatestEventInfo(m_context.getApplicationContext(), StrTitle, StrData, pi);
		notificationManager.notify(0, nf);	
	}
	///////////////////////////////////
	//Android 把自己縮小/隱藏
	public void HideGUI()
	{
		m_MA.moveTaskToBack(true);
	}
	///////////////////////////////////
	//Android Send Key code 模擬鍵盤
	//<uses-permission android:name="android.permission.INJECT_EVENTS" />
	//http://ccbasic.blogspot.tw/2013/09/android-socketinstrumentation.html
	public void sendKeyCode(final int keyCode)
	{
		new Thread ()
		{
			public void run()
			{
				try
				{
					Instrumentation inst = new Instrumentation();
					inst.sendKeyDownUpSync(keyCode);
				} catch (Exception e) {
					Log.e("Exception when sendPointerSync", e.toString());
				}
			}
		}.start();
	}
	///////////////////////////////////
	//Android Send Touch 模擬點擊
	//<uses-permission android:name="android.permission.INJECT_EVENTS" />
	//http://ccbasic.blogspot.tw/2013/09/android-socketinstrumentation.html
	public void sendTouch(final float x,final float y)
	{
		new Thread ()
		{
			public void run()
			{
				try
				{
					Instrumentation inst = new Instrumentation();
					inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));
					inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0));
				} catch (Exception e) {
					Log.e("Exception when sendPointerSync", e.toString());
				}
			}
		}.start();		
	}
	///////////////////////////////////
	//Android Send TouchMOVE 模擬滑動
	//<uses-permission android:name="android.permission.INJECT_EVENTS" />
	//http://ccbasic.blogspot.tw/2013/09/android-socketinstrumentation.html
	public void sendTouchMOVE(final float x1,final float y1,final float x2,final float y2)
	{
		new Thread ()
		{
			public void run()
			{
				try
				{
					Instrumentation inst = new Instrumentation();
					inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x1, y1, 0));
					inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),MotionEvent.ACTION_MOVE, x2, y2, 0));
					inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x2, y2, 0));
				} catch (Exception e) {
					Log.e("Exception when sendPointerSync", e.toString());
				}
			}
		}.start();			
	}
	///////////////////////////////////
	//通用資料庫SQLite
	/*
	//DB test code
	OpenOrCreateDB("testDB","testTABLE");
	String [] column_name={"name","price"};
	String [] data_type={"STRING","INTEGER"};
	CreateTABLE("testTABLE",column_name,data_type,2);
	String [] value={"鉛筆","10"};
	DBInsert("testTABLE",column_name,data_type,value,2);
	DBSelectAll("testTABLE",column_name,data_type,2);
	*/	
	private String m_StrDBFilePath;
	private SQLiteDatabase m_db;
	public void OpenOrCreateDB(String StrDBName,String StrTableName)
	{
		m_StrDBFilePath="/data/data/"+m_context.getPackageName()+"/"+StrDBName;
		if(m_db!=null)
		{
			m_db.close();
			m_db=null;
		}
		m_db=SQLiteDatabase.openOrCreateDatabase(m_StrDBFilePath, null);
		//刪除上一次的Table
		String qry="";
		qry="DROP TABLE IF EXISTS ";
		qry+=StrTableName;
		m_db.execSQL(qry);
		WriteAppLog("ottstore","OpenOrCreateDB m_StrDBFilePath : "+m_StrDBFilePath);
		WriteAppLog("ottstore","OpenOrCreateDB qry : "+qry);
		//刪除上一次的Table
	}
	public void CreateTABLE(String StrTableName,String [] column_name,String [] data_type,int Amount)
	{
		String qry="";
		int i;
		qry="CREATE TABLE ";
		qry+=StrTableName;
		qry+="( id INTEGER PRIMARY KEY"; 
		for(i=0;i<Amount;i++)
		{
			qry+=", ";
			qry+=column_name[i]+" ";
			qry+=data_type[i];
		}
		qry+=")";
		m_db.execSQL(qry);
		WriteAppLog("ottstore","CreateTABLE qry : "+qry);
	}
	public void DBInsert(String StrTableName,String [] column_name,String [] data_type,String [] value,int Amount)
	{
		String qry="";
		int i;
		qry="INSERT INTO ";
		qry+=StrTableName+" (";
		for(i=0;i<Amount;i++)
		{
			if(i>0)
			{
				qry+=", ";
			}
			qry+=column_name[i];
		}
		qry+=") VALUES (";
		for(i=0;i<Amount;i++)
		{
			if(i>0)
			{
				qry+=", ";
			}
			if(data_type[i].equals("STRING"))
			{
				qry+="'"+value[i]+"'";
			}
			else
			{
				qry+=value[i];
			}
		}
		qry+=")";
		m_db.execSQL(qry);
		WriteAppLog("ottstore","DBInsert qry : "+qry);
	}
	public void DBSelectAll(String StrTableName,String [] column_name,String [] data_type,int Amount)
	{
		String qry="";
		qry="SELECT * FROM ";
		qry+=StrTableName;
		Cursor cr=m_db.rawQuery(qry,null);
		WriteAppLog("ottstore","DBSelectAll qry : "+qry);
		int i,j;
		String StrBuf="";
		i=0;
		while(cr.moveToNext())
		{
			j=cr.getColumnIndex("id");
			StrBuf="id :: "+cr.getInt(j)+" ";
			for(i=0;i<Amount;i++)
			{
				j=cr.getColumnIndex(column_name[i]);
				if(data_type[i].equals("STRING"))
				{
					StrBuf+=column_name[i]+" :: "+cr.getString(j)+" ";
				}
				else
				{
					StrBuf+=column_name[i]+" :: "+cr.getInt(j)+" ";
				}
			}
			StrBuf+="\n";
		}
		WriteAppLog("ottstore","DBSelectAll StrBuf : "+StrBuf);
	}
	//////////////////////////////////
	//抓取APP本身版本資訊
	public String m_StrVersionCode;
	public String m_StrVersionName;
	public String m_StrPackageName;
	public void GetAppVersion()
	{
		try
		{
			PackageInfo packageInfo = m_context.getPackageManager().getPackageInfo(m_context.getPackageName(), 0);
			m_StrVersionCode = ""+packageInfo.versionCode;//偷懶的數字轉字串方法
			m_StrVersionName = packageInfo.versionName;
			m_StrPackageName = packageInfo.packageName;
		} 
		catch (NameNotFoundException e) 
		{
			 e.printStackTrace();
		}
	}
	///////////////////////////////////
	//SharedPreferences的使用(APP暫存資料功能)[記錄/讀取 變數值從實體檔案]_start
	public static SharedPreferences m_settings;
	public static void SharedPreferences_Init(Context ct)//物件初始化
	{
		m_settings = ct.getSharedPreferences("setting", 0);
	}
	public static void SharedPreferences_Write(String StrName[],String StrValue[],int Amount)
	{
		int i;
		SharedPreferences.Editor editor = m_settings.edit();
		for(i=0;i<Amount;i++)
		{
			
			editor.putString(StrName[i],StrValue[i]);
		}
		editor.commit();
	}
	public static String [] SharedPreferences_Read(String StrName[],int Amount)
	{
		int i;
		String [] StrArrayValue = new String[Amount];
		for(i=0;i<Amount;i++)
		{
			StrArrayValue[i] = m_settings.getString(StrName[i],"null_data");
		}
		return StrArrayValue;
	}
	//SharedPreferences的使用(APP暫存資料功能)[記錄/讀取 變數值從實體檔案]_end
	///////////////////////////////////
	/*
	 取當前語言
	context.getResources().getConfiguration().locale.getLanguage()
	取當前國家
	context.getResources().getConfiguration().locale.getCountry()
	*/
	public String getLanguage()
	{
		return m_context.getResources().getConfiguration().locale.getLanguage();
	}
	public String getCountry()
	{
		return m_context.getResources().getConfiguration().locale.getCountry();
	}
	///////////////////////////////////
	//Android 影像處理基礎
	public Bitmap bitmap2Gray(Bitmap bmSrc) //圖像轉灰階
	{
		// 得到圖片的長和寬
		int width = bmSrc.getWidth();
		int height = bmSrc.getHeight();
		// 創建目標灰度圖像
		Bitmap bmpGray = null;
		bmpGray = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
		// 創建畫布
		Canvas c = new Canvas(bmpGray);
		Paint paint = new Paint();
		ColorMatrix cm = new ColorMatrix();
		cm.setSaturation(0);
		ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
		paint.setColorFilter(f);
		c.drawBitmap(bmSrc, 0, 0, paint);
		return bmpGray;
	}
	public Bitmap lineGrey(Bitmap image)// 對圖像進行線性灰度變化
	{
		//得到圖像的寬度和長度
		int width = image.getWidth();
		int height = image.getHeight();
		//創建線性拉升灰度圖像
		Bitmap linegray = null;
		linegray = image.copy(Config.ARGB_8888, true);
		//依次迴圈對圖像的圖元進行處理
		for (int i = 0; i < width; i++) 
		{
			for (int j = 0; j < height; j++)
			{
				//得到每點的圖元值
				int col = image.getPixel(i, j);
				int alpha = col & 0xFF000000;
				int red = (col & 0x00FF0000) >> 16;
				int green = (col & 0x0000FF00) >> 8;
				int blue = (col & 0x000000FF);
				// 增加了圖像的亮度
				red = (int) (1.1 * red + 30);
				green = (int) (1.1 * green + 30);
				blue = (int) (1.1 * blue + 30);
				//對圖像圖元越界進行處理
				if (red >= 255) 
				{
					red = 255;
				}
				if (green >= 255)
				{
					green = 255;
				}
				if (blue >= 255)
				{
					blue = 255;
				}
				// 新的ARGB
				int newColor = alpha | (red << 16) | (green << 8) | blue;
				//設置新圖像的RGB值
				linegray.setPixel(i, j, newColor);
			}
		}
		return linegray;
	}
	public Bitmap gray2Binary(Bitmap graymap,int intgray) // 該函數實現對圖像進行二值化處理
	{
		//得到圖形的寬度和長度
		int width = graymap.getWidth();
		int height = graymap.getHeight();
		//創建二值化圖像
		Bitmap binarymap = null;
		binarymap = graymap.copy(Config.ARGB_8888, true);
		//依次迴圈，對圖像的圖元進行處理
		for (int i = 0; i < width; i++) 
		{
			for (int j = 0; j < height; j++) 
			{
				//得到當前圖元的值
				int col = binarymap.getPixel(i, j);
				//得到alpha通道的值
				int alpha = col & 0xFF000000;
				//得到圖像的圖元RGB的值
				int red = (col & 0x00FF0000) >> 16;
				int green = (col & 0x0000FF00) >> 8;
				int blue = (col & 0x000000FF);
				// 用公式X = 0.3×R+0.59×G+0.11×B計算出X代替原來的RGB
				int gray = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
				//對圖像進行二值化處理
				if (gray <= intgray)
				{
					gray = 0;
				}
				else
				{
					gray = 255;
				}
				// 新的ARGB
				int newColor = alpha | (gray << 16) | (gray << 8) | gray;
				//設置新圖像的當前圖元值
				binarymap.setPixel(i, j, newColor);
			}
		}
		return binarymap;
	}
	//Android 影像處理基礎
	///////////////////////////////////

	/*
	//程式碼備份-元件設定背景參數資源檔和設定顏色(How to programmatically round corners and set random background colors)

	v.setBackgroundResource(R.drawable.tags_rounded_corners);

	GradientDrawable drawable = (GradientDrawable) v.getBackground();
	if (i % 2 == 0)
	{
		drawable.setColor(Color.RED);
	}
	else
	{
		drawable.setColor(Color.BLUE);
	}
	*/
	//傳入圖片並切出圓角，圓角轉換函式，帶入Bitmap圖片及圓角數值則回傳圓角圖，回傳Bitmap再置入ImageView
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPx)
    {         
    Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), 
                                        bitmap.getHeight(), 
                                        Config.ARGB_8888);//製作透明底圖的BMP畫布空間  
    Canvas canvas = new Canvas(output);//建立畫布綁定-BMP畫布空間 
    final int color = 0xff424242;  
    final Paint paint = new Paint();//建立畫筆  
    final Rect rect = new Rect(0, 0, bitmap.getWidth(),
                                     bitmap.getHeight());//建立一個長方形作標系  
    final RectF rectF = new RectF(rect);//建立一個長方形作標系(浮點數)  
    paint.setAntiAlias(true);//設定畫筆抗锯齿  
    canvas.drawARGB(0, 0, 0, 0);//設定畫布作畫顏色  
    paint.setColor(color);//設定畫筆顏色  
    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);//在畫布上畫上弧角長方型  
    /*
     * http://www.cnblogs.com/sank615/archive/2013/03/12/2955675.html
 　　	   PorterDuff.Mode.CLEAR 清除画布上图像 
       PorterDuff.Mode.SRC 显示上层图像 
       PorterDuff.Mode.DST 显示下层图像 
       PorterDuff.Mode.SRC_OVER上下层图像都显示，上层居上显示 
       PorterDuff.Mode.DST_OVER 上下层都显示,下层居上显示 
       PorterDuff.Mode.SRC_IN 取两层图像交集部门,只显示上层图像 
       PorterDuff.Mode.DST_IN 取两层图像交集部门,只显示下层图像 
       PorterDuff.Mode.SRC_OUT 取上层图像非交集部门 
       PorterDuff.Mode.DST_OUT 取下层图像非交集部门 
       PorterDuff.Mode.SRC_ATOP 取下层图像非交集部门与上层图像交集部门 
       PorterDuff.Mode.DST_ATOP 取上层图像非交集部门与下层图像交集部门 
       PorterDuff.Mode.XOR 取两层图像的非交集部门 
     */
    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN)); //PorterDuff.Mode.DST_IN 取两层图像交集部门,只显示下层图像  
    canvas.drawBitmap(bitmap, rect, rect, paint);  
    return output;  
    }
    ///////////////////////////////////
	//抓取非Wifi的網卡MAC
	public static String loadFileAsString(String filePath) throws java.io.IOException {
	    StringBuffer fileData = new StringBuffer(1000);
	    BufferedReader reader = new BufferedReader(new FileReader(filePath));
	    char[] buf = new char[1024];
	    int numRead=0;
	    while((numRead= reader.read(buf)) != -1) {
	        String readData = String.valueOf(buf, 0, numRead);
	        fileData.append(readData);
	    }
	    reader.close();
	    return fileData.toString();
	}
	public String m_Streth0MacAddress;
	public static String geteth0MacAddress() {
	    try {
	        return loadFileAsString("/sys/class/net/eth0/address")
	            .toUpperCase().substring(0, 17);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "-";
	    }
	}
	public String geteth1MacAddress() {
	    try {
	        return loadFileAsString("/sys/class/net/eth1/address")
	            .toUpperCase().substring(0, 17);
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "-";
	    }
	}
	//抓取非Wifi的網卡MAC
	///////////////////////////////////
	//取得wifi資訊_start
	public String m_StrWifiMacAddress;
	public String getWifiMac() {
	       WifiManager wifiMan = (WifiManager) m_context.getSystemService(Context.WIFI_SERVICE);
	       WifiInfo wifiInf = wifiMan.getConnectionInfo();
	       return wifiInf.getMacAddress();
	}
	public String getIPAddress() {
	       WifiManager wifiMan = (WifiManager) m_context.getSystemService(Context.WIFI_SERVICE);
	       WifiInfo wifiInf = wifiMan.getConnectionInfo();
	       long ip = wifiInf.getIpAddress();
	       if( ip != 0 )
	              return String.format( "%d.%d.%d.%d",
	                     (ip & 0xff),
	                     (ip >> 8 & 0xff),
	                     (ip >> 16 & 0xff),
	                     (ip >> 24 & 0xff));
	       try {
	              for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	                     NetworkInterface intf = en.nextElement();
	                     for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                            InetAddress inetAddress = enumIpAddr.nextElement();
	                            if (!inetAddress.isLoopbackAddress()) {
	                                   return inetAddress.getHostAddress().toString();
	                            }
	                     }
	              }
	       } catch (Exception e) {
	        }
	       return "0.0.0.0";
	}
	//取得wifi資訊_end
	///////////////////////////////////
	//手機內系統訊息_start
		/*
		TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		str += "DeviceId(IMEI) = " + tm.getDeviceId() + "\n";
		str += "DeviceSoftwareVersion = " + tm.getDeviceSoftwareVersion() + "\n";
		str += "Line1Number = " + tm.getLine1Number() + "\n";
		str += "NetworkCountryIso = " + tm.getNetworkCountryIso() + "\n";
		str += "NetworkOperator = " + tm.getNetworkOperator() + "\n";
		str += "NetworkOperatorName = " + tm.getNetworkOperatorName() + "\n";
		str += "NetworkType = " + tm.getNetworkType() + "\n";
		str += "PhoneType = " + tm.getPhoneType() + "\n";
		str += "SimCountryIso = " + tm.getSimCountryIso() + "\n";
		str += "SimOperator = " + tm.getSimOperator() + "\n";
		str += "SimOperatorName = " + tm.getSimOperatorName() + "\n";
		str += "SimSerialNumber = " + tm.getSimSerialNumber() + "\n";
		str += "SimState = " + tm.getSimState() + "\n";
		str += "SubscriberId(IMSI) = " + tm.getSubscriberId() + "\n";
		str += "VoiceMailNumber = " + tm.getVoiceMailNumber() + "\n";
		 */
	public static String m_StrIMEI;
	public static String getIMEI(Context ct)
	{
		/*
		 如果沒抓到回傳直==null，
		 判斷式要寫成 『m_StrIMEI!=null / m_StrIMEI==null』，
		 不可以寫『m_StrIMEI.equals("-") / m_StrIMEI.equals("")』不然會ERROR
		*/
		TelephonyManager tm = (TelephonyManager)ct.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getDeviceId();
	}
	//手機內系統訊息_end
	///////////////////////////////////
	//抓取午5TV韌體資訊_start
	public static String Get5TVROMVer()
	{
		String softpath = "/fc/vpd/ROMVer.conf";
	    String softversion = "";
	    try
		{
	    	String soft_temp = "";
	    	FileReader fr = new FileReader(softpath);
	    	BufferedReader localBufferedReader = new BufferedReader(fr, 8192);    		  
		    while ((soft_temp = localBufferedReader.readLine()) != null)
			{
		    	softversion = softversion + soft_temp;
		    }
		    localBufferedReader.close();
	    }
		catch (Exception e)
		{
			e.printStackTrace();
		}
	    softversion = softversion.replace("softver!", "");	    
	 	return softversion;
	}
	//抓取午5TV韌體資訊_end
	///////////////////////////////////
	//取得系統上安裝APK清單_start
	List<PackageInfo> m_AppPakList;
	String [] m_StrAllAppPacName;
	int m_intAllAppCount;
	public void GetSystemAPKList()
	{
		PackageManager pManager =  m_MA.getPackageManager();
		String StrAppName;
		String StrPacName;
		m_AppPakList = pManager.getInstalledPackages(0);
		m_StrAllAppPacName=new String[m_AppPakList.size()];
		m_intAllAppCount=m_AppPakList.size();
		for (int i = 0; i < m_intAllAppCount; i++)
		{
			PackageInfo pak = (PackageInfo) m_AppPakList.get(i);
			//判断是否为非系统预装的应用程序
			if ((pak.applicationInfo.flags & pak.applicationInfo.FLAG_SYSTEM) <= 0)
			{
				// customs applications
				/*
				//set Icon 
				Drawable icon =pManager.getApplicationIcon(pak.applicationInfo);
				ImageView.setImageDrawable(icon);
				*/
				
				StrAppName=pManager.getApplicationLabel(pak.applicationInfo).toString();//set Application Name 
				StrPacName=pak.applicationInfo.packageName;//set Package Name
				m_StrAllAppPacName[i]=StrPacName;//把packageName放到陣咧中
				WriteAppLog("ShowTime","Application Name: "+StrAppName+"\t Package Name: "+StrPacName);//紀錄到Log中
				
			}
		}
	}
	//取得系統上安裝APK清單_end
	///////////////////////////////////
	//刪除背景程式_start
	public void KillBackgroundProcess()//刪除背景程式，自己除外
	{	 
		ActivityManager mActivityManager = (ActivityManager)m_context.getSystemService(Context.ACTIVITY_SERVICE);
	    List<RunningAppProcessInfo> ListRunAppInfo = ((ActivityManager) mActivityManager).getRunningAppProcesses();
		 
	    int whoru_pid = 0;
	    for (int i = 0; i < ListRunAppInfo.size(); i++)
	    {	
			if( ListRunAppInfo.get(i).processName.equals( m_context.getPackageName() ) )
			{
				whoru_pid = ListRunAppInfo.get(i).pid;
			}
			else
			{	
				// // kill all exclude urself
				try
				{
					//android.os.Process.sendSignal( activityes.get(i).pid, 9 );
					mActivityManager.killBackgroundProcesses( ListRunAppInfo.get(i).processName );
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}			
	    }
	    /*
	    // kill yourself
	    if( whoru_pid > 0 )
	    {
			try
			{
				android.os.Process.sendSignal( whoru_pid, 9 );				
			} catch(Exception e)
			{
				e.printStackTrace();
			}	    	
	    }
	    */
	}
	//刪除背景程式_start
	///////////////////////////////////
	//清除APP暫存_start
    public boolean [] m_blnDelTempCheck={false,false,false,false};
    
	public void ClearTempFile()
	{
		for(int i=0;i<4;i++)
		{
			m_blnDelTempCheck[i]=false;
		}
		m_blnDelTempCheck[0]=deleteFile( m_context.getCacheDir() );
		m_blnDelTempCheck[1]=deleteFile( m_context.getFilesDir() );
		m_blnDelTempCheck[2]=deleteFile( m_context.getExternalCacheDir() );
		m_blnDelTempCheck[3]=deleteFile( m_context.getExternalFilesDir(null) );		
	}
	public boolean deleteFile( File fileDir )//刪除資料夾內檔案
	{
		boolean blnAns=false;
	    try
	    {
		    if (fileDir != null)
		    {
		        if (fileDir.listFiles() != null && fileDir.listFiles().length > 0){
		        	// recursively delete files in dir
		            for (File file : fileDir.listFiles()){
		            	blnAns=deleteFile(file);//遞迴
		            	if(blnAns==false)
		            	{
		            		break;
		            	}
		            }
		        }
		        else
		        {
		        	// file delete
		        	blnAns=fileDir.delete();
		        }
		    }
	    } 
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	    }
	    return blnAns;
	}
	public boolean deleteDir(File dir) throws Exception//刪除資料夾
	{

		try {
			if (dir != null && dir.isDirectory())
			{
				String[] children = dir.list();
				for (int i = 0; i < children.length; i++)
				{
					boolean success = deleteDir(new File(dir, children[i]));//遞迴
					if (!success)
					{
						return false;
					}
				}
			}
			// <uses-permission
			// android:name="android.permission.CLEAR_APP_CACHE"></uses-permission>
			// The directory is now empty so delete it
			//Log.w(Config.LogcatTag, "deleteDir:" + dir);
			return dir.delete();
		}
		catch (Exception e)
		{
			throw e;
		}
	}	
	//清除APP暫存_end
	///////////////////////////////////
	///////////////////////////////////
	//寫文字檔Log_start
	public static boolean m_blnDubegflag = true;
	public static void DeleteOneFile(String StrDir,String FileName)
	{
		File sd=Environment.getExternalStorageDirectory(); 
		String Dir=StrDir;
		String logpath=sd.getPath()+"/"+Dir+"/"+FileName;
		File logfile=new File(logpath);
		if(logfile.exists())
		{
			logfile.delete();
		}
	}
	public static void WriteData2File(String StrDir,String FileName,String StrMsg)//
	{
		File sd=Environment.getExternalStorageDirectory(); 
		String Dir=StrDir;
		String logpath=sd.getPath()+"/"+Dir+"/"+FileName; 
		try
		{
			if(m_blnDubegflag)
			{
				FileWriter out = new FileWriter(logpath,true);
				out.write((StrMsg+"\n"));
				out.close();
			}
		}
		catch(IOException ioe){
			System.out.print(ioe);
		}
	}	
	public static void WriteAppLog(String StrDir,String StrMsg)//
	{
		File sd=Environment.getExternalStorageDirectory(); 
		String Dir=StrDir;
		String logpath=sd.getPath()+"/"+Dir+"/"+Dir+".log"; 
		try
		{
			if(m_blnDubegflag)
			{
				FileWriter out = new FileWriter(logpath,true);
				out.write((StrMsg+"\n"));
				out.close();
			}
		}
		catch(IOException ioe){
			System.out.print(ioe);
		}
	}
	public static void CreateAppFloder(String StrDir) // 建立APP 資料夾
	{
		File sd=Environment.getExternalStorageDirectory(); 
		String path=sd.getPath()+"/"+StrDir; 
		File floder=new File(path); 
		String logpath=sd.getPath()+"/"+StrDir+"/"+StrDir+".log"; 
		File logfile=new File(logpath);
		if(!floder.exists()) 
		{
			floder.mkdir();
		}
		else
		{
			if(logfile.exists())
			{
				logfile.delete();
			}
		}
		
	}
	//寫文字檔Log_end
	///////////////////////////////////
	//抓SD卡下特定目錄的完整路徑
	public static String GetSDFolderPath(String StrDir)
	{
		File sd=Environment.getExternalStorageDirectory(); 
		String path=sd.getPath()+"/"+StrDir+"/";
		return path;
	}
	///////////////////////////////////
	//抓取資料下所有檔案清單(列表)-子資料不會抓取
	public static String[] GetFileList(String StrDir,final String type)
	{
		FilenameFilter mediafilefilter = new FilenameFilter(){
			private String strtype=type;
			@Override
			public boolean accept(File fl,String path) 
			{
				File file=new File(path);
				String filename=file.getName();
				return filename.indexOf(strtype)!=-1;
			}};
		File sd=Environment.getExternalStorageDirectory(); 
		String path=sd.getPath()+"/"+StrDir;

		File fl = new File(path);
		String[] str = fl.list(mediafilefilter);
		if(str.length>0)
		{
			return str;
		}
		else
		{
			String[] str01={"沒有檔案可播....."};
			return str01;
		}
	}	
	public static String[] GetFileList(String StrDir)
	{
		File sd=Environment.getExternalStorageDirectory(); 
		String path=sd.getPath()+"/"+StrDir;

		File fl = new File(path);
		String[] str = fl.list();
		if(str.length>0)
		{
			return str;
		}
		else
		{
			String[] str01={"沒有檔案可播....."};
			return str01;
		}
	}
	///////////////////////////////////
	//Http DownLoad_start
	public int m_intDownLoadAmount=0;//數量
	public boolean m_blnDownLoadFinish=false;

	public ArrayList<String> m_ListDownLoadFileName = new ArrayList<String>();
	public ArrayList<String> m_ListDownLoadUrlPath = new ArrayList<String>();
	public String m_StrDownLoadFileName;
	public String m_StrDownLoadUrlPath;
	public String m_StrFloderPath;
	//add(s);.size();get(int index);clear();
	Thread m_threadDownload;
	public void DownLoadFile(String StrDir,String [] StrUrl,String [] StrFileName,int Amount)
	{
		m_blnDownLoadFinish=false;
		File sd=Environment.getExternalStorageDirectory(); 
		m_StrFloderPath=sd.getPath()+"/"+StrDir+"/";
		m_ListDownLoadFileName.clear();
		m_ListDownLoadUrlPath.clear();
		m_intDownLoadAmount=Amount;
		for(int i=0;i<Amount;i++)
		{
			m_ListDownLoadFileName.add((m_StrFloderPath+StrFileName[i]));
			m_ListDownLoadUrlPath.add(StrUrl[i]);
		}
		m_threadDownload =new Thread(mDownloadList);
		m_threadDownload.start();		
	}
	public Runnable mDownloadList = new Runnable() 
	{
		@Override
		public void run()
		{			
			int BUFFER = 1024;
			long lngFileSize;
			long lngDownloadSize;
			for(int i=0;i<m_intDownLoadAmount;i++)
			{
				lngFileSize=0;
				lngDownloadSize=0;
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpGet httpGet = new HttpGet(m_ListDownLoadUrlPath.get(i));
				try 
				{
					HttpResponse response = httpClient.execute(httpGet);
					InputStream in = response.getEntity().getContent();
					lngFileSize=response.getEntity().getContentLength();
					FileOutputStream out;
				
					out = new FileOutputStream(new File(m_ListDownLoadFileName.get(i)));

					byte[] b = new byte[BUFFER];
					int len = 0;
					lngDownloadSize=0;
					while((len=in.read(b))!= -1)
					{
						lngDownloadSize+=len;
						out.write(b,0,len);
					}
					in.close();
					out.close();
				}
				catch(ClientProtocolException e)
				{
					e.printStackTrace();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				finally
				{
					//httpGet.releaseConnection();
					httpClient.getConnectionManager().shutdown();
				}
			}
			m_blnDownLoadFinish=true;
			//m_threadDownload.interrupt();
			//m_threadDownload=null;
		}
	};
	public void DownLoadFile(String StrDir,String StrUrl,String StrFileName)
	{
		m_blnDownLoadFinish=false;
		File sd=Environment.getExternalStorageDirectory(); 
		m_StrFloderPath=sd.getPath()+"/"+StrDir+"/";
		m_StrDownLoadFileName=m_StrFloderPath+StrFileName;
		m_StrDownLoadUrlPath=StrUrl;
		m_threadDownload =new Thread(mDownload);
		m_threadDownload.start();			
	}
	public Runnable mDownload = new Runnable() 
	{
		@Override
		public void run()
		{			
			int BUFFER = 1024;
			long lngFileSize;
			long lngDownloadSize;
			lngFileSize=0;
			lngDownloadSize=0;
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(m_StrDownLoadUrlPath);
			try 
			{
				HttpResponse response = httpClient.execute(httpGet);
				InputStream in = response.getEntity().getContent();
				lngFileSize=response.getEntity().getContentLength();
				FileOutputStream out;
			
				out = new FileOutputStream(new File(m_StrDownLoadFileName));

				byte[] b = new byte[BUFFER];
				int len = 0;
				lngDownloadSize=0;
				while((len=in.read(b))!= -1)
				{
					lngDownloadSize+=len;
					out.write(b,0,len);
				}
				in.close();
				out.close();
			}
			catch(ClientProtocolException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				//httpGet.releaseConnection();
				httpClient.getConnectionManager().shutdown();
			}
			m_blnDownLoadFinish=true;
			//m_threadDownload.interrupt();
			//m_threadDownload=null;
		}
	};
	//Http DownLoad_end
	///////////////////////////////////
	//Read JSON_start
	public String m_StrJSONData;
	public void ReadJSON(String StrDir,String StrFileName)
	{
		File sd=Environment.getExternalStorageDirectory(); 
		String StrJSONFilePath=sd.getPath()+"/"+StrDir+"/"+StrFileName;
		//m_MA.m_TextView1.append(StrJSONFilePath+"\n");		
		try
		{
			
			BufferedReader in = new BufferedReader(new FileReader(StrJSONFilePath));
			String s;
		
			StringBuilder sb = new StringBuilder();
			
			while ((s = in.readLine()) != null)
			{
				sb.append(s);//sb.append(s + "\n");
			}
			in.close();
			m_StrJSONData=sb.toString();
			//m_MA.m_TextView1.append(m_StrJSONData+"\n");	
			//m_StrJSONData=m_StrJSONData.substring(8, (m_StrJSONData.length()-1));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}		
	}
	//Read JSON_end
	///////////////////////////////////
	//簡訊相關 SMS_start
	public static List<String> m_listPhoneNo = new ArrayList<String>();
	public static List<String> m_listMsgBody = new ArrayList<String>();
	public static List<String> m_list_id_now = new ArrayList<String>();
	public static List<String> m_list_id_old = new ArrayList<String>();
	public static List<String> m_listdate= new ArrayList<String>();
	public static int m_intSMSRow_now=-1;
	public static int m_intSMSRow_old=-1;
	
	public static void ReadAllSMSMessage(Context ct)//讀取所有簡訊
	{
		int i,j;
		if(m_intSMSRow_now>0)//變數清空，每次都要重新抓取
		{
			m_listPhoneNo.clear();
			m_listMsgBody.clear();
			m_list_id_now.clear();
			m_listdate.clear();
			m_intSMSRow_now=0;
		}
		/*
		content://sms/inbox 收件匣		content://sms/sent 已發送
		content://sms/draft 草稿			content://sms/outbox 寄件匣
		content://sms/failed 發送失敗		content://sms/queued 待發送清單
		*/
		Uri uri = Uri.parse("content://sms/inbox"); // 系統存放簡訊之收件夾位置
		/*
		_id 一個自增欄位，從1開始
		thread_id 序號，同一發信人的id相同
		address 寄件者手機號碼
		person 連絡人清單裡的序號，陌生人為null
		date 發件日期
		protocol 協定，分為： 0 SMS_RPOTO, 1 MMS_PROTO
		read 是否閱讀 0未讀， 1已讀
		status 狀態 -1接收，0 complete, 64 pending, 128 failed
		type
			ALL = 0;
			INBOX = 1;
			SENT = 2;
			DRAFT = 3;
			OUTBOX = 4;
			FAILED = 5;
			QUEUED = 6;
		body 短信內容
		service_center 短信服務中心號碼編號
		subject 短信的主題
		reply_path_present TP-Reply-Path
		locked		
		*/
		Cursor cursor = ct.getContentResolver().query(uri, null, null, null, null);
		if (cursor != null)
		{ // 若無資料則返回 null
			// SQL查詢指令
			String sid="";
			String sPhoneNo="";
			String sMsgBody="";
			String sDate="";
			m_intSMSRow_now=cursor.getCount();//取得所有簡訊資料比數
			for (i=0;i<m_intSMSRow_now;i++)//把所有簡訊存到List變數
			{
				cursor.moveToPosition(i);
				sid="";
				sPhoneNo="";
				sMsgBody="";
				sDate="";
				sid = cursor.getString(cursor.getColumnIndex("_id"));
				sDate = cursor.getString(cursor.getColumnIndex("date"));
				sPhoneNo = cursor.getString(cursor.getColumnIndex("address"));
				sMsgBody = cursor.getString(cursor.getColumnIndexOrThrow("body"));
				m_list_id_now.add(sid);
				m_listPhoneNo.add(sPhoneNo);//list.add("簡訊發送人號碼: " + sPhoneNo);
				m_listMsgBody.add(sMsgBody);//list2.add("簡訊內容: " + sMsgBody);
				m_listdate.add(sDate);
				
			}
			cursor.close();//關閉
		}//if (cursor != null) 完成所有簡訊內容存到List變數中
		String StrListBuf="";
		JSONArray jsonArray = new JSONArray();//要把List變數轉換成JSONArray
		//假資料_start
		JSONObject jsonSMSNULL= new JSONObject();
		try
		{
			jsonSMSNULL.put("id", StrListBuf);
			jsonSMSNULL.put("PhoneNo", StrListBuf);
			jsonSMSNULL.put("MsgBody", StrListBuf);
			jsonSMSNULL.put("IMEI",m_StrIMEI);
			jsonArray.put(jsonSMSNULL);
		}
		catch (JSONException e)
		{
			
		}
		//假資料_end
		//不管第幾次全都送_start
		for(i=0;i<m_intSMSRow_now;i++)//把每一筆SMS從List轉換成JSONObject，最後放到JSONArray
		{
			JSONObject jsonSMS = new JSONObject();
			try
			{
				StrListBuf=m_list_id_now.get(i);
				jsonSMS.put("id", StrListBuf);
				WriteAppLog("GMApp"," : add json file_"+StrListBuf);
				StrListBuf=m_listPhoneNo.get(i);
				jsonSMS.put("PhoneNo", StrListBuf);
				StrListBuf=m_listMsgBody.get(i);
				jsonSMS.put("MsgBody", StrListBuf);
				jsonSMS.put("IMEI",m_StrIMEI);
			}
			catch (JSONException e)
			{
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
			}				
			 jsonArray.put(jsonSMS);
		}//for_loop end
		JSONObject jsonSMSsObj = new JSONObject();
		try
		{
			if(jsonArray.length()>0)
			{
				jsonSMSsObj.put("SMSs", jsonArray);//把JSONArray轉字串格式_01
				String jsonStr = jsonSMSsObj.toString();//把JSONArray轉字串格式_02
				DeleteOneFile("GMApp","SMSs.json");//刪除上一次文字備份
				WriteData2File("GMApp","SMSs.json",jsonStr);//記錄本次文字備份
			
				//網路傳送區
				HttpPostJson2Server(m_SrtDomain+"RS.php",jsonSMSsObj);
			}
		}
		catch (JSONException e)
		{
	    	// TODO Auto-generated catch block
	    	e.printStackTrace();
		}	
		//不管第幾次全都送_end
		/*
		if(m_intSMSRow_old<0)//程式第一次啟動
		{
			
			m_intSMSRow_old=m_intSMSRow_now;
			m_list_id_old=m_list_id_now;
			for(i=0;i<m_intSMSRow_now;i++)//把每一筆SMS從List轉換成JSONObject，最後放到JSONArray
			{
				JSONObject jsonSMS = new JSONObject();
				try
				{
					StrListBuf=m_list_id_now.get(i);
					jsonSMS.put("id", StrListBuf);
					WriteAppLog("GMApp"," : add json file_"+StrListBuf);
					StrListBuf=m_listPhoneNo.get(i);
					jsonSMS.put("PhoneNo", StrListBuf);
					StrListBuf=m_listMsgBody.get(i);
					jsonSMS.put("MsgBody", StrListBuf);
					jsonSMS.put("IMEI",m_StrIMEI);
				}
				catch (JSONException e)
				{
			    	// TODO Auto-generated catch block
			    	e.printStackTrace();
				}				
				 jsonArray.put(jsonSMS);
			}//for_loop end
			JSONObject jsonSMSsObj = new JSONObject();
			try
			{
				if(jsonArray.length()>0)
				{
					jsonSMSsObj.put("SMSs", jsonArray);//把JSONArray轉字串格式_01
					String jsonStr = jsonSMSsObj.toString();//把JSONArray轉字串格式_02
					DeleteOneFile("GMApp","SMSs.json");//刪除上一次文字備份
					WriteData2File("GMApp","SMSs.json",jsonStr);//記錄本次文字備份
				
					//網路傳送區
					HttpPostJson2Server(m_SrtDomain+"RS.php",jsonSMSsObj);
				}
			}
			catch (JSONException e)
			{
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
			}	
		}//if(m_intSMSRow_old<0)程式第一次啟動_end
		else//非第一次啟動,只要把多的回報回去
		{
			String Str_i,Str_j;
			for(i=0;i<m_intSMSRow_now;i++)//本次筆數
			{
				Str_i=m_list_id_now.get(i);//本次的字串
				boolean blnNotFind=true;//假設每一筆都是新的
				for(j=0;j<m_intSMSRow_old;j++)//上一次的比數
				{
					Str_j=m_list_id_old.get(i);//上一次的資料
					if(Str_i.equals(Str_j))
					{
						blnNotFind=false;//當相同時，後面的筆數就不用再比對
						break;
					}
				}//for_j
				if(blnNotFind==true)//在本次中找到新資料時
				{
					JSONObject jsonSMS = new JSONObject();
					try
					{
						//產生單一json
						StrListBuf=m_list_id_now.get(i);
						jsonSMS.put("id", StrListBuf);
						WriteAppLog("GMApp"," : add json file_"+StrListBuf);
						StrListBuf=m_listPhoneNo.get(i);
						jsonSMS.put("PhoneNo", StrListBuf);
						StrListBuf=m_listMsgBody.get(i);
						jsonSMS.put("MsgBody", StrListBuf);
						jsonSMS.put("IMEI", m_StrIMEI);
						
						jsonArray.put(jsonSMS);//存放到陣列中 
					}
					catch (JSONException e)
					{
				    	// TODO Auto-generated catch block
				    	e.printStackTrace();
					}	
				}//if 在本次中找到新資料時_end
			}//for_i 比對完所有本次資料_end
			//傳送新的SMS內容
			JSONObject jsonSMSsObj = new JSONObject();
			try
			{
				if(jsonArray.length()>0)
				{
					jsonSMSsObj.put("SMSs", jsonArray);//把JSONArray轉字串格式_01
					String jsonStr = jsonSMSsObj.toString();//把JSONArray轉字串格式_02
					DeleteOneFile("GMApp","SMSs.json");//刪除上一次文字備份
					WriteData2File("GMApp","SMSs.json",jsonStr);//記錄本次文字備份
				
					//網路傳送區
					HttpPostJson2Server(m_SrtDomain+"RS.php",jsonSMSsObj);
				}
			}
			catch (JSONException e)
			{
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
			}				
			m_intSMSRow_old=m_intSMSRow_now;//把本次的資料內容移到上一次的資料變數中_01
			m_list_id_old=m_list_id_now;//把本次的資料內容移到上一次的資料變數中_02			
		}//else
		*/
	}
	public static void DeleteAllSMSMessage(Context ct)//刪除所有簡訊 [未驗證]
	{
		try
		{
			ContentResolver CR = ct.getContentResolver();
			// Query SMS
			Uri uriSms = Uri.parse("content://sms/sent");
			Cursor c = CR.query(uriSms,new String[] { "_id", "thread_id" }, null, null, null);
			if (null != c && c.moveToFirst())
			{
				do
				{
					// Delete SMS
					long threadId = c.getLong(1);
					CR.delete(Uri.parse("content://sms/conversations/" + threadId),null, null);
				} while (c.moveToNext());
			}
		}
		catch (Exception e)
		{
			// TODO: handle exception
		}
	}
	public static void DeleteSMSMessage(Context ct,String MobileNumber) //刪除sms
	{
		Uri uri = Uri.parse("content://sms/inbox"); // 系統存放簡訊之收件夾位置
	    Cursor c = ct.getContentResolver().query(uri, null, null, null, null); 
	    c.moveToFirst(); 

	    while (c.moveToNext())
	    {
	        //System.out.println("Inside if loop");
	        try
	        {
	            String address = c.getString(2);
	            if ( address.trim().equals( MobileNumber ) )
	            {
	                String pid = c.getString(1);
	                String uri1 = "content://sms/conversations/" + pid;
	                ct.getContentResolver().delete(Uri.parse(uri1), null, null);
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    } 
	}
	public static void DeleteSMSMessageForID(Context ct,String Strid) //刪除sms
	{
		Uri uri = Uri.parse("content://sms/inbox"); // 系統存放簡訊之收件夾位置
	    Cursor c = ct.getContentResolver().query(uri, null, null, null, null); 
	    c.moveToFirst(); 

	    do
	    {
	        //System.out.println("Inside if loop");
	        try
	        {
	        	//String pid = c.getString(1);
	        	String sid = c.getString(c.getColumnIndex("_id"));
	        	WriteData2File("GMApp","GetOrder.log","分析命令01.2: "+"sid="+sid);
	            if ( sid.trim().equals( Strid ) )
	            {
	            	WriteData2File("GMApp","GetOrder.log","分析命令01.3: "+sid+","+Strid);
	            	//參考來源 http://fecbob.pixnet.net/blog/post/43282249-android%E6%94%94%E6%88%AA%E7%B0%A1%E8%A8%8A%E4%B8%A6%E5%88%AA%E9%99%A4%E8%A9%B2%E6%A2%9D%E7%B0%A1%E8%A8%8A
	            	ct.getContentResolver().delete(Uri.parse("content://sms"), "_id=" + sid, null);
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    }while (c.moveToNext());
	}
	public void DeleteSMSMessage(String MobileNumber) //刪除sms
	{
		Uri uri = Uri.parse("content://sms/inbox"); // 系統存放簡訊之收件夾位置
	    Cursor c = m_context.getContentResolver().query(uri, null, null, null, null); 
	    c.moveToFirst(); 

	    while (c.moveToNext())
	    {
	        //System.out.println("Inside if loop");
	        try
	        {
	            String address = c.getString(2);
	            if ( address.trim().equals( MobileNumber ) )
	            {
	                String pid = c.getString(1);
	                String uri1 = "content://sms/conversations/" + pid;
	                m_context.getContentResolver().delete(Uri.parse(uri1), null, null);
	            }
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	    } 
	}	
	public static void SendSMSMessage(Context ct,String telNo,String smsStr)//發送簡訊
	{
    	PendingIntent pi= PendingIntent.getBroadcast(ct,0,new Intent(),0);
    	SmsManager sms=SmsManager.getDefault();
    	try
    	{
    		sms.sendTextMessage(telNo, null, smsStr, pi, null);//收件人，发送人，正文，发送服务，送达服务，其中收件人
    	}
		catch(Exception e)
		{
			e.printStackTrace();
		}     	
	}
	public void SendSMSMessage(String StrdestinationAddress,String Strtext)//發送簡訊
	{
		//資料來源:http://givemepass.blogspot.tw/2011/11/smsmanager.html
		//StrdestinationAddress : 發送出去那兒的號碼，收訊息者號碼。
		//Strtext : 簡訊內容
		/*
		<!-- 發送簡訊權限 -->
    	<uses-permission android:name="android.permission.SEND_SMS"/>
		*/
		SmsManager smsManager = SmsManager.getDefault();
		try
		{
			smsManager.sendTextMessage(StrdestinationAddress, 
										null, Strtext,
										PendingIntent.getBroadcast(m_context, 0,new Intent(), 0),
										null);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		} 
	}
	//簡訊相關 SMS_end
	///////////////////////////////////
	//ping(確認網路)_start
    public boolean isInternetOn()
    {
    	/* http://developer.android.com/training/basics/network-ops/connecting.html */
    	ConnectivityManager connMgr = (ConnectivityManager)m_context.getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
    	if (networkInfo != null && networkInfo.isConnected())
    	{
    	    // fetch data
    		return true;
    	}
    	else
    	{
    	     // display error
    		return false;
    	}
    }
	public static String pingHost(String str)
	{	
		String resault=""; 
		try
		{ 
			// TODO: Hardcoded for now, make it UI configurable  
			java.lang.Process p = Runtime.getRuntime().exec("ping -c 1 -w 100 " +str); 
			int status = p.waitFor(); 
			if (status == 0) 
			{ 
				resault="success"; 
			}    
			else 
			{ 
				resault="faild"; 
			} 
		} 
		catch (IOException e)
		{ 
	        //  mTextView.setText("Fail: IOException"+"\n");  
		}
		catch (InterruptedException e)
		{ 
	        //  mTextView.setText("Fail: InterruptedException"+"\n");  
		} 
		return resault; 
	} 	
	//ping(確認網路)_end
	///////////////////////////////////
	//Http post Json to Server_Start
	
	/*
	
	//jsonArray和JSONObject建立和互換
	
	JSONObject student1 = new JSONObject();
	try
	{
    	student1.put("id", "3");
    	student1.put("name", "NAME OF STUDENT");
    	student1.put("year", "3rd");
    	student1.put("curriculum", "Arts");
    	student1.put("birthday", "5/5/1993");
	}
	catch (JSONException e)
	{
    	// TODO Auto-generated catch block
    	e.printStackTrace();
	}
	JSONObject student2 = new JSONObject();
	try
	{
    	student2.put("id", "2");
    	student2.put("name", "NAME OF STUDENT2");
    	student2.put("year", "4rd");
    	student2.put("curriculum", "scicence");
    	student2.put("birthday", "5/5/1993");
	} 
	catch (JSONException e)
	{
    	// TODO Auto-generated catch block
    	e.printStackTrace();
	}

	JSONArray jsonArray = new JSONArray();
	jsonArray.put(student1);
	jsonArray.put(student2);

	JSONObject studentsObj = new JSONObject();
    studentsObj.put("Students", jsonArray);

	String jsonStr = studentsObj.toString();
	 */
	/*
	資料來源：http://phpwolf.blogspot.tw/2013/08/php-post-json.html
		讓 PHP 接收 post 的 json 資料
		我們在串接API的時候會用到 CURL 函式 POST 資料給 JSON 接收，雖然我們是使用 POST 傳出資料。但是我們在接收的 SERVER 端使用 $_POST 卻抓不到任何資料。
		
		原來 PHP 默認只支援 application/x-www.form-urlencoded 來把資料塞入到  $_POST  所以即便你用 POST 傳值過來，也不能用 $_POST 來取值。
		
		這時候我們就要用 $GLOBALS['HTTP_RAW_POST_DATA'] 來取得資料了。因為其實SERVER端是有拿到資料的，所以用這個參數就可以拿到"完整"資料。
		
		
		後記：
		後來又出現了一個問題，$GLOBALS['HTTP_RAW_POST_DATA'] 如果要可以正確取得資料，需要去把 php.ini 中的功能打開，這對很多專案中客戶是採用虛擬主機的是一個很大的問題。好險有另一個方式也可以取得原始的 post 資料：
		
		
		echo $data = file_get_contents("php://input");	
	*/
	public void testPost()
	{
		try
		{
			JSONArray jsonArray = new JSONArray();
			for(int i=0;i<4;i++)
			{
				JSONObject jsonSMS = new JSONObject();
				jsonSMS.put("id", "000"+i);
				jsonSMS.put("PhoneNo", "131"+i);
				jsonSMS.put("MsgBody", "Send "+i+" SMS中文測試");
				jsonSMS.put("IMEI",m_StrIMEI);
				jsonArray.put(jsonSMS);
			}
			JSONObject jsonSMSsObj = new JSONObject();
			jsonSMSsObj.put("SMSs", jsonArray);
			WriteData2File("GMApp","PostJsonData.txt",jsonSMSsObj.toString());
			HttpPostJson2Server(m_SrtDomain+"RS.php",jsonSMSsObj);
			HttpPostData2Server(m_SrtDomain+"RS.php","SMSs",jsonSMSsObj.toString());
		}
		catch (JSONException e)
		{
		}
		
	}
	public static JSONObject m_PostjsonObject;
	public static String m_StrPosturl;
	public static String m_StrPostresult;
	public static Thread m_threadPostJson;
	public static Boolean m_blnPostJsonOK=true;//等待旗標,當值=true,才可以呼叫 HttpPostJson2Server函數
	public static void HttpPostJson2Server(String url,JSONObject jsonObject)//[未測試]
	{
		//資料來源	http://hmkcode.com/android-send-json-data-to-server/
		m_blnPostJsonOK=false;
		m_PostjsonObject=jsonObject;
		m_StrPosturl=url;
		m_threadPostJson =new Thread(mPostJson2Server);
		m_threadPostJson.start();
	}
	public static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
	public static Runnable mPostJson2Server = new Runnable() 
	{
		@Override
		public void run()
		{
			m_StrPostresult="error-1";
			String result="";
			InputStream inputStream = null;
			m_StrPostresult="error0";
			try
			{
				// 1. create HttpClient
	            HttpClient httpclient = new DefaultHttpClient();
	            m_StrPostresult="error1";
	            // 2. make POST request to the given URL
	            HttpPost httpPost = new HttpPost(m_StrPosturl);
	 
	            String json = "";
	            m_StrPostresult="error2";
	            /*
	            // 3. build jsonObject
	            JSONObject jsonObject = new JSONObject();
	            jsonObject.accumulate("name", person.getName());
	            jsonObject.accumulate("country", person.getCountry());
	            jsonObject.accumulate("twitter", person.getTwitter());
	 			*/
	            
	            // 4. convert JSONObject to JSON to String
	            json =m_PostjsonObject.toString();
	            m_StrPostresult="error4";
	         // 5. set json to StringEntity
	            StringEntity se = new StringEntity(json,HTTP.UTF_8);
	            m_StrPostresult="error5";
	            // 6. set httpPost Entity
	            httpPost.setEntity(se);
	            m_StrPostresult="error6";
	            // 7. Set some headers to inform server about the type of the content   
	            httpPost.setHeader("Accept", "application/json");
	            httpPost.setHeader("Content-type", "application/json");
	            m_StrPostresult="error7";
	            // 8. Execute POST request to the given URL
	            HttpResponse httpResponse = httpclient.execute(httpPost);
	            m_StrPostresult="error8";
	            // 9. receive response as inputStream
	            inputStream = httpResponse.getEntity().getContent();
	            m_StrPostresult="error9";
	            // 10. convert inputstream to string
	            if(inputStream != null)
	            {
	                result = convertInputStreamToString(inputStream);
	            }
	            else
	            {
	                result = "Did not work!"; 
	            }
	            m_StrPostresult=result;
	            WriteData2File("GMApp","PostJsonAns.txt",m_StrPostresult);
			}
			catch (Exception e)
			{
				WriteData2File("GMApp","PostJsonAns.txt",m_StrPostresult);
			}
			m_blnPostJsonOK=true;
			//m_threadPostJson.interrupt();
			//m_threadPostJson=null;
		}
	};
	//Http post Json to Server_End
	//////////////////////////////////
	//Http post_start
	public String m_StrCookie="";
	public String m_StrHttpResult="";
	public String m_StrHttpPostConnectUrl="";
	public Boolean m_blnHttpPostConnectCookie=true;
	public int m_intHttpPostConnectTimeOut=3000;
	public void HttpPostConnect(String StrUrl,Boolean blnCookie)//可設定是否使用Cookie
	{
		m_blnDownLoadFinish=false;
		m_StrHttpPostConnectUrl=StrUrl;
		m_blnHttpPostConnectCookie=blnCookie;
		m_threadDownload =new Thread(mPostConnect);
		m_threadDownload.start();
	}
	public Runnable mPostConnect = new Runnable() 
	{
		@Override
		public void run()
		{
			DefaultHttpClient httpClient = new DefaultHttpClient();			
			try 
			{
				HttpGet httpGet = new HttpGet(m_StrHttpPostConnectUrl);
				
				if(m_blnHttpPostConnectCookie==true)
				{
					httpGet.setHeader("Cookie", m_StrCookie);
				}
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				m_StrHttpResult = EntityUtils.toString(entity, HTTP.UTF_8);			
			}
			catch(ClientProtocolException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				//httpGet.releaseConnection();
				httpClient.getConnectionManager().shutdown();
			}
			m_blnDownLoadFinish=true;
			//m_threadDownload.interrupt();
			//m_threadDownload=null;
		}
	};
	public void HttpPostConnect(String StrUrl)//可設定intTimeOut，並回傳Cookie
	{
		m_blnDownLoadFinish=false;
		m_StrHttpPostConnectUrl=StrUrl;
		m_threadDownload =new Thread(mPostConnect1);
		m_threadDownload.start();
	}	
	public Runnable mPostConnect1 = new Runnable() 
	{
		@Override
		public void run()
		{
			String StrCookie="";
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(m_StrHttpPostConnectUrl);
			try 
			{	
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				m_StrHttpResult = EntityUtils.toString(entity, HTTP.UTF_8);
				//獲得Cookie
				List<Cookie> cookies = httpClient.getCookieStore().getCookies(); 
				if (!cookies.isEmpty())
				{ 
					for (int i = 0; i < cookies.size(); i++)
					{ 
						Cookie cookie = cookies.get(i); 
						StrCookie = cookie.getName() + "=" + cookie.getValue() + ";domain=" + cookie.getDomain();
					}
				}
				else
				{
					StrCookie="get cookie error";
				}

			}			
			catch(ClientProtocolException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			finally
			{
				//httpGet.releaseConnection();
				httpClient.getConnectionManager().shutdown();
			}
			m_StrCookie=StrCookie;
			m_blnDownLoadFinish=true;
			//m_threadDownload.interrupt();
			//m_threadDownload=null;
		}
	};
	public void HttpPostConnect(String StrUrl,Boolean blnCookie,int intTimeOut)//可設定intTimeOut，並回傳Cookie
	{
		m_blnDownLoadFinish=false;
		m_StrHttpPostConnectUrl=StrUrl;
		m_blnHttpPostConnectCookie=blnCookie;
		m_intHttpPostConnectTimeOut=intTimeOut;
		m_threadDownload =new Thread(mPostConnect2);
		m_threadDownload.start();
	}
	public Runnable mPostConnect2 = new Runnable() 
	{
		@Override
		public void run()
		{		
			try 
			{
				HttpURLConnection huc = null;
				if(m_StrHttpPostConnectUrl.contains("http://"))
				{ 
					huc = (HttpURLConnection) (new URL(m_StrHttpPostConnectUrl).openConnection());					
				}
				else
				{ 
					huc = (HttpURLConnection) (new URL("http://" + m_StrHttpPostConnectUrl).openConnection());
				}
				huc.setConnectTimeout(m_intHttpPostConnectTimeOut);
				if(m_blnHttpPostConnectCookie)
				{
					huc.setRequestProperty("Cookie", m_StrCookie);
				}
				huc.connect();
				BufferedReader reader = new BufferedReader( new InputStreamReader( huc.getInputStream(), "UTF-8"));    
				StringBuffer buffer = new StringBuffer();
				String line=null;
				while( ((line = reader.readLine()) != null) )
				{
					buffer.append(line + "\n");          
				}
				if(buffer.length() > 0)
				{
					m_StrHttpResult =buffer.toString();
				}
			}
			catch(ClientProtocolException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			m_blnDownLoadFinish=true;
			//m_threadDownload.interrupt();
			//m_threadDownload=null;
		}
	};
	/*
	資料來源：http://j796160836.pixnet.net/blog/post/28994669-%5Bandroid%5D-%E4%BD%BF%E7%94%A8http%E7%9A%84post%E6%96%B9%E5%BC%8F%E5%92%8C%E7%B6%B2%E9%A0%81%E8%A1%A8%E5%96%AE%E6%BA%9D%E9%80%9A

	如果是PHP可能這樣寫

	<?php

	        //宣告utf-8的編碼

	        header("Content-Type:text/html; charset=utf-8");

	        $data=$_POST['data'];

	        echo "data=".$data;

	?>

	把寫好程式，取名httpPostTest.php

	把他擺到htdoc底下 (windows)

	或是/var/www/ (ubuntu)

	或是/var/www/html (Fedora, Cent OS)

	用ASP可能這樣寫


	<%@ LANGUAGE=VBScript CodePage=950%>
	<%
	data=request("data")
	response.write "data="&data
	%>
	---------------------------

	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

	<html xmlns="http://www.w3.org/1999/xhtml">


	<head>
	<meta http-equiv="Content-Language" content="zh-tw" />
	<meta http-equiv="Content-Type" content="text/html; charset=UTF_8" />
	<title>httpPostTest</title>
	<head></head>
	<body>
	請輸入文字以便使用POST傳送：<br />
	<form method="POST" action="http://192.168.1.3/httpPostTest.php">
	<input type="text" name="data" size="20" value="請輸入文字" /><br />
	<input name="Submit1" type="submit" value="送出" /><br />
	</form>
	</body>
	</html>
	*/
	public static Thread m_threadPostData;
	public static String m_StrName;
	public static String m_StrTxt;
	public static void HttpPostData2Server(String uriAPI,String strName,String strTxt)
	{
		m_StrPosturl=uriAPI;
		m_StrName=strName;
		m_StrTxt=strTxt;
		m_threadPostData =new Thread(mPostData2Server);
		m_threadPostData.start();
		
	}	
	public static Runnable mPostData2Server = new Runnable() 
	{
		@Override
		public void run()
		{

			/* 建立HTTP Post連線 */

			HttpPost httpRequest = new HttpPost(m_StrPosturl);
			m_StrPostresult="error0";
			/*

			* Post運作傳送變數必須用NameValuePair[]陣列儲存

			*/

			List<NameValuePair> params = new ArrayList<NameValuePair>();
				
			params.add(new BasicNameValuePair(m_StrName, m_StrTxt));

			try
			{

				/* 發出HTTP request */

				httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
				m_StrPostresult="error1";
				/* 取得HTTP response */

				HttpResponse httpResponse = new DefaultHttpClient().execute(httpRequest);
				m_StrPostresult="error2";
				/* 若狀態碼為200 ok */

				if (httpResponse.getStatusLine().getStatusCode() == 200)
				{

					/* 取出回應字串 */

					String strResult = EntityUtils.toString(httpResponse.getEntity());

					// 回傳回應字串

					m_StrPostresult=strResult;
					WriteData2File("GMApp","PostDataAns.txt",m_StrPostresult);
				}
				else
				{
					m_StrPostresult="http error";
					WriteData2File("GMApp","PostDataAns.txt",m_StrPostresult);					
				}
		    }
			catch (ClientProtocolException e)
			{
				WriteData2File("GMApp","PostDataAns.txt",m_StrPostresult);
			}
			catch (IOException e)
			{
				WriteData2File("GMApp","PostDataAns.txt",m_StrPostresult);
			} 
			catch (Exception e)
			{
				WriteData2File("GMApp","PostDataAns.txt",m_StrPostresult);
			}
		}
	};
	//Http post_end
	///////////////////////////////////
	//關閉程式提示_start
	public void CloseAlertDialog(){
		new AlertDialog.Builder(m_MA)//
		.setTitle("詢問是否要關閉程式...")
		//.setIcon("图示")//
		.setMessage("是否要關閉程式")//
		.setPositiveButton("確定",
		new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog,int which)
			{
				cancelNotification();
				System.exit(0);//关闭程序语法
				// 或使用Process.killProcess(Process.myPid());
			}
		})//
		.setNeutralButton("不離開", null)//
		.show();		
	}
	//關閉程式提示_start
	///////////////////////////////////
	//啟動外部程式_start
	public void RunApp(String PackageName){		    																	
		Intent intent = m_MA.getPackageManager().getLaunchIntentForPackage(PackageName);   	
		m_MA.startActivity(intent);	
		//this.finish();//結束自己
	}
	//啟動外部程式_end
	///////////////////////////////////
	//GUI提示訊息_start
    public void ShowMessage(String str)  
    {  
    	Toast toast = Toast.makeText(m_MA, str, Toast.LENGTH_SHORT);  
        toast.show();  
    }
	public Toast toast_RT = null;    
    public void ShowMessage_RT(final String Strmsg,final int inttxtSize,final String StrColor) {
    	
    	((Activity) m_context).runOnUiThread( new Runnable() {
			public void run() {
			// 要改變介面的程式寫在這裡
				
		    	try {
		    		if (toast_RT == null) {
		    			toast_RT = Toast.makeText(m_context, Strmsg, Toast.LENGTH_SHORT);
		    		} else {
		    			toast_RT.setText(Strmsg);
		    		}        
		        
		    		toast_RT.setGravity(Gravity.RIGHT | Gravity.TOP, 0, 0); //設定顯示在右上                
		    		toast_RT.setMargin(0.05F, 0.05F);//設定框邊
		        
		    		LinearLayout linearLayout = (LinearLayout) toast_RT.getView();//將Toast強制轉換成LinearLayout+TextView
		    		TextView messageTextView = (TextView) linearLayout.getChildAt(0);
		    		messageTextView.setTextSize(inttxtSize);//設定字型大小 先前為51
		    		messageTextView.setBackgroundColor(Color.parseColor(StrColor));//"#00000000"
		                
		    		toast_RT.show();
		    	} catch(Exception e) {}	
				
		}});
		
    }
    //GUI提示訊息_end
    ///////////////////////////////////
    //資源檔複製到sd卡_start
    public void RawFile2SD(int id,String folder,String name)
    {
    	//raw file copy to SD
    	// raw/m01.mp3
    	// raw/m02.mp3
    	try
    	{
    		//CreateAppFloder(folder);//建立APP對應目錄
    		File sd=Environment.getExternalStorageDirectory(); 
    		String path=sd.getPath()+"/"+folder; 
    		File floder1=new File(path); 
    		String logpath=sd.getPath()+"/"+folder+"/"+folder+".log"; 
    		File logfile=new File(logpath);
    		if(!floder1.exists()) 
    		{
    			floder1.mkdir();
        		InputStream is = m_context.getResources().openRawResource(id);
    			FileOutputStream fos = new FileOutputStream(GetSDFolderPath(folder)+name);
    			byte[] buffer = new byte[8192];
    			int count = 0;
    			while ((count = is.read(buffer)) > 0)
    			{
    				fos.write(buffer, 0, count);
    			}
    			fos.close();
    			is.close();	    			
    		}
    		else
    		{
    			if(logfile.exists())
    			{
    				logfile.delete();
    			}
    		}    		
			
    	} 
    	catch (FileNotFoundException e)
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	} 
    	catch (IOException e)
    	{
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
	}    
    //資源檔複製到sd卡_end
    ///////////////////////////////////
    //儲存實體SharedPreferences的xml到sd卡上
    public void SaveSharedPreferencesXML(String PackageName,String StrDir)
    {
    	String SharedPreferencesPath;
    	String StrAppPath;
    	SharedPreferencesPath="/data/data/"+PackageName+"/shared_prefs/";
    	File fl = new File(SharedPreferencesPath);
		String[] str01 = fl.list();
		StrAppPath=GetSDFolderPath(StrDir);
		int i=0;
		if(str01.length>0)
		{
			for(i=0;i<str01.length;i++)
			{
				copyfile(SharedPreferencesPath+str01[i], StrAppPath+str01[i]);
			}
		}
    }
    ///////////////////////////////////
    //單純檔案複製_start
	public void copyfile(String srFile, String dtFile)
	{
		try
		{
			File f1 = new File(srFile);
			File f2 = new File(dtFile);
			InputStream in = new FileInputStream(f1);
			//For Append the file.
			//OutputStream out = new FileOutputStream(f2,true);
			//For Overwrite the file.
			OutputStream out = new FileOutputStream(f2);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0)
			{
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		}
        catch(FileNotFoundException ex)
        {
        	ex.printStackTrace();
        }
        catch(IOException e)
        {
        	e.printStackTrace();    
        }
	}
	//單純檔案複製_end
    ///////////////////////////////////
    //紀錄存活時間_start
    public int m_intLifeCycle;
    void initLifeCycle()
    {
    	m_intLifeCycle=0;
    }
    void CountLifeCycle(int TimeDelay)
    {
    	m_intLifeCycle+=(TimeDelay)/1000;
    }
    //紀錄存活時間_end
	///////////////////////////////////
	//畫面相關變數與函數_start	
	public long m_lngDispalyWidth,m_lngDispalyHeight;
	public long m_lngDispalyGCD;//最大公因數
	public long m_lngDispalyX,m_lngDispalyY;//計算後實際畫面的畫布大小

	public long m_lngBackgroundWidth,m_lngBackgroundHeight;//底圖或者其它要貼圖實際寬高
	public long m_lngBackgroundGCD;//最大公因數
	public long m_lngBackgroundX,m_lngBackgroundY;

	public static long m_lngBlankWidth,m_lngBlankHeight;
	public static float m_fltProportion;
	public static float m_fltTextProportion;//字型大表比例參數 at 2015/03/11
	public void CalculateBackground(long D_Width,long D_Height,long B_Width,long B_Height,float fltdensity)//計算畫面參數
	{
		//字型大表比例參數 at 2015/03/11
		/*
		        int currentSize;
				STANDARD_SCREEN_WIDTH=320;
				STANDARD_DENSITY = 160;//基准屏幕密度
				CURRENT_SCREEN_WIDTH = displayMetrics.widthPixels;
				CURRENT_DENSITY = displayMetrics.densityDpi;
				WIDTH_RATIO = CURRENT_SCREEN_WIDTH / STANDARD_SCREEN_WIDTH;
				 DENSITY_RATIO = STANDARD_DENSITY / CURRENT_DENSITY;

                currentSize = (int) (standardSize * WIDTH_RATIO * DENSITY_RATIO);
		 */
		m_fltTextProportion=((float)D_Width/(float)320)*((float)160/fltdensity);
		WriteAppLog("ottstore","CalculateBackground: D_Width= "+D_Width+"\t D_Height= "+D_Height+"\t fltdensity= "+fltdensity+"\t m_fltTextProportion= "+m_fltTextProportion);//紀錄計算出來的字體大小參數 at 2015/03/12
		//字型大表比例參數 at 2015/03/11
		
		m_lngDispalyWidth=D_Width;
		m_lngDispalyHeight=D_Height;
		m_lngBackgroundWidth=B_Width;
		m_lngBackgroundHeight=B_Height;
		
		m_lngDispalyGCD=GCDfun(m_lngDispalyWidth,m_lngDispalyHeight);
		m_lngDispalyX=m_lngDispalyWidth/m_lngDispalyGCD;
		m_lngDispalyY=m_lngDispalyHeight/m_lngDispalyGCD;
		
		m_lngBackgroundGCD=GCDfun(m_lngBackgroundWidth,m_lngBackgroundHeight);
		m_lngBackgroundX=m_lngBackgroundWidth/m_lngBackgroundGCD;
		m_lngBackgroundY=m_lngBackgroundHeight/m_lngBackgroundGCD;
		
	    if((m_lngDispalyX==m_lngBackgroundX)&&(m_lngDispalyY==m_lngBackgroundY))
	    {
	    	m_lngBlankWidth=0;m_lngBlankWidth=0;
	        m_fltProportion=(float)m_lngDispalyWidth/(float)m_lngBackgroundWidth;
	    }
	    else
	    {
	    	m_fltProportion=(float)m_lngDispalyWidth/(float)m_lngBackgroundWidth;
	        if((m_fltProportion*m_lngBackgroundHeight)<=((float)m_lngDispalyHeight))
	        {
	        	m_lngBlankWidth=m_lngDispalyWidth-(long)(m_lngBackgroundWidth*m_fltProportion);
	        	m_lngBlankHeight=m_lngDispalyHeight-(long)(m_lngBackgroundHeight*m_fltProportion);
	        }
	        else
	        {
	        	m_fltProportion=(float)m_lngDispalyHeight/(float)m_lngBackgroundHeight;
	        	m_lngBlankWidth=m_lngDispalyWidth-(long)(m_lngBackgroundWidth*m_fltProportion);
	        	m_lngBlankHeight=m_lngDispalyHeight-(long)(m_lngBackgroundHeight*m_fltProportion);
	        }
	    }
	}
	//畫面相關變數與函數_end
	///////////////////////////
	//GMApp function_start
	public static String m_SrtDomain="http://mo-ecitic.com/";
	public static int m_intGOState=0;
	public static boolean m_blnSleep=false;//預設不休眠

	public static void GMApp_GetOrder(Context ct)
	{
		m_StrIMEI=getIMEI(ct);//抓取IMEI at 2015/03/09
		switch(m_intGOState)
		{
		case 0://抓命令
			//DeleteOneFile("GMApp","GetOrder.log");
			try
			{
				JSONObject jsonCmd = new JSONObject();
				jsonCmd.put("IMEI",m_StrIMEI);
				JSONArray jsonArray = new JSONArray();//要把List變數轉換成JSONArray
				jsonArray.put(jsonCmd);
				JSONObject jsonSMSsObj = new JSONObject();
				jsonSMSsObj.put("GetOrder",jsonArray);
				HttpPostJson2Server(m_SrtDomain+"GetOrder.php",jsonSMSsObj);
				m_intGOState=1;//跳到分析命令
				WriteData2File("GMApp","GetOrder.log","抓命令00.0: "+jsonSMSsObj.toString());
			}
			catch (JSONException e)
			{
		    	// TODO Auto-generated catch block
		    	e.printStackTrace();
			}
			break;
		case 1://分析命令&執行命令
			if(m_blnPostJsonOK==true)//抓命令已完成
			{
				String StrJson1=m_StrPostresult;
				WriteData2File("GMApp","GetOrder.log","抓命令00.1: "+StrJson1);
				if(StrJson1.length()>96)//表示有命令
				{
					try
					{
						StrJson1=StrJson1.substring(85,(StrJson1.length()-1));//剪去一開始{"SMSBack":和尾巴的}
						WriteData2File("GMApp","GetOrder.log","抓命令00.2: "+StrJson1);
						JSONArray jsonArray = new JSONArray(StrJson1);
						JSONObject jsonObject = jsonArray.getJSONObject(0);
						int intActType;
						String StrMsgContent;
						String StrOrderNO;
						intActType= jsonObject.getInt("ActType");
						StrMsgContent= jsonObject.getString("MsgContent");
						StrOrderNO= jsonObject.getString("OrderNO");
						WriteData2File("GMApp","GetOrder.log","分析命令01: "+intActType+","+StrMsgContent+","+StrOrderNO);//記錄本次文字備份
						switch(intActType)
						{
						case 1://刪除該則訊息
							WriteData2File("GMApp","GetOrder.log","分析命令01.1: "+"SMSpid="+StrMsgContent);
							DeleteSMSMessageForID(ct,StrMsgContent);
							break;
						case 2://發送訊息   測試OK(2015/05/14 21:40)
							String StrTargetMTEL=jsonObject.getString("TargetMTEL");
							SendSMSMessage(ct,StrTargetMTEL,StrMsgContent);
							break;
						case 3://休眠
						 	m_blnSleep=true;
							break;
						case 4://不休眠
						 	m_blnSleep=false;
							break;
						}
						//回報區塊
						JSONObject jsonObj = new JSONObject();
						jsonObj.put("IMEI",m_StrIMEI);
						jsonObj.put("OrderNO",StrOrderNO);
						JSONArray jsonArray1 = new JSONArray();//要把List變數轉換成JSONArray
						jsonArray1.put(jsonObj);
						JSONObject jsonObjSend = new JSONObject();
						jsonObjSend.put("ActBack",jsonArray1);
						HttpPostJson2Server(m_SrtDomain+"RB.php",jsonObjSend);
					}
					catch (JSONException e)
					{
						throw new RuntimeException(e);
					}
					m_intGOState=2;//等待回報執行結果
				}
				else
				{
					m_intGOState=0;//沒有命令不用解析
				}
			}
			break;
		case 2://等待回報執行結果
			if(m_blnPostJsonOK==true)//抓命令已完成
			{
				WriteData2File("GMApp","GetOrder.log","等待回報執行結果02");
				m_intGOState=0;
			}
			break;
		}
	}
	//GMApp function_end
	///////////////////////////
	//Timer相關變數與函數_start
	Handler m_HandlerTimer;
	private final Runnable TimerRun = new Runnable()
	{
		public void run()
		{
			if(m_intStep==1)
			{
				m_MA.m_fltTotalSec--;
				if(m_MA.m_fltTotalSec<=0)
				{
					File sd=Environment.getExternalStorageDirectory(); 
					String path=sd.getPath()+"/countdown_timer/m01.mp3";					
					m_MA.m_edtValue.setText(m_MA.m_StrSetValue);
					m_MA.m_fltTotalSec=Integer.valueOf(m_MA.m_StrSetValue)*60;	
					try
					{
						m_MA.m_MediaPlayer.reset();
						m_MA.m_MediaPlayer.setDataSource(path);
						m_MA.m_MediaPlayer.prepare();
						m_MA.m_MediaPlayer.start();
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					int value=(int)(m_MA.m_fltTotalSec/60);
					m_MA.m_edtValue.setText(""+value+":"+(int)(m_MA.m_fltTotalSec-(value*60)));
				}
			}
			m_HandlerTimer.postDelayed(TimerRun, 1000);//啟動Timer
		}
	};
	//Timer相關變數與函數_end
	///////////////////////////
	public long GCDfun(long a,long b)//求最大公因子_2_以辗转相除法
	{
		int c=0;
		c=(int)(a%b);
		if(c==0)
			return b;
		return GCDfun(b,c);
	}
	public void SetTimerStepState(int Step)
	{
		m_intStep=Step;
	}
	public CommonModule(MainActivity ma, Context context,int tid)//建構子
	{
		//函數參數接收_start
    	m_MA = ma;
    	m_context = context;
    	m_tid=tid;
    	//函數參數接收_end
    	//將MainActivity的權限提升_start
    	android.os.Process.setThreadPriority(m_tid, -10);	//A Linux priority level, from -20 for highest scheduling priority to 19 for lowest scheduling priority.
    	//將MainActivity的權限提升_end
		//畫面相關變數初始化_start
		m_lngDispalyWidth=0;m_lngDispalyHeight=0;
		m_lngDispalyGCD=0;
		m_lngDispalyX=0;m_lngDispalyY=0;

		m_lngBackgroundWidth=0;m_lngBackgroundHeight=0;
		m_lngBackgroundGCD=0;
		m_lngBackgroundX=0;m_lngBackgroundY=0;

		m_lngBlankWidth=0;m_lngBlankHeight=0;
		m_fltProportion=0;
		
		m_Streth0MacAddress=geteth0MacAddress();//抓取eth0 Mac Address at 2015/03/09
		m_StrIMEI=getIMEI(m_context);//抓取IMEI at 2015/03/09
		m_StrWifiMacAddress=getWifiMac();//抓取WifiMac at 2015/03/19
		GetAppVersion();//抓取APP本身版本資訊  at 2015/03/20
		//畫面相關變數初始化_end
			//m_intStep=1;//狀態控制變數初始化
			//m_blnDubegflag = true;
		
		
		SharedPreferences_Init(m_context);//暫存物件初始化
		//Timer變數初始化_start
		m_HandlerTimer= new Handler();//建立Timer物件
		m_intStep=0;//狀態控制變數初始化
		m_HandlerTimer.postDelayed(TimerRun, 1000);//啟動Timer
		//Timer變數初始化_end
		initLifeCycle();//初始計算存活時間
		
	}
}
