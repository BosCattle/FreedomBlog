package org.jiangtao.service;

import org.jiangtao.model.Articles;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by MrJiang on 4/2/2016.
 * 文章请求
 */
public interface ArticleService {
  /**
   * 添加文章
   */
  @FormUrlEncoded @POST("/article/insert") Call<Articles> insertArticle(
      @Field("account_id") String accountId, @Field("content") String content);
}
