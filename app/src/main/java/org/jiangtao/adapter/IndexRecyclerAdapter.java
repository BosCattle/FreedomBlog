package org.jiangtao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Article;
import org.jiangtao.viewholder.IndexViewHolder;

/**
 * Method:IndexRecyclerAdapter <br>
 * Description:适配器 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/10 18:48 <br>
 */
public class IndexRecyclerAdapter extends RecyclerView.Adapter<IndexViewHolder> {
  private Context mContext;
  private ArrayList<Article> mArticleList;
  private LayoutInflater mInflater;

  public IndexRecyclerAdapter(Context context, ArrayList<Article> articles) {
    mContext = context;
    mArticleList = articles;
    mInflater = LayoutInflater.from(mContext);
  }

  @Override public IndexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.list_item_index, parent, false);
    IndexViewHolder indexViewHolder = new IndexViewHolder(view);
    return indexViewHolder;
  }

  @Override public void onBindViewHolder(IndexViewHolder holder, int position) {
    holder.setText(mArticleList, position);
  }

  @Override public int getItemCount() {
    return mArticleList.size();
  }
}
