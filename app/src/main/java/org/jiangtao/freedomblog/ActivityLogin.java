package org.jiangtao.freedomblog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import org.jiangtao.listener.LoginClickListener;

/**
 * 登录界面
 * author:Kevin
 * description:登录界面
 *
 */
public class ActivityLogin extends AppCompatActivity {
    private static final String TAG = ActivityLogin.class.getSimpleName();
    @Bind(R.id.register_blog) TextView mRegisterBlogTextView;
    private LoginClickListener mLoginClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);
        initializtionControls();
        implementsOnClickListener();
    }

    private void implementsOnClickListener() {
        mRegisterBlogTextView.setOnClickListener(mLoginClickListener);
    }

    private void initializtionControls() {
        mRegisterBlogTextView = (TextView) findViewById(R.id.register_blog);
        mLoginClickListener = new LoginClickListener(this);
    }

}
