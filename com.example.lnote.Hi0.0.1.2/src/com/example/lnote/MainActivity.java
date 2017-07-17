package com.example.lnote;

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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.text.Editable.Factory;

public class MainActivity extends Activity {

	
	EditText e1,e2;
	Button b1,b2;
	SharedPreferences Data,Start;
	CheckBox c1,c2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        e1=(EditText)findViewById(R.id.editText1);
        e2=(EditText)findViewById(R.id.editText2);
        b1=(Button)findViewById(R.id.button1);
        b2=(Button)findViewById(R.id.button2);
        
        Start=getSharedPreferences("LNoteStart", MODE_PRIVATE);
        Data=getSharedPreferences("LNoteData", MODE_PRIVATE);
        e1.setText(Data.getString("UserName", ""));
        
        b1.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if(e1.getText().toString().equals("")){
					Toast.makeText(MainActivity.this, "请输入用户名", 1).show();
				}
				
				else if(e2.getText().toString().equals("")){
					Toast.makeText(MainActivity.this, "请输入密码", 1).show();
					
				}
				else if(e2.getText().toString().equals(Data.getString("Password", ""))){
					Toast.makeText(MainActivity.this, "登陆成功", 1).show();
					Intent i=new Intent();
					i.setClass(MainActivity.this, Note.class);
					startActivity(i);
					finish();
					
				}
				else{
					Toast.makeText(MainActivity.this, "用户名或密码错误", 1).show();
				}
				
			}
		});

        
        b2.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.activity_main, menu);
	    menu.add(0,1,0,"重置账号");
       // menu.add(0,2,0,"返回");
        
        //menu.add(0,4,0,"检查更新");
        //menu.add(0,3,0,"退出");
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	 if(item.getItemId()==1){
    		 
    		 
				Intent i=new Intent();
				i.setClass(MainActivity.this, CZ.class);
				startActivity(i);
				finish();
    		 
    		 
    		 
//    		// View text=
//    		 
//    		 AlertDialog.Builder b= new AlertDialog.Builder(MainActivity.this);
//				
//				b.setTitle(Start.getString("Reset", "").toString());
//				
//				
//				
//				
//				
//				b.setPositiveButton("确认", new DialogInterface.OnClickListener() {
//					
//					public void onClick(DialogInterface arg0, int arg1) {
//						// TODO Auto-generated method stub
//						if(Start.getString("Reset", "").toString().equals("1")){
//		    	        	
//		    	        	
//		    	        }else{
//		    	        	
//		    	        	
//		    	        }
//		    			 SharedPreferences.Editor StartEdit=Start.edit();
//		    		 	StartEdit.putString("Start", "");
//						StartEdit.commit();
//						Intent i=new Intent();
//						i.setClass(MainActivity.this, Hi.class);
//						startActivity(i);
//						finish();
//					}
//				});
//				b.show();
//    		 
    		 
    		 
 	    }
    	
    	
    	
    	
    	
    	return super.onOptionsItemSelected(item);
    }

    
}
