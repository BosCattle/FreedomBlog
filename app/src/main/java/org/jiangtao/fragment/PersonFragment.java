package org.jiangtao.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import org.jiangtao.adapter.PromptRecyclerViewAdapter;
import org.jiangtao.application.BlogApplication;
import org.jiangtao.divider.RecyclerViewDividerItemDecoration;
import org.jiangtao.freedomblog.PersonAttentionActivity;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Prompt;
import org.jiangtao.utils_resource.TurnActivity;

/**
 * 个人界面
 */
public class PersonFragment extends Fragment implements View.OnClickListener {
  private static final String TAG = PersonFragment.class.getSimpleName();
  private Context mContext;
  private View mView;
  private RelativeLayout mRelativeLayoutHead;
  private TextView mTextViewAttention;
  private RecyclerView mRecyclerView;
  private PromptRecyclerViewAdapter mPromptRecyclerViewAdapter;
  private ArrayList<Prompt> mPersonLists;

  public PersonFragment() {
  }

  @SuppressLint("ValidFragment") public PersonFragment(Context context) {
    mContext = context;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_person, container, false);
    initializtionControls();
    mRecyclerView = (RecyclerView) mView.findViewById(R.id.recyclerview_person);
    setup();
    setRecyclerViewAdapter();
    addOnClickListener();
    return mView;
  }

  private void setup() {
    mPersonLists = new ArrayList<>();
    mPersonLists.add(new Prompt(R.drawable.ic_favorite_outline_blue_24dp, "喜欢",
        R.drawable.ic_keyboard_arrow_right_blue_grey_800_24dp));
    mPersonLists.add(new Prompt(R.drawable.ic_mode_comment_black_24dp, "评论",
        R.drawable.ic_keyboard_arrow_right_blue_grey_800_24dp));
    mPersonLists.add(new Prompt(R.drawable.ic_collections_blue_24dp, "收藏",
        R.drawable.ic_keyboard_arrow_right_blue_grey_800_24dp));
    mPersonLists.add(new Prompt(R.drawable.ic_share_black_24dp, "分享",
        R.drawable.ic_keyboard_arrow_right_blue_grey_800_24dp));
  }

  private void setRecyclerViewAdapter() {
    mPromptRecyclerViewAdapter = new PromptRecyclerViewAdapter(getContext(), mPersonLists);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    mRecyclerView.addItemDecoration(new RecyclerViewDividerItemDecoration(getContext(), 0));
    mRecyclerView.setAdapter(mPromptRecyclerViewAdapter);
  }

  @Override public void onPause() {
    super.onPause();
    mPersonLists.clear();
  }

  /**
   * 为按钮添加监听器
   */
  private void addOnClickListener() {
    mRelativeLayoutHead.setOnClickListener(this);
    mTextViewAttention.setOnClickListener(this);
  }

  /**
   * 初始化控件
   */
  private void initializtionControls() {
    mRelativeLayoutHead = (RelativeLayout) mView.findViewById(R.id.sub_fragment_person_image);
    mTextViewAttention = (TextView) mView.findViewById(R.id.sublayout_textview_attention);
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.sub_fragment_person_image: {
        clickHeadImage();
        break;
      }
      case R.id.sublayout_textview_attention: {
        Intent intent = new Intent(mContext, PersonAttentionActivity.class);
        startActivity(intent);
        break;
      }
      case R.id.sublayout_textview_article: {

        break;
      }
      case R.id.sublayout_textview_fans: {

        break;
      }
    }
  }

  /**
   * 点击头像跳转
   */
  private void clickHeadImage() {
    if (BlogApplication.isLogin) {

    } else {
      TurnActivity.turnLoginActivity((AppCompatActivity) mContext);
    }
  }
}
