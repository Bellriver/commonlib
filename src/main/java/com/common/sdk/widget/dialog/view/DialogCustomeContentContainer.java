/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.sdk.R;


/**
 * Created by liang.wu1 on 2017/8/17.
 */

public class DialogCustomeContentContainer extends FrameLayout{
    private TextView mContentTv;
    private ImageView mLeftIconIv;

    public DialogCustomeContentContainer(Context context) {
        super(context);
    }

    public DialogCustomeContentContainer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DialogCustomeContentContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentTv = (TextView) findViewById(R.id.dialog_content_tv);
        mLeftIconIv = (ImageView) findViewById(R.id.dialog_left_iv);
    }

    public void init(CharSequence content){
        mContentTv.setText(content);
        this.post(new Runnable() {
            @Override
            public void run() {
                if (mContentTv.getLineCount() == 1) {
                    mContentTv.setGravity(Gravity.CENTER);
                }
            }
        });

        if(mLeftIconIv != null){
            mLeftIconIv.setVisibility(View.GONE);
        }
    }

    public void init(CharSequence content, int iconId){
        mContentTv.setText(content);
        mLeftIconIv.setImageResource(iconId);
    }

    private String formatStr(String str) {
        if (!TextUtils.isEmpty(str)) {
            char[] c = str.toCharArray();
            for (int i = 0; i < c.length; i++) {
                if (c[i] == 12288) {
                    c[i] = (char) 32;
                    continue;
                }
                if (c[i] > 65280 && c[i] < 65375) {
                    c[i] = (char) (c[i] - 65248);
                }

            }
            return new String(c);
        } else {
            return str;
        }
    }
}
