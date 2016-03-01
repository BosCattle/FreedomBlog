package org.jiangtao.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by MrJiang on 2016/2/26.
 * 自定义Account页面样式和分隔条
 */
public class RecyclerViewDividerItemDecoration extends RecyclerView.ItemDecoration {

  private static final int[] ATTRS = new int[] {
      android.R.attr.listDivider
  };
  public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
  public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
  private Drawable mDivider;
  private int mUserIdentity;

  public RecyclerViewDividerItemDecoration(Context context, int mUserIdentity) {
    this.mUserIdentity = mUserIdentity;
    final TypedArray a = context.obtainStyledAttributes(ATTRS);
    mDivider = a.getDrawable(0);
    a.recycle();
  }

  public void setOrientation(int orientation) {
    if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
      throw new IllegalArgumentException("invalid orientation");
    }
  }

  public void drawVertical(Canvas c, RecyclerView parent) {
    final int left = parent.getPaddingLeft();
    final int right = parent.getWidth() - parent.getPaddingRight();
    final int childCount = parent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = parent.getChildAt(i);
      RecyclerView v = new RecyclerView(parent.getContext());
      final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
      final int top = child.getBottom() + params.bottomMargin;
      final int bottom = top + mDivider.getIntrinsicHeight();
      mDivider.setBounds(left, top, right, bottom);
      mDivider.draw(c);
    }
  }

  @Override public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {
    super.getItemOffsets(outRect, view, parent, state);
    outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
  }

  @Override public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    super.onDraw(c, parent, state);
    drawVertical(c, parent);
  }
}
