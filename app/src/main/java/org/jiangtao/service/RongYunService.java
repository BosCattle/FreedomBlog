package org.jiangtao.service;

import org.jiangtao.model.RongYun;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by MrJiang on 4/24/2016.
 */
public interface RongYunService {

  @FormUrlEncoded @POST("rongyun/token") Call<RongYun> register(@Field("userId") String userId,
      @Field("userName") String userName, @Field("portraitUri") String portraitUri);
}
