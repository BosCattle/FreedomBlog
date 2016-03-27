package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import org.jiangtao.utils.TurnActivity;

/**
 * author:Kevin
 * description:设置界面
 */
public class SettingsActivity extends AppCompatActivity {

  @Override public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
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
