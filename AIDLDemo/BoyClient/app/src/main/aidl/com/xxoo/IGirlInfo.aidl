package com.xxoo;
import com.xxoo.Hobbies;
import com.xxoo.ILoveCallback;

interface IGirlInfo {
    boolean haveBoyFriend(int fraction);
    Hobbies getHobbies();
    void registerLove(ILoveCallback cb);
    void unregisterLove(ILoveCallback cb);
}