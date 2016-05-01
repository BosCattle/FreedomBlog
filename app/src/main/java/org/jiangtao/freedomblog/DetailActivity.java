package org.jiangtao.freedomblog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyRecyclerAdapter;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.smartydroid.android.starter.kit.app.StarterActivity;
import java.util.ArrayList;
import jp.wasabeef.richeditor.RichEditor;
import org.jiangtao.holder.DetailViewHolder;
import org.jiangtao.model.Account;
import org.jiangtao.model.Articles;
import org.jiangtao.model.Comment;
import org.jiangtao.service.ApiService;
import org.jiangtao.service.CommentService;
import org.jiangtao.utils.AccountManager;
import org.jiangtao.utils.SnackBarUtil;
import org.jiangtao.utils.TurnActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailActivity extends StarterActivity
    implements EasyViewHolder.OnItemClickListener, DetailViewHolder.NameOnClickListener {

  @Bind(R.id.avatar) SimpleDraweeView mAvatar;
  @Bind(R.id.username) TextView mUsername;
  @Bind(R.id.container_index) RelativeLayout mContainerIndex;
  @Bind(R.id.rich_editor) RichEditor mRichEditor;
  @Bind(R.id.recycler_comment) RecyclerView mRecyclerComment;
  @Bind(R.id.send_mge) TextView mSendMessage;
  @Bind(R.id.comments_content) EditText mCommentsContent;
  private Articles mArticles;
  private CommentService mCommentService;
  private ArrayList<Comment> mComments;
  private EasyRecyclerAdapter mEasyRecyclerAdapter;
  private int isParent;
  private Account mParentAccount;
  private Account account;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);
    ButterKnife.bind(this);
    init();
    setUpView();
    getAllComment();
    setUpAdapter();
    setUpRecyclerView();
  }

  private void getAllComment() {
    Call<ArrayList<Comment>> call = mCommentService.getAllComment(mArticles.id);
    call.enqueue(new Callback<ArrayList<Comment>>() {
      @Override
      public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
        if (response.isSuccessful()) {
          if (response.body() != null && response.body().size() != 0) {
            mComments = response.body();
            startUpdate();
          }
        } else {
          showErrorMessage();
        }
      }

      @Override public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
        showErrorMessage();
      }
    });
  }

  private void setUpRecyclerView() {
    mRecyclerComment.setHasFixedSize(true);
    mRecyclerComment.setLayoutManager(new LinearLayoutManager(this));
    mRecyclerComment.setAdapter(mEasyRecyclerAdapter);
  }

  private void setUpAdapter() {
    mEasyRecyclerAdapter = new EasyRecyclerAdapter(this);
    mEasyRecyclerAdapter.bind(Comment.class, DetailViewHolder.class);
    mEasyRecyclerAdapter.setOnClickListener(this);
    mEasyRecyclerAdapter.appendAll(mComments);
  }

  public void startUpdate() {
    mEasyRecyclerAdapter.clear();
    mEasyRecyclerAdapter.appendAll(mComments);
    mEasyRecyclerAdapter.notifyDataSetChanged();
  }

  private void init() {
    Intent intent = getIntent();
    mArticles = intent.getParcelableExtra("article");
    mCommentService = ApiService.createCommentService();
    mComments = new ArrayList<>();
    initAccount();
  }

  public void initAccount(){
    mParentAccount = new Account();
    mParentAccount.id = 0;
    account = AccountManager.getInstance().getAccount(this);
  }

  private void setUpView() {
    mRichEditor.setFocusable(false);
    mRichEditor.setEnabled(false);
    mRichEditor.setHtml(mArticles.content);
    mAvatar.setImageURI(mArticles.accounts.uri());
    mUsername.setText(mArticles.accounts.username);
  }

  @OnClick({ R.id.avatar, R.id.send_mge }) public void onClick(View v) {
    switch (v.getId()) {
      case R.id.avatar:
        Account account = mArticles.accounts;
        TurnActivity.startUserDetailActivity(DetailActivity.this, account);
        break;
      case R.id.send_mge:
        String text = mCommentsContent.getText().toString();
        if (text != null) {
          sendMessage(text);
        }
        break;
    }
  }

  public void sendMessage(String text) {
    hideSoftInputMethod();
    showHud("正在提交....");
    final Call<ArrayList<Comment>> mCommentCall =
        mCommentService.insertComment(mArticles.id, text, isParent, account.id, mParentAccount.id);
    mCommentCall.enqueue(new Callback<ArrayList<Comment>>() {
      @Override
      public void onResponse(Call<ArrayList<Comment>> call, Response<ArrayList<Comment>> response) {
        dismissHud();
        if (response.isSuccessful()) {
          mComments = response.body();
          isParent = 0;
          initAccount();
          mCommentsContent.setText("");
          startUpdate();
        } else {
          isParent = 0;
          initAccount();
          mCommentsContent.setText("");
          showErrorMessage();
        }
      }

      @Override public void onFailure(Call<ArrayList<Comment>> call, Throwable t) {
        dismissHud();
        isParent = 0;
        initAccount();
        mCommentsContent.setText("");
        showErrorMessage();
      }
    });
  }

  public void showErrorMessage() {
    SnackBarUtil.showText(this, "发生了错误");
  }

  @Override public void onItemClick(int position, View view) {

  }

  @Override public void sendUser(boolean status,Account account, Account parentAccount) {
    if (status) {
      this.account = account;
      mCommentsContent.requestFocus();
      mCommentsContent.setHint("回复" + account.username + ":");
      mParentAccount = parentAccount;
      isParent = 1;
    }else {
      this.account = account;
      mCommentsContent.requestFocus();
      mCommentsContent.setHint("回复" + parentAccount.username + ":");
      mParentAccount = parentAccount;
      isParent = 1;
    }
  }
}
