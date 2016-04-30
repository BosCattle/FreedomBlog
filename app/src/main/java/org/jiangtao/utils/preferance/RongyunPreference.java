package org.jiangtao.utils.preferance;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import org.jiangtao.model.RongYun;

/**
 * Created by MrJiang on 4/30/2016.
 */
public class RongyunPreference {
  private static RongyunPreference instance;
  public static String SHARE_NAME = "rongyun";
  public static String SHARE_VALUE = "value";

  private RongyunPreference() {
  }

  public static RongyunPreference getInstance() {
    if (instance == null) {
      synchronized (RongyunPreference.class) {
        instance = new RongyunPreference();
      }
    }
    return instance;
  }

  /**
   * 保存融云账号
   */
  public static void saveRongYun(Context context, RongYun rongYun) {
    SharedPreferences sharedPreferences =
        context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    Gson gson = new Gson();
    String rongyun = gson.toJson(rongYun);
    editor.putString(SHARE_VALUE, rongyun);
    editor.apply();
  }

  public static RongYun getRongYun(Context context) {
    SharedPreferences sharedPreferences =
        context.getSharedPreferences(SHARE_NAME, Context.MODE_PRIVATE);
    String object = sharedPreferences.getString(SHARE_VALUE, null);
    Gson gson = new Gson();
    return gson.fromJson(object, RongYun.class);
  }
}
