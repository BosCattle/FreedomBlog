package org.jiangtao.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.jiangtao.freedomblog.R;
import org.jiangtao.view.SettingItems;

public class CheckViewHolder extends CellViewHolder {

  @Bind(android.R.id.text1) TextView mTextView;

  public CheckViewHolder(Context context, ViewGroup parent) {
    super(context,
        LayoutInflater.from(context).inflate(R.layout.list_item_check_button, parent, false));
    ButterKnife.bind(this, itemView);
  }

  @Override protected void bindTo(SettingItems settingItem) {
    super.bindTo(settingItem);
    Object data = settingItem.mText;
    mTextView.setText(data.toString());
  }
}