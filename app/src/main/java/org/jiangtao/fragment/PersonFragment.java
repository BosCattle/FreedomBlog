package org.jiangtao.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.jiangtao.freedomblog.R;

/**
 * 个人界面
 */
public class PersonFragment extends Fragment {
  private static final String TAG = PersonFragment.class.getSimpleName();
  private View mView;

  public PersonFragment() {
  }

  @SuppressLint("ValidFragment") public PersonFragment(Context context) {

  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_person, container, false);
    return mView;
  }
}
