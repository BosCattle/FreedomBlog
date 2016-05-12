/**
 * Created by YuGang Yang on September 18, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package org.jiangtao.holder;

import android.content.Context;
import org.jiangtao.view.cell.EmptyCell;

/**
 * Empty ViewHolder
 * 周鑫
 * 我的个人中心分割行
 */
public class EmptyCellViewHolder extends CellViewHolder {

  public EmptyCellViewHolder(Context context) {
    super(context, new EmptyCell(context));
  }

  public EmptyCellViewHolder(Context context,int height) {
    super(context, new EmptyCell(context,height));
  }
}
