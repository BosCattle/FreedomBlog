package org.jiangtao.utils_resource;

import android.content.Context;
import android.content.Intent;

import org.jiangtao.freedomblog.SettingsActivity;

/**
 * Created by MrJiang on 2015/12/31.
 * 全局页面跳转
 */
public class TurnActivity {
    /**
     * 跳转到设置界面
     *
     * @param mContext
     */
    public static void turnSettingsActivity(Context mContext) {
        Intent startSettings = new Intent(mContext, SettingsActivity.class);
        mContext.startActivity(startSettings);
    }
}
