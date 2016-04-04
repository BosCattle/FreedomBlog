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

  public HomeViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.list_item_home);
    ButterKnife.bind(this,itemView);
  }

  @Override public void bindTo(Articles value) {
    try {
      mTitle.setText(value.title);
      mHomeImage.setImageURI(value.getUri());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
