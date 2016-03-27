package org.jiangtao.utils;

import com.smartydroid.android.starter.kit.retrofit.RetrofitBuilder;
import org.jiangtao.service.AccountService;
import retrofit2.Retrofit;

/**
 * Created by MrJiang on 2016/3/19.
 */
public class ApiService {
  /**
   * 注册service
   */
  public static AccountService createAccountService() {
    return retrofit().create(AccountService.class);
  }

  private static Retrofit retrofit() {
    return RetrofitBuilder.get().retrofit();
  }
}
