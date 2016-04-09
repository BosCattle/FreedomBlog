package org.jiangtao.freedomblog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import jp.wasabeef.richeditor.RichEditor;
import org.jiangtao.model.Articles;

public class DetailActivity extends StarterActivity {

  @Bind(R.id.avatar) SimpleDraweeView mAvatar;
  @Bind(R.id.username) TextView mUsername;
  @Bind(R.id.attentions) Button mAttentions;
  @Bind(R.id.container_index) RelativeLayout mContainerIndex;
  @Bind(R.id.rich_editor) RichEditor mRichEditor;
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
}
