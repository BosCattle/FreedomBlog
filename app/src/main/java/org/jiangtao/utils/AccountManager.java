package org.jiangtao.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import org.jiangtao.model.Account;
import org.json.JSONException;

/**
 * Created by Kevin
 * on 2016/3/20.
 * 持久化account信息
 * 持续化token信息
 */
public class AccountManager {

  private static AccountManager mAccountManager;

  private AccountManager() {
  }

  public static AccountManager getInstance() {
    if (mAccountManager == null) {
      synchronized (AccountManager.class) {
        mAccountManager = new AccountManager();
      }
    }
    return mAccountManager;
  }

  /**
   * 保存用户信息
   *
   * @throws JSONException
   */
  public void saveAccount(Account account, Context context) {
    SharedPreferences preferences = context.getSharedPreferences("accounts", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = preferences.edit();
    Gson gson = new Gson();
    String account1 = gson.toJson(account);
    editor.putString("account", account1);
    editor.apply();
  }

  /**
   * 获取用户信息
   */
  public Account getAccount(Context context) {
    SharedPreferences shared = context.getSharedPreferences("accounts", Context.MODE_PRIVATE);
    String object = shared.getString("account", null);
    Gson gson = new Gson();
    return gson.fromJson(object, Account.class);
  }

  /**
   * 持续化token
   */
  public void saveToken(Context context, String token) {
    SharedPreferences shared = context.getSharedPreferences("tokens", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = shared.edit();
    editor.putString("token", token);
    editor.apply();
  }

  /**
   * 获取token
   */
  public String getToken(Context context) {
    SharedPreferences shared = context.getSharedPreferences("tokens", Context.MODE_PRIVATE);
    return shared.getString("token", null);
  }

  /**
   * 每次启动applications
   * 保存当前日期
   */
  public void saveTime(Context context, java.util.Date date) {
    SharedPreferences shared = context.getSharedPreferences("intervals", Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = shared.edit();
    Gson gson = new Gson();
    String time = gson.toJson(date);
    editor.putString("time", time);
    editor.apply();
  }

  /**
   * 获取上次保存的数据
   * 错误
   */
  public java.util.Date getTime(Context context) {
    SharedPreferences shared = context.getSharedPreferences("intervals", Context.MODE_PRIVATE);
    Gson gson = new Gson();
    String object = shared.getString("time", "null");
    return gson.fromJson(object, java.util.Date.class);
  }

  /**
   * 获取两次登录的时间差
   * 用于判断token是否有效
   */
  public int daysBetween(java.util.Date smdate, java.util.Date bdate) {
    return (int) ((smdate.getTime() / 86400000L) - (bdate.getTime() / 86400000L));
  }
}
