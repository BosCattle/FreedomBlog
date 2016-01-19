package org.jiangtao.listener;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import org.jiangtao.freedomblog.ActivityRegister;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils_resource.StaticResources;

/**
 * Created by MrJiang on 2016/1/19.
 */
public class LoginClickListener implements View.OnClickListener {
    private AppCompatActivity mContext;
    private static final String TAG = LoginClickListener.class.getSimpleName();

    public LoginClickListener(AppCompatActivity context) {
        mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register_blog: {
                Log.d(TAG, "外部类实现监听");
                turnRegisterActivity();
                break;
            }
        }
    }

    /**
     * 跳转到注册界面
     */
    private void turnRegisterActivity() {
        Intent intent = new Intent(mContext, ActivityRegister.class);
        mContext.startActivityForResult(intent, StaticResources.requestCodeRegister);
    }
}
