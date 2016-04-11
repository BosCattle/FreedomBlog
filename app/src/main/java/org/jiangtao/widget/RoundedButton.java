package org.jiangtao.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.view.View;
import org.jiangtao.freedomblog.R;

import static android.support.v4.graphics.ColorUtils.compositeColors;
import static org.jiangtao.utils.ThemeUtils.getDisabledThemeAttrColor;
import static org.jiangtao.utils.ThemeUtils.getThemeAttrColor;
import static org.jiangtao.utils.ThemeUtils.getThemeAttrDimension;

/**
 * Set background via {@link android.R.attr#background android:background} will be discarded.
 * <p/>
 * Created by KorHsien on 2015/6/25.
 */
public class RoundedButton extends AppCompatButton {
  public RoundedButton(Context context) {
    this(context, null);
  }

  public RoundedButton(Context context, AttributeSet attrs) {
    this(context, attrs, R.attr.buttonStyle);
  }

  public RoundedButton(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      setBackgroundResource(R.drawable.btn_default);
    } else {
      setBackground(this, createButtonBackground(context));
      ViewCompat.setBackgroundTintList(this, createButtonColorStateList(context));
    }
  }

  public static void setBackground(View view, Drawable drawable) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
      view.setBackground(drawable);
    } else {
      view.setBackgroundDrawable(drawable);
    }
  }

  protected Drawable createButtonBackground(Context context) {
    final float buttonCornerRadius = getThemeAttrDimension(context, R.attr.buttonCornerRadius);
    float[] outerRadii = new float[8];
    for (int i = 0 ; i < outerRadii.length; i++) {
      outerRadii[i] = buttonCornerRadius;
    }
    return new ShapeDrawable(new RoundRectShape(outerRadii, null, null));
  }

  protected ColorStateList createButtonColorStateList(Context context) {
    final int[][] states = new int[4][];
    final int[] colors = new int[4];
    int i = 0;

    final int colorButtonDisabled = getThemeAttrColor(context, R.attr.colorRoundButtonDisabled);
    final int colorButtonNormal = getThemeAttrColor(context, R.attr.colorRoundButtonNormal);
    final int colorControlHighlight = getThemeAttrColor(context, R.attr.colorRoundButtonPressed);

    // Disabled state
    states[i] = new int[]{-android.R.attr.state_enabled};
    colors[i] = colorButtonDisabled != 0 ? colorButtonDisabled : getDisabledThemeAttrColor(context, R.attr.colorRoundButtonPressed);
    i++;

    states[i] = new int[]{android.R.attr.state_pressed};
    colors[i] = compositeColors(colorControlHighlight, colorButtonNormal);
    i++;

    states[i] = new int[]{android.R.attr.state_focused};
    colors[i] = compositeColors(colorControlHighlight, colorButtonNormal);
    i++;

    // Default enabled state
    states[i] = new int[]{};
    colors[i] = colorButtonNormal;

    return new ColorStateList(states, colors);
  }
}
