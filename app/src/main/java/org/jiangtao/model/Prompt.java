package org.jiangtao.model;

/**
 * Created by MrJiang on 2016/3/1.
 */
public class Prompt {
  private int iconImage;
  private String text;
  private int iconArrow;

  public int getIconImage() {
    return iconImage;
  }

  public void setIconImage(int iconImage) {
    this.iconImage = iconImage;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public int getIconArrow() {
    return iconArrow;
  }

  public void setIconArrow(int iconArrow) {
    this.iconArrow = iconArrow;
  }

  public Prompt(int iconImage, String text, int iconArrow) {
    this.iconImage = iconImage;
    this.text = text;
    this.iconArrow = iconArrow;
  }
}
