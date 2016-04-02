package org.jiangtao.model;

import android.os.Parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by MrJiang on 4/2/2016.
 * 文章详情
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Articles extends Entity {

  public int id;
  @JsonProperty("account_id") private int accountId;
  @JsonProperty("content") private int content;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeInt(this.accountId);
    dest.writeInt(this.content);
  }

  public Articles() {
  }

  protected Articles(Parcel in) {
    this.id = in.readInt();
    this.accountId = in.readInt();
    this.content = in.readInt();
  }

  public static final Creator<Articles> CREATOR = new Creator<Articles>() {
    @Override public Articles createFromParcel(Parcel source) {
      return new Articles(source);
    }

    @Override public Articles[] newArray(int size) {
      return new Articles[size];
    }
  };
}
