package org.jiangtao.freedomblog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kevin on 16-5-25.
 * 意见反馈
 */
public class FeedbackActivity extends BaseActivity {

  @Bind(R.id.infor) EditText mInfor;
  @Bind(R.id.submit) Button mSubmit;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_feedback);
    ButterKnife.bind(this);
  }

  @OnClick(R.id.submit) public void onClick(View v) {

  }
}
