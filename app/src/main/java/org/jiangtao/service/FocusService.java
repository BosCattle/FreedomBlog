package org.jiangtao.service;

import java.util.ArrayList;
import org.jiangtao.model.Focus;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MrJiang on 5/2/2016.
 */
public interface FocusService {
  /**
   * 添加关注
   */
  @GET("focus/add") Call<ArrayList<Focus>> addFocus(@Query("account_id") Integer account_id,
      @Query("focus_id") Integer focus_id);

  /**
   * 取消关注
   */
  @GET("focus/add") Call<ArrayList<Focus>> cancelFocus(@Query("account_id") Integer account_id,
      @Query("focus_id") Integer focus_id);

  /**
   * 获取所有关注
   */
  @GET("focus/all") Call<ArrayList<Focus>> allFocus(@Query("account_id") Integer account_id);
}
