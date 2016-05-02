package org.jiangtao.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Account;
import org.jiangtao.model.Focus;
import org.jiangtao.utils.TurnActivity;

/**
 * Created by MrJiang on 5/2/2016.
 */
public class AttentionsViewHolder extends EasyViewHolder<Focus> {
  @Bind(R.id.focus_avatar) SimpleDraweeView mFocusAvatar;
  @Bind(R.id.focus_username) TextView mFocusUsername;
  private Focus mFocus;
  private Context mContext;

  public AttentionsViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.list_item_focus);
    ButterKnife.bind(this, itemView);
    mContext = context;
  }

  @Override public void bindTo(Focus value) {
    mFocus = value;
    mFocusAvatar.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Account account = mFocus.accountFocus;
        TurnActivity.startUserDetailActivity(mContext, account);
      }
    });
    if (value != null) {
      mFocusAvatar.setImageURI(value.accountFocus.uri());
      mFocusUsername.setText(value.accountFocus.username);
    }
  }
}
