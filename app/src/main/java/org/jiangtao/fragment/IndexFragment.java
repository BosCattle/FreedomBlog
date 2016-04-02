package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.jiangtao.freedomblog.R;

/**
 * A simple {@link Fragment} subclass.
 * author:Kevin
 * class: IndexFragment
 * description:主页中的fragment
 * 从网络中取数据填充
 */
public class IndexFragment extends Fragment {

  private View mView;

  public IndexFragment() {

  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_index, container, false);
    return mView;
  }
}
