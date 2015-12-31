package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.jiangtao.fragment.AttentionFragment;
import org.jiangtao.fragment.IndexFragment;
import org.jiangtao.fragment.PersonFragment;
import org.jiangtao.fragment.PromptFragment;
import org.jiangtao.utils_resource.TurnActivity;

public class ActivityIndex extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {
    private Fragment[] mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_index);
        drawerSetting();
        initializationFragment();
    }

    private void initializationFragment() {
        mFragments = new Fragment[4];
        Fragment indexFragment = new IndexFragment();
        Fragment attentionFragment = new AttentionFragment();
        Fragment promptFragment = new PromptFragment();
        Fragment personFragment = new PersonFragment();
        mFragments[0] = indexFragment;
        mFragments[1] = attentionFragment;
        mFragments[2] = promptFragment;
        mFragments[3] = personFragment;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container_index, mFragments[0])
                .add(R.id.container_index, mFragments[1])
                .add(R.id.container_index, mFragments[2])
                .add(R.id.container_index, mFragments[3])
                .show(mFragments[0])
                .hide(mFragments[1])
                .hide(mFragments[2])
                .hide(mFragments[3])
                .commit();
    }

    /**
     * 侧滑栏设置
     */
    private void drawerSetting() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_index, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            TurnActivity.turnSettingsActivity(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_index) {

        } else if (id == R.id.nav_sort) {

        } else if (id == R.id.nav_set) {
            TurnActivity.turnSettingsActivity(this);
        } else if (id == R.id.nav_about) {

        } else if (id == R.id.nav_exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * index下的图片点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.index_fragment_index: {
                underTurnFragment(false, 0);
                break;
            }
            case R.id.index_fragment_attention: {
                underTurnFragment(false, 1);
                break;
            }
            case R.id.index_fragment_pupopmenu: {

                break;
            }
            case R.id.index_fragment_prompt: {
                underTurnFragment(false, 2);
                break;
            }
            case R.id.index_fragment_person: {
                underTurnFragment(false, 3);
                break;
            }
        }
    }

    /**
     * 切换fragment
     *
     * @param ischeck
     * @param location
     */
    public void underTurnFragment(boolean ischeck, int location) {
        if (!ischeck) {
            turnFragment(location);
        } else {
            //判断登陆情况，如果登陆，直接进入，否则提示登陆，并且让用户选择

        }
    }

    /**
     * 根据位置切换fragment
     *
     * @param location
     */
    public void turnFragment(int location) {
        switch (location) {
            case 0: {
                getSupportFragmentManager().beginTransaction()
                        .show(mFragments[0])
                        .hide(mFragments[1])
                        .hide(mFragments[2])
                        .hide(mFragments[3])
                        .commit();
                break;
            }
            case 1: {
                getSupportFragmentManager().beginTransaction()
                        .show(mFragments[1])
                        .hide(mFragments[0])
                        .hide(mFragments[2])
                        .hide(mFragments[3])
                        .commit();
                break;
            }
            case 2: {
                getSupportFragmentManager().beginTransaction()
                        .show(mFragments[2])
                        .hide(mFragments[1])
                        .hide(mFragments[0])
                        .hide(mFragments[3])
                        .commit();
                break;
            }
            case 3: {
                getSupportFragmentManager().beginTransaction()
                        .show(mFragments[3])
                        .hide(mFragments[1])
                        .hide(mFragments[2])
                        .hide(mFragments[0])
                        .commit();
                break;
            }
        }
    }
}
