package org.jiangtao.service;

import org.jiangtao.model.Account;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 账户服务
 */
public interface AccountService {
  /**
   * 注册
   */
  @FormUrlEncoded @POST("/auth/register") Call<Account> register(@Field("phone") String phone,
      @Field("password") String password);

  /**
   * 登录
   */
  @FormUrlEncoded @POST("/auth/login") Call<Account>
  login(@Field("phone") String phone, @Field("password") String password);
}
