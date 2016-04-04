package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.smartydroid.android.starter.kit.app.StarterFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import org.jiangtao.adapter.AccountAdapter;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils.UpdateUserInfoActivityUtils;
import org.jiangtao.view.SettingItems;

/**
 * 用户界面设置
 */
public class UserSettingFragment extends StarterFragment
    implements EasyViewHolder.OnItemClickListener {

  public static final int AVATAR = 1;
  public static final int USERNAME = 2;
  public static final int SEX = 3;
  public static final int PHONE_NUMBER = 7;

  @Bind(android.R.id.list) RecyclerView mUserInfoRecyclerView;
  private ArrayList<SettingItems> mSettingsItems = new ArrayList<>();
  private AccountAdapter mAdapter;

  public static StarterFragment create() {
    return new UserSettingFragment();
  }

  private void setupRecyclerView() {
    mAdapter = new AccountAdapter(getContext(), mSettingsItems);
    mAdapter.setOnClickListener(this);
    mUserInfoRecyclerView.setHasFixedSize(true);
    mUserInfoRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    mUserInfoRecyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(getContext()).size(1)
            .showLastDivider()
            .build());
    mUserInfoRecyclerView.setAdapter(mAdapter);
  }

  private void setUpViews() {
    mSettingsItems.addAll(UpdateUserInfoActivityUtils.buildUserViews(getContext()));
  }

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_user_setting;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    ButterKnife.bind(this, rootView);
    return rootView;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setUpViews();
    setupRecyclerView();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void onItemClick(int position, View view) {

  }
}
