package org.jiangtao.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Account;
import org.jiangtao.view.SettingItems;

/**
 * Created by YuGang Yang on March 01, 2016.
 * Copyright 2015-2016 qiji.tech. All rights reserved.
 */
public class AccountCellViewHolder extends CellViewHolder {

  @Bind(R.id.avatar) SimpleDraweeView avatarView;
  @Bind(R.id.text_account_username) TextView usernameTextView;
  private Context mContext;

  public AccountCellViewHolder(Context context, ViewGroup parent) {
    super(context,
        LayoutInflater.from(context).inflate(R.layout.list_item_account_header, parent, false));
    mContext = context;
    ButterKnife.bind(this, itemView);
  }

  @Override protected void bindTo(SettingItems settingItem) {
    super.bindTo(settingItem);
    Object data = settingItem.mData;
    if (data instanceof Account) {
      Account user = (Account) data;
      if (user.uri() != null && user.uri().toString().length() != 0) {
        avatarView.setImageURI(user.uri());
      }
      usernameTextView.setText(user.username != null ? user.username : "用户名");
    }
  }
}
