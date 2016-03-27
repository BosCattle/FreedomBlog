package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import org.jiangtao.freedomblog.R;
import butterknife.Bind;
import org.xwalk.core.XWalkView;

/**
 * 发布界面
 * why
 */
public class SendFragment extends Fragment {

  @Bind(R.id.x_walk_view) XWalkView mXWalkView;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View mView = inflater.inflate(R.layout.fragment_send, container, false);
    ButterKnife.bind(this, mView);
    mXWalkView.load("https://www.baidu.com/?tn=92655425_hao_pg", null);
    return mView;
  }
}
