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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Class:BlogApplication <br>
 * Description:单例模式 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/15 23:43 <br>
 */
// TODO: 5/1/2016 必须写入数据库,在首页中完成
public class BlogApplication extends MultiDexApplication implements RongIM.UserInfoProvider {
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
      RongIM.setUserInfoProvider(this, true);
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

  @Override public UserInfo getUserInfo(String s) {
    Call<ArrayList<Account>> arrayListCall = mAccountService.getAllAcoount();
    arrayListCall.enqueue(new Callback<ArrayList<Account>>() {
      @Override
      public void onResponse(Call<ArrayList<Account>> call, Response<ArrayList<Account>> response) {
        if (response.isSuccessful()) {
          mAccountsLists = response.body();
          for (Account a : mAccountsLists) {
            UserInfo userInfo = new UserInfo(a.phone != null ? a.phone : null,
                a.username != null ? a.username : null, a.uri() != null ? a.uri() : null);
            mUserInfoLists.add(userInfo);
          }
        }
      }

      @Override public void onFailure(Call<ArrayList<Account>> call, Throwable t) {

      }
    });
    return getUser(s);
  }

  public UserInfo getUser(String s) {
    for (UserInfo us : mUserInfoLists) {
      if (us.getUserId().equals(s)) {
        return us;
      }
    }
    return null;
  }
}
