package org.jiangtao.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Focus;

/**
 * Created by MrJiang on 5/2/2016.
 */
public class AttentionsViewHolder extends EasyViewHolder<Focus> {
  @Bind(R.id.focus_avatar) SimpleDraweeView mFocusAvatar;
  @Bind(R.id.focus_username) TextView mFocusUsername;

  public AttentionsViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.list_item_focus);
    ButterKnife.bind(this, itemView);
  }

  @Override public void bindTo(Focus value) {
    if (value!=null) {
      mFocusAvatar.setImageURI(value.accountFocus.uri());
      mFocusUsername.setText(value.accountFocus.username);
    }
  }
}
