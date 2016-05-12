package org.jiangtao.freedomblog;

import android.support.v4.app.Fragment;
import com.smartydroid.android.starter.kit.app.SimpleSinglePaneActivity;
import org.jiangtao.fragment.PersonalFocusFragment;

/**
 * 关注界面
 * 李松柏
 */
public class PersonAttentionActivity extends SimpleSinglePaneActivity {
    @Override protected Fragment onCreatePane() {
        return PersonalFocusFragment.create();
    }
}
