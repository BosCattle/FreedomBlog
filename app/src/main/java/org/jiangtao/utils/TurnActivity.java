package org.jiangtao.utils;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import org.jiangtao.freedomblog.ActivityIndex;
import org.jiangtao.freedomblog.ActivityLogin;
import org.jiangtao.freedomblog.ActivityRegister;
import org.jiangtao.freedomblog.ActivityRetrieve;
import org.jiangtao.freedomblog.SettingsActivity;

/**
 * Created by MrJiang on 2015/12/31.
 * 全局页面跳转
 */
public class TurnActivity {
  /**
   * 跳转到设置界面
   */
  public static void turnSettingsActivity(Context mContext) {
    Intent startSettings = new Intent(mContext, SettingsActivity.class);
    mContext.startActivity(startSettings);
  }

  /**
   * 跳转到登陆界面
   */
  public static void turnLoginActivity(AppCompatActivity mContext) {
    Intent startSettings = new Intent(mContext, ActivityLogin.class);
    mContext.startActivityForResult(startSettings, StaticResources.requestCodeLogin);
  }

  /**
   * 跳转到主界面
   */
  public static void startIndexActivity(AppCompatActivity activity) {
    Intent intent = new Intent(activity, ActivityIndex.class);
    activity.startActivity(intent);
    activity.finish();
  }

  /**
   * 跳到找回密码界面
   */
  public static void startRetrieveActivity(AppCompatActivity activity) {
    Intent intent = new Intent(activity, ActivityRetrieve.class);
    activity.startActivity(intent);
  }

  /**
   * 跳到注册界面
   */
  public static void startRegisterActivity(AppCompatActivity activity) {
    Intent intent = new Intent(activity, ActivityRegister.class);
    activity.startActivity(intent);
  }
}
