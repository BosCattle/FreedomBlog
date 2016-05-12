package org.jiangtao.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.smartydroid.android.starter.kit.app.StarterFragment;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils.TurnActivity;

/**
 * 开启发布界面
 * 李松柏
 */
public class SendFragment extends StarterFragment {

  @Bind(R.id.image_show) ImageView mImageShow;
  @Bind(R.id.btn_publish) Button mBtnPublish;

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_send;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @OnClick(R.id.btn_publish) public void onClick(View v) {
    TurnActivity.startPublishActivity(getActivity());
  }
}
