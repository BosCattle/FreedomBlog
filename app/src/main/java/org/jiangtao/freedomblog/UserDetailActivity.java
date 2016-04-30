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
import org.jiangtao.model.Account;

public class UserDetailActivity extends BaseActivity {

  public static String TAG = UserDetailActivity.class.getSimpleName();

  @Bind(R.id.detail_avatar) SimpleDraweeView mDetailAvatar;
  @Bind(R.id.text_account_username) TextView mTextAccountUsername;
  @Bind(R.id.text_account) TextView mTextAccount;
  @Bind(R.id.container_header) RelativeLayout mContainerHeader;
  @Bind(R.id.add_friend) Button mAddFriend;
  private Account mAccount;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_index);
    ButterKnife.bind(this);
    setDetailActionBar();
    getDetailAccount();
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

  @OnClick(R.id.add_friend) public void onClick(View v) {

    if (RongIM.getInstance() != null) {
      RongIM.getInstance().startPrivateChat(this, mAccount.phone, "又来消息啦");
    }
  }
}
