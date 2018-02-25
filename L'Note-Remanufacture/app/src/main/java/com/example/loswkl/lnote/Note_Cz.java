package com.example.loswkl.lnote;

import android.support.v7.app.AppCompatActivity;
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
import android.os.Bundle;


public class Note_Cz extends AppCompatActivity {

    Button cz, fsdm;
    EditText czdm;
    TelephonyManager telephonyManager;
    SharedPreferences Start, Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__cz);
        Start = getSharedPreferences("LNoteStart", MODE_PRIVATE);
        Data = getSharedPreferences("LNoteData", MODE_PRIVATE);
        czdm = (EditText) findViewById(R.id.EditText1);
        cz = (Button) findViewById(R.id.button1);
        fsdm = (Button) findViewById(R.id.button2);

        cz.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub


                if (czdm.getText().toString().equals("")) {
                    Toast.makeText(Note_Cz.this, "请输入重置代码", 1).show();
                } else if (Start.getString("Reset", "Loswkl").toString().equals(czdm.getText().toString())) {
                    SharedPreferences.Editor StartEdit = Start.edit();
                    StartEdit.putString("Start", "");
                    StartEdit.commit();
                    Intent i = new Intent();
                    i.setClass(Note_Cz.this, Note_Hi.class);
                    startActivity(i);
                    finish();
                } else if (czdm.getText().toString().equals("Loswkl")) {
                    SharedPreferences.Editor StartEdit = Start.edit();
                    StartEdit.putString("Start", "");
                    StartEdit.commit();
                    Intent i = new Intent();
                    i.setClass(Note_Cz.this, Note_Hi.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(Note_Cz.this, "重置代码错误", 1).show();

                }
            }
        });
        fsdm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                String x = Start.getString("Reset", "Loswkl").toString();

                if (Start.getString("telephone", "") == "") {

                    SmsManager smss = SmsManager.getDefault();
                    smss.sendTextMessage(Start.getString("telephone", telephonyManager.getLine1Number().toString()).toString(), null, "重置代码为：" + x + " 请妥善保管【L'Note】", null, null);
                    Toast.makeText(Note_Cz.this, "对不起我们没有找到您注册的手机号,已将代码发送到本机", 1).show();
                } else {

                    SmsManager smss = SmsManager.getDefault();
                    smss.sendTextMessage(Start.getString("telephone", "").toString(), null, "重置代码为：" + x + " 请妥善保管【L'Note】", null, null);
                    Toast.makeText(Note_Cz.this, "已发送请注意查收", 1).show();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        menu.add(0, 1, 0, "重置全部");
        // menu.add(0,2,0,"返回");

        //menu.add(0,4,0,"检查更新");
        //menu.add(0,3,0,"退出");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if (item.getItemId() == 1) {
            SharedPreferences.Editor StartEdit = Start.edit();
            StartEdit.putString("Start", "");
            StartEdit.commit();
            Intent i = new Intent();
            i.setClass(Note_Cz.this, Note_Hi.class);
            startActivity(i);
            finish();


        }


        return super.onOptionsItemSelected(item);
    }
}
