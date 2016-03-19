package org.jiangtao.service;

import org.jiangtao.model.Account;
import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by MrJiang on 2016/3/19.
 */
public interface AccountService {

  @FormUrlEncoded @POST("/auth/register") Call<Account> register
      (@Field("phone") String phone,
      @Field("password") String password);
}
