package com.example.loswkl.lnote;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


/**
 * A login screen that offers login via email/password.
 */
public class Note_Zc extends AppCompatActivity {

    EditText e_yhm, e_mm, e_qrmm, e_sjh;
    Button b_zc, b_cz;
    String yhm, mm, qrmm;
    SharedPreferences Start, Data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note__zc);
        Start = getSharedPreferences("LNoteStart", MODE_PRIVATE);
        Data = getSharedPreferences("LNoteData", MODE_PRIVATE);


        e_yhm = (EditText) findViewById(R.id.editText1);
        e_mm = (EditText) findViewById(R.id.editText2);
        e_qrmm = (EditText) findViewById(R.id.editText3);
        e_sjh = (EditText) findViewById(R.id.editText4);
        b_zc = (Button) findViewById(R.id.button1);
        b_cz = (Button) findViewById(R.id.button2);


        b_zc.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                if (e_sjh.getText().toString().equals("")) {
                    Toast.makeText(Note_Zc.this, "请输入手机号", 1).show();
                } else if (!e_sjh.getText().toString().matches("[0-9]{1,}")) {
                    Toast.makeText(Note_Zc.this, "请输入数字的手机号", 1).show();
                } else if (e_sjh.getText().toString().length() != 11) {
                    Toast.makeText(Note_Zc.this, "请输入11位手机号", 1).show();
                } else if (e_yhm.getText().toString().equals("")) {
                    Toast.makeText(Note_Zc.this, "请输入用户名", 1).show();
                } else if (e_mm.getText().toString().equals("") && e_qrmm.getText().toString().equals("")) {
                    Toast.makeText(Note_Zc.this, "请输入密码", 1).show();
                } else if (e_mm.getText().toString().equals(e_qrmm.getText().toString())) {

                    SharedPreferences.Editor DataEdit = Data.edit();
                    DataEdit.putString("UserName", e_yhm.getText().toString());
                    DataEdit.putString("Password", e_mm.getText().toString());

                    DataEdit.commit();


                    //生成重置代码
                    Random r1 = new Random();
                    String x = r1.nextInt() + "";
                    Toast.makeText(Note_Zc.this, "即将向您输入的手机号发送一条重置密码(运营商收取一条短信费用)", 1).show();
                    //发送重置代码
                    SmsManager smss = SmsManager.getDefault();
                    smss.sendTextMessage(e_sjh.getText().toString(), null, "重置代码为：" + x + " 请妥善保管【L'Note】", null, null);
                    SharedPreferences.Editor StartEdit = Start.edit();
                    StartEdit.putString("Start", "1");
                    StartEdit.putString("telephone", e_sjh.getText().toString());
                    StartEdit.putString("Reset", x);
                    StartEdit.commit();
                    Toast.makeText(Note_Zc.this, "注册成功", 1).show();
                    Intent i = new Intent();
                    i.setClass(Note_Zc.this, Note_Hi.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(Note_Zc.this, "密码和确认密码不一致", 1).show();
                }


            }
        });

        b_cz.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                e_yhm.setText("");
                e_mm.setText("");
                e_qrmm.setText("");


            }
        });


    }

}