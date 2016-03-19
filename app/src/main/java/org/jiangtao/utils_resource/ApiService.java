package org.jiangtao.utils_resource;

import org.jiangtao.service.AccountService;
import retrofit.Retrofit;

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
    return StarterNetwork.get().retrofit();
  }
}
