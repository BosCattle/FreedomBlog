package org.jiangtao.freedomblog;

import android.support.v4.app.Fragment;
import com.smartydroid.android.starter.kit.app.SimpleSinglePaneActivity;
import org.jiangtao.fragment.PersonalFocusFragment;

public class PersonAttentionActivity extends SimpleSinglePaneActivity {
    @Override protected Fragment onCreatePane() {
        return PersonalFocusFragment.create();
    }
}
