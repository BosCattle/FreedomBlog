package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import java.sql.Date;
import org.jiangtao.application.BlogApplication;
import org.jiangtao.model.Account;
import org.jiangtao.service.AccountService;
import org.jiangtao.utils_resource.AccountManager;
import org.jiangtao.utils_resource.ApiService;
import org.jiangtao.utils_resource.TurnActivity;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;

/**
 * Class:ActivityRegister <br>
 * Description:注册界面 <br>
 * Creator: MrJiang <br>
 * Date: 2016/3/20 0:17 <br>
 * 抽出方法
 * 尽量完成接口对接
 */
public class ActivityRegister extends AppCompatActivity implements View.OnClickListener {

  @Bind(R.id.edittext_email_phonenumber) EditText mPhoneEdit;
  @Bind(R.id.send_validate) TextView mValidateText;
  @Bind(R.id.sub_edittext_password) EditText mPassWordEdit;
  @Bind(R.id.register_edittext_validate) EditText mVakidateEdit;
  @Bind(R.id.button_register) Button mRegisterButton;
  @Bind(R.id.container) RelativeLayout mRelativelayout;
  private AccountService mAccountService;
  private String phoneText;
  private String passwordText;
  private String validateText;
  private EventHandler mEventHandler;
  private Handler mHandler = new Handler() {
    @Override public void handleMessage(Message msg) {
      super.handleMessage(msg);
      //returnResult(msg.arg1, msg.arg2, msg.obj);
    }
  };

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_activity_register);
    ButterKnife.bind(this);
    initSMSSDK();
    mAccountService = ApiService.createAccountService();
  }

  /**
   * 初始化事件接收器
   * 注册
   */
  private void initSMSSDK() {
    mEventHandler = new EventHandler() {
      @Override public void afterEvent(int event, int result, Object data) {
        super.afterEvent(event, result, data);
        Message msg = new Message();
        msg.arg1 = event;
        msg.arg2 = result;
        msg.obj = data;
        mHandler.sendMessage(msg);
      }
    };
    SMSSDK.registerEventHandler(mEventHandler);
    mValidateText.setOnClickListener(this);
    mRegisterButton.setOnClickListener(this);
  }

  /**
   * 根据返回码进行处理
   */
  public void returnResult(int event, int result, Object data) {
    if (result == SMSSDK.RESULT_COMPLETE) {
      Snackbar.make(mRelativelayout, "回调完成", Snackbar.LENGTH_SHORT).show();
      if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
      } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
        Snackbar.make(mRelativelayout, "获取验证码成功", Snackbar.LENGTH_SHORT).show();
      } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
        Snackbar.make(mRelativelayout, "返回支持发送验证码的国家列表", Snackbar.LENGTH_SHORT).show();
      }
    } else {
      ((Throwable) data).printStackTrace();
      Snackbar.make(mRelativelayout, "验证码系统出现错误，我们将尽快解决", Snackbar.LENGTH_SHORT).show();
    }
  }

  @Override public void onClick(View v) {
    phoneText = mPhoneEdit.getText().toString();
    passwordText = mPassWordEdit.getText().toString();
    validateText = mVakidateEdit.getText().toString();
    switch (v.getId()) {
      case R.id.send_validate:
        if (formatIsTrue(0)) SMSSDK.getVerificationCode("+86", phoneText);
        mValidateText.setClickable(false);
        break;
      case R.id.button_register:
        if (formatIsTrue(1)) SMSSDK.submitVerificationCode("+86", phoneText, validateText);
        Snackbar.make(mRelativelayout, "提交验证码成功", Snackbar.LENGTH_SHORT).show();
        //进行联网操作，提交信息给服务器
        Call<Account> call = mAccountService.register(phoneText, passwordText);
        Callback<Account> callback = new Callback<Account>() {

          @Override public void onResponse(Response<Account> response) {
            if (response.isSuccess()) {
              Account account = response.body();
              if (account != null) {
                if (account.token != null) {
                  AccountManager.getInstance().saveAccount(account, getApplicationContext());
                  AccountManager.getInstance().saveToken(getApplicationContext(), account.token);
                  java.util.Date date = new java.util.Date();
                  Date date1 = new Date(date.getTime());
                  Log.d("--->", date1.toString() + "你好");
                  AccountManager.getInstance()
                      .saveTime(BlogApplication.getInstance().getApplicationContext(), date1);
                  TurnActivity.startIndexActivity(ActivityRegister.this);
                }
              } else {
                Snackbar.make(mRelativelayout, "手机号已经存在", Snackbar.LENGTH_SHORT).show();
              }
              Log.d("----->", account.toString());
            }
          }

          @Override public void onFailure(Throwable t) {
            Log.d("----->", t.toString());
            Snackbar.make(mRelativelayout, "手机号已经存在", Snackbar.LENGTH_SHORT).show();
          }
        };
        call.enqueue(callback);
        break;
    }
  }

  public boolean formatIsTrue(int i) {
    switch (i) {
      case 0:
        if (phoneText.length() == 11 && passwordText.length() >= 6) {
          return true;
        }
        Snackbar.make(mRelativelayout, "输入信息不符合", Snackbar.LENGTH_SHORT).show();
        break;
      case 1:
        if (phoneText.length() == 11 && passwordText.length() >= 6 && validateText.length() > 1) {
          return true;
        }
        Snackbar.make(mRelativelayout, "输入信息不符合", Snackbar.LENGTH_SHORT).show();
        break;
    }
    return false;
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    SMSSDK.unregisterAllEventHandler();
  }
}
