package org.jiangtao.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import org.jiangtao.freedomblog.DetailActivity;
import org.jiangtao.freedomblog.FeedbackActivity;
import org.jiangtao.freedomblog.IndexActivity;
import org.jiangtao.freedomblog.LoginActivity;
import org.jiangtao.freedomblog.PersonAttentionActivity;
import org.jiangtao.freedomblog.PublishActivity;
import org.jiangtao.freedomblog.PublishMineActivity;
import org.jiangtao.freedomblog.RegisterActivity;
import org.jiangtao.freedomblog.RetrieveActivity;
import org.jiangtao.freedomblog.SettingsActivity;
import org.jiangtao.freedomblog.SoftImformationActivity;
import org.jiangtao.freedomblog.UserArticleActivity;
import org.jiangtao.freedomblog.UserDetailActivity;
import org.jiangtao.freedomblog.UserSettingsActivity;
import org.jiangtao.model.Account;
import org.jiangtao.model.Articles;

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
    Intent startSettings = new Intent(mContext, LoginActivity.class);
    startSettings.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    mContext.startActivityForResult(startSettings, StaticResources.requestCodeLogin);
  }

  /**
   * 跳转到主界面
   */
  public static void startIndexActivity(AppCompatActivity activity) {
    Intent intent = new Intent(activity, IndexActivity.class);
    activity.startActivity(intent);
    activity.finish();
  }

  /**
   * 跳到找回密码界面
   */
  public static void startRetrieveActivity(AppCompatActivity activity) {
    Intent intent = new Intent(activity, RetrieveActivity.class);
    activity.startActivity(intent);
  }

  /**
   * 跳到注册界面
   */
  public static void startRegisterActivity(AppCompatActivity activity) {
    Intent intent = new Intent(activity, RegisterActivity.class);
    activity.startActivity(intent);
  }

  /**
   * 跳转到发布界面
   */
  public static void startPublishActivity(Activity activity) {
    Intent intent = new Intent(activity, PublishActivity.class);
    activity.startActivity(intent);
  }

  /**
   * 跳转到详情界面
   */
  public static void startDetailActivity(Activity activity, Articles articles) {
    Intent intent = new Intent(activity, DetailActivity.class);
    intent.putExtra("article", articles);
    activity.startActivity(intent);
  }

  /**
   * 跳转到用户设置界面
   */
  public static void turnUserSettingsActivity(Context mContext) {
    Intent startSettings = new Intent(mContext, UserSettingsActivity.class);
    mContext.startActivity(startSettings);
  }

  public static void startUserDetailActivity(Context mContext, Account account) {
    Intent startSettings = new Intent(mContext, UserDetailActivity.class);
    startSettings.putExtra("account", account);
    mContext.startActivity(startSettings);
  }

  /**
   * 跳转到我的发布界面
   */
  public static void turnPublishMineActivity(Activity mContext) {
    Intent startSettings = new Intent(mContext, PublishMineActivity.class);
    mContext.startActivity(startSettings);
  }

  /**
   * 跳转到关注界面
   */
  public static void turnPersonAttentionActivity(Activity mContext) {
    Intent startSettings = new Intent(mContext, PersonAttentionActivity.class);
    mContext.startActivity(startSettings);
  }

  /**
   * 跳转到别的用户发布的文章
   */
  public static void turnUserArticleActivity(Activity mContext, int id) {
    Intent startSettings = new Intent(mContext, UserArticleActivity.class);
    startSettings.putExtra("id", id);
    mContext.startActivity(startSettings);
  }

  /**
   * 跳转到意见反馈
   */
  public static void turnFeedbackActivity(Activity mContext) {
    Intent startSettings = new Intent(mContext, FeedbackActivity.class);
    mContext.startActivity(startSettings);
  }


  public static void turnSoftInformationActivity(Activity mContext) {
    Intent startSettings = new Intent(mContext, SoftImformationActivity.class);
    mContext.startActivity(startSettings);
  }
}
