package org.jiangtao.model;

import android.os.Parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by MrJiang on 5/7/2016.
 * 收藏
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Collections
    extends com.smartydroid.android.starter.kit.model.entity.Entity {

  public int id;
  public Account account;
  public int article_id;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeParcelable(this.account, flags);
    dest.writeInt(this.article_id);
  }

  public Collections() {
  }

  protected Collections(Parcel in) {
    this.id = in.readInt();
    this.account = in.readParcelable(Account.class.getClassLoader());
    this.article_id = in.readInt();
  }

  public static final Creator<Collections> CREATOR = new Creator<Collections>() {
    @Override public Collections createFromParcel(Parcel source) {
      return new Collections(source);
    }

    @Override public Collections[] newArray(int size) {
      return new Collections[size];
    }
  };
}
