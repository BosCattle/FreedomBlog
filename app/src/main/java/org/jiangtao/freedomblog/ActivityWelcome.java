package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import org.jiangtao.application.BlogApplication;
import org.jiangtao.model.Account;
import org.jiangtao.model.RongYun;
import org.jiangtao.service.ApiService;
import org.jiangtao.service.RongYunService;
import org.jiangtao.utils.AccountManager;
import org.jiangtao.utils.TurnActivity;
import org.jiangtao.utils.preferance.RongyunPreference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 杨杰
 * 欢迎界面
 */
public class ActivityWelcome extends BaseActivity {

  private AlphaAnimation aa;
  private View view;
  private java.util.Date mDate = new java.util.Date();
  private RongYunService mRongYunService;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    view = View.inflate(this, R.layout.activity_activity_welcome, null);
    setContentView(view);
    mRongYunService = ApiService.createRongYunService();
    // 渐变展示启动屏
    openAlphaAnimation();
  }

  private void openAlphaAnimation() {
    aa = new AlphaAnimation(0.5f, 1.0f);
    aa.setDuration(200);
    view.startAnimation(aa);
    aa.setAnimationListener(new Animation.AnimationListener() {
      @Override public void onAnimationEnd(Animation arg0) {
        redirectTo();
      }

      @Override public void onAnimationRepeat(Animation animation) {
      }

      @Override public void onAnimationStart(Animation animation) {
      }
    });
  }

  private void redirectTo() {
    Account account = AccountManager.getInstance().getAccount(this);
    if (account != null && isTimeOut()) {
      getToken();
    }else {
      TurnActivity.turnLoginActivity(ActivityWelcome.this);
      finish();
    }
  }

  @Override protected void onStop() {
    super.onStop();
    finish();
  }

  public void getToken() {
    final RongYun rongYun = RongyunPreference.getRongYun(getApplicationContext());
    if (rongYun == null) {
      TurnActivity.turnLoginActivity(this);
      finish();
    }
    Call<RongYun> call1 =
        mRongYunService.register(rongYun.userId, rongYun.userName, rongYun.portraitUri);
    call1.enqueue(new Callback<RongYun>() {
      @Override public void onResponse(Call<RongYun> call, Response<RongYun> response) {
        if (response.isSuccessful()) {
          RongyunPreference.saveRongYun(getApplicationContext(), response.body());
          connect(response.body().token);
        } else {
          TurnActivity.turnLoginActivity(ActivityWelcome.this);
          finish();
        }
      }

      @Override public void onFailure(Call<RongYun> call, Throwable t) {
        TurnActivity.turnLoginActivity(ActivityWelcome.this);
        finish();
      }
    });
  }

  /**
   * 判断是否超时
   *
   * @return false :超时
   * true :没超时
   */
  public boolean isTimeOut() {
    java.util.Date date = AccountManager.getInstance().getTime(this);
    if (date==null){
      AccountManager.getInstance().saveTime(this,mDate);
      return true;
    }
    int daysBetween = AccountManager.getInstance().daysBetween(mDate, date);
    AccountManager.getInstance().saveTime(this, mDate);
    Log.d("----------->", daysBetween + "");
    return daysBetween < 10;
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

          Log.d("LoginActivity", "--onTokenIncorrect");
        }

        /**
         * 连接融云成功
         * @param userid 当前 token
         */
        @Override public void onSuccess(String userid) {
          Log.d("LoginActivity", "--onSuccess" + userid);
          TurnActivity.startIndexActivity(ActivityWelcome.this);
          finish();
        }

        /**
         * 连接融云失败
         * @param errorCode 错误码，可到官网 查看错误码对应的注释
         */
        @Override public void onError(RongIMClient.ErrorCode errorCode) {
          Log.d("LoginActivity", "--onError" + errorCode);
        }
      });
    }
  }
}
