package com.coderpig.example.girlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.xxoo.Hobbies;
import com.xxoo.IGirlInfo;
import com.xxoo.ILoveCallback;
import com.xxoo.LoveInfo;

/**
 * 描述：
 *
 * @author coder-pig： 2016/10/26 8:41
 */
public class GirlService extends Service{

    private Hobbies hobbies;

    private LoveInfo loveInfo1,loveInfo2;

    private final RemoteCallbackList<ILoveCallback> cbs = new RemoteCallbackList<>();

    private final IGirlInfo.Stub mInfo = new IGirlInfo.Stub() {
        @Override
        public boolean haveBoyFriend(int fraction) throws RemoteException {
            return fraction >= 5;
        }

        @Override
        public Hobbies getHobbies() throws RemoteException {
            loveInfo1 = new LoveInfo("说爱我");
            loveInfo2 = new LoveInfo("我漂亮吗");
            for(int i =0;i < 10;i++) {
                if(i % 2 == 0) {
                    askLove(loveInfo1);
                } else {
                    askLove(loveInfo2);
                }
            }
            return hobbies;
        }

        @Override
        public void registerLove(ILoveCallback cb) throws RemoteException {
            if(cb != null) {
                cbs.register(cb);
            }
        }

        @Override
        public void unregisterLove(ILoveCallback cb) throws RemoteException {
            if(cb != null) {
                cbs.unregister(cb);
            }
        }
    };

    @Override public void onCreate() {
        super.onCreate();
        hobbies = new Hobbies("折耳猫","Blue","A Little Story","煎饼果子");
    }

    @Nullable @Override public IBinder onBind(Intent intent) {
        return mInfo;
    }

    private void askLove(LoveInfo info) {
        final int len = cbs.beginBroadcast();
        for (int i =0;i < len;i++) {
            try {
                cbs.getBroadcastItem(i).askLoveMe(info);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        cbs.finishBroadcast();
    }

}























