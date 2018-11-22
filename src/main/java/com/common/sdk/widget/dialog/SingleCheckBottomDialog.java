/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;

import com.common.sdk.R;
import com.common.sdk.widget.dialog.view.DialogBtnContainer;
import com.common.sdk.widget.dialog.view.DialogCustomeSingleCheckContainer;

/**
 * Created by liang.wu1 on 2017/8/17.
 */

public class SingleCheckBottomDialog extends BottomDialog {
    private DialogCustomeSingleCheckContainer mContentContainer;

    public SingleCheckBottomDialog(Context context) {
        super(context, R.layout.single_check_dialog);

        mContentContainer = (DialogCustomeSingleCheckContainer)findViewById(R.id.dialog_custome_single_check_container);
    }

    public static SingleCheckBottomDialog createDialog(Context context, CharSequence title, CharSequence content,
                                                       boolean defaultValue, CharSequence checkTitle,
                                                       CompoundButton.OnCheckedChangeListener listener,
                                                       CharSequence cancelTitle, CharSequence positiveTitle,
                                                       DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                       DialogBtnContainer.DialogBtnClickListener positiveListener){
        SingleCheckBottomDialog dialog = new SingleCheckBottomDialog(context);
        dialog.setCustomeTitle(title);
        dialog.updateDividerVisibility(View.GONE);
        dialog.initContentContainer(content, defaultValue, checkTitle, listener);
        dialog.initBtnContainer(cancelTitle, positiveTitle, cancelListener, positiveListener);
        return dialog;
    }

    public static SingleCheckBottomDialog showDialog(Context context, CharSequence title, CharSequence content,
                                                     boolean defaultValue, CharSequence checkTitle,
                                                     CompoundButton.OnCheckedChangeListener listener,
                                                     CharSequence cancelTitle, CharSequence positiveTitle,
                                                     DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                     DialogBtnContainer.DialogBtnClickListener positiveListener){
        SingleCheckBottomDialog dialog = createDialog(context, title, content, defaultValue, checkTitle, listener, cancelTitle,
                positiveTitle, cancelListener, positiveListener);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        return dialog;
    }


    public static SingleCheckBottomDialog showServiceDialog(Context context, CharSequence title, CharSequence content,
                                                            boolean defaultValue, CharSequence checkTitle,
                                                            CompoundButton.OnCheckedChangeListener listener,
                                                            CharSequence cancelTitle, CharSequence positiveTitle,
                                                            DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                            DialogBtnContainer.DialogBtnClickListener positiveListener) {
        SingleCheckBottomDialog dialog = createDialog(context, title, content, defaultValue, checkTitle, listener, cancelTitle,
                positiveTitle, cancelListener, positiveListener);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        return dialog;
    }

    private void initContentContainer(CharSequence content, boolean defaultValue, CharSequence checkTitle, CompoundButton.OnCheckedChangeListener listener) {
        mContentContainer.init(content, defaultValue, checkTitle, listener);
    }
}
