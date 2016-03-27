/**
 * Created by YuGang Yang on September 17, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package org.jiangtao.view.cell;

import android.content.Context;
import android.widget.FrameLayout;

public class EmptyCell extends FrameLayout {

  int cellHeight;

  public EmptyCell(Context context) {
    this(context, 14);
  }

  public EmptyCell(Context context, int height) {
    super(context);
    cellHeight = height;
  }

  public void setHeight(int height) {
    cellHeight = height;
    requestLayout();
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(cellHeight, MeasureSpec.EXACTLY));
  }
}

