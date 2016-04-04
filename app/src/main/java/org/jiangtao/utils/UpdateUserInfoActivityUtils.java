package org.jiangtao.utils;

import android.content.Context;
import java.util.ArrayList;
import org.jiangtao.fragment.UserSettingFragment;
import org.jiangtao.model.Account;
import org.jiangtao.utils.data.UserInfo;
import org.jiangtao.view.SettingItems;

/**
 * Class:UpdateUserInfoActivityUtils <br>
 * Description:填充UpdateUserInfoActivity中Recyclerview数据 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/12 15:47 <br>
 */
public final class UpdateUserInfoActivityUtils {

  private static ArrayList<SettingItems> settingItems = new ArrayList<>();

  public UpdateUserInfoActivityUtils() {
  }

  public static ArrayList<SettingItems> buildUserViews(Context context) {
    Account user = AccountManager.getInstance().getAccount(context);
    settingItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());
    settingItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_AVATAR)
        .data(new UserInfo("头像", user.uri()))
        .type(UserSettingFragment.AVATAR)
        .enabled(true)
        .build());
    settingItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());
    settingItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_TEXT_DETAIL)
        .data(new UserInfo("昵称",
            (user.username != null && user.username.length() != 0) ? user.username : "请输入"))
        .enabled(true)
        .type(UserSettingFragment.USERNAME)
        .build());
    settingItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_TEXT_DETAIL)
        .data(new UserInfo("年龄", (user.age == null) || (user.age.isEmpty()) ? "请输入" : user.age))
        .enabled(false)
        .type(UserSettingFragment.PHONE_NUMBER)
        .build());
    settingItems.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_TEXT_DETAIL)
        .data(new UserInfo("性别", (user.sex != null) && (!user.sex.isEmpty()) ? user.sex : "请选择"))
        .enabled(true)
        .type(UserSettingFragment.SEX)
        .build());
    return settingItems;
  }
}
