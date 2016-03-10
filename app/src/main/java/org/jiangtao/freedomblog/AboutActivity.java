package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import org.jiangtao.utils_resource.TurnActivity;

/**
 * author:Kevin
 * description:关于界面
 */
public class AboutActivity extends AppCompatActivity {
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_index, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      TurnActivity.turnSettingsActivity(this);
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}
