package org.jiangtao.utils;

import android.app.Activity;
import com.afollestad.materialdialogs.MaterialDialog;
import org.jiangtao.freedomblog.R;

/**
 * 现实对话框
 * 周鑫
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
