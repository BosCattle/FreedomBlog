package org.jiangtao.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.RequestCallbackWrapper;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.model.RecentContact;
import com.smartydroid.android.starter.kit.app.StarterFragment;
import java.util.List;
import org.jiangtao.freedomblog.R;

/**
 * Class:PromptFragment <br>
 * Description:记录页面 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/1 20:46 <br>
 */
public class PromptFragment extends StarterFragment {

  @Bind(R.id.prompt_recyclerview) RecyclerView mPromptRecyclerview;

  @Override protected int getFragmentLayout() {
    return R.layout.fragment_prompt;
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = super.onCreateView(inflater, container, savedInstanceState);
    ButterKnife.bind(this, rootView);
    getRecentContact();
    return rootView;
  }

  /**
   * 获取仅仅聊天记录
   */
  private void getRecentContact() {
    NIMClient.getService(MsgService.class)
        .queryRecentContacts()
        .setCallback(new RequestCallbackWrapper<List<RecentContact>>() {
          @Override public void onResult(int code, List<RecentContact> recents, Throwable e) {
            // recents参数即为最近联系人列表（最近会话列表）

          }
        });
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
