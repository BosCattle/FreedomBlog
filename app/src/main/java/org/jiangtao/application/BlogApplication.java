package org.jiangtao.application;

import android.content.Context;
import android.content.res.Configuration;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import cn.smssdk.SMSSDK;
import com.alibaba.mobileim.YWAPI;
import com.alibaba.wxlib.util.SysUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import org.jiangtao.utils.StaticResources;

/**
 * Class:BlogApplication <br>
 * Description:单例模式 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/15 23:43 <br>
 */
public class BlogApplication extends MultiDexApplication {
  private static BlogApplication mApp = new BlogApplication();

  public static BlogApplication getInstance() {
    if (mApp == null) {
      synchronized (BlogApplication.class) {
        mApp = new BlogApplication();
      }
    }
    return mApp;
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    MultiDex.install(this);
  }

  @Override public void onCreate() {
    super.onCreate();
    Fresco.initialize(this);
    SMSSDK.initSDK(this, StaticResources.MOB_SMS_KEY, StaticResources.MOB_SMS_SECRET);
    SysUtil.setApplication(this);
    if (SysUtil.isTCMSServiceProcess(this)) {
      return;
    }
    if (SysUtil.isMainProcess(this)) {
      YWAPI.init(this, StaticResources.APP_KEY);
    }
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
  }
}
