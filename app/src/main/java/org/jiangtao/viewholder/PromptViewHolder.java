package org.jiangtao.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.jiangtao.freedomblog.R;

/**
 * Created by MrJiang on 2016/3/1.
 */
public class PromptViewHolder extends RecyclerView.ViewHolder {
  public ImageView mImageView;
  public TextView mTextView;
  public ImageView mImageArrow;

  public PromptViewHolder(View itemView) {
    super(itemView);
    mImageView = (ImageView) itemView.findViewById(R.id.icon_image);
    mTextView = (TextView) itemView.findViewById(R.id.textview);
    mImageArrow = (ImageView) itemView.findViewById(R.id.icon_arrow);
  }
}
