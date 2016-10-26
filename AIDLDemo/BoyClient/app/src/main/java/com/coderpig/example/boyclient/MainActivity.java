package com.coderpig.example.boyclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xxoo.Hobbies;
import com.xxoo.IGirlInfo;
import com.xxoo.ILoveCallback;
import com.xxoo.LoveInfo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button askBtn;

    private Button askHobbiesBtn;

    private IGirlInfo info;

    private GirlConnection conn;

    private int flag = 1;

    private ILoveCallback mCallback = new ILoveCallback.Stub() {
        @Override
        public void askLoveMe(LoveInfo info) throws RemoteException {
            switch (info.getAsk()) {
                case "说爱我":
                    Log.e("MainActivity","小逗比，我爱你啊~ x" + flag);
                    break;
                case "我漂亮吗":
                    Log.e("MainActivity","最美就是你了~ x" + flag);
                    break;
            }
            flag++;
        }
    };

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAndBindService();
        initView();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        try {
            info.unregisterLove(mCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if(conn != null) {
            unbindService(conn);
        }
    }

    private void initView() {
        askBtn = (Button) findViewById(R.id.btn_ask);
        askHobbiesBtn = (Button) findViewById(R.id.btn_ask_hobbies);
        askBtn.setOnClickListener(this);
        askHobbiesBtn.setOnClickListener(this);
    }

    private void initAndBindService() {
        conn = new GirlConnection();
        Intent it = new Intent();
        it.setAction("xxoo");
        it.setPackage("com.coderpig.example.girlserver");
        bindService(it,conn,BIND_AUTO_CREATE);
    }

    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ask:
                try {
                    Toast.makeText(getApplicationContext()
                            ,info.haveBoyFriend(6) ? "妹子没有那朋友，嘿嘿嘿~":"妹子有男朋友了！绝望QAQ"
                            ,Toast.LENGTH_SHORT)
                            .show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_ask_hobbies:
                try {
                    Hobbies hobbies = info.getHobbies();
                    if(hobbies != null) {
                        Toast.makeText(getApplicationContext(),hobbies.toString(),Toast.LENGTH_SHORT).show();
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private final class GirlConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            info = IGirlInfo.Stub.asInterface(service);
            try {
                info.registerLove(mCallback);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            info = null;
        }
    }
}









