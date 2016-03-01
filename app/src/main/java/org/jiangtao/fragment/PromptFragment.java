package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.jiangtao.adapter.PromptRecyclerViewAdapter;
import org.jiangtao.divider.RecyclerViewDividerItemDecoration;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Prompt;

/**
 * Class:PromptFragment <br>
 * Description:记录页面 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/1 20:46 <br>
 */
public class PromptFragment extends Fragment {

  private RecyclerView recyclerView;
  private View mView;
  private PromptRecyclerViewAdapter promptRecyclerViewAdapter;
  private ArrayList<Prompt> mPromptLists;

  public PromptFragment() {
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_prompt, container, false);
    recyclerView = (RecyclerView) mView.findViewById(R.id.prompt_recyclerview);
    setup();
    setRecyclerViewAdapter();
    return mView;
  }

  private void setup() {
    mPromptLists = new ArrayList<>();
    mPromptLists.add(new Prompt(R.drawable.ic_favorite_outline_blue_24dp, "喜欢",
        R.drawable.ic_keyboard_arrow_right_blue_grey_800_24dp));
    mPromptLists.add(new Prompt(R.drawable.ic_mode_comment_black_24dp, "评论",
        R.drawable.ic_keyboard_arrow_right_blue_grey_800_24dp));
    mPromptLists.add(new Prompt(R.drawable.ic_collections_blue_24dp, "收藏",
        R.drawable.ic_keyboard_arrow_right_blue_grey_800_24dp));
    mPromptLists.add(new Prompt(R.drawable.ic_share_black_24dp, "分享",
        R.drawable.ic_keyboard_arrow_right_blue_grey_800_24dp));
  }

  private void setRecyclerViewAdapter() {
    promptRecyclerViewAdapter = new PromptRecyclerViewAdapter(getContext(), mPromptLists);
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setHasFixedSize(true);
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.addItemDecoration(new RecyclerViewDividerItemDecoration(getContext(), 0));
    recyclerView.setAdapter(promptRecyclerViewAdapter);
  }

  @Override public void onPause() {
    super.onPause();
    mPromptLists.clear();
  }
}
