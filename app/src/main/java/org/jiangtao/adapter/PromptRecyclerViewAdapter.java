package org.jiangtao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Prompt;
import org.jiangtao.viewholder.PromptViewHolder;

/**
 * Created by MrJiang on 2016/3/1.
 */
public class PromptRecyclerViewAdapter extends RecyclerView.Adapter<PromptViewHolder> {
  private Context mContext;
  private ArrayList<Prompt> mPromptLists;
  private LayoutInflater mInflater;
  private View mView;

  public PromptRecyclerViewAdapter(Context context, ArrayList<Prompt> lists) {
    this.mContext = context;
    this.mPromptLists = lists;
    mInflater = LayoutInflater.from(context);
  }

  @Override public PromptViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new PromptViewHolder(
        mInflater.inflate(R.layout.recyclerview_item_prompt,parent,false));
  }

  @Override public void onBindViewHolder(PromptViewHolder holder, int position) {
    holder.mImageView.setImageResource(mPromptLists.get(position).getIconImage());
    holder.mTextView.setText(mPromptLists.get(position).getText());
    holder.mImageArrow.setImageResource(mPromptLists.get(position).getIconArrow());
  }

  @Override public int getItemCount() {
    return mPromptLists.size();
  }

  @Override public long getItemId(int position) {
    return super.getItemId(position);
  }
}
