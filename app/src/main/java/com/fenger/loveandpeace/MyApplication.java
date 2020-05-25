package com.fenger.loveandpeace;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

/**
 * com.fenger.loveandpeace
 * Created by fenger
 * in 2020/5/18
 */
public class MyApplication extends Application {
    public static final int SDKAPPID = 1400371629; // 您的 SDKAppID
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        CrashReport.initCrashReport(getApplicationContext(), "5f01f65bb5", true);
        // 配置 Config，请按需配置
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new TIMSdkConfig(SDKAPPID));
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());

        TUIKit.init(this, SDKAPPID, configs);
    }
    public static MyApplication instance() {
        return instance;
    }
}
