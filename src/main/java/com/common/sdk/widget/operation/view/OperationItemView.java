/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.operation.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.sdk.R;
import com.common.sdk.widget.operation.data.OperationItemData;

/**
 * Created by liang.wu1 on 2017/6/30.
 */

public class OperationItemView extends RelativeLayout {

    private TextView mTv;
    private ImageView mIv;

    public OperationItemView(Context context) {
        super(context);
    }

    public OperationItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public OperationItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mTv = (TextView) findViewById(R.id.item_tv);
        mIv = (ImageView) findViewById(R.id.item_iv);
    }

    public void bindData(OperationItemData data) {
        mTv.setText(data.getTitle());
        mIv.setImageResource(data.getIconId());
    }
}
