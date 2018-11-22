/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.operation.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.sdk.R;

/**
 * Created by liang.wu1 on 2017/6/30.
 */

public class OperationView extends RelativeLayout {

    private TextView mTv;
    private ImageView mIv;

    private int mIconResId;
    private int mTitleResId;

    public OperationView(Context context) {
        this(context, null);
    }

    public OperationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OperationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray typedAttributes = context.obtainStyledAttributes(attrs, R.styleable.Operation);
        mIconResId = typedAttributes.getResourceId(R.styleable.Operation_operationItemIcon, -1);
        mTitleResId = typedAttributes.getResourceId(R.styleable.Operation_operationItemTitle, -1);
        typedAttributes.recycle();

        final LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.operation_item_content, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTv = (TextView) findViewById(R.id.item_tv);
        mIv = (ImageView) findViewById(R.id.item_iv);

        if(mIconResId > 0){
            mIv.setImageResource(mIconResId);
        }
        if(mTitleResId > 0){
            mTv.setText(mTitleResId);
        }
        //OperationBgHelper.setBg(this);
    }

    public void updateIcon(int iconId){
        mIv.setImageResource(iconId);
    }

    public void updateTitle(int titleId){
        mTv.setText(titleId);
    }

    public ImageView getIconView(){
        return mIv;
    }

    public TextView getTitleView(){
        return mTv;
    }
}
