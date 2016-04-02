package org.jiangtao.utils.build;

import java.util.ArrayList;
import org.jiangtao.freedomblog.R;
import org.jiangtao.holder.object.IconData;
import org.jiangtao.view.SettingItems;

/**
 * Created by MrJiang on 2016/3/27.
 * 发布界面的选项
 */
public class SendFragmentUtils {

  private static SendFragmentUtils instance;

  private SendFragmentUtils() {
  }

  public static SendFragmentUtils getInstance() {
    if (instance == null) {
      synchronized (SendFragmentUtils.class) {
        instance = new SendFragmentUtils();
      }
    }
    return instance;
  }

  public ArrayList<Integer> mIconLists;
  public ArrayList<SettingItems> mSettingItems;

  public void buildIconLists() {
    mIconLists = new ArrayList<>();
    mSettingItems = new ArrayList<>();
    mIconLists.add(R.drawable.ic_send_undo);
    mIconLists.add(R.drawable.ic_send_redo);
    mIconLists.add(R.drawable.ic_send_bold);
    mIconLists.add(R.drawable.ic_send_italic);
    mIconLists.add(R.drawable.ic_send_subscript);
    mIconLists.add(R.drawable.ic_send_blockquote);
    mIconLists.add(R.drawable.ic_send_insert_image);
    mIconLists.add(R.drawable.ic_send_insert_link);
    mIconLists.add(R.drawable.ic_send_superscript);
    mIconLists.add(R.drawable.ic_send_strikethrough);
    mIconLists.add(R.drawable.ic_send_underline);
    mIconLists.add(R.drawable.ic_send_h1);
    mIconLists.add(R.drawable.ic_send_h2);
    mIconLists.add(R.drawable.ic_send_h3);
    mIconLists.add(R.drawable.ic_send_h4);
    mIconLists.add(R.drawable.ic_send_h5);
    mIconLists.add(R.drawable.ic_send_h6);
    mIconLists.add(R.drawable.ic_send_indent);
    mIconLists.add(R.drawable.ic_send_outdent);
    mIconLists.add(R.drawable.ic_send_justify_left);
    mIconLists.add(R.drawable.ic_send_justify_center);
    mIconLists.add(R.drawable.ic_send_justify_right);
  }

  /**
   * 填充settingItems
   */
  public ArrayList<SettingItems> buildArticleAttribute() {
    buildIconLists();
    for (int i = 0; i < mIconLists.size(); i++) {
      mSettingItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_ATTRIBUTE)
          .data(new IconData(mIconLists.get(i)))
          .enabled(true)
          .build());
    }
    return mSettingItems;
  }
}
