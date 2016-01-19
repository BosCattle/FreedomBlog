package org.jiangtao.application;

import android.app.Application;
import android.content.res.Configuration;

/**
 * Created by MrJiang on 2015/12/30.
 */
public class BlogApplication extends Application{
    public static boolean isLogin;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
