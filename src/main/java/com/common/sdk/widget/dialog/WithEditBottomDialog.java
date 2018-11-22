/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.common.sdk.R;
import com.common.sdk.widget.dialog.view.DialogBtnContainer;
import com.common.sdk.widget.dialog.view.DialogCustomeEditContainer;

/**
 * Created by liang.wu1 on 2017/8/17.
 */

public class WithEditBottomDialog extends BottomDialog {

    private DialogCustomeEditContainer mEditContainer;

    public WithEditBottomDialog(Context context) {
        super(context, R.layout.with_edit_bottom_dialog);
        initEditContainer();
    }

    private void initEditContainer(){
        mEditContainer = (DialogCustomeEditContainer)mRootView.findViewById(R.id.dialog_edit_container);
    }

    public static WithEditBottomDialog createDialog(Context context, CharSequence title,
                                                    CharSequence iputHint, CharSequence secondTitle,
                                                    CharSequence cancelTitle, CharSequence positiveTitle,
                                                    DialogBtnContainer.DialogBtnClickListener cancelBtnClickListener,
                                                    DialogBtnContainer.DialogBtnClickListener positiveBtnClickListener
                                                            ){
        WithEditBottomDialog dialog = new WithEditBottomDialog(context);
        dialog.setCustomeTitle(title);
        dialog.updateDividerVisibility(View.GONE);
        dialog.initEidtContainer(iputHint, secondTitle);
        dialog.initBtnContainer(cancelTitle, positiveTitle, cancelBtnClickListener, positiveBtnClickListener);
        return dialog;
    }

    public static WithEditBottomDialog showDialog(Context context, CharSequence title,
                                                  CharSequence iputHint, CharSequence secondTitle,
                                                  CharSequence cancelTitle, CharSequence positiveTitle,
                                                  DialogBtnContainer.DialogBtnClickListener cancelBtnClickListener,
                                                  DialogBtnContainer.DialogBtnClickListener positiveBtnClickListener){
        WithEditBottomDialog dialog = createDialog(context, title, iputHint, secondTitle, cancelTitle, positiveTitle,
                    cancelBtnClickListener, positiveBtnClickListener);
        dialog.show();
        return dialog;
    }

    private void initEidtContainer(CharSequence iputHint, CharSequence secondTitle) {
        mEditContainer.init(iputHint, secondTitle);
    }

    public EditText getEditText(){
        if(mEditContainer != null){
            return mEditContainer.getEditText();
        }
        return null;
    }
}
