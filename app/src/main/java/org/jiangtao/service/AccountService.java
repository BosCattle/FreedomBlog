package org.jiangtao.service;

import java.util.ArrayList;
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
  @FormUrlEncoded @POST("auth/register") Call<Account> register(@Field("phone") String phone,
      @Field("password") String password);

  /**
   * 登录
   */
  @FormUrlEncoded @POST("auth/login") Call<Account> login(@Field("phone") String phone,
      @Field("password") String password);

  /**
   * 修改昵称
   */
  @FormUrlEncoded @POST("auth/revise/username") Call<Account> reviseUserName(
      @Field("username") String username, @Field("id") int id);

  /**
   * 修改性别
   */
  @FormUrlEncoded @POST("auth/revise/sex") Call<Account> reviseSex(@Field("sex") String sex,
      @Field("id") int id);

  /**
   * 修改性别
   */
  @FormUrlEncoded @POST("auth/revise/age") Call<Account> reviseAge(@Field("age") String age,
      @Field("id") int id);

  /**
   * 获取所有用户信息
   */
  @POST("auth/allInfo") Call<ArrayList<Account>> getAllAcoount();
}
