package org.jiangtao.model;

import android.os.Parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by MrJiang on 4/24/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class RongYun extends com.smartydroid.android.starter.kit.model.entity.Entity {
  public String userId;
  public String userName;
  public String portraitUri;
  public String token;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.userId);
    dest.writeString(this.userName);
    dest.writeString(this.portraitUri);
    dest.writeString(this.token);
  }

  public RongYun() {
  }

  protected RongYun(Parcel in) {
    this.userId = in.readString();
    this.userName = in.readString();
    this.portraitUri = in.readString();
    this.token = in.readString();
  }

  public static final Creator<RongYun> CREATOR = new Creator<RongYun>() {
    @Override public RongYun createFromParcel(Parcel source) {
      return new RongYun(source);
    }

    @Override public RongYun[] newArray(int size) {
      return new RongYun[size];
    }
  };
}
