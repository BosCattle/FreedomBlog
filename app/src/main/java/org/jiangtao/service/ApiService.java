package org.jiangtao.service;

import org.jiangtao.utils.StarterNetwork;
import retrofit2.Retrofit;

/**
 * Created by MrJiang on 2016/3/19.
 */
public class ApiService {
  /**
   * 注册service
   */
  public static AccountService createAccountService() {
    return retrofit().create(AccountService.class);
  }

  public static ArticleService createArticleService() {
    return retrofit().create(ArticleService.class);
  }

  public static QiNiuService createQiNiuService() {
    return retrofit().create(QiNiuService.class);
  }

  public static RongYunService createRongYunService() {
    return retrofit().create(RongYunService.class);
  }

  public static CommentService createCommentService() {
    return retrofit().create(CommentService.class);
  }

  private static Retrofit retrofit() {
    return StarterNetwork.get().retrofit();
  }
}
