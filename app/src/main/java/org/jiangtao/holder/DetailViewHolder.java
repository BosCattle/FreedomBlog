package org.jiangtao.holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Comment;

/**
 * Created by MrJiang on 5/1/2016.
 */
public class DetailViewHolder extends EasyViewHolder<Comment> {

  @Bind(R.id.who_to_who) TextView mWhoToWho;
  @Bind(R.id.content) TextView mContent;

  public DetailViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.list_item_comment);
    ButterKnife.bind(this, itemView);
  }

  @SuppressLint("SetTextI18n") @Override public void bindTo(Comment value) {
    if (value.parent_id == 0) {
      mWhoToWho.setText(
          value.account != null && value.account.username != null ? value.account.username + "说"
              : "");
      mContent.setText(value.content != null ? value.content : "");
    }
  }
}
