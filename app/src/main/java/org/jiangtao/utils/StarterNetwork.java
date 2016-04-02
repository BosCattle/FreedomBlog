package org.jiangtao.utils;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by MrJiang on 2016/3/19.
 */
public class StarterNetwork {
  private static final String sBaseUrl = StaticResources.BASE_URL;
  private Retrofit mRetrofit;

  private static class SingletonHolder {
    private static final StarterNetwork INSTANCE = new StarterNetwork();
  }

  public static synchronized StarterNetwork get() {
    return SingletonHolder.INSTANCE;
  }

  protected Retrofit.Builder newRetrofitBuilder() {
    return new Retrofit.Builder();
  }

  public Retrofit retrofit() {
    if (mRetrofit == null) {
      Retrofit.Builder builder = newRetrofitBuilder();
      mRetrofit =
          builder.baseUrl(sBaseUrl).addConverterFactory(JacksonConverterFactory.create()).build();
    }

    return mRetrofit;
  }
}
