package org.jiangtao.service;

import org.jiangtao.model.FirCheck;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kevin on 16-5-25.
 * 检查更新service
 */
public interface VersionCheckService {

  @GET("/latest/:id") Call<FirCheck> versionCheck(@Query("id") String id,
      @Query("api_token") String apiToken, @Query("type") String type);
}
