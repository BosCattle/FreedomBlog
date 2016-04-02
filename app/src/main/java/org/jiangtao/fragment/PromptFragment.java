package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.jiangtao.freedomblog.R;

/**
 * Class:PromptFragment <br>
 * Description:记录页面 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/1 20:46 <br>
 */
public class PromptFragment extends Fragment {

  private RecyclerView recyclerView;
  private View mView;

  public PromptFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_prompt, container, false);
    recyclerView = (RecyclerView) mView.findViewById(R.id.prompt_recyclerview);
    return mView;
  }
}
