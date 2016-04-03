package org.jiangtao.model;

import android.os.Parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by MrJiang on 4/3/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class QiNiuToken extends Entity {

  public String uploadToken;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.uploadToken);
  }

  public QiNiuToken() {
  }

  protected QiNiuToken(Parcel in) {
    this.uploadToken = in.readString();
  }

  public static final Creator<QiNiuToken> CREATOR = new Creator<QiNiuToken>() {
    @Override public QiNiuToken createFromParcel(Parcel source) {
      return new QiNiuToken(source);
    }

    @Override public QiNiuToken[] newArray(int size) {
      return new QiNiuToken[size];
    }
  };
}
