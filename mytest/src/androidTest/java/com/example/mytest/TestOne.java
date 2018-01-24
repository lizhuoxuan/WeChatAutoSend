package com.example.mytest;

import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Lzx on 2018/1/23.
 */
@RunWith(AndroidJUnit4.class)
public class TestOne {

    private UiDevice mUIDevice = null;
    private Context mContext = null;
    String APP = "com.tencent.mm";

    @Before
    public void setUp() throws RemoteException {
        mUIDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());  //获得device对象
        mContext = InstrumentationRegistry.getContext();

        if (!mUIDevice.isScreenOn()) {  //唤醒屏幕
            mUIDevice.wakeUp();
        }
        mUIDevice.pressHome();  //按home键
    }

    @Test
    public void demo() {
        Intent myIntent = mContext.getPackageManager().getLaunchIntentForPackage(APP);  //启动app
        mContext.startActivity(myIntent);
        mUIDevice.waitForWindowUpdate(APP, 5 * 2000);
        UiObject sender = mUIDevice.findObject(new UiSelector().text("Send"));  //定位text内容为Send的控键
        try {
            sender.click();  //点击按键
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
