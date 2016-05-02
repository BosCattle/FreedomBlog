package org.jiangtao.model;

import android.os.Parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by MrJiang on 5/2/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class IsFocus extends com.smartydroid.android.starter.kit.model.entity.Entity {

  public boolean is_focus;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeByte(is_focus ? (byte) 1 : (byte) 0);
  }

  public IsFocus() {
  }

  protected IsFocus(Parcel in) {
    this.is_focus = in.readByte() != 0;
  }

  public static final Creator<IsFocus> CREATOR = new Creator<IsFocus>() {
    @Override public IsFocus createFromParcel(Parcel source) {
      return new IsFocus(source);
    }

    @Override public IsFocus[] newArray(int size) {
      return new IsFocus[size];
    }
  };
}
