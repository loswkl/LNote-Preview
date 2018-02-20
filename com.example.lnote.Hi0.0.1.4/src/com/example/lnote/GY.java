package com.example.lnote;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class GY extends Activity {

	public static final int SHOW_RESPONSE = 0;
	String strbb;
	WebView tz;
	TextView gx;
	//int ail=0;
    //String strbb;
    String lsv="0.0.1.4";
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

    	
	    //检测更新-支线获取
    	new Thread(new Runnable() {
		    
            public void run() {
		try  
		{  
		URL url = new URL("https://loswkl.win/lnote/lnotev");  
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoInput(true);  
		//conn.setDefaultUseCaches(false);//禁用缓存
		conn.setConnectTimeout(10000);  
		conn.setRequestMethod("GET");  
		conn.setRequestProperty("accept", "*/*");  
		String location = conn.getRequestProperty("location");  
		int resCode = conn.getResponseCode();  
		conn.connect();  
		InputStream stream = conn.getInputStream();  
		byte[] data=new byte[102400];  
		int length=stream.read(data);  
		String str=new String(data,0,length); 
		//在子线程中将Message对象发出去
		Message message = new Message();
	    message.obj = str.toString();
        handler.sendMessage(message);
		conn.disconnect();  
		System.out.println(str);  
		stream.close();  
		}  
		catch(Exception ee)  
		{  
		//System.out.print("ee:"+ee.getMessage());   
		}      
        }
     }).start();//这个start()方法不要忘记了        
    	
    	
    	
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gy);
        gx=(TextView)findViewById(R.id.TextView01);
        
        
        tz = (WebView)findViewById(R.id.webView1);
        tz.getSettings().setJavaScriptEnabled(true);
        tz.setScrollBarStyle(0);
        // 启用支持javascript 
        WebSettings settings = tz.getSettings(); 
        settings.setJavaScriptEnabled(true); 
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
	 
       // tz.loadUrl("http://lnote.flzhan.com/");
       
        tz.loadUrl("https://loswkl.win/lnotegy");
        
        tz.setWebViewClient(new WebViewClient()
        {
        	public boolean shouldOverrideUrlLoading(final WebView view, final String url) {
  			  
        		loadurl(view,url);
        		return true;  
        	}
        });

    }
    public void loadurl(final WebView view,final String url){
    	new Thread(){
    		public void run(){
    			view.loadUrl(url);
    
    		}
    	}.start();
    	
    	

    	gx.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		     	 //判断是否有新版本
		   		if(strbb.equals(lsv)){
		   			Toast.makeText(getApplicationContext(), "软件已为最新版", Toast.LENGTH_SHORT).show();
		   	
		   		}else{
		   		
		   			AlertDialog.Builder b= new AlertDialog.Builder(GY.this);
		   			b.setTitle("更新");
		   			b.setMessage("已发现最新版："+strbb);
		   	
		   			
		   			b.setPositiveButton("确认", new DialogInterface.OnClickListener() {
		   		
		   				public void onClick(DialogInterface arg0, int arg1) {
		   					// TODO Auto-generated method stub
		   					Intent intent = new Intent();        
		   					intent.setAction("android.intent.action.VIEW");    
		   					Toast.makeText(getApplicationContext(), "我们将跳转到下载页面", Toast.LENGTH_SHORT).show();
		   					Uri content_url = Uri.parse("https://loswkl.win/lnotexz");    //http://a.loswkl.xyz/lapp/LStudy.ap
		   					intent.setData(content_url);  
		   					startActivity(intent);
		   					
		   					
		   				}
		   			});
		   			b.setNegativeButton("取消", new DialogInterface.OnClickListener() {
		   				
		   				public void onClick(DialogInterface arg0, int arg1) {
		   					// TODO Auto-generated method stub
		   			
		   				}
		   			});
		   			b.show();
		   		}
		   		
		   		
		   		
		    	
			}
		});
  
    	

    	
    	
    	
    	
    	
    	
    	
    }
    
    
    
    
    //检测更新-获取支线信息
    private Handler handler = new Handler() {
   	 
        @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
            switch (msg.what) {
            case SHOW_RESPONSE:
                String response = (String) msg.obj;
                strbb=response;
                break;

            default:
                break;
            }            
        }

    };
    
    
    
}