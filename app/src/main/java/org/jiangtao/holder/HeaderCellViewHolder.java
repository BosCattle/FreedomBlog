/**
 * Created by YuGang Yang on September 18, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package org.jiangtao.holder;

import android.content.Context;
import org.jiangtao.view.SettingItems;
import org.jiangtao.view.cell.HeaderCell;

/**
 * 头部最大分割
 * 周鑫
 * 我的个人中心
 */
public class HeaderCellViewHolder extends CellViewHolder {

  public HeaderCellViewHolder(Context context) {
    super(context, new HeaderCell(context));
  }

  @Override protected void bindTo(SettingItems settingItem) {
    super.bindTo(settingItem);
    HeaderCell headerCell = (HeaderCell) itemView;
    headerCell.setText(settingItem.mText);
  }
}
