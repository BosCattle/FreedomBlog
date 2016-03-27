package org.jiangtao.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.ArrayList;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Article;

/**
 * Created by MrJiang on 2016/3/10.
 */
public class IndexViewHolder extends RecyclerView.ViewHolder {
  @Bind(R.id.collections_text_title) TextView mTextTitle;
  @Bind(R.id.collections_text_content) TextView mTextContent;
  @Bind(R.id.collections_text_author) TextView mTextAuthor;
  @Bind(R.id.collections_text_time) TextView mTextTime;

  public IndexViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
  }

  public void setText(ArrayList<Article> list, int position) {
    mTextTitle.setText(list.get(position).getTitle());
    mTextContent.setText(list.get(position).getContent());
    mTextAuthor.setText(list.get(position).getAuthor());
    mTextTime.setText(list.get(position).getTime());
  }
}
