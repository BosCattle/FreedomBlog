package org.jiangtao.application;

import android.content.res.Configuration;
import cn.smssdk.SMSSDK;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.smartydroid.android.starter.kit.app.StarterKitApp;
import org.jiangtao.utils_resource.StaticResources;

/**
 * Class:BlogApplication <br>
 * Description:单例模式 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/15 23:43 <br>
 */
public class BlogApplication extends StarterKitApp {
  private static BlogApplication mApp = new BlogApplication();

  public static BlogApplication getInstance() {
    if (mApp == null) {
      synchronized (BlogApplication.class) {
        mApp = new BlogApplication();
      }
    }
    return mApp;
  }

  @Override public void onCreate() {
    super.onCreate();
    Fresco.initialize(this);
    SMSSDK.initSDK(this, StaticResources.MOB_SMS_KEY, StaticResources.MOB_SMS_SECRET);
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
  }

  @Override public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
  }
}
