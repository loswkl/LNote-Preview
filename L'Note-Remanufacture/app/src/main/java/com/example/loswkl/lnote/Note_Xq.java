package com.example.loswkl.lnote;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.example.loswkl.lnote.Note;
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

public class Note_Xq extends AppCompatActivity {

    // private static final CharSequence[] Note.str1 = null;
    TextView t1, t2;
    ImageView back, bc;
    EditText e_name, e_content;
    SharedPreferences NoteData;
    int no;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__xq);
        e_name = (EditText) findViewById(R.id.editText2);
        e_content = (EditText) findViewById(R.id.editText3);
        back = (ImageView) findViewById(R.id.imageView1);
        bc = (ImageView) findViewById(R.id.imageView2);
        NoteData = getSharedPreferences("NoteData", MODE_PRIVATE);

        // 设置EditText的显示方式为多行文本输入
        e_content.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
        // 文本显示的位置在EditText的最上方
        e_content.setGravity(Gravity.TOP);
        // 改变默认的单行模式
        e_content.setSingleLine(false);
        // 水平滚动设置为False
        e_content.setHorizontallyScrolling(false);
        Intent i = getIntent();
        no = i.getIntExtra("no", 1);
        // ("no");
        // =i.getIntExtra("no");
        e_name.setText(Note.str1[no]);
        e_content.setText(Note.str2[no]);
        // i.getStringExtra("x");

        back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                //SharedPreferences.Editor DataEdit = NoteData.edit();
                //DataEdit.putString("NoteName" + no, e_name.getText().toString());
                //DataEdit.putString("NoteContent" + no, e_content.getText().toString());
                // DataEdit.putInt("all", 1);
                //DataEdit.commit();
                Intent i = new Intent();
                i.setClass(Note_Xq.this, Note.class);
                startActivity(i);
                Toast.makeText(getApplicationContext(), "未保存", 1).show();

                finish();
            }
        });
        bc.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SharedPreferences.Editor DataEdit = NoteData.edit();
                DataEdit.putString("NoteName" + no, e_name.getText().toString());
                DataEdit.putString("NoteContent" + no, e_content.getText().toString());
                // DataEdit.putInt("all", 1);
                DataEdit.commit();
                Toast.makeText(getApplicationContext(), "已保存", 1).show();
            }
        });

    }

    // 改写物理按键 返回键的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            SharedPreferences.Editor DataEdit = NoteData.edit();
            DataEdit.putString("NoteName" + no, e_name.getText().toString());
            DataEdit.putString("NoteContent" + no, e_content.getText().toString());
            // DataEdit.putInt("all", 1);
            DataEdit.commit();
            Intent i = new Intent();
            i.setClass(Note_Xq.this, Note.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), "已保存", 1).show();
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

}
