/**
 * Created by YuGang Yang on September 17, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package org.jiangtao.view.cell;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import com.smartydroid.android.starter.kit.utilities.ScreenUtils;

/**
 * description:empty view
 */
public class ShadowSectionCell extends View {

  private int mHeight = 0;

  public ShadowSectionCell(Context context) {
    super(context);
    mHeight = ScreenUtils.dp2px(10);
  }

  public ShadowSectionCell(Context context, int height) {
    super(context);
    mHeight = height;
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY));
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }
}
