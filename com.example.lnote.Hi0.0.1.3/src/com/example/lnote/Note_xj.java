package com.example.lnote;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.lnote.Note;

public class Note_xj extends Activity {

	// private static final CharSequence[] Note.str1 = null;
	TextView t1, t2;
	ImageView back, bc;
	EditText e_name, e_content;
	SharedPreferences NoteData;
	int no;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.note_xj);
		e_name = (EditText) findViewById(R.id.editText2);
		e_content = (EditText) findViewById(R.id.editText3);
		back = (ImageView) findViewById(R.id.imageView1);
		bc = (ImageView) findViewById(R.id.imageView2);

		// ����EditText����ʾ��ʽΪ�����ı�����
		e_content.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		// �ı���ʾ��λ����EditText�����Ϸ�
		e_content.setGravity(Gravity.TOP);
		// �ı�Ĭ�ϵĵ���ģʽ
		e_content.setSingleLine(false);
		// ˮƽ��������ΪFalse
		e_content.setHorizontallyScrolling(false);
		
		
		
		//��ת��������
		
//		Intent i = getIntent();
//		no = i.getIntExtra("no", 1);
//		// ("no");
//		// =i.getIntExtra("no");
//		e_name.setText(Note.str1[no]);
//		e_content.setText(Note.str2[no]);
//		// i.getStringExtra("x");
		
		NoteData = getSharedPreferences("NoteData", MODE_PRIVATE);
		no = NoteData.getInt("all", -1);

		if (no == -1) {
			// Toast.makeText(getApplicationContext(), "�Բ����ȡ�������½�һ��",
			// 1).show();
			no=0;
			Toast.makeText(getApplicationContext(), "�Բ��𣬳��ִ������������¿�ʼ�½�", 1).show();
			
		}
		
		
		

		back.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub

//				SharedPreferences.Editor DataEdit = NoteData.edit();
//				DataEdit.putString("NoteName" + no, e_name.getText().toString());
//				DataEdit.putString("NoteContent" + no, e_content.getText().toString());
//				// DataEdit.putInt("all", 1);
//				DataEdit.commit();
//				Intent i = new Intent();
//				i.setClass(Note_xj.this, Note.class);
//				startActivity(i);
				Toast.makeText(getApplicationContext(), "�ѷ����½�", 1).show();
				Intent i = new Intent();
				i.setClass(Note_xj.this, Note.class);
				startActivity(i);
				 finish();
			}
		});
		bc.setOnClickListener(new View.OnClickListener() {

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SharedPreferences.Editor DataEdit = NoteData.edit();
				DataEdit.putString("NoteName" + (no), e_name.getText().toString());
				DataEdit.putString("NoteContent" + (no), e_content.getText().toString());
				 DataEdit.putInt("all", no+1);
				DataEdit.commit();
				Toast.makeText(getApplicationContext(), "�ѱ���", 1).show();
				
				Intent i = new Intent();
				i.setClass(Note_xj.this, Note.class);
				startActivity(i);
				finish();
			}
		});

	}

	// ��д������ ���ؼ����߼�
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			SharedPreferences.Editor DataEdit = NoteData.edit();
//			DataEdit.putString("NoteName" + no, e_name.getText().toString());
//			DataEdit.putString("NoteContent" + no, e_content.getText().toString());
//			// DataEdit.putInt("all", 1);
//			DataEdit.commit();
//			Intent i = new Intent();
//			i.setClass(Note_xj.this, Note.class);
//			startActivity(i);
			Toast.makeText(getApplicationContext(), "�ѷ����½�", 1).show();
			Intent i = new Intent();
			i.setClass(Note_xj.this, Note.class);
			startActivity(i);
			 finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
