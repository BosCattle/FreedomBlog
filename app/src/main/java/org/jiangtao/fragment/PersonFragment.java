package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Bind;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.smartydroid.android.starter.kit.app.StarterFragment;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import org.jiangtao.adapter.AccountAdapter;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils.TurnActivity;
import org.jiangtao.utils.build.AccountBuildUtils;
import org.jiangtao.view.SettingItems;

/**
 * 周鑫
 * 个人界面
 */
public class PersonFragment extends StarterFragment implements EasyViewHolder.OnItemClickListener {

  public static final int ITEM_VIEW_HEADER = 100;
  public static final int ITEM_VIEW_COMMENT = 200;
  public static final int ITEM_VIEW_SETTING = 300;
  public static final int ITEM_VIEW_PUBLISH = 400;
  public static final int ITEM_VIEW_COLLECTION = 500;

  @Bind(R.id.recycler_person) RecyclerView mPersonRecycler;

  private static final String TAG = PersonFragment.class.getSimpleName();
  private AccountAdapter mAccountAdapter;
  private ArrayList<SettingItems> mSettingItems = new ArrayList<>();

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_person;
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setUpView();
    setUpAdapter();
  }

  private void setUpAdapter() {
    mAccountAdapter = new AccountAdapter(getContext(), mSettingItems);
    mAccountAdapter.setOnClickListener(this);
    mPersonRecycler.setHasFixedSize(true);
    mPersonRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
    mPersonRecycler.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(getContext()).size(1)
            .showLastDivider()
            .build());
    mPersonRecycler.setAdapter(mAccountAdapter);
  }

  private void setUpView() {
    mSettingItems.clear();
    mSettingItems.addAll(AccountBuildUtils.buildParentViews(getContext()));
  }

  @Override public void onItemClick(int position, View view) {
    SettingItems settingItems = mSettingItems.get(position);
    switch (settingItems.mType) {
      case ITEM_VIEW_HEADER:
        TurnActivity.turnUserSettingsActivity(getActivity());
        break;
      case ITEM_VIEW_COMMENT:
        TurnActivity.turnPersonAttentionActivity(getActivity());
        break;
      case ITEM_VIEW_SETTING:
        TurnActivity.turnSettingsActivity(getActivity());
        break;
      case ITEM_VIEW_PUBLISH:
        TurnActivity.turnPublishMineActivity(getActivity());
        break;
      case ITEM_VIEW_COLLECTION:
        break;
    }
  }

  @Override public void onResume() {
    super.onResume();
    setUpView();
    setUpAdapter();
    mAccountAdapter.notifyDataSetChanged();
  }
}
