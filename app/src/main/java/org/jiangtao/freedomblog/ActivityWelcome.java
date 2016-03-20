package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import org.jiangtao.model.Account;
import org.jiangtao.utils_resource.AccountManager;
import org.jiangtao.utils_resource.TurnActivity;

public class ActivityWelcome extends AppCompatActivity {
  
  private AlphaAnimation aa;
  private View view;
  java.util.Date mDate = new java.util.Date();
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    view = View.inflate(this, R.layout.activity_activity_welcome, null);
    setContentView(view);
    // 渐变展示启动屏
    openAlphaAnimation();
  }

  private void openAlphaAnimation() {
    aa = new AlphaAnimation(0.5f, 1.0f);
    aa.setDuration(800);
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
      TurnActivity.startIndexActivity(this);
    } else {
      TurnActivity.turnLoginActivity(this);
    }
  }

  @Override protected void onStop() {
    super.onStop();
    finish();
  }

  /**
   * 判断是否超时
   *
   * @return false :超时
   * true :没超时
   */
  public boolean isTimeOut() {
    java.util.Date date =
        AccountManager.getInstance().getTime(this);
    Log.d("--------->",date.toString());
    Log.d("---------->",mDate.toString());
    int daysBetween = AccountManager.getInstance().daysBetween(mDate, date);
    AccountManager.getInstance().saveTime(this, mDate);
    Log.d("----------->",daysBetween+"");
    return daysBetween < 10;
  }
}
