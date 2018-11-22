/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog.data;

import android.graphics.drawable.Drawable;

/**
 * Created by liang.wu1 on 2017/8/31.
 */

public class DialogIconItemData {
    private int mIconId;
    private CharSequence mTitle;
    private CharSequence mSubTitle;
    private boolean mIsChecked;
    private Drawable mDrawable;

    public DialogIconItemData(int iconId, CharSequence title, CharSequence subTitle, boolean isChecked) {
        this.mIconId = iconId;
        this.mDrawable = null;
        this.mTitle = title;
        this.mSubTitle = subTitle;
        this.mIsChecked = isChecked;
    }

    public DialogIconItemData(Drawable drawable, CharSequence title, CharSequence subTitle, boolean isChecked) {
        this.mIconId = -1;
        this.mDrawable = drawable;
        this.mTitle = title;
        this.mSubTitle = subTitle;
        this.mIsChecked = isChecked;
    }

    public int getIconId() {
        return mIconId;
    }

    public CharSequence getTitle() {
        return mTitle;
    }

    public CharSequence getSubTitle() {
        return mSubTitle;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.mIsChecked = isChecked;
    }

    public Drawable getDrawable() {
        return mDrawable;
    }
}
