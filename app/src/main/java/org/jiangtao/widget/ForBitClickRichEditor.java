package org.jiangtao.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import jp.wasabeef.richeditor.RichEditor;

/**
 * Created by MrJiang on 5/2/2016.
 * mrJiang
 */
public class ForBitClickRichEditor extends RichEditor {
  public ForBitClickRichEditor(Context context) {
    super(context);
  }

  public ForBitClickRichEditor(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ForBitClickRichEditor(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    return false;
  }
}
