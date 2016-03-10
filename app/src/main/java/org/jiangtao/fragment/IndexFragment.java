package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.jiangtao.freedomblog.R;

/**
 * A simple {@link Fragment} subclass.
 * author:Kevin
 * class: IndexFragment
 * description:主页中的fragment
 * 从网络中取数据填充
 */
public class IndexFragment extends Fragment {

  //@Bind(R.id.index_recyclerview) RecyclerView mRecyclerView;
  //private ArrayList<Article> mArticleList;
  private View mView;

  public IndexFragment() {

  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mView = inflater.inflate(R.layout.fragment_index, container, false);
    //setUp();
    //setUpRecyclerView();
    return mView;
  }

  //private void setUpRecyclerView() {
  //  IndexRecyclerAdapter adapter = new IndexRecyclerAdapter(getContext(), mArticleList);
  //  mRecyclerView.addItemDecoration(new RecyclerViewDividerItemDecoration(getContext(), 0));
  //  mRecyclerView.setAdapter(adapter);
  //}

  /**
   * Mehod: setUp <br>
   * Descrption: 初始化list <br>
   * Creator: MrJiang <br>
   * Date: 2016/3/10 18:54 <br>
   */
  private void setUp() {

  }
}
