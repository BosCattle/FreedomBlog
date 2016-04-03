package org.jiangtao.utils;

import android.app.Activity;
import android.util.Log;
import org.jiangtao.model.QiNiuToken;
import org.jiangtao.service.ApiService;
import org.jiangtao.service.QiNiuService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by MrJiang on 4/3/2016.
 * 获取七牛token
 */
public class QiNiuTokenUtils {

  private static QiNiuTokenUtils instance;
  private String token;

  private QiNiuTokenUtils() {
  }

  public static QiNiuTokenUtils getInstance() {
    if (instance == null) {
      synchronized (QiNiuTokenUtils.class) {
        instance = new QiNiuTokenUtils();
      }
    }
    return instance;
  }

  private QiNiuService mQiNiuService;

  /**
   * 从服务器端获取token
   */
  public String getToken(final Activity activity) {
    mQiNiuService = ApiService.createQiNiuService();
    Call<QiNiuToken> call = mQiNiuService.getToken();
    call.enqueue(new Callback<QiNiuToken>() {
      @Override public void onResponse(Call<QiNiuToken> call, Response<QiNiuToken> response) {
        if (response.isSuccessful()) {
          token = response.body().uploadToken;
          Log.d("<<<<<<<<<", "onResponse: "+token);
        } else {
          SnackBarUtil.showText(activity, "获取token错误");
        }
      }

      @Override public void onFailure(Call<QiNiuToken> call, Throwable t) {
        SnackBarUtil.showText(activity, "服务器错误");
      }
    });
    // TODO: 4/3/2016 获取的token一直为null
    assert token != null;
    return token;
  }

  /**
   * 拼接七牛key
   */
  public String spliceUrl(String key) {
    return "http://7xq3zt.com1.z0.glb.clouddn.com/" + key;
  }
}
