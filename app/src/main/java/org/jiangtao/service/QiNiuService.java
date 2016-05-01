package org.jiangtao.service;

import org.jiangtao.model.QiNiuToken;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by MrJiang on 4/3/2016.
 * 获取七牛token
 */
public interface QiNiuService {

  @GET("qiniu/token") Call<QiNiuToken> getToken();
}
