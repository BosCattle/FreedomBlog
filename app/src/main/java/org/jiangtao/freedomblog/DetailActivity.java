package org.jiangtao.freedomblog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.drawee.view.SimpleDraweeView;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import jp.wasabeef.richeditor.RichEditor;
import org.jiangtao.model.Account;
import org.jiangtao.model.Articles;
import org.jiangtao.utils.TurnActivity;

public class DetailActivity extends StarterActivity {

  @Bind(R.id.avatar) SimpleDraweeView mAvatar;
  @Bind(R.id.username) TextView mUsername;
  @Bind(R.id.attentions) Button mAttentions;
  @Bind(R.id.container_index) RelativeLayout mContainerIndex;
  @Bind(R.id.rich_editor) RichEditor mRichEditor;
  @Bind(R.id.recycler_comment) RecyclerView mRecyclerComment;
  private Articles mArticles;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    Intent intent = getIntent();
    mArticles = intent.getParcelableExtra("article");
    setUpView();
  }

  private void setUpView() {
    mRichEditor.setFocusable(false);
    mRichEditor.setEnabled(false);
    mRichEditor.setHtml(mArticles.content);
  }

  @OnClick(R.id.avatar) public void onClick(View v) {
    switch (v.getId()) {
      case R.id.avatar:
        Account account = mArticles.accounts;
        TurnActivity.startUserDetailActivity(DetailActivity.this, account);
        break;
    }
  }
}
