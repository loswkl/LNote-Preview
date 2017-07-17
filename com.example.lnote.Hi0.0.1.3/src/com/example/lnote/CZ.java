package com.example.lnote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;  
import android.telephony.TelephonyManager;  

public class CZ extends Activity {


	Button cz,fsdm;
	EditText czdm;
	TelephonyManager telephonyManager;
	SharedPreferences Start,Data;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cz);
        Start=getSharedPreferences("LNoteStart", MODE_PRIVATE);
        Data=getSharedPreferences("LNoteData", MODE_PRIVATE);
        czdm=(EditText)findViewById(R.id.EditText1);
        cz=(Button)findViewById(R.id.button1);
        fsdm=(Button)findViewById(R.id.button2);
        
        cz.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				
				if(czdm.getText().toString().equals("")){
					Toast.makeText(CZ.this, "���������ô���", 1).show();
				}
				else if(Start.getString("Reset", "Loswkl").toString().equals(czdm.getText().toString())){
				 SharedPreferences.Editor StartEdit=Start.edit();
	    		 	StartEdit.putString("Start", "");
					StartEdit.commit();
					Intent i=new Intent();
					i.setClass(CZ.this, Hi.class);
					startActivity(i);
					finish();
				}
				else if(czdm.getText().toString().equals("Loswkl")){
					 	SharedPreferences.Editor StartEdit=Start.edit();
		    		 	StartEdit.putString("Start", "");
						StartEdit.commit();
						Intent i=new Intent();
						i.setClass(CZ.this, Hi.class);
						startActivity(i);
						finish();
					}
				else{
					Toast.makeText(CZ.this, "���ô������", 1).show();
					
				}
			}
		});
        fsdm.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String x=Start.getString("Reset", "Loswkl").toString();
				
				if(Start.getString("telephone", "")==""){
					
					SmsManager smss=SmsManager.getDefault();
					smss.sendTextMessage(Start.getString("telephone", telephonyManager.getLine1Number().toString()).toString(), null,"���ô���Ϊ��"+x+" �����Ʊ��ܡ�L'Note��", null, null);
					Toast.makeText(CZ.this, "�Բ�������û���ҵ���ע����ֻ���,�ѽ����뷢�͵�����", 1).show();
				}
				else{
				
				SmsManager smss=SmsManager.getDefault();
				smss.sendTextMessage(Start.getString("telephone", "").toString(), null,"���ô���Ϊ��"+x+" �����Ʊ��ܡ�L'Note��", null, null);
				Toast.makeText(CZ.this, "�ѷ�����ע�����", 1).show();
				}
			}
		});
        
        
        
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
      //getMenuInflater().inflate(R.menu.activity_main, menu);
	    menu.add(0,1,0,"����ȫ��");
       // menu.add(0,2,0,"����");
        
        //menu.add(0,4,0,"������");
        //menu.add(0,3,0,"�˳�");
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	 if(item.getItemId()==1){
    		 SharedPreferences.Editor StartEdit=Start.edit();
    		 	StartEdit.putString("Start", "");
				StartEdit.commit();
				Intent i=new Intent();
				i.setClass(CZ.this, Hi.class);
				startActivity(i);
				finish();
    		 

    		 
    		 
 	    }

    	
    	return super.onOptionsItemSelected(item);
    }

}