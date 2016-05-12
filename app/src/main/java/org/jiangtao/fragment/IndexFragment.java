package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * A simple {@link Fragment} subclass.
 * author:李松柏
 * class: IndexFragment
 * description:主页中的fragment
 * 从网络中取数据填充
 */
public class IndexFragment extends StarterKeysFragment<Articles> {

  private ArticleService mArticleService;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mArticleService = ApiService.createArticleService();
  }

  @Override public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    getRecyclerView().addItemDecoration(RecyclerViewUtils.buildItemDecoration(getContext()));
  }

  @Override public void bindViewHolders(EasyRecyclerAdapter adapter) {
    adapter.bind(Articles.class, HomeViewHolder.class);
  }

  @Override
  public Call<ArrayList<Articles>> paginate(Articles sinceItem, Articles maxItem, int perPage) {
    String maxId = maxItem == null ? "0" : maxItem.id + "";
    return mArticleService.getArticles(maxId, null, perPage);
  }

  @Override public Object getKeyForData(Articles item) {
    return item.id;
  }

  @Override public void onItemClick(int position, View view) {
    super.onItemClick(position, view);
    Articles articles = (Articles) getAdapter().get(position);
    TurnActivity.startDetailActivity(getActivity(), articles);
  }
}
