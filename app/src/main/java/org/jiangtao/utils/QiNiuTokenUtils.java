package org.jiangtao.utils;

import android.content.Context;
import com.smartydroid.android.starter.kit.utilities.ScreenUtils;

/**
 * Created by MrJiang on 4/3/2016.
 * 获取七牛token
 */
public class QiNiuTokenUtils {

  private static QiNiuTokenUtils instance;

  private QiNiuTokenUtils() {
  }

  public static QiNiuTokenUtils getInstance() {
    if (instance == null) {
      synchronized (QiNiuTokenUtils.class) {
        instance = new QiNiuTokenUtils();
      }
    }
    return instance;
  }

  /**
   * 拼接七牛key
   */
  public String spliceUrl(String key) {
    return "http://7xq3zt.com1.z0.glb.clouddn.com/" + key + "?imageView2/0/w/600/h/800";
  }

  public String spliceUrl(String key, Context context) {
    return "http://7xq3zt.com1.z0.glb.clouddn.com/" + key + "?imageView2/0/w/" + ScreenUtils.dp2px(
        100, context) + "/h/" + ScreenUtils.dp2px(200, context);
  }
}
