package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.jiangtao.fragment.AttentionFragment;
import org.jiangtao.fragment.IndexFragment;
import org.jiangtao.fragment.PersonFragment;
import org.jiangtao.fragment.PromptFragment;
import org.jiangtao.fragment.SendFragment;
import org.jiangtao.utils.TurnActivity;

public class ActivityIndex extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

  public static final String TAB_HOME = "tag_home_identifier";
  public static final String TAB_MESSAGE = "tag_message_identifier";
  public static final String TAB_EDUCATION = "tag_education_identifier";
  public static final String TAB_CLASS_CIRCLE = "tag_classes_identifier";
  public static final String TAB_ACCOUNT = "tag_account_identifier";

  @Bind(R.id.mian_tabhost) FragmentTabHost mTabHost;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_activity_index);
    drawerSetting();
    ButterKnife.bind(this);
    setTabHost();
  }

  private void setTabHost() {
    mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
    mTabHost.addTab(createTabSpec(TAB_HOME, R.string.index_index, R.drawable.selector_tab_home),
        IndexFragment.class, null);
    mTabHost.addTab(
        createTabSpec(TAB_MESSAGE, R.string.index_comment, R.drawable.selector_tab_messge),
        PromptFragment.class, null);
    mTabHost.addTab(createTabSpec(TAB_EDUCATION, R.string.index_fragment_pupopmenu,
        R.drawable.selector_tab_education), SendFragment.class, null);
    mTabHost.addTab(createTabSpec(TAB_CLASS_CIRCLE, R.string.index_attention,
        R.drawable.selector_tab_class_circle), AttentionFragment.class, null);
    mTabHost.addTab(createTabSpec(TAB_ACCOUNT, R.string.index_me, R.drawable.selector_tab_account),
        PersonFragment.class, null);
  }

  private TabHost.TabSpec createTabSpec(String tag, int stringRes, int drawableResId) {
    TabHost.TabSpec spec = mTabHost.newTabSpec(tag);
    spec.setIndicator(createTabIndicator(stringRes, drawableResId));
    spec.setContent(new TabHost.TabContentFactory() {
      public View createTabContent(String tag) {
        return findViewById(android.R.id.tabcontent);
      }
    });
    return spec;
  }

  /**
   * android.R.id.icon1 固定
   * 设置底部布局
   */
  private View createTabIndicator(int res, int drawableResId) {
    LinearLayout tabIndicator = (LinearLayout) LayoutInflater.from(this)
        .inflate(R.layout.tab_indicator, mTabHost.getTabWidget(), false);
    ImageView icon = (ImageView) tabIndicator.findViewById(android.R.id.icon1);
    icon.setImageResource(drawableResId);
    TextView text = ButterKnife.findById(tabIndicator, android.R.id.text1);
    text.setText(res);
    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabIndicator.getLayoutParams();
    tabIndicator.setEnabled(true);
    params.weight = 1.0F;
    tabIndicator.setGravity(Gravity.CENTER);
    return tabIndicator;
  }

  /**
   * 侧滑栏设置
   */
  private void drawerSetting() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
    assert drawer != null;
    drawer.setDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    assert navigationView != null;
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    assert drawer != null;
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.nav_index) {

    } else if (id == R.id.nav_sort) {

    } else if (id == R.id.nav_set) {
      TurnActivity.turnSettingsActivity(this);
    } else if (id == R.id.nav_about) {

    } else if (id == R.id.nav_exit) {
      onLogout();
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    assert drawer != null;
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  /**
   * index下的图片点击事件
   */
  @Override public void onClick(View v) {
    switch (v.getId()) {
    }
  }

  // 注销
  private void onLogout() {
    // 清理缓存&注销监听

    // 启动登录
    ActivityLogin.start(this, true);
    finish();
  }
}
