package org.jiangtao.fragment;

import com.carlosdelachica.easyrecycleradapters.adapter.EasyRecyclerAdapter;
import com.smartydroid.android.starter.kit.app.StarterFragment;
import com.smartydroid.android.starter.kit.app.StarterKeysFragment;
import java.util.ArrayList;
import org.jiangtao.model.Articles;
import retrofit2.Call;

/**
 * Created by MrJiang on 4/4/2016.
 * 我的发布fragment
 */
public class PublishMineFragment extends StarterKeysFragment<Articles> {

  public static StarterFragment create() {
    return new PublishMineFragment();
  }

  @Override
  public Call<ArrayList<Articles>> paginate(Articles sinceItem, Articles maxItem, int perPage) {
    return null;
  }

  @Override public Object getKeyForData(Articles item) {
    return null;
  }

  @Override public void bindViewHolders(EasyRecyclerAdapter adapter) {

  }
}
