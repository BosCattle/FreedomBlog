package org.jiangtao.freedomblog;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by kevin on 16-5-25.
 * 软件信息
 */
public class SoftImformationActivity extends BaseActivity {

  @Bind(R.id.web_view) WebView mWebView;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_software_information);
    ButterKnife.bind(this);
    initWebView();
  }

  private void initWebView() {
    mWebView.setBackgroundColor(Color.WHITE);
    mWebView.getSettings().setJavaScriptEnabled(true);
    mWebView.loadUrl("http://jiangtao.org/");
  }
}
