package org.jiangtao.utils;

import android.app.Activity;
import com.afollestad.materialdialogs.MaterialDialog;
import org.jiangtao.freedomblog.R;

/**
 * Created by YuGang Yang on 03 15, 2016.
 * Copyright 20015-2016 honc.tech. All rights reserved.
 */
public class DialogUtil {

  private DialogUtil() {
  }

  public static void showChoosePhotoDialog(Activity activity,
      MaterialDialog.ListCallback callback) {
    MaterialDialog.Builder builder =
        new MaterialDialog.Builder(activity).items(R.array.takes_dialog_items);
    if (callback != null) {
      builder.itemsCallback(callback);
    }
    builder.show();
  }

  public static void sexChooseDialog(Activity activity, MaterialDialog.ListCallback callback) {
    MaterialDialog.Builder builder =
        new MaterialDialog.Builder(activity).items(R.array.sex_dialog_items);
    if (callback != null) {
      builder.itemsCallback(callback);
    }
    builder.show();
  }
}
