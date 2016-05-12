package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import java.util.Date;
import org.jiangtao.application.BlogApplication;
import org.jiangtao.model.Account;
import org.jiangtao.model.RongYun;
import org.jiangtao.service.AccountService;
import org.jiangtao.service.ApiService;
import org.jiangtao.service.RongYunService;
import org.jiangtao.utils.AccountManager;
import org.jiangtao.utils.SnackBarUtil;
import org.jiangtao.utils.TurnActivity;
import org.jiangtao.utils.preferance.RongyunPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 登录界面
 * author:周鑫
 * description:登录界面
 */
public class ActivityLogin extends StarterActivity implements View.OnClickListener {

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
  private RongYunService mRongYunService;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_activity_login);
    ButterKnife.bind(this);
    setEditTextValue();
    mAccountService = ApiService.createAccountService();
    mRongYunService = ApiService.createRongYunService();
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
            Call<RongYun> call1 =
                mRongYunService.register(account.phone, account.username, account.imageUrl);
            call1.enqueue(new Callback<RongYun>() {
              @Override public void onResponse(Call<RongYun> call, Response<RongYun> response) {
                if (response.isSuccessful()) {
                  RongyunPreference.saveRongYun(getApplicationContext(), response.body());
                  connect(response.body().token);
                } else {
                  TurnActivity.turnLoginActivity(ActivityLogin.this);
                  finish();
                }
              }

              @Override public void onFailure(Call<RongYun> call, Throwable t) {
                TurnActivity.turnLoginActivity(ActivityLogin.this);
                finish();
              }
            });
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

  private void connect(String token) {

    if (getApplicationInfo().packageName.equals(
        BlogApplication.getCurProcessName(getApplicationContext()))) {

      /**
       * IMKit SDK调用第二步,建立与服务器的连接
       */
      RongIM.connect(token, new RongIMClient.ConnectCallback() {

        /**
         * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
         */
        @Override public void onTokenIncorrect() {
          dismissHud();
          Log.d("LoginActivity", "--onTokenIncorrect");
        }

        /**
         * 连接融云成功
         * @param userid 当前 token
         */
        @Override public void onSuccess(String userid) {
          dismissHud();
          Log.d("LoginActivity", "--onSuccess" + userid);
          TurnActivity.startIndexActivity(ActivityLogin.this);
          finish();
        }

        /**
         * 连接融云失败
         * @param errorCode 错误码，可到官网 查看错误码对应的注释
         */
        @Override public void onError(RongIMClient.ErrorCode errorCode) {
          dismissHud();
          Log.d("LoginActivity", "--onError" + errorCode);
        }
      });
    }
  }
}
