package org.jiangtao.freedomblog;

import android.support.v4.app.Fragment;
import com.smartydroid.android.starter.kit.app.SimpleSinglePaneActivity;
import org.jiangtao.fragment.PublishMineFragment;

/**
 * Created by MrJiang on 4/4/2016.
 * 我的发布
 * 李松柏
 */
public class PublishMineActivity extends SimpleSinglePaneActivity {
  @Override protected Fragment onCreatePane() {
    return PublishMineFragment.create();
  }
}
