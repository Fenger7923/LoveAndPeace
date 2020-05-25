package com.fenger.loveandpeace;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.conversation.base.ConversationInfo;
import com.tencent.qcloud.tim.uikit.utils.TUIKitConstants;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * com.fenger.loveandpeace
 * Created by fenger
 * in 2020/5/16
 * 单独的聊天app
 */
public class SoloActivity extends AppCompatActivity {

    private TitleBarLayout mTitleBar;
    private ChatLayout chatLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solo);

        // 从布局文件中获取聊天面板
        chatLayout = findViewById(R.id.chat_layout);
        // 单聊面板的默认 UI 和交互初始化
        chatLayout.initDefault();
        // 传入 ChatInfo 的实例，这个实例必须包含必要的聊天信息，一般从调用方传入
        // 构造 mChatInfo 可参考 StartC2CChatActivity.java 的方法 startConversation

        final ChatInfo chatInfo = new ChatInfo();
        chatInfo.setType(TIMConversationType.C2C);
        chatInfo.setId("fenger");
        String chatName = "fenger";
        if (!TextUtils.isEmpty("")) {
            chatName = "";
        } else if (!TextUtils.isEmpty("")) {
            chatName = "";
        }
        chatInfo.setChatName(chatName);
        chatLayout.setChatInfo(chatInfo);
        //获取单聊面板的标题栏

        //获取单聊面板的标题栏
        mTitleBar = chatLayout.getTitleBar();

        //单聊面板标记栏返回按钮点击事件，这里需要开发者自行控制
        mTitleBar.setOnLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SoloActivity.this, SoulActivity.class);
                startActivity(intent);
            }
        });
            mTitleBar.setOnRightClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(SoloActivity.this, SoulActivity.class);
                    startActivity(intent);
                }
            });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}
