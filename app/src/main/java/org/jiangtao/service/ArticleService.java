package org.jiangtao.service;

import java.util.ArrayList;
import org.jiangtao.model.Articles;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by MrJiang on 4/2/2016.
 * 文章请求
 */
public interface ArticleService {
  /**
   * 添加文章
   */
  @FormUrlEncoded @POST("/article/insert") Call<Articles> insertArticle(
      @Field("account_id") String accountId, @Field("content") String content,
      @Field("title") String title, @Field("image_url") String imageUrl);

  /**
   * 获取用户上传的所有文章
   */
  @GET("/article/all") Call<ArrayList<Articles>> getArticles(@Query("max-id") String maxId,
      @Query("since-id") String sinceId, @Query("page_size") int pageSize);
}
