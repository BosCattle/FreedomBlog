package org.jiangtao.utils;

import com.qiniu.android.storage.UploadManager;

/**
 * Created by MrJiang on 4/3/2016.
 * 初始化uploadManager
 */
public class InitUploadManager {

  private static InitUploadManager instance;
  private UploadManager mUploadManager;

  private InitUploadManager() {
  }

  public static InitUploadManager getInstance() {
    if (instance == null) {
      synchronized (InitUploadManager.class) {
        instance = new InitUploadManager();
      }
    }
    return instance;
  }

  /**
   * 获取uploadmanger
   */
  public UploadManager getmUploadManager() {
    if (mUploadManager == null) {
      mUploadManager = new UploadManager();
    }
    return mUploadManager;
  }
}
