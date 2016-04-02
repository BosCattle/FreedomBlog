package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.yqritc.recyclerviewflexibledivider.VerticalDividerItemDecoration;
import java.util.ArrayList;
import jp.wasabeef.richeditor.RichEditor;
import org.jiangtao.adapter.AccountAdapter;
import org.jiangtao.model.Account;
import org.jiangtao.model.Articles;
import org.jiangtao.service.ApiService;
import org.jiangtao.service.ArticleService;
import org.jiangtao.utils.AccountManager;
import org.jiangtao.utils.SnackBarUtil;
import org.jiangtao.utils.build.SendFragmentUtils;
import org.jiangtao.view.SettingItems;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 发布界面
 */
public class PublishActivity extends StarterActivity
    implements EasyViewHolder.OnItemClickListener, RichEditor.OnTextChangeListener {

  private static final String TAG = PublishActivity.class.getSimpleName();
  @Bind(R.id.recycler_icon) RecyclerView mIconRecycler;
  @Bind(R.id.edit_title) EditText mTitleEditText;
  @Bind(R.id.editor) RichEditor mEditor;

  private AccountAdapter mAccountAdapter;
  private ArrayList<SettingItems> mSettingItems;
  private StringBuffer mBuffer = new StringBuffer();
  private String mBodyText;
  private ArticleService mArticleService;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_publish);
    mArticleService = ApiService.createArticleService();
    setUpAdapter();
    setUpRichEditor();
    ButterKnife.bind(this);
  }

  private void setUpRichEditor() {
    mEditor.setEditorHeight(500);
    mEditor.setEditorFontSize(16);
    mEditor.setPlaceholder("Insert text here...");
    mEditor.setPadding(10, 10, 10, 10);
    mEditor.setOnTextChangeListener(this);
  }

  protected void setUpAdapter() {
    mSettingItems = new ArrayList<>();
    mSettingItems = SendFragmentUtils.getInstance().buildArticleAttribute();
    mAccountAdapter = new AccountAdapter(this, mSettingItems);
    mAccountAdapter.setOnClickListener(this);
    mIconRecycler.setHasFixedSize(true);
    LinearLayoutManager manager = new LinearLayoutManager(this);
    manager.setOrientation(LinearLayoutManager.HORIZONTAL);
    mIconRecycler.setLayoutManager(manager);
    mIconRecycler.addItemDecoration(new VerticalDividerItemDecoration.Builder(this).size(1)
        .color(R.color.white)
        .showLastDivider()
        .build());
    mIconRecycler.setAdapter(mAccountAdapter);
  }

  @Override public void onItemClick(int position, View view) {
    switch (position) {
      case 0:
        mEditor.undo();
        break;
      case 1:
        mEditor.redo();
        break;
      case 2:
        mEditor.setBold();
        break;
      case 3:
        mEditor.setItalic();
        break;
      case 4:
        mEditor.setSubscript();
        break;
      case 5:
        mEditor.setBlockquote();
        break;
      case 6:
        mEditor.insertImage("http://www.1honeywan.com/dachshund/image/7.21/7.21_3_thumb.JPG",
            "dachshund");
        break;
      case 7:
        inputWebsite();
        break;
      case 8:
        mEditor.setSuperscript();
        break;
      case 9:
        mEditor.setStrikeThrough();
        break;
      case 10:
        mEditor.setUnderline();
        break;
      case 11:
        mEditor.setHeading(1);
        break;
      case 12:
        mEditor.setHeading(2);
        break;
      case 13:
        mEditor.setHeading(3);
        break;
      case 14:
        mEditor.setHeading(4);
        break;
      case 15:
        mEditor.setHeading(5);
        break;
      case 16:
        mEditor.setHeading(6);
        break;
      case 17:
        mEditor.setIndent();
        break;
      case 18:
        mEditor.setOutdent();
        break;
      case 19:
        mEditor.setAlignLeft();
        break;
      case 20:
        mEditor.setAlignCenter();
        break;
      case 21:
        mEditor.setAlignRight();
        break;
    }
  }

  @Override public void onTextChange(String text) {
    Log.d("--------->", text);
    mBodyText = text;
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.menu_publish, menu);
    return super.onCreateOptionsMenu(menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_publish) {
      // TODO: 4/2/2016 点击将生成的内容发送到服务器
      if (isTitleNull()) {
        mBuffer.append("<!DOCTYPE html><html><head>");
        mBuffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
        mBuffer.append("<title>")
            .append(mTitleEditText.getText().toString())
            .append("</title></head>");
        mBuffer.append("<body>").append(mBodyText).append("</body></html>\n");
        Log.d(TAG, "onOptionsItemSelected: " + mBuffer);
        submitArticle(mBuffer);
      }
    }
    return super.onOptionsItemSelected(item);
  }

  public void submitArticle(StringBuffer buffer) {
    showHud("正在上传,请耐心等待..");
    Account account = AccountManager.getInstance().getAccount(this);
    Call<Articles> call = mArticleService.insertArticle(account.id + "", buffer.toString());
    call.enqueue(new Callback<Articles>() {
      @Override public void onResponse(Call<Articles> call, Response<Articles> response) {
        dismissHud();
        if (response.isSuccessful()) {
          SnackBarUtil.showText(PublishActivity.this, "发布成功.");
        } else {
          SnackBarUtil.showText(PublishActivity.this, "发生错误.");
        }
      }

      @Override public void onFailure(Call<Articles> call, Throwable t) {
        dismissHud();
        SnackBarUtil.showText(PublishActivity.this, t.toString());
      }
    });
  }

  /**
   * 输入网址
   */
  public void inputWebsite() {
    new MaterialDialog.Builder(this).title("网址")
        .input("请输入网址", null, new MaterialDialog.InputCallback() {
          @Override public void onInput(MaterialDialog dialog, CharSequence input) {

          }
        })
        .negativeText("取消")
        .cancelable(false)
        .onNegative(new MaterialDialog.SingleButtonCallback() {
          @Override public void onClick(MaterialDialog dialog, DialogAction which) {
            dialog.dismiss();
          }
        })
        .onPositive(new MaterialDialog.SingleButtonCallback() {
          @Override public void onClick(MaterialDialog dialog, DialogAction which) {
            if (dialog.getInputEditText() != null && dialog.getInputEditText()
                .getText()
                .toString()
                .equals("")) {
              Toast.makeText(getApplicationContext(), "输入内容不能为空", Toast.LENGTH_LONG).show();
            } else {
              mEditor.insertLink(dialog.getInputEditText().getText().toString(), "链接");
              mEditor.requestFocus();
              dialog.dismiss();
            }
          }
        })
        .autoDismiss(false)
        .build()
        .show();
  }

  /**
   * 判断title标题是否为null
   */
  public boolean isTitleNull() {
    if (mTitleEditText.getText().length() != 0) {
      return true;
    } else {
      hideSoftInputMethod();
      SnackBarUtil.showText(this, "文章标题不能为空哦..");
    }
    return false;
  }
}
