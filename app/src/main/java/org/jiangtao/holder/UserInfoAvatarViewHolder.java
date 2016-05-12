package org.jiangtao.holder;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils.data.UserInfo;
import org.jiangtao.view.SettingItems;

/**
 * Class:UserInfoAvatarViewHolder <br>
 * Description:用户信息界面头像item viewholder <br>
 * Creator: 周鑫 <br>
 * Date: 2016/3/15 22:41 <br>
 */
public class UserInfoAvatarViewHolder extends CellViewHolder {

  @Bind(R.id.user_option_avatar) SimpleDraweeView mSimpleDraweeView;
  @Bind(R.id.user_option_text) TextView mTextView;

  public UserInfoAvatarViewHolder(Context context, ViewGroup parent) {
    super(context,
        LayoutInflater.from(context).inflate(R.layout.list_item_user_info_header, parent, false));
    ButterKnife.bind(this, itemView);
  }

  @Override protected void bindTo(SettingItems settingItem) {
    super.bindTo(settingItem);
    Object data = settingItem.mData;
    if (data != null && data instanceof UserInfo) {
      UserInfo userInfo = (UserInfo) data;
      mTextView.setText(userInfo.option);
      if (userInfo.detail != null && userInfo.detail.toString().length() != 0) {
        Uri uri = Uri.parse(userInfo.detail.toString());
        mSimpleDraweeView.setImageURI(uri);
      }
    }
  }
}
