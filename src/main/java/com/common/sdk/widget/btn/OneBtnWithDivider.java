/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.btn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import com.common.sdk.R;

/**
 * Created by liang.wu1 on 2017/7/20.
 */

public class OneBtnWithDivider extends LinearLayout {
    private Button mButton;

    public OneBtnWithDivider(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(getContext()).inflate(R.layout.one_btn_with_divider, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mButton = (Button) findViewById(R.id.btn);
        //mButton.setBackground(OssdkViewBgHelper.create(mButton));
    }

    public void initBtn(CharSequence text, OnClickListener listener){
        mButton.setText(text);
        mButton.setOnClickListener(listener);
    }

    public Button getButton() {
        return mButton;
    }
}
