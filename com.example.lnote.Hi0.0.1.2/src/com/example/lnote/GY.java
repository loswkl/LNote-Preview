package com.example.lnote;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class GY extends Activity {

	public static final int SHOW_RESPONSE = 0;
	String strbb;
	WebView tz;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gy);
        
        
        tz = (WebView)findViewById(R.id.webView1);
        tz.getSettings().setJavaScriptEnabled(true);
        tz.setScrollBarStyle(0);
        // ∆Ù”√÷ß≥÷javascript 
        WebSettings settings = tz.getSettings(); 
        settings.setJavaScriptEnabled(true); 
	 
        tz.loadUrl("http://lnote.flzhan.com/");

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
    }
}