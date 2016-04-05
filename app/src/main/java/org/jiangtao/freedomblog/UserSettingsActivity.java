package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import org.jiangtao.adapter.AccountAdapter;
import org.jiangtao.utils.UpdateUserInfoActivityUtils;
import org.jiangtao.view.SettingItems;

/**
 * 设置用户信息
 */
public class UserSettingsActivity extends StarterActivity
    implements EasyViewHolder.OnItemClickListener {

  public static final int AVATAR = 1;
  public static final int USERNAME = 2;
  public static final int SEX = 3;
  public static final int PHONE_NUMBER = 7;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_user_setting);
    mSettingsItems.clear();
    setUpViews();
    setupRecyclerView();
  }

  @Bind(android.R.id.list) RecyclerView mUserInfoRecyclerView;
  private ArrayList<SettingItems> mSettingsItems = new ArrayList<>();
  private AccountAdapter mAdapter;

  private void setupRecyclerView() {
    mAdapter = new AccountAdapter(this, mSettingsItems);
    mAdapter.setOnClickListener(this);
    mUserInfoRecyclerView.setHasFixedSize(true);
    mUserInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mUserInfoRecyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this).size(1).showLastDivider().build());
    mUserInfoRecyclerView.setAdapter(mAdapter);
  }

  private void setUpViews() {
    mSettingsItems.addAll(UpdateUserInfoActivityUtils.buildUserViews(this));
  }

  @Override public void onItemClick(int position, View view) {

  }
}
