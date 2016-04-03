package org.jiangtao.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * Created by MrJiang on 4/3/2016.
 * 获取图片路径
 */
public final class ImageUtils {

  /**
   * 获取图片路径
   */
  public static String getImagePath(Intent data, Context context) {
    Uri selectedImage = data.getData();
    String[] filePathColumn = { MediaStore.Images.Media.DATA };
    Cursor cursor =
        context.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
    assert cursor != null;
    cursor.moveToFirst();

    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
    return cursor.getString(columnIndex);
  }
}
