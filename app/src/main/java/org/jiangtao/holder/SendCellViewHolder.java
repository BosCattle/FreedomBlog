package org.jiangtao.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import butterknife.ButterKnife;
import org.jiangtao.freedomblog.R;
import org.jiangtao.holder.object.IconData;
import org.jiangtao.view.SettingItems;
import butterknife.Bind;

/**
 * Created by MrJiang on 2016/3/28.
 * 发送fragment 选择不同的属性
 */
public class SendCellViewHolder extends CellViewHolder {

  @Bind(R.id.item_image) ImageView mImageView;
  private Context mContext;

  public SendCellViewHolder(Context context, ViewGroup parent) {
    super(context, LayoutInflater.from(context).inflate(R.layout.list_item_publish, parent, false));
    mContext = context;
    ButterKnife.bind(this,itemView);
  }

  @Override protected void bindTo(SettingItems settingItem) {
    super.bindTo(settingItem);
    IconData iconData = (IconData) settingItem.mData;
    mImageView.setImageResource(iconData.iconRes);
  }
}
