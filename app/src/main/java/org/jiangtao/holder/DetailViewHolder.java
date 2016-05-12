package org.jiangtao.holder;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.carlosdelachica.easyrecycleradapters.adapter.EasyViewHolder;
import org.jiangtao.freedomblog.R;
import org.jiangtao.model.Account;
import org.jiangtao.model.Comment;
import org.jiangtao.utils.AccountManager;

/**
 * Created by 李松柏
 * on 5/1/2016.
 * 文章详情ViewHolder
 */
public class DetailViewHolder extends EasyViewHolder<Comment> {

  @Bind(R.id.who_to_who) TextView mWhoToWho;
  @Bind(R.id.content) TextView mContent;
  private Context mContext;
  private NameOnClickListener mNameOnClickListener;
  private Comment mComment;

  public DetailViewHolder(Context context, ViewGroup parent) {
    super(context, parent, R.layout.list_item_comment);
    ButterKnife.bind(this, itemView);
    mContext = context;
    setNameOnClickListener((NameOnClickListener) context);
  }

  @Override public void bindTo(Comment value) {
    mComment = value;
    if (value.parent_id == 0) {
      SpannableString ss = new SpannableString(
          value.account != null && value.account.username != null ? value.account.username + ":"
              : "");
      ss.setSpan(new TextSpanClick(false), 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      ss.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.colorPrimary)), 0,
          value.account.username.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      mWhoToWho.setText(ss);
    } else {
      if (value.parent_account != null) {
        String parentName = " 回复 " + value.parent_account.username;
        SpannableString ss = new SpannableString(value.account.username + parentName);

        ss.setSpan(new TextSpanClick(true), 0, value.account.username.length(),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new TextSpanClick(false), value.account.username.length() + 4,
            value.account.username.length() + value.parent_account.username.length() + 4,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new ForegroundColorSpan(mContent.getResources().getColor(R.color.colorPrimary)),
            0, value.account.username.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new ForegroundColorSpan(mContent.getResources().getColor(R.color.colorPrimary)),
            value.account.username.length() + 4,
            value.parent_account.username.length() + 4 + value.account.username.length(),
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mWhoToWho.setText(ss);
      }
    }
    mContent.setText(value.content != null ? value.content : "");
    mWhoToWho.setMovementMethod(LinkMovementMethod.getInstance());
  }

  class TextSpanClick extends ClickableSpan {

    private boolean status;

    public TextSpanClick(boolean status) {
      this.status = status;
    }

    @Override public void updateDrawState(TextPaint ds) {
      super.updateDrawState(ds);
      ds.setUnderlineText(false);// 取消下划线
    }

    @Override public void onClick(View widget) {
      Account account = AccountManager.getInstance().getAccount(mContext);
      if (status) {
        mNameOnClickListener.sendUser(status,account, mComment.parent_account);
      } else {
        mNameOnClickListener.sendUser(status,account, mComment.account);
      }
    }
  }

  public interface NameOnClickListener {
    void sendUser(boolean status ,Account account, Account parentAccount);
  }

  public void setNameOnClickListener(NameOnClickListener nameOnClickListener) {
    mNameOnClickListener = nameOnClickListener;
  }
}
