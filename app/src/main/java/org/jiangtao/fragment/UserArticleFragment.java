package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyRecyclerAdapter;
import com.smartydroid.android.starter.kit.app.StarterKeysFragment;
import com.smartydroid.android.starter.kit.utilities.RecyclerViewUtils;
import java.util.ArrayList;
import org.jiangtao.holder.HomeViewHolder;
import org.jiangtao.model.Articles;
import org.jiangtao.service.ApiService;
import org.jiangtao.service.ArticleService;
import org.jiangtao.utils.TurnActivity;
import retrofit2.Call;

/**
 * Created by MrJiang on 5/2/2016.
 */
public class UserArticleFragment extends StarterKeysFragment<Articles> {

  private ArticleService mArticleService;
  private int mId;

  public static UserArticleFragment create() {
    return new UserArticleFragment();
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mArticleService = ApiService.createArticleService();
    mId = getActivity().getIntent().getIntExtra("id", 0);
  }

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getRecyclerView().addItemDecoration(RecyclerViewUtils.buildItemDecoration(getContext()));
  }

  @Override
  public Call<ArrayList<Articles>> paginate(Articles sinceItem, Articles maxItem, int perPage) {
    return mArticleService.getMinePublish(mId);
  }

  @Override public Object getKeyForData(Articles item) {
    return item != null && item.id != 0 ? item.id : 0;
  }

  @Override public void bindViewHolders(EasyRecyclerAdapter adapter) {
    adapter.bind(Articles.class, HomeViewHolder.class);
  }

  @Override public void onItemClick(int position, View view) {
    super.onItemClick(position, view);
    Articles articles = (Articles) getAdapter().get(position);
    TurnActivity.startDetailActivity(getActivity(), articles);
  }
}
