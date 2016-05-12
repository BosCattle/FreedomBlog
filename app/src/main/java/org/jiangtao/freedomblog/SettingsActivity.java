package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import org.jiangtao.adapter.AccountAdapter;
import org.jiangtao.utils.TurnActivity;
import org.jiangtao.utils.data.IconData;
import org.jiangtao.view.SettingItems;

/**
 * author:周 鑫
 * description:设置界面
 */
public class SettingsActivity extends StarterActivity
    implements View.OnClickListener, EasyViewHolder.OnItemClickListener {

  public static final int MODIFY_PASSWORD = 1;
  public static final int CLEAR_CACHE = 2;
  public static final int CHECK_UPDATES = 3;
  public static final int SOFTWARE_INFORMATION = 4;
  public static final int FEED_BACK = 5;
  public static final int LOGOUT_BUTTON = 6;
  private static final int[] ATTRS = new int[] {
      android.R.attr.listDivider
  };

  @Bind(android.R.id.list) RecyclerView mRecyclerView;

  private AccountAdapter mAdapter;
  private ArrayList<SettingItems> mSettingsItems = new ArrayList<>();

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_setting);

    setup();
    setupRecyclerView();
  }

  /**
   * 填充RecyclerView的数据
   */
  private void setup() {
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_TEXT)
        .data(new IconData("修改密码"))
        .type(MODIFY_PASSWORD)
        .enabled(true)
        .build());
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_TEXT)
        .data(new IconData("清理缓存"))
        .type(CLEAR_CACHE)
        .enabled(true)
        .build());
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_TEXT)
        .data(new IconData("检查更新"))
        .type(CHECK_UPDATES)
        .enabled(true)
        .build());
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_TEXT)
        .data(new IconData("关于我们"))
        .type(SOFTWARE_INFORMATION)
        .enabled(true)
        .build());
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_TEXT)
        .data(new IconData("意见反馈"))
        .type(FEED_BACK)
        .enabled(true)
        .build());
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());
    mSettingsItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_CHECK_BUTTON)
        .enabled(false)
        .text("退出登录")
        .type(LOGOUT_BUTTON)
        .build());
  }

  /**
   * 设置adapter
   */
  private void setupRecyclerView() {
    mAdapter = new AccountAdapter(this, mSettingsItems);
    mRecyclerView.setHasFixedSize(true);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this).size(1).showLastDivider().build());
    mRecyclerView.setAdapter(mAdapter);
    mAdapter.setOnClickListener(this);
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());
  }

  @Override public void onItemClick(int position, View view) {
    switch (mSettingsItems.get(position).mType) {
      case MODIFY_PASSWORD:
        break;
      case CLEAR_CACHE:
        break;
      case CHECK_UPDATES:

        break;
      case SOFTWARE_INFORMATION:
        break;
      case FEED_BACK:
        break;
      case LOGOUT_BUTTON:
        TurnActivity.turnLoginActivity(SettingsActivity.this);
        break;
    }
  }

  @Override public void onClick(View v) {

  }
}
