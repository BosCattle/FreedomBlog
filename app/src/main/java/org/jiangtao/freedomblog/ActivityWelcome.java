package org.jiangtao.freedomblog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import org.jiangtao.service.PreLoadService;

public class ActivityWelcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view = View.inflate(this, R.layout.activity_activity_welcome, null);
        setContentView(view);
        // 渐变展示启动屏
        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(800);
        view.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                redirectTo();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
    }

    private void redirectTo() {
        //启动服务预加载
        Intent preLoadService = new Intent(ActivityWelcome.this, PreLoadService.class);
        startService(preLoadService);
        Intent openIndexActivity = new Intent(ActivityWelcome.this, ActivityIndex.class);
        startActivity(openIndexActivity);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
