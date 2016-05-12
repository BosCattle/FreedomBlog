package org.jiangtao.utils.data;

import android.net.Uri;

public class IconData {

  public int iconRes;
  public Uri uri;
  public String title;
  public String subtitle;

  public IconData(String title) {
    this.title = title;
  }

  public IconData(int iconRes, String title) {
    this.iconRes = iconRes;
    this.title = title;
  }

  public IconData(int iconRes, String title, String subtitle) {
    this.iconRes = iconRes;
    this.title = title;
    this.subtitle = subtitle;
  }

  public IconData(Uri uri, String title, String subtitle) {
    this.uri = uri;
    this.title = title;
    this.subtitle = subtitle;
  }
}
