package org.jiangtao.utils.build;

import android.content.Context;
import java.util.ArrayList;
import org.jiangtao.fragment.PersonFragment;
import org.jiangtao.freedomblog.R;
import org.jiangtao.holder.object.IconData;
import org.jiangtao.model.Account;
import org.jiangtao.utils.AccountManager;
import org.jiangtao.view.SettingItems;

/**
 * Created by MrJiang on 2016/3/27.
 * account下构建item
 */
public final class AccountBuildUtils {

  public static ArrayList<SettingItems> buildParentViews(Context context) {

    ArrayList<SettingItems> SettingItemss = new ArrayList<>();
    Account account = AccountManager.getInstance().getAccount(context);
    SettingItemss.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());

    SettingItemss.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_ACCOUNT_HEADER)
        .data(account)
        .type(PersonFragment.ITEM_VIEW_HEADER)
        .enabled(true)
        .build());

    SettingItemss.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());

    SettingItemss.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_ITEM)
        .data(new IconData(R.drawable.ic_account_collection, "我的收藏"))
        .type(PersonFragment.ITEM_VIEW_COLLECTION)
        .enabled(true)
        .build());

    SettingItemss.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());

    SettingItemss.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_ITEM)
        .data(new IconData(R.drawable.ic_account_feed, "我的发布"))
        .enabled(true)
        .type(PersonFragment.ITEM_VIEW_PUBLISH)
        .build());

    SettingItemss.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_ITEM)
        .data(new IconData(R.drawable.ic_account_document, "我的评论"))
        .enabled(true)
        .type(PersonFragment.ITEM_VIEW_COMMENT)
        .build());

    SettingItemss.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_EMPTY)
        .enabled(false)
        .build());

    SettingItemss.add(new SettingItems.Builder().itemViewType(SettingItems.VIEW_TYPE_ITEM)
        .data(new IconData(R.drawable.ic_account_setting, "设置"))
        .type(PersonFragment.ITEM_VIEW_SETTING)
        .enabled(true)
        .build());
    return SettingItemss;
  }
}
