package org.jiangtao.application;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import cn.smssdk.SMSSDK;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import org.jiangtao.freedomblog.ActivityIndex;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils.DisplayUtils;
import org.jiangtao.utils.StaticResources;
import org.jiangtao.utils.yunxin.Preferences;

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
    // SDK初始化（启动后台服务，若已经存在用户登录信息， SDK 将完成自动登录）
    if (mApp != null) {
      NIMClient.init(this, loginInfo(), options());
    }
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
  }

  // 如果返回值为 null，则全部使用默认参数。
  private SDKOptions options() {
    SDKOptions options = new SDKOptions();

    // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
    StatusBarNotificationConfig config = new StatusBarNotificationConfig();
    // TODO: 4/10/2016 做了修改
    config.notificationEntrance = ActivityIndex.class; // 点击通知栏跳转到该Activity
    config.notificationSmallIconId = R.drawable.ic_avatar_default;
    options.statusBarNotificationConfig = config;

    // 配置保存图片，文件，log 等数据的目录
    // 如果 options 中没有设置这个值，SDK 会使用下面代码示例中的位置作为 SDK 的数据目录。
    // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
    // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
    String sdkPath = Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/nim";
    options.sdkStorageRootPath = sdkPath;

    // 配置是否需要预下载附件缩略图，默认为 true
    options.preloadAttach = true;

    // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
    // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
    // TODO: 4/10/2016 做了修改
    options.thumbnailSize = DisplayUtils.dip2px(getApplicationContext(), 100) / 2;

    // 用户资料提供者, 目前主要用于提供用户资料，用于新消息通知栏中显示消息来源的头像和昵称
    options.userInfoProvider = new UserInfoProvider() {
      @Override public UserInfoProvider.UserInfo getUserInfo(String account) {
        return null;
      }

      @Override public int getDefaultIconResId() {
        // TODO: 4/10/2016 做了修改
        return R.drawable.ic_avatar_default;
      }

      @Override public Bitmap getTeamIcon(String tid) {
        return null;
      }

      @Override public Bitmap getAvatarForMessageNotifier(String account) {
        return null;
      }

      @Override public String getDisplayNameForMessageNotifier(String account, String sessionId,
          SessionTypeEnum sessionType) {
        return null;
      }
    };
    return options;
  }

  // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
  private LoginInfo loginInfo() {
    return getLoginInfo();
  }

  private LoginInfo getLoginInfo() {
    //从本地读取上次登录成功时保存的用户登录信息
    String account = Preferences.getUserAccount();
    String token = Preferences.getUserToken();

    if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
      return new LoginInfo(account, token);
    } else {
      return null;
    }
  }
}
