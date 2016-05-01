package org.jiangtao.holder;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Articles;
import org.jiangtao.utils.DateFormat;

/**
 * Created by MrJiang on 4/4/2016.
 * 主页中的viewholder
 */
public class HomeViewHolder extends EasyViewHolder<Articles> {

  @Bind(R.id.home_avatar) SimpleDraweeView mHomeAvatar;
  @Bind(R.id.username) TextView mUsername;
  @Bind(R.id.header) RelativeLayout header;
  @Bind(R.id.title) TextView mTitle;
  @Bind(R.id.home_image) SimpleDraweeView mHomeImage;
  @Bind(R.id.content) TextView mContent;
  @Bind(R.id.time) TextView mTime;
  @Bind(R.id.read_num) TextView mReadNum;

  public HomeViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.list_item_home);
    ButterKnife.bind(this, itemView);
  }

  // TODO: 4/9/2016 获取文章的参数有问题
  @Override public void bindTo(Articles value) {
    try {
      mTitle.setText(value.title);
      mHomeImage.setImageURI(value.getUri());
      mHomeAvatar.setImageURI(value.accounts.uri());
      mUsername.setText(value.accounts.username);
      mContent.setText(value.content);
      mTime.setText(
          Long.valueOf(value.createAt) != null ? DateFormat.getRelativeTime(value.createAt) : "");
      mReadNum.setText(
          Long.valueOf(value.createAt) != null ? DateFormat.getCommonTime(value.createAt) : "");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
