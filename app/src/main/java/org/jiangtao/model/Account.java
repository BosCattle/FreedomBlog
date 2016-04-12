package org.jiangtao.model;

import android.net.Uri;
import android.os.Parcel;
import android.text.TextUtils;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by MrJiang on 2016/3/19.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class Account
    extends Entity {
  public int id;
  public String phone;
  public String username;
  public String password;
  public String token;
  @JsonProperty("image_url") public String imageUrl;
  public String age;
  public String sex;
  @JsonProperty("start_time") public String starTime;

  @Override public String toString() {
    return "Account{" +
        "id=" + id +
        ", phone='" + phone + '\'' +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", token='" + token + '\'' +
        ", image_url='" + imageUrl + '\'' +
        ", age='" + age + '\'' +
        ", sex='" + sex + '\'' +
        ", start_time='" + starTime + '\'' +
        '}';
  }

  public Uri uri() {
    if (TextUtils.isEmpty(imageUrl)) return null;

    if (imageUrl.startsWith("http://")) {
      return Uri.parse(imageUrl);
    }

    return null;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(this.id);
    dest.writeString(this.phone);
    dest.writeString(this.username);
    dest.writeString(this.password);
    dest.writeString(this.token);
    dest.writeString(this.imageUrl);
    dest.writeString(this.age);
    dest.writeString(this.sex);
    dest.writeString(this.starTime);
  }

  public Account() {
  }

  protected Account(Parcel in) {
    this.id = in.readInt();
    this.phone = in.readString();
    this.username = in.readString();
    this.password = in.readString();
    this.token = in.readString();
    this.imageUrl = in.readString();
    this.age = in.readString();
    this.sex = in.readString();
    this.starTime = in.readString();
  }

  public static final Creator<Account> CREATOR = new Creator<Account>() {
    @Override public Account createFromParcel(Parcel source) {
      return new Account(source);
    }

    @Override public Account[] newArray(int size) {
      return new Account[size];
    }
  };
}
