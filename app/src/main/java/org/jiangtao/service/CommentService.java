package org.jiangtao.service;

import java.util.ArrayList;
import org.jiangtao.model.Comment;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by MrJiang on 5/1/2016.
 */
public interface CommentService {
  /**
   * 插入评论
   */
  @GET("/comment/insert") Call<ArrayList<Comment>> insertComment(
      @Query("article_id") int article_id, @Query("content") String comtent,
      @Query("is_parent") int is_parent, @Query("account_id") int account_id,
      @Query("parent_account_id") int parent_account_id);

  /**
   * 获取所有的评论
   */
  @GET("/comment/all") Call<ArrayList<Comment>> getAllComment(@Query("article_id") int article_id);
}
