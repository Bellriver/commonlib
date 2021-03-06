/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.common.sdk.R;

/**
 * Created by liang.wu1 on 2017/8/17.
 */

public class DialogCustomeSingleCheckContainer extends FrameLayout{
    private TextView mContentTv;
    private CheckBox mCheckBox;

    public DialogCustomeSingleCheckContainer(Context context) {
        super(context);
    }

    public DialogCustomeSingleCheckContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DialogCustomeSingleCheckContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentTv = (TextView) findViewById(R.id.dialog_content_tv);
        mCheckBox = (CheckBox) findViewById(R.id.dialog_cb);
    }

    public void init(CharSequence content,
                     boolean defaultValue, CharSequence checkTitle, CompoundButton.OnCheckedChangeListener listener) {
        mContentTv.setText(content);
        mCheckBox.setText(checkTitle);
        mCheckBox.setChecked(defaultValue);
        mCheckBox.setOnCheckedChangeListener(listener);
    }
}
