package org.jiangtao.freedomblog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import io.rong.imkit.RongIM;
import java.util.ArrayList;
import org.jiangtao.model.Account;
import org.jiangtao.model.Focus;
import org.jiangtao.model.IsFocus;
import org.jiangtao.service.ApiService;
import org.jiangtao.service.FocusService;
import org.jiangtao.utils.AccountManager;
import org.jiangtao.utils.SnackBarUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 点击关注和进行聊天界面
 */
public class UserDetailActivity extends BaseActivity {

  public static String TAG = UserDetailActivity.class.getSimpleName();

  @Bind(R.id.detail_avatar) SimpleDraweeView mDetailAvatar;
  @Bind(R.id.text_account_username) TextView mTextAccountUsername;
  @Bind(R.id.text_account) TextView mTextAccount;
  @Bind(R.id.container_header) RelativeLayout mContainerHeader;
  @Bind(R.id.add_friend) Button mAddFriend;
  @Bind(R.id.detail_focus) Button mFocus;
  private Account mAccount;
  private FocusService mFocusService;
  private IsFocus mIsFocus;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_index);
    ButterKnife.bind(this);
    mIsFocus = new IsFocus();
    mIsFocus.is_focus = true;
    mFocusService = ApiService.createFocusService();
    setDetailActionBar();
    getDetailAccount();
    isFocus();
    setUpViews();
  }

  private void setUpViews() {
    mDetailAvatar.setImageURI(mAccount.uri());
    mTextAccountUsername.setText(mAccount.username);
    mTextAccount.setText(mAccount.phone);
  }

  private void getDetailAccount() {
    Intent intent = getIntent();
    mAccount = intent.getParcelableExtra("account");
  }

  private void setDetailActionBar() {
    ActionBar bar = getSupportActionBar();
    assert bar != null;
    bar.setDisplayHomeAsUpEnabled(true);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
    }
    return super.onOptionsItemSelected(item);
  }

  @OnClick({ R.id.add_friend, R.id.detail_focus }) public void onClick(View v) {

    switch (v.getId()) {
      case R.id.add_friend:
        if (RongIM.getInstance() != null) {
          RongIM.getInstance().startPrivateChat(this, mAccount.phone, "又来消息啦");
        }
        break;
      case R.id.detail_focus:
        if (mIsFocus.is_focus) {
          cancelFocus();
        } else {
          addFocus();
        }
        break;
    }
  }

  public void addFocus() {
    Account account = AccountManager.getInstance().getAccount(this);
    Call<ArrayList<Focus>> addCall = mFocusService.addFocus(account.id, mAccount.id);
    addCall.enqueue(new Callback<ArrayList<Focus>>() {
      @Override
      public void onResponse(Call<ArrayList<Focus>> call, Response<ArrayList<Focus>> response) {
        if (response.isSuccessful()) {
          isFocus();
        } else {
          showErrorMesage();
        }
      }

      @Override public void onFailure(Call<ArrayList<Focus>> call, Throwable t) {
        showErrorMesage();
      }
    });
  }

  public void cancelFocus() {
    Account account = AccountManager.getInstance().getAccount(this);
    Call<ArrayList<Focus>> cancelCall = mFocusService.cancelFocus(account.id, mAccount.id);
    cancelCall.enqueue(new Callback<ArrayList<Focus>>() {
      @Override
      public void onResponse(Call<ArrayList<Focus>> call, Response<ArrayList<Focus>> response) {
        if (response.isSuccessful()) {
          isFocus();
        } else {
          showErrorMesage();
        }
      }

      @Override public void onFailure(Call<ArrayList<Focus>> call, Throwable t) {
        showErrorMesage();
      }
    });
  }

  public void isFocus() {
    Account account = AccountManager.getInstance().getAccount(this);
    Call<IsFocus> focusCall = mFocusService.isFocus(account.id, mAccount.id);
    focusCall.enqueue(new Callback<IsFocus>() {
      @Override public void onResponse(Call<IsFocus> call, Response<IsFocus> response) {
        if (response.isSuccessful()) {
          mIsFocus = response.body();
          if (mIsFocus != null && mIsFocus.is_focus) {
            mFocus.setText("已关注");
          } else {
            mFocus.setText("关注");
          }
        } else {
          showErrorMesage();
        }
      }

      @Override public void onFailure(Call<IsFocus> call, Throwable t) {
        showErrorMesage();
      }
    });
  }

  public void showErrorMesage() {
    SnackBarUtil.showText(this, "服务器出现故障，我们将尽快修复");
  }
}
