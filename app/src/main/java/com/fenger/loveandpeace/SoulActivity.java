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
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.fenger.loveandpeace.adapter.TestAdapter;
import com.sdsmdg.tastytoast.TastyToast;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import me.haowen.soulplanet.view.SoulPlanetsView;

/**
 * com.fenger.loveandpeace
 * Created by fenger
 * in 2020/5/14
 */
public class SoulActivity extends AppCompatActivity {

    private static final int REQ_PERMISSION_CODE = 0x100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soul);
        checkPermission(this);

        SoulPlanetsView soulPlanet = findViewById(R.id.soulPlanetView);
        soulPlanet.setAdapter(new TestAdapter());

        soulPlanet.setOnTagClickListener(new SoulPlanetsView.OnTagClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View view, int position) {
//                Intent intent1 = new Intent(SoulActivity.this,LoveActivity.class);
//                SoulActivity.this.startActivity(intent1);
                Toast.makeText(SoulActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
//                switch (position){
//                    case 0:
//                        Intent intent1 = new Intent(SoulActivity.this,LoveActivity.class);
//                        SoulActivity.this.startActivity(intent1);
//                        break;
//                    case 1:
//                        Intent intent2 = new Intent(SoulActivity.this,FindMeActivity.class);
//                        SoulActivity.this.startActivity(intent2);
//                        break;
//                    case 2:
//                        Intent intent3 = new Intent(SoulActivity.this,FinalActivity.class);
//                        SoulActivity.this.startActivity(intent3);
//                        break;
//                    case 3:
//                        // 获取userSig函数
//                        String userSig = GenerateTestUserSig.genTestUserSig("小云");
//                        TUIKit.login("小云", userSig, new IUIKitCallBack() {
//                            @Override
//                            public void onError(String module, final int code, final String desc) {
//                                runOnUiThread(new Runnable() {
//                                    public void run() {
//                                        Toast toast = TastyToast.makeText(SoulActivity.this, "网络好像不太对呢？\n        要不然 \n等下再点我一次？", TastyToast.LENGTH_LONG, TastyToast.ERROR);
//                                        toast.setGravity(Gravity.CENTER, 0, 0);
//                                        toast.show();                                    }
//                                });
//                                Log.i("TAG", "imLogin errorCode = " + code + ", errorInfo = " + desc);
//                            }
//
//                            @Override
//                            public void onSuccess(Object data) {
//                                Intent intent = new Intent(SoulActivity.this, SoloActivity.class);
//                                startActivity(intent);
//                                finish();
//                            }
//                        });
//                }
            }
        });
    }

    //权限检查
    public static boolean checkPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            List<String> permissions = new ArrayList<>();
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.CAMERA)) {
                permissions.add(Manifest.permission.CAMERA);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.RECORD_AUDIO)) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.READ_PHONE_STATE)) {
                permissions.add(Manifest.permission.READ_PHONE_STATE);
            }
            if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(TUIKit.getAppContext(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
            }
            if (permissions.size() != 0) {
                String[] permissionsArray = permissions.toArray(new String[1]);
                ActivityCompat.requestPermissions(activity,
                        permissionsArray,
                        REQ_PERMISSION_CODE);
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }

    /**
     * 系统请求权限回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQ_PERMISSION_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast toast = TastyToast.makeText(SoulActivity.this, "       给我点权限好不好？\n    不然我不好意思告诉你 \n             我喜欢你啊", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
//                    ToastUtil.toastLongMessage("给我点权限好不撒，不然我没法告诉你我喜欢你啊！");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
