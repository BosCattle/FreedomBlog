package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyRecyclerAdapter;
import com.smartydroid.android.starter.kit.app.StarterKeysFragment;
import com.smartydroid.android.starter.kit.utilities.RecyclerViewUtils;
import java.util.ArrayList;
import org.jiangtao.holder.AttentionsViewHolder;
import org.jiangtao.model.Account;
import org.jiangtao.model.Focus;
import org.jiangtao.service.ApiService;
import org.jiangtao.service.FocusService;
import org.jiangtao.utils.AccountManager;
import org.jiangtao.utils.TurnActivity;
import retrofit2.Call;

/**
 * 获取所有关注
 * 关注我的人
 * 李松柏
 */
public class AttentionFragment extends StarterKeysFragment<Focus> {

  private FocusService mFocusService;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mFocusService = ApiService.createFocusService();
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getRecyclerView().addItemDecoration(RecyclerViewUtils.buildItemDecoration(getContext()));
  }

  @Override public Call<ArrayList<Focus>> paginate(Focus sinceItem, Focus maxItem, int perPage) {
    Account account = AccountManager.getInstance().getAccount(getContext());
    return mFocusService.personalFocus(account.id);
  }

  @Override public Object getKeyForData(Focus item) {
    return item != null && item.id != 0 ? item.id : 0;
  }

  @Override public void bindViewHolders(EasyRecyclerAdapter adapter) {
    adapter.bind(Focus.class, AttentionsViewHolder.class);
    adapter.setOnClickListener(this);
  }

  @Override public void onItemClick(int position, View view) {
    Focus focus = (Focus) getAdapter().get(position);
    Account account = focus.accountFocus;
    TurnActivity.turnUserArticleActivity(getActivity(), account.id);
  }
}
