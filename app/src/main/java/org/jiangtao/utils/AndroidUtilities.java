/**
 * Created by YuGang Yang on September 17, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package org.jiangtao.utils;

import android.graphics.Typeface;
import com.smartydroid.android.starter.kit.app.StarterKitApp;
import java.util.Hashtable;

public class AndroidUtilities {

  public static float density = 1;
  private static final Hashtable<String, Typeface> typefaceCache = new Hashtable<>();

  public static Typeface getTypeface(String assetPath) {
    synchronized (typefaceCache) {
      if (!typefaceCache.containsKey(assetPath)) {
        try {
          Typeface t = Typeface.createFromAsset(StarterKitApp.appContext().getAssets(), assetPath);
          typefaceCache.put(assetPath, t);
        } catch (Exception e) {
          FileLog.e("Typefaces", "Could not get typeface '" + assetPath + "' because " + e.getMessage());
          return null;
        }
      }
      return typefaceCache.get(assetPath);
    }
  }

  static {
    density = StarterKitApp.appResources().getDisplayMetrics().density;
  }

  public static int dp(float value) {
    if (value == 0) {
      return 0;
    }
    return (int)Math.ceil(density * value);
  }

}
