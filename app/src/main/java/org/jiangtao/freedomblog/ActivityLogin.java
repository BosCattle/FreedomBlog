package org.jiangtao.freedomblog;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.netease.nim.uikit.cache.DataCacheManager;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallback;
import com.netease.nimlib.sdk.auth.AuthService;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import java.util.Date;
import org.jiangtao.model.Account;
import org.jiangtao.service.AccountService;
import org.jiangtao.service.ApiService;
import org.jiangtao.utils.AccountManager;
import org.jiangtao.utils.SnackBarUtil;
import org.jiangtao.utils.TurnActivity;
import org.jiangtao.utils.yunxin.DemoCache;
import org.jiangtao.utils.yunxin.Preferences;
import org.jiangtao.utils.yunxin.UserPreferences;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录界面
 * author:Kevin
 * description:登录界面
 */
public class ActivityLogin extends StarterActivity implements View.OnClickListener {
  private static final String TAG = ActivityLogin.class.getSimpleName();
  @Bind(R.id.register_blog) TextView mRegisterBlogTextView;
  @Bind(R.id.button_login) TextView mLoginButton;
  @Bind(R.id.text_forget) TextView mForgetText;
  @Bind(R.id.edittext_username) EditText mPhoneEdit;
  @Bind(R.id.edittext_password) EditText mPassWordEdit;
  @Bind(R.id.sub_edittext_username) TextInputLayout mNameTextInput;
  @Bind(R.id.sub_edittext_password) TextInputLayout mPassTextInput;
  @Bind(R.id.container_login) RelativeLayout mRelativelayout;
  private AccountService mAccountService;
  private String mPhone;
  private String mPassWord;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_activity_login);
    ButterKnife.bind(this);
    setEditTextValue();
    mAccountService = ApiService.createAccountService();
  }

  /**
   * 如果account账户中有数据，保存
   */
  @SuppressWarnings("ConstantConditions") private void setEditTextValue() {
    Account account = AccountManager.getInstance().getAccount(this);
    if (account != null && account.phone.length() != 0 && account.password.length() != 0) {
      mNameTextInput.getEditText().setText(account.phone);
      mPassTextInput.getEditText().setText(account.password);
    }
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.register_blog:
        TurnActivity.startRegisterActivity(ActivityLogin.this);
        break;
      case R.id.text_forget:
        TurnActivity.startRetrieveActivity(ActivityLogin.this);
        break;
      case R.id.button_login:
        submitLogin();
        break;
    }
  }

  private void submitLogin() {
    showHud("正在登陆...");
    mPhone = mPhoneEdit.getText().toString();
    mPassWord = mPassWordEdit.getText().toString();
    Callback<Account> callback = new Callback<Account>() {
      @Override public void onResponse(Call<Account> call, Response<Account> response) {
        if (response.isSuccessful()) {
          Account account = response.body();
          if (account != null) {
            AccountManager.getInstance().saveAccount(account, ActivityLogin.this);
            AccountManager.getInstance().saveToken(ActivityLogin.this, account.token);
            AccountManager.getInstance().saveTime(ActivityLogin.this, new Date());
            doLogin();
          }
        } else {
          dismissHud();
          SnackBarUtil.showText(ActivityLogin.this, "轻博客登录失败");
        }
      }

      @Override public void onFailure(Call<Account> call, Throwable t) {
        dismissHud();
        Snackbar.make(mRelativelayout, "有一些小故障哦", Snackbar.LENGTH_SHORT).show();
      }
    };
    if (mPhone != null && mPassWord != null && mPhone.length() == 11 && mPassWord.length() >= 6) {
      Call<Account> call = mAccountService.login(mPhone, mPassWord);
      call.enqueue(callback);
    } else {
      Snackbar.make(mRelativelayout, "请填写正确信息", Snackbar.LENGTH_SHORT).show();
    }
  }

  /**
   * 登录云信
   */
  public void doLogin() {
    if (mPhone != null && mPassWord != null && mPhone.length() == 11 && mPassWord.length() >= 6) {
      final String account = mPhoneEdit.getEditableText().toString().toLowerCase();
      final String token = tokenFromPassword(mPassWordEdit.getEditableText().toString());
      LoginInfo info = new LoginInfo(account, token); // config...
      RequestCallback<LoginInfo> callback = new RequestCallback<LoginInfo>() {
        @Override public void onSuccess(LoginInfo loginInfo) {
          //保存IM账号信息
          dismissHud();
          DemoCache.setAccount(account);
          saveLoginInfo(account, token);
          // 初始化消息提醒
          NIMClient.toggleNotification(UserPreferences.getNotificationToggle());

          // 初始化免打扰
          if (UserPreferences.getStatusConfig() == null) {
            UserPreferences.setStatusConfig(DemoCache.getNotificationConfig());
          }
          NIMClient.updateStatusBarNotificationConfig(UserPreferences.getStatusConfig());
          // 构建缓存
          DataCacheManager.buildDataCacheAsync();
          TurnActivity.startIndexActivity(ActivityLogin.this);
        }

        @Override public void onFailed(int i) {
          dismissHud();
          SnackBarUtil.showText(ActivityLogin.this, i + "error");
        }

        @Override public void onException(Throwable throwable) {
          dismissHud();
          SnackBarUtil.showText(ActivityLogin.this, throwable.toString());
        }
        // 可以在此保存LoginInfo到本地，下次启动APP做自动登录用
      };
      NIMClient.getService(AuthService.class).login(info).setCallback(callback);
    }
  }

  private void saveLoginInfo(final String account, final String token) {
    Preferences.saveUserAccount(account);
    Preferences.saveUserToken(token);
  }

  //DEMO中使用 username 作为 NIM 的account ，md5(password) 作为 token
  //开发者需要根据自己的实际情况配置自身用户系统和 NIM 用户系统的关系
  private String tokenFromPassword(String password) {
    String appKey = readAppKey(this);
    boolean isDemo = "45c6af3c98409b18a84451215d0bdd6e".equals(appKey)
        || "fe416640c8e8a72734219e1847ad2547".equals(appKey);

    return isDemo ? MD5.getStringMD5(password) : password;
  }

  /**
   * 获取appkey
   */
  private static String readAppKey(Context context) {
    try {
      ApplicationInfo appInfo = context.getPackageManager()
          .getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
      if (appInfo != null) {
        return appInfo.metaData.getString("com.netease.nim.appKey");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
