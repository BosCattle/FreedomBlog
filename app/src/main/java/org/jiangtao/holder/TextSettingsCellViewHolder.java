/**
 * Created by YuGang Yang on September 18, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package org.jiangtao.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils.data.IconData;
import org.jiangtao.view.SettingItems;

/**
 * 周鑫
 * 我的个人中心
 */
public class TextSettingsCellViewHolder extends CellViewHolder {
  @Bind(R.id.setting_list_text_item) TextView mTextView;

  public TextSettingsCellViewHolder(Context context, ViewGroup parent) {
    super(context, LayoutInflater.from(context)
        .inflate(R.layout.list_item_setting_text_settings, parent, false));
    ButterKnife.bind(this, itemView);
  }

  @Override protected void bindTo(SettingItems settingItem) {
    super.bindTo(settingItem);
    Object data = settingItem.mData;
    if (data != null && data instanceof IconData) {
      IconData iconData = (IconData) data;
      mTextView.setText(iconData.title);
    }
  }
}
