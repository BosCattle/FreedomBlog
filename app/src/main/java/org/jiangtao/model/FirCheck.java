package org.jiangtao.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kevin on 16-5-25.
 */
public class FirCheck extends com.smartydroid.android.starter.kit.model.entity.Entity {

  public String name;
  public String version;
  public String changelog;
  public String versionShort;
  public String build;
  public String installUrl;
  public String install_url;
  public String update_url;
  public Object binary;

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.name);
    dest.writeString(this.version);
    dest.writeString(this.changelog);
    dest.writeString(this.versionShort);
    dest.writeString(this.build);
    dest.writeString(this.installUrl);
    dest.writeString(this.install_url);
    dest.writeString(this.update_url);
    dest.writeParcelable((Parcelable) this.binary, flags);
  }

  public FirCheck() {
  }

  protected FirCheck(Parcel in) {
    this.name = in.readString();
    this.version = in.readString();
    this.changelog = in.readString();
    this.versionShort = in.readString();
    this.build = in.readString();
    this.installUrl = in.readString();
    this.install_url = in.readString();
    this.update_url = in.readString();
    this.binary = in.readParcelable(Object.class.getClassLoader());
  }

  public static final Creator<FirCheck> CREATOR = new Creator<FirCheck>() {
    @Override public FirCheck createFromParcel(Parcel source) {
      return new FirCheck(source);
    }

    @Override public FirCheck[] newArray(int size) {
      return new FirCheck[size];
    }
  };

  @Override public String toString() {
    return "FirCheck{" +
        "name='" + name + '\'' +
        ", version='" + version + '\'' +
        ", changelog='" + changelog + '\'' +
        ", versionShort='" + versionShort + '\'' +
        ", build='" + build + '\'' +
        ", installUrl='" + installUrl + '\'' +
        ", install_url='" + install_url + '\'' +
        ", update_url='" + update_url + '\'' +
        ", binary=" + binary +
        '}';
  }
}
