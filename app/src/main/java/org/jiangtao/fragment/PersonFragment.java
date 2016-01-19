package org.jiangtao.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jiangtao.application.BlogApplication;
import org.jiangtao.freedomblog.PersonAttentionActivity;
import org.jiangtao.freedomblog.R;
import org.jiangtao.utils_resource.TurnActivity;

/**
 * 个人界面
 */
public class PersonFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = PersonFragment.class.getSimpleName();
    private Context mContext;
    private View mView;
    private RelativeLayout mRelativeLayoutHead;
    private TextView mTextViewAttention;

    public PersonFragment() {
    }

    @SuppressLint("ValidFragment")
    public PersonFragment(Context context) {
        mContext = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_person, container, false);
        initializtionControls();
        addOnClickListener();
        return mView;
    }

    /**
     * 为按钮添加监听器
     */
    private void addOnClickListener() {
        mRelativeLayoutHead.setOnClickListener(this);
        mTextViewAttention.setOnClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initializtionControls() {
        mRelativeLayoutHead = (RelativeLayout) mView.findViewById(R.id.sub_fragment_person_image);
        mTextViewAttention = (TextView) mView.findViewById(R.id.sublayout_textview_attention);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sub_fragment_person_image: {
                clickHeadImage();
                break;
            }
            case R.id.sublayout_textview_attention: {
                Intent intent = new Intent(mContext, PersonAttentionActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.sublayout_textview_article: {

                break;
            }
            case R.id.sublayout_textview_fans: {

                break;
            }
            case R.id.sublayout_person_love: {

                break;
            }
            case R.id.sublayout_person_comment: {

                break;
            }
            case R.id.sublayout_person_collect: {

                break;
            }
            case R.id.sublayout_person_share: {

                break;
            }
        }
    }

    /**
     * 点击头像跳转
     */
    private void clickHeadImage(){
        if (BlogApplication.isLogin){

        }else {
            TurnActivity.turnLoginActivity((AppCompatActivity) mContext);
        }
    }
}
