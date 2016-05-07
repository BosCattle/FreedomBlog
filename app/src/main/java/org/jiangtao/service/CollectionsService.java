package org.jiangtao.service;

import org.jiangtao.model.Collections;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MrJiang on 5/7/2016.
 */
public interface CollectionsService {
  /**
   * 判断是否收藏
   */
  @GET("collections/single") Call<Collections> getSingleCollection(
      @Query("article_id") Integer article_id, @Query("account_id") Integer account_id);

  /**
   * 添加收藏
   */
  @GET("collections/add") Call<Collections> addCollections(@Query("article_id") Integer article_id,
      @Query("account_id") Integer account_id);

  /**
   * 取消收藏
   */
  @GET("collections/cancel") Call<Collections> cancelCollections(
      @Query("article_id") Integer article_id, @Query("account_id") Integer account_id,
      @Query("id") Integer id);

  /**
   * 获取用户所有的收藏
   */
  @GET("collections/all") Call<Collections> allCollections(@Query("account_id") Integer account_id);
}
