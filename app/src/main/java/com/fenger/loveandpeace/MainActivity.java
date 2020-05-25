package com.fenger.loveandpeace;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){ // 为了减少代码使用匿名Handler创建一个延时的调用
            public void run() {
                Intent i = new Intent(MainActivity.this,SoulActivity.class);
                MainActivity.this.startActivity(i);
                MainActivity.this.finish();
            } }, 1500);
    }
}
