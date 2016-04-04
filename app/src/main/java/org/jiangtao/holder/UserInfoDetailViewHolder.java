package org.jiangtao.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils.data.UserInfo;
import org.jiangtao.view.SettingItems;

/**
 * Created by MrJiang on 2016/3/12.
 * 用户信息设置
 */
public class UserInfoDetailViewHolder extends CellViewHolder {

  @Bind(R.id.user_option_body_text) TextView mTextViewOptions;
  @Bind(R.id.user_option_body_update) TextView mTextViewDetail;

  public UserInfoDetailViewHolder(Context context, ViewGroup parent) {
    super(context,
        LayoutInflater.from(context).inflate(R.layout.list_item_info_body, parent, false));
    ButterKnife.bind(this, itemView);
  }

  @Override protected void bindTo(SettingItems settingItem) {
    super.bindTo(settingItem);
    Object data = settingItem.mData;
    if (data != null && data instanceof UserInfo) {
      UserInfo userInfo = (UserInfo) data;
      mTextViewOptions.setText(userInfo.option);
      if (userInfo.detail != null) {
        mTextViewDetail.setText(userInfo.detail.toString());
      }
    }
  }
}
