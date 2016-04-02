package org.jiangtao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.carlosdelachica.easyrecycleradapters.adapter.debouncedlisteners.DebouncedOnClickListener;
import java.util.ArrayList;
import org.jiangtao.holder.AccountCellViewHolder;
import org.jiangtao.holder.AccountItemCellViewHolder;
import org.jiangtao.holder.CellViewHolder;
import org.jiangtao.holder.EmptyCellViewHolder;
import org.jiangtao.holder.HeaderCellViewHolder;
import org.jiangtao.holder.SendCellViewHolder;
import org.jiangtao.view.SettingItems;

/**
 * Class:AccountAdapter br>
 * Description:適配器 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/27 15:20 <br>
 */
public class AccountAdapter extends RecyclerView.Adapter<CellViewHolder> {

  private final ArrayList<SettingItems> mSettingItemss;
  private final Context mContext;

  private EasyViewHolder.OnItemClickListener itemClickListener;
  private int mPosition;

  public AccountAdapter(Context context, ArrayList<SettingItems> SettingItemss) {
    this.mContext = context;
    this.mSettingItemss = SettingItemss;
  }

  @Override public int getItemViewType(int position) {
    final SettingItems SettingItems = mSettingItemss.get(position);
    return SettingItems.itemViewType;
  }

  @Override public CellViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final Context context = mContext;
    CellViewHolder cellViewHolder = null;
    switch (viewType) {
      case SettingItems.VIEW_TYPE_EMPTY:
        cellViewHolder = new EmptyCellViewHolder(context);
        break;
      case SettingItems.VIEW_TYPE_HEADER:
        cellViewHolder = new HeaderCellViewHolder(context);
        break;
      case SettingItems.VIEW_TYPE_ACCOUNT_HEADER:
        cellViewHolder = new AccountCellViewHolder(context, parent);
        break;
      case SettingItems.VIEW_TYPE_ITEM:
        cellViewHolder = new AccountItemCellViewHolder(context, parent);
        break;

      case SettingItems.VIEW_TYPE_ATTRIBUTE:
        cellViewHolder = new SendCellViewHolder(context, parent);
        break;
    }
    bindListeners(cellViewHolder);
    return cellViewHolder;
  }

  public void setOnClickListener(final EasyViewHolder.OnItemClickListener listener) {
    this.itemClickListener = new DebouncedOnClickListener() {
      @Override public boolean onDebouncedClick(View v, int position) {
        if (listener != null) {
          listener.onItemClick(position, v);
        }
        return true;
      }
    };
  }

  private void bindListeners(CellViewHolder cellViewHolder) {
    if (cellViewHolder != null) {
      cellViewHolder.setItemClickListener(itemClickListener);
    }
  }

  @Override public void onBindViewHolder(CellViewHolder holder, int position) {
    final SettingItems SettingItems = mSettingItemss.get(position);
    holder.bindView(SettingItems);
  }

  @Override public int getItemCount() {
    return mSettingItemss == null ? 0 : mSettingItemss.size();
  }
}
