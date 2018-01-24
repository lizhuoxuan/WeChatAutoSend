package com.example.mytest;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.BySelector;
import android.support.test.uiautomator.Configurator;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

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
        mUIDevice.waitForWindowUpdate(APP, 5 * 1000);
//        UiObject sender = mUIDevice.findObject(new UiSelector().text("Send"));  //定位text内容为Send的控键

        //微信内容   id   com.tencent.mm:id/c3p
        //微信通讯录
//        UiObject sender = mUIDevice.findObject(new UiSelector().text("通讯录"));
//
//        try {
//            sender.click();  //点击按键
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //微信
        UiObject sender1 = mUIDevice.findObject(new UiSelector().text("微信"));

        try {
            sender1.click();  //点击按键
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<UiObject2> uiObjects = mUIDevice.findObjects(By.res("com.tencent.mm:id/apr"));

        try {
            for (UiObject2 uiObject : uiObjects) {
                UiObject2 uiObject2 = uiObject.findObject(By.clazz("android.widget.RelativeLayout"));
//                if (uiObject2.getChildCount() > 1) {
                    UiObject2 uiObject21 = uiObject2.getParent().findObject(By.res("com.tencent.mm:id/apt"));
                    String weChat = uiObject21.getText();
//                    Log.e("有新消息", weChat);
                    if (weChat.equals("姐姐")) {
                        uiObject21.click();
                        Thread.sleep(1000 * 2);

                        //com.tencent.mm:id/a_h      微信对话框
//                        UiObject2 uiObject22 = mUIDevice.findObject(By.res("com.tencent.mm:id/a_h"));
//                        List<UiObject2> uiObjects2 = uiObject22.findObjects(By.clazz("android.view.View").res("com.tencent.mm:id/j_"));
//                        for (UiObject2 uiObject11 : uiObjects2) {
//                            uiObject11.longClick();
////                            Log.e("消息-------------------------------", uiObject11.getText());
//                        }
//                        UiObject2 uiObject11 = uiObjects2.get(uiObjects2.size()-1);
//                        uiObject11.longClick();

//                        ClipboardManager cbm=(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);

                        UiObject2 uiObject3 = mUIDevice.findObject(By.res("com.tencent.mm:id/aaf"));
                        uiObject3.click();

                        Configurator config= Configurator.getInstance();
                        config.setKeyInjectionDelay(40);
                        uiObject3.setText("自动发消息测试111111");
                        config.setKeyInjectionDelay(0);

                        UiObject2 send = mUIDevice.findObject(By.clazz("android.widget.Button").res("com.tencent.mm:id/aal").text("发送"));
                        send.click();
                    }
//                }
            }
        } catch (Exception e) {
            Log.e("error-------------------------------", e.getMessage());
        }
    }
}
