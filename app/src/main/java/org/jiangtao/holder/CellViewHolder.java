/**
 * Created by YuGang Yang on September 18, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package org.jiangtao.holder;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import org.jiangtao.view.SettingItems;

/**
 * common ViewHolder
 * 杨杰
 */
public class CellViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

  private Activity mActivity;
  private boolean enabled;
  private SettingItems mSettingItem;

  private EasyViewHolder.OnItemClickListener itemClickListener;

  public CellViewHolder(Context context, View itemView) {
    super(itemView);
    mActivity = (Activity) context;
    bindListeners();
  }

  private void bindListeners() {
    itemView.setOnClickListener(this);
  }

  public void bindView(SettingItems settingItem) {
    this.mSettingItem = settingItem;
    if (settingItem.enabled) {
      itemView.setOnClickListener(this);
    }
    bindTo(settingItem);
  }

  protected void bindTo(SettingItems settingItem) {
  }

  @Override public void onClick(View v) {
    if (itemClickListener == null) return;
    itemClickListener.onItemClick(getAdapterPosition(), v);
  }

  public SettingItems getSettingItem() {
    return mSettingItem;
  }

  public Activity getActivity() {
    return mActivity;
  }

  public void setItemClickListener(EasyViewHolder.OnItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }
}
