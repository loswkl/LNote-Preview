package com.example.lnote;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import android.R.layout;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Hi extends Activity {

	LinearLayout bl;
	SharedPreferences Start;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hi);
        bl=(LinearLayout)findViewById(R.id.bl);
        
        Start=getSharedPreferences("LNoteStart", MODE_PRIVATE);
        Intent intent = new Intent(Hi.this,Zc.class);
        if(Start.getString("Start", "").toString().equals("1")){
        	intent = new Intent(Hi.this,MainActivity.class);
        	
        }else{
        	intent = new Intent(Hi.this,Zc.class);
        	
        }
        
        final Intent inten=intent;

        Timer time = new Timer();  
        
        TimerTask tk = new TimerTask() {  
          

        
            @Override  
            public void run() {  
                // TODO Auto-generated method stub  
            	Bundle bundle = new Bundle();
                inten.putExtras(bundle);//将Bundle添加到Intent,也可以在Bundle中添加相应数据传递给下个页面,例如：bundle.putString("abc", "bbb");
                startActivityForResult(inten, 0);// 跳转并要求返回值，0代表请求值(可以随便写)
                //startActivity(intent);  
                finish();  
            }  
        };time.schedule(tk, 1000); 
      
        
        

	   
        
    }

    protected void loadurl(WebView view, String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {

       
        return true;
    }

}
