package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import org.jiangtao.freedomblog.R;

/**
 * 发布界面
 * why
 */
public class SendFragment extends Fragment implements View.OnClickListener {

  @Bind(R.id.action_a) FloatingActionButton mFloatingActionButtonA;
  @Bind(R.id.action_b) FloatingActionButton mFloatingActionButtonB;
  @Bind(R.id.action_c) FloatingActionButton mFloatingActionButtonC;
  @Bind(R.id.multiple_actions) FloatingActionsMenu mFloatingActionMenu;
  @Bind(R.id.container_send) RelativeLayout mRelativeLayout;

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View mView = inflater.inflate(R.layout.fragment_send, container, false);
    ButterKnife.bind(this, mView);
    initOnclick();
    return mView;
  }

  private void initOnclick() {
    mFloatingActionButtonA.setOnClickListener(this);
    mFloatingActionButtonB.setOnClickListener(this);
    mFloatingActionButtonC.setOnClickListener(this);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.action_a:
        Snackbar.make(mRelativeLayout,"点击button1",Snackbar.LENGTH_SHORT).show();
        break;
      case R.id.action_b:
        Snackbar.make(mRelativeLayout,"点击button1",Snackbar.LENGTH_SHORT).show();
        break;
      case R.id.action_c:
        Snackbar.make(mRelativeLayout,"点击button1",Snackbar.LENGTH_SHORT).show();
        break;
    }
  }
}
