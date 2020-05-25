package com.fenger.loveandpeace;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.fenger.loveandpeace.utils.FlowerView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * com.fenger.loveandpeace
 * Created by fenger
 * in 2020/5/16
 */
public class FindMeActivity extends AppCompatActivity {

    private WebView webView;
    private FlowerView mFlowerView;
    // 屏幕宽度
    public static int screenWidth;
    // 屏幕高度
    public static int screenHeight;

    Timer myTimer = null;
    TimerTask mTask = null;
    private static final int SNOW_BLOCK = 1;
    private Handler mHandler = new Handler() {
        public void dispatchMessage(Message msg) {
            mFlowerView.inva();
        };
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findme);

        webView = (WebView) findViewById(R.id.webView2);
        //需要加载的网页的url
        webView.loadUrl("file:///android_asset/index4.html");//这里写的是assets文件夹下html文件的名称，需要带上后面的后缀名，前面的路径是安卓系统自己规定的android_asset就是表示的在assets文件夹下的意思。
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//自适应屏幕
        webView.getSettings().setLoadWithOverviewMode(true);//自适应屏幕
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setUseWideViewPort(true);//扩大比例的缩放
        // webView.getSettings().setBuiltInZoomControls(true);//设置是否出现缩放工具,这里我想就不出现了，影响效果
        WebSettings settings = webView.getSettings();
        // 如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                view.loadUrl(url);
                return true;
            }
        });
        if (Build.VERSION.SDK_INT >= 21) {//设置顶部状态栏为半透明
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);}

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.hide();
        }

        snowshow();
    }

    void  snowshow() {
        mFlowerView =  findViewById(R.id.flowerview);
        screenWidth = getWindow().getWindowManager().getDefaultDisplay()
                .getWidth();
        screenHeight = getWindow().getWindowManager().getDefaultDisplay()
                .getHeight();

        DisplayMetrics dis = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dis);
        float de = dis.density;
        mFlowerView.setWH(screenWidth, screenHeight, de);
        mFlowerView.loadFlower();
        mFlowerView.addRect();

        myTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = SNOW_BLOCK;
                mHandler.sendMessage(msg);
            }
        };
        myTimer.schedule(mTask, 1000, 10);

    }
}
