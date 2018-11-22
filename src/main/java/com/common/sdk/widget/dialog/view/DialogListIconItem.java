/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.sdk.R;
import com.common.sdk.widget.dialog.data.DialogIconItemData;

/**
 * Created by liang.wu1 on 2017/9/1.
 */

public class DialogListIconItem extends RelativeLayout implements Checkable {
    private ImageView mIconView;
    private TextView mTitleView;
    private TextView mSubTitleView;
    private CheckedTextView mCheckedTextView;

    private Context mContext;

    public DialogListIconItem(Context context) {
        super(context);
        mContext = context;
    }

    public DialogListIconItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public DialogListIconItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mIconView = (ImageView) findViewById(R.id.iv);
        mTitleView = (TextView) findViewById(R.id.title_tv);
        mSubTitleView = (TextView) findViewById(R.id.subtitle_tv);
        mCheckedTextView = (CheckedTextView) findViewById(R.id.checktextview);
    }

    public void bindView(DialogIconItemData data){
        if(data.getIconId() <=0 ){
            if(data.getDrawable() != null){
                mIconView.setImageDrawable(data.getDrawable());
                mIconView.setVisibility(View.VISIBLE);
            } else {
                mIconView.setVisibility(View.GONE);
            }
        } else {
            mIconView.setImageResource(data.getIconId());
            mIconView.setVisibility(View.VISIBLE);
        }

        if(TextUtils.isEmpty(data.getTitle())){
            mTitleView.setVisibility(View.GONE);
        } else {
            mTitleView.setText(data.getTitle());
            mTitleView.setVisibility(View.VISIBLE);
        }

        if(TextUtils.isEmpty(data.getSubTitle())){
            mSubTitleView.setVisibility(View.GONE);
            this.setMinimumHeight(mContext.getResources().getDimensionPixelSize(R.dimen.dialog_listitem_height));
        } else {
            mSubTitleView.setText(data.getSubTitle());
            mSubTitleView.setVisibility(View.VISIBLE);
            this.setMinimumHeight(mContext.getResources().getDimensionPixelSize(R.dimen.listitem_twoline_height));
        }
    }

    @Override
    public void setChecked(boolean checked) {
        if (mCheckedTextView != null){
            mCheckedTextView.setChecked(checked);
        }
    }

    @Override
    public boolean isChecked() {
        if(mCheckedTextView != null){
            return mCheckedTextView.isChecked();
        }
        return false;
    }

    @Override
    public void toggle() {
        if(mCheckedTextView != null){
            setChecked(!isChecked());
        }
    }

    public ImageView getIconView() {
        return mIconView;
    }

    public TextView getTitleView() {
        return mTitleView;
    }

    public TextView getSubTitleView() {
        return mSubTitleView;
    }

    public CheckedTextView getCheckedTextView() {
        return mCheckedTextView;
    }
}
