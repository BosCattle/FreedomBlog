package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.rong.imkit.RongIM;
import org.jiangtao.freedomblog.R;

/**
 * Class:PromptFragment <br>
 * Description:记录页面 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/1 20:46 <br>
 */
@Deprecated public class PromptFragment extends Fragment {

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater,  ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view =
        LayoutInflater.from(getContext()).inflate(R.layout.fragment_prompt, container,false);
    return view;
  }

  @Override public void onResume() {
    super.onResume();
    RongIM.getInstance().startConversationList(getContext());
  }
}
