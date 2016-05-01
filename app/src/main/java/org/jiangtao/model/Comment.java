package org.jiangtao.model;

import android.os.Parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by MrJiang on 5/1/2016.
 * 评论
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Comment
    extends com.smartydroid.android.starter.kit.model.entity.Entity {

  public int id;
  public int parent_id;
  public String content;
  public long create_at;
  public Account account;
  public Account parent_account;
  public int article_id;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeInt(this.parent_id);
    dest.writeString(this.content);
    dest.writeLong(this.create_at);
    dest.writeParcelable(this.account, flags);
    dest.writeParcelable(this.parent_account, flags);
    dest.writeInt(this.article_id);
  }

  public Comment() {
  }

  protected Comment(Parcel in) {
    this.id = in.readInt();
    this.parent_id = in.readInt();
    this.content = in.readString();
    this.create_at = in.readLong();
    this.account = in.readParcelable(Account.class.getClassLoader());
    this.parent_account = in.readParcelable(Account.class.getClassLoader());
    this.article_id = in.readInt();
  }

  public static final Creator<Comment> CREATOR = new Creator<Comment>() {
    @Override public Comment createFromParcel(Parcel source) {
      return new Comment(source);
    }

    @Override public Comment[] newArray(int size) {
      return new Comment[size];
    }
  };
}
