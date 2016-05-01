package org.jiangtao.model;

import android.os.Parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by MrJiang on 5/2/2016.
 * 关注
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Focus extends
    com.smartydroid.android.starter.kit.model.entity.Entity {

  public int id;
  @JsonProperty("account_id") public int accountId;
  @JsonProperty("account_focus") public Account accountFocus;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeInt(this.accountId);
    dest.writeParcelable(this.accountFocus, flags);
  }

  public Focus() {
  }

  protected Focus(Parcel in) {
    this.id = in.readInt();
    this.accountId = in.readInt();
    this.accountFocus = in.readParcelable(Account.class.getClassLoader());
  }

  public static final Creator<Focus> CREATOR = new Creator<Focus>() {
    @Override public Focus createFromParcel(Parcel source) {
      return new Focus(source);
    }

    @Override public Focus[] newArray(int size) {
      return new Focus[size];
    }
  };
}
