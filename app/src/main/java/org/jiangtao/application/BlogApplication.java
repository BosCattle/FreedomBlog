package org.jiangtao.application;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import cn.smssdk.SMSSDK;
import com.facebook.drawee.backends.pipeline.Fresco;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import java.util.ArrayList;
import org.jiangtao.model.Account;
import org.jiangtao.service.AccountService;
import org.jiangtao.service.ApiService;
import org.jiangtao.utils.StaticResources;

/**
 * Class:BlogApplication <br>
 * Description:单例模式 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/15 23:43 <br>
 */
// TODO: 5/1/2016 必须写入数据库,在首页中完成
public class BlogApplication extends MultiDexApplication {
  private static BlogApplication mApp = new BlogApplication();
  private AccountService mAccountService;
  private ArrayList<Account> mAccountsLists;
  private ArrayList<UserInfo> mUserInfoLists;

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
    mAccountsLists = new ArrayList<>();
    mUserInfoLists = new ArrayList<>();
    mAccountService = ApiService.createAccountService();
    Fresco.initialize(this);
    SMSSDK.initSDK(this, StaticResources.MOB_SMS_KEY, StaticResources.MOB_SMS_SECRET);
    if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext()))
        || "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

      /**
       * IMKit SDK调用第一步 初始化
       */
      RongIM.init(this);
    }
  }

  public static String getCurProcessName(Context context) {

    int pid = android.os.Process.myPid();

    ActivityManager activityManager =
        (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

    for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {

      if (appProcess.pid == pid) {
        return appProcess.processName;
      }
    }
    return null;
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
  }
}
