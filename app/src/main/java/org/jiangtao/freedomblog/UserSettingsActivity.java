package org.jiangtao.freedomblog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import butterknife.Bind;
import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.io.File;
import java.util.ArrayList;
import org.jiangtao.adapter.AccountAdapter;
import org.jiangtao.model.Account;
import org.jiangtao.model.QiNiuToken;
import org.jiangtao.service.AccountService;
import org.jiangtao.service.ApiService;
import org.jiangtao.service.QiNiuService;
import org.jiangtao.utils.AccountManager;
import org.jiangtao.utils.DialogUtil;
import org.jiangtao.utils.InitUploadManager;
import org.jiangtao.utils.QiNiuTokenUtils;
import org.jiangtao.utils.SnackBarUtil;
import org.jiangtao.utils.UpdateUserInfoActivityUtils;
import org.jiangtao.view.SettingItems;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.tungdx.mediapicker.MediaItem;
import vn.tungdx.mediapicker.MediaOptions;
import vn.tungdx.mediapicker.activities.MediaPickerActivity;

/**
 * 设置用户信息
 */
public class UserSettingsActivity extends StarterActivity
    implements EasyViewHolder.OnItemClickListener, MaterialDialog.ListCallback {

  public static final int AVATAR = 1;
  public static final int USERNAME = 2;
  public static final int SEX = 3;
  public static final int AGE = 7;
  public static final int REQUEST_PHOTOGRAPH = 100;
  public static final int REQUEST_MEDIA = 200;
  private static final String TAG = UserSettingsActivity.class.getSimpleName();
  private QiNiuService mQiNiuService;
  private AccountService mAccountService;
  private String mUserName;
  private MaterialDialog.ListCallback mCallback = new MaterialDialog.ListCallback() {
    @Override
    public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
      switch (which) {
        case 0: // 男
          updateSex("男");
          break;
        case 1: // 女
          updateSex("女");
          break;
      }
    }
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.fragment_user_setting);
    mQiNiuService = ApiService.createQiNiuService();
    mAccountService = ApiService.createAccountService();
    setUpViews();
    setupRecyclerView();
  }

  @Bind(android.R.id.list) RecyclerView mUserInfoRecyclerView;
  private ArrayList<SettingItems> mSettingsItems = new ArrayList<>();
  private AccountAdapter mAdapter;

  private void setupRecyclerView() {
    mAdapter = new AccountAdapter(this, mSettingsItems);
    mAdapter.setOnClickListener(this);
    mUserInfoRecyclerView.setHasFixedSize(true);
    mUserInfoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    mUserInfoRecyclerView.addItemDecoration(
        new HorizontalDividerItemDecoration.Builder(this).size(1).showLastDivider().build());
    mUserInfoRecyclerView.setAdapter(mAdapter);
  }

  private void setUpViews() {
    mSettingsItems.clear();
    mSettingsItems.addAll(UpdateUserInfoActivityUtils.buildUserViews(this));
  }

  @Override public void onItemClick(int position, View view) {
    SettingItems settingItems = mSettingsItems.get(position);
    switch (settingItems.mType) {
      case AVATAR:
        DialogUtil.showChoosePhotoDialog(this, this);
        break;
      case USERNAME:
        reviseUserName();
        break;
      case SEX:
        DialogUtil.sexChooseDialog(this, mCallback);
        break;
      case AGE:
        reviseAge();
        break;
    }
  }

  private void reviseUserName() {
    new MaterialDialog.Builder(this).title("昵称")
        .input("请输入昵称", null, new MaterialDialog.InputCallback() {
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
              mUserName = dialog.getInputEditText().getText().toString();
              updateUserName(mUserName);
              dialog.dismiss();
            }
          }
        })
        .autoDismiss(false)
        .build()
        .show();
  }

  /**
   * 修改年龄
   */
  private void reviseAge() {
    new MaterialDialog.Builder(this).title("年龄")
        .input("请输入年龄", null, new MaterialDialog.InputCallback() {
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
              int age = Integer.parseInt(dialog.getInputEditText().getText().toString());
              if (age > 0 && age < 150) {
                String mAge = dialog.getInputEditText().getText().toString();
                updateAge(String.valueOf(mAge));
                dialog.dismiss();
              } else {
                dialog.dismiss();
                SnackBarUtil.showText(UserSettingsActivity.this, "请输入正确的年龄");
              }
            }
          }
        })
        .autoDismiss(false)
        .build()
        .show();
  }

  private void updateUserName(String userName) {
    Account mAccount = AccountManager.getInstance().getAccount(UserSettingsActivity.this);
    Call<Account> call = mAccountService.reviseUserName(userName, mAccount.id);
    showHud("正在修改...");
    call.enqueue(new Callback<Account>() {
      @Override public void onResponse(Call<Account> call, Response<Account> response) {
        if (response.isSuccessful()) {
          Account accounts = response.body();
          AccountManager.getInstance().saveAccount(accounts, UserSettingsActivity.this);
          setUpViews();
          mAdapter.notifyDataSetChanged();
          dismissHud();
        } else {
          dismissHud();
          SnackBarUtil.showText(UserSettingsActivity.this, "修改失败...");
        }
      }

      @Override public void onFailure(Call<Account> call, Throwable t) {
        dismissHud();
        SnackBarUtil.showText(UserSettingsActivity.this, t.toString());
      }
    });
  }

  @Override
  public void onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
    MediaOptions.Builder builders = new MediaOptions.Builder();
    MediaOptions options = builders.setIsCropped(true).setFixAspectRatio(true).build();
    switch (which) {
      case 0: // 拍照
        MediaPickerActivity.open(this, REQUEST_PHOTOGRAPH, options);
        break;
      case 1: // 相册选择
        MediaPickerActivity.open(this, REQUEST_MEDIA, options);
        break;
    }
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      switch (requestCode) {
        case REQUEST_PHOTOGRAPH:
        case REQUEST_MEDIA:
          ArrayList<MediaItem> mediaItems = MediaPickerActivity.getMediaItemSelected(data);
          if (mediaItems != null) {
            for (MediaItem mediaitem : mediaItems) {
              Log.e("------->", mediaitem.toString());
              configSimpleQiNiu(mediaitem.getPathCropped(UserSettingsActivity.this));
            }
            mediaItems.clear();
          }
          break;
      }
    }
  }

  /**
   * 修改头像
   */
  private void configSimpleQiNiu(final String path) {
    Call<QiNiuToken> call = mQiNiuService.getToken();
    call.enqueue(new Callback<QiNiuToken>() {
      @Override public void onResponse(Call<QiNiuToken> call, Response<QiNiuToken> response) {
        if (response.isSuccessful()) {
          String token = response.body().uploadToken;
          Log.d("<<<<<<<<<", "onResponse: " + token);
          File data = new File(path);
          Log.d(TAG, "configSimpleQiNiu: " + token);
          InitUploadManager.getInstance()
              .getmUploadManager()
              .put(data, null, token, new UpCompletionHandler() {
                @Override public void complete(String key, ResponseInfo info, JSONObject res) {
                  Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
                  try {
                    String url = QiNiuTokenUtils.getInstance().spliceUrl(res.getString("key"));
                    Account account =
                        AccountManager.getInstance().getAccount(UserSettingsActivity.this);
                    account.imageUrl = url;
                    AccountManager.getInstance().saveAccount(account, UserSettingsActivity.this);
                    setUpViews();
                    setupRecyclerView();
                    mAdapter.notifyDataSetChanged();
                  } catch (JSONException e) {
                    e.printStackTrace();
                  }
                }
              }, null);
        } else {
          SnackBarUtil.showText(UserSettingsActivity.this, "获取token错误");
        }
      }

      @Override public void onFailure(Call<QiNiuToken> call, Throwable t) {
        SnackBarUtil.showText(UserSettingsActivity.this, "服务器错误");
      }
    });
  }

  /**
   * 修改性别
   */
  public void updateSex(String sex) {
    Account mAccount = AccountManager.getInstance().getAccount(UserSettingsActivity.this);
    Call<Account> call = mAccountService.reviseSex(sex, mAccount.id);
    showHud("正在修改..");
    call.enqueue(new Callback<Account>() {
      @Override public void onResponse(Call<Account> call, Response<Account> response) {
        if (response.isSuccessful()) {
          dismissHud();
          AccountManager.getInstance().saveAccount(response.body(), UserSettingsActivity.this);
          setUpViews();
          mAdapter.notifyDataSetChanged();
        } else {
          dismissHud();
          SnackBarUtil.showText(UserSettingsActivity.this, "服务器错误");
        }
      }

      @Override public void onFailure(Call<Account> call, Throwable t) {
        dismissHud();
        SnackBarUtil.showText(UserSettingsActivity.this, t.toString());
      }
    });
  }

  /**
   * 修改性别
   */
  public void updateAge(String age) {
    Account mAccount = AccountManager.getInstance().getAccount(UserSettingsActivity.this);
    Call<Account> call = mAccountService.reviseAge(age, mAccount.id);
    showHud("正在修改..");
    call.enqueue(new Callback<Account>() {
      @Override public void onResponse(Call<Account> call, Response<Account> response) {
        if (response.isSuccessful()) {
          dismissHud();
          AccountManager.getInstance().saveAccount(response.body(), UserSettingsActivity.this);
          setUpViews();
          mAdapter.notifyDataSetChanged();
        } else {
          dismissHud();
          SnackBarUtil.showText(UserSettingsActivity.this, "服务器错误");
        }
      }

      @Override public void onFailure(Call<Account> call, Throwable t) {
        dismissHud();
        SnackBarUtil.showText(UserSettingsActivity.this, t.toString());
      }
    });
  }
}
