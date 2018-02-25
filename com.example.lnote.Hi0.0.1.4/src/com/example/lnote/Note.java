package com.example.lnote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class Note extends Activity {

	ListView l;
	int all;
	SharedPreferences NoteData; 
	public static  String str1[], str2[];
	ImageView i_gy,i_xj;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note);
		
		i_gy=(ImageView)findViewById(R.id.imageView1);
		i_xj=(ImageView)findViewById(R.id.imageView2);
		
		l = (ListView) findViewById(R.id.listView1);

		
		//��ȡ����
		NoteData = getSharedPreferences("NoteData", MODE_PRIVATE);
		all = NoteData.getInt("all", -1);
		// no=Note.getInt("no", -1);

		if (all == -1) {
			// Toast.makeText(getApplicationContext(), "�Բ����ȡ�������½�һ��",
			// 1).show();
			SharedPreferences.Editor DataEdit = NoteData.edit();
			DataEdit.putString("NoteName0", "��ӭʹ��L'Note");
			DataEdit.putString("NoteContent0", "��������  Loswkl Ye ����");
			DataEdit.putString("NoteName1", "�½�");
			DataEdit.putString("NoteContent1", "������ϽǼӺ�");
			DataEdit.putString("NoteName2", "�޸�");
			DataEdit.putString("NoteContent2", "�����޸�");
			DataEdit.putString("NoteName3", "ɾ��");
			DataEdit.putString("NoteContent3", "����ɾ��");
			DataEdit.putInt("all", 4);
			DataEdit.commit();
			all = NoteData.getInt("all", -1);
		}
		str1 = new String[all];
		str2 = new String[all];

		for (int i = 0; i < all; i++) {
			// no[i]=i;

			str1[i] = NoteData.getString("NoteName" + i, null);
			str2[i] = NoteData.getString("NoteContent" + i, null);

		}

		//��ʾ����
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < str1.length; i++) {// str1.length
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("name", str1[i]);
			m.put("talk", str2[i]);
			// m.put("tx", tx[i]);
			data.add(m);
		}
		// ArrayAdapter<String> aa=new ArrayAdapter<String>(MainActivity.this,
		// android.R.layout.simple_list_item_multiple_choice, str) ;
		// l.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		SimpleAdapter sa = new SimpleAdapter(this, data, R.layout.note_ys,new String[] { "name", "talk" }, new int[] { R.id.textView1,R.id.textView2 });
		l.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClass(Note.this, Note_xq.class);
				i.putExtra("no", arg2);
				//i.putExtra("t", str2[arg2]);
				// i.putExtra("x", tx[arg2]);
				startActivity(i);
				finish();
			}

		});
		l.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,final int arg2, long arg3) {
				// TODO Auto-generated method stub
				AlertDialog.Builder d= new AlertDialog.Builder(Note.this);
				d.setTitle("ɾ������");
				d.setMessage("����ɾ������Ϊ��"+str1[arg2]+"���ıʼ�");
				d.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
						
						
						for(int i=arg2;i<all-1;i++){
							SharedPreferences.Editor DataEdit = NoteData.edit();
							DataEdit.putString("NoteName"+i, str1[i+1]);
							DataEdit.putString("NoteContent"+i, str2[i+1]);
							DataEdit.commit();
							
							
							
						}
						
						SharedPreferences.Editor DataEdit = NoteData.edit();
						DataEdit.remove("NoteName"+(all-1));
						DataEdit.remove("NoteContent"+(all-1));
						DataEdit.putInt("all", all-1);
						DataEdit.commit();
						Toast.makeText(getApplicationContext(), "ɾ���ɹ�", 1).show();
						Intent i = new Intent();
						i.setClass(Note.this, Note.class);
						startActivity(i);
						finish();
						
					}
				});
				d.setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface arg0, int arg1) {
					// TODO Auto-generated method stub
								
					}
				});
				d.show();
				
				return true;
			}
		});
		
		

		
		l.setAdapter(sa);
		
		
		
		i_gy.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClass(Note.this, GY.class);
				startActivity(i);
				
			}
		});
		
		i_xj.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i = new Intent();
				i.setClass(Note.this, Note_xj.class);
				startActivity(i);
				finish();
				
			}
		});
		
		

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub

		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// getMenuInflater().inflate(R.menu.activity_main, menu);
		//menu.add(0, 1, 0, "�����˺�");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
//		if (item.getItemId() == 1) {
//
//			finish();
//
//		}

		return super.onOptionsItemSelected(item);
	}

}
