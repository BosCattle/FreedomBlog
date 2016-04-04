package org.jiangtao.view;

/**
 * Created by MrJiang on 2016/3/27.
 * 用户创建我的界面和设置界面的items
 */
public class SettingItems {

  public static final int VIEW_TYPE_EMPTY = 0;
  public static final int VIEW_TYPE_HEADER = 1;
  public static final int VIEW_TYPE_ITEM = 3;
  public static final int VIEW_TYPE_ACCOUNT_HEADER = 4;
  public static final int VIEW_TYPE_ATTRIBUTE = 5;
  public static final int VIEW_TYPE_AVATAR = 6;
  public static final int VIEW_TYPE_TEXT_DETAIL = 7;
  public static final int VIEW_TYPE_TEXT = 8;
  public static final int VIEW_TYPE_CHECK_BUTTON = 9;

  public boolean enabled;
  public int itemViewType;
  public int mItemIcon;
  public int mItemImage;
  public String mText;
  public int mType;
  public Object mData;

  private SettingItems() {
  }

  public static class Builder {

    private boolean enabled;
    private int mItemIcon;
    private int mItemImage;
    private int itemViewType;
    private String mText;
    private int mType;
    private Object data;

    public SettingItems build() {
      SettingItems settingItem = new SettingItems();
      settingItem.enabled = enabled;
      settingItem.itemViewType = itemViewType;
      settingItem.mText = mText;
      settingItem.mType = mType;
      settingItem.mItemIcon = mItemIcon;
      settingItem.mItemImage = mItemImage;
      settingItem.mData = data;
      return settingItem;
    }

    public Builder enabled(boolean enabled) {
      this.enabled = enabled;
      return this;
    }

    public Builder type(int type) {
      this.mType = type;
      return this;
    }

    public Builder itemViewType(int itemViewType) {
      this.itemViewType = itemViewType;
      return this;
    }

    public Builder text(String text) {
      this.mText = text;
      return this;
    }

    public void itemIcon(int mItemIcon) {
      this.mItemIcon = mItemIcon;
    }

    public void itemImage(int mItemImage) {
      this.mItemImage = mItemImage;
    }

    public Builder data(Object data) {
      this.data = data;
      return this;
    }
  }

  public SettingItems(int itemViewType, int mItemIcon, String mText, int mItemImage,
      boolean enabled) {
    this.itemViewType = itemViewType;
    this.mText = mText;
    this.enabled = enabled;
    this.mItemImage = mItemImage;
    this.mItemIcon = mItemIcon;
  }

  public SettingItems(int itemViewType, boolean enabled) {
    this.enabled = enabled;
    this.itemViewType = itemViewType;
  }

  public SettingItems(int itemViewType, boolean enabled, boolean divider) {
    this.enabled = enabled;
    this.itemViewType = itemViewType;
  }

  public SettingItems(int itemViewType, String text, boolean enabled) {
    this.itemViewType = itemViewType;
    this.mText = text;
    this.enabled = enabled;
  }

  public SettingItems(int itemViewType, String text, boolean enabled, int type) {
    this.itemViewType = itemViewType;
    this.mText = text;
    this.enabled = enabled;
    mType = type;
  }
}
