/**
 * Created by YuGang Yang on September 17, 2015.
 * Copyright 2007-2015 Laputapp.com. All rights reserved.
 */
package org.jiangtao.view.cell;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.smartydroid.android.starter.kit.app.StarterKitApp;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils.AndroidUtilities;
import org.jiangtao.utils.LayoutHelper;
import org.jiangtao.utils.LocaleController;

public class HeaderCell extends FrameLayout {

  private TextView textView;

  public HeaderCell(Context context) {
    super(context);

    textView = new TextView(getContext());
    textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
    textView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
    textView.setTextColor(ContextCompat.getColor(StarterKitApp.appContext(), R.color.colorPrimary));
    textView.setGravity(
        (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL);
    addView(textView, LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT, LayoutHelper.MATCH_PARENT,
        (LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.TOP, 17, 15, 17, 0));
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec,
        MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(38), MeasureSpec.EXACTLY));
  }

  public void setText(String text) {
    textView.setText(text);
  }
}
