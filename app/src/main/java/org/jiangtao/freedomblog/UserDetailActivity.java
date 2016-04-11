package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.MenuItem;

public class UserDetailActivity extends BaseActivity {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_index);
    setDetailActionBar();
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
}
