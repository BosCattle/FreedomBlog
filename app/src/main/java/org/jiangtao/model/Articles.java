package org.jiangtao.model;

import android.net.Uri;
import android.os.Parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by MrJiang on 4/2/2016.
 * 文章详情
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Articles
    extends com.smartydroid.android.starter.kit.model.entity.Entity {

  public int id;
  @JsonProperty("account_id") public int accountId;
  public Account accounts;
  @JsonProperty("content") public String content;
  @JsonProperty("title") public String title;
  @JsonProperty("image_url") public String imageUrl;
  @JsonProperty("create_at") public long createAt;

  public Uri getUri() {
    return Uri.parse(imageUrl);
  }

  public Articles() {
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeInt(this.accountId);
    dest.writeParcelable(this.accounts, flags);
    dest.writeString(this.content);
    dest.writeString(this.title);
    dest.writeString(this.imageUrl);
    dest.writeLong(this.createAt);
  }

  protected Articles(Parcel in) {
    this.id = in.readInt();
    this.accountId = in.readInt();
    this.accounts = in.readParcelable(Account.class.getClassLoader());
    this.content = in.readString();
    this.title = in.readString();
    this.imageUrl = in.readString();
    this.createAt = in.readLong();
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
