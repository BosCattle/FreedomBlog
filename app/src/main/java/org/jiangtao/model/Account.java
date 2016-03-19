package org.jiangtao.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartydroid.android.starter.kit.model.entity.Entitiy;

/**
 * Created by MrJiang on 2016/3/19.
 */
@SuppressLint("ParcelCreator") @JsonIgnoreProperties(ignoreUnknown = true) public class Account
    extends Entitiy {
  public int id;
  public String phone;
  public String username;
  public String password;
  public String token;
  @JsonProperty("image_url") public String image_url;
  public String age;
  public String sex;
  @JsonProperty("start_time") public String start_time;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {

    dest.writeInt(id);
    dest.writeString(phone);
    dest.writeString(username);
    dest.writeString(password);
    dest.writeString(token);
    dest.writeString(image_url);
    dest.writeString(age);
    dest.writeString(sex);
    dest.writeString(start_time);
  }

  public Account() {

  }

  public Account(Parcel in) {
    this.id = in.readInt();
    this.phone = in.readString();
    this.username = in.readString();
    this.password = in.readString();
    this.token = in.readString();
    this.image_url = in.readString();
    this.age = in.readString();
    this.sex = in.readString();
    this.start_time = in.readString();
  }

  @Override public String toString() {
    return "Account{" +
        "id=" + id +
        ", phone='" + phone + '\'' +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", token='" + token + '\'' +
        ", image_url='" + image_url + '\'' +
        ", age='" + age + '\'' +
        ", sex='" + sex + '\'' +
        ", start_time='" + start_time + '\'' +
        '}';
  }
}
