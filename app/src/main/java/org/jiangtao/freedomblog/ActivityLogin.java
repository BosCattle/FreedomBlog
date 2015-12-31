package org.jiangtao.freedomblog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_login);
        hideActionBar();

    }

    /**
     * 隐藏ActionBar
     */
    private void hideActionBar() {
        android.support.v7.app.ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();
    }
}
