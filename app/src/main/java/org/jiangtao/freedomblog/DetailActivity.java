package org.jiangtao.freedomblog;

import android.content.Intent;
import android.os.Bundle;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import org.xwalk.core.XWalkView;

public class DetailActivity extends StarterActivity {

  @Bind(R.id.x_walk) XWalkView mXWalkView;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    Intent intent = getIntent();
    mXWalkView.load(intent.getExtras().getString("url"), null);
  }
}
