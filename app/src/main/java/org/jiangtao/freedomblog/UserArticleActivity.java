package org.jiangtao.freedomblog;

import android.support.v4.app.Fragment;
import com.smartydroid.android.starter.kit.app.SimpleSinglePaneActivity;
import org.jiangtao.fragment.UserArticleFragment;

/**
 * Created by MrJiang on 5/2/2016.
 * 首页文章界面
 */
public class UserArticleActivity extends SimpleSinglePaneActivity {
  @Override protected Fragment onCreatePane() {
    return UserArticleFragment.create();
  }
}
