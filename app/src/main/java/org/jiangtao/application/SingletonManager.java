package org.jiangtao.application;

import java.util.HashMap;
import java.util.Map;

/**
 * Class:SingletonManager <br>
 * Description: 利用容器管理所有的单例<br>
 * Creator: MrJiang <br>
 * Date: 2016/3/16 0:06 <br>
 */
public class SingletonManager {
  private static Map<String, Object> single = new HashMap<>();

  private SingletonManager() {
  }

  public static void registerService(String key, Object object) {
    if (!single.containsKey(key)) {
      single.put(key, object);
    }
  }

  public static Object getService(String key) {
    return single.get(key);
  }
}
