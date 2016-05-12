package org.jiangtao.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.jiangtao.freedomblog.R;
import org.jiangtao.holder.object.IconData;
import org.jiangtao.view.SettingItems;

/**
 * 设置个人资料ViewHolder
 * 周鑫
 */
public class AccountItemCellViewHolder extends CellViewHolder {

  @Bind(android.R.id.icon) ImageView mIconView;
  @Bind(android.R.id.text1) TextView mTextView;

  public AccountItemCellViewHolder(Context context, ViewGroup parent) {
    super(context,
        LayoutInflater.from(context).inflate(R.layout.list_item_setting_image, parent, false));
    ButterKnife.bind(this, itemView);
  }

  @Override protected void bindTo(SettingItems settingItem) {
    Object data = settingItem.mData;
    if (data != null && data instanceof IconData) {
      IconData iconData = (IconData) data;
      mIconView.setImageResource(iconData.iconRes);
      mTextView.setText(iconData.title);
    }
  }
}
