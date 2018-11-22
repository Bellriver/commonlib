/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.common.sdk.R;
import com.common.sdk.widget.dialog.view.DialogBtnContainer;
import com.common.sdk.widget.dialog.view.DialogCustomeContentContainer;

/**
 * Created by liang.wu1 on 2017/8/17.
 */

public class ContentBottomDialog extends BottomDialog {
    private DialogCustomeContentContainer mContentContainer;

    public ContentBottomDialog(Context context) {
        super(context, R.layout.content_dialog);
        init();
    }

    private void init(){
        mContentContainer = (DialogCustomeContentContainer) mRootView.findViewById(R.id.dialog_custome_content_container);
    }

    public static ContentBottomDialog createDialog(Context context, CharSequence title, CharSequence content,
                                                   CharSequence cancelTitle, CharSequence positiveTitle,
                                                   DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                   DialogBtnContainer.DialogBtnClickListener positiveListener){
        ContentBottomDialog dialog = showDialogBase(context, title);
        dialog.initContentContainter(content);
        dialog.initBtnContainer(cancelTitle, positiveTitle, cancelListener, positiveListener);
        return dialog;
    }

    public static ContentBottomDialog showDialog(Context context, CharSequence title, CharSequence content,
                                                 CharSequence cancelTitle, CharSequence positiveTitle,
                                                 DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                 DialogBtnContainer.DialogBtnClickListener positiveListener) {
        ContentBottomDialog dialog = createDialog(context, title, content, cancelTitle, positiveTitle, cancelListener, positiveListener);
        dialog.show();
        return dialog;
    }

    public static ContentBottomDialog showDialogSelfUpdate(final Context context, CharSequence title, CharSequence content,
                                                           CharSequence cancelTitle, CharSequence positiveTitle,
                                                           DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                           DialogBtnContainer.DialogBtnClickListener positiveListener) {
        ContentBottomDialog dialog = createDialog(context, title, content, cancelTitle, positiveTitle, cancelListener, positiveListener);
        dialog.setCancelable(false);
        dialog.show();
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    /*Intent mIntent = new Intent();
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    ComponentName comp = new ComponentName("com.android.launcher3", "com.android.launcher3.Launcher");
                    mIntent.setComponent(comp);
                    mIntent.setAction(Intent.ACTION_DEFAULT);
                    startActivity(mIntent);*/
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    context.startActivity(intent);

                }
                return false;
            }
        });
        return dialog;
    }

    public static ContentBottomDialog showServiceDialog(Context context, CharSequence title, CharSequence content,
                                                        CharSequence cancelTitle, CharSequence positiveTitle,
                                                        DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                        DialogBtnContainer.DialogBtnClickListener positiveListener) {
        ContentBottomDialog dialog = createDialog(context, title, content, cancelTitle, positiveTitle, cancelListener, positiveListener);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        return dialog;
    }

    public static ContentBottomDialog showForceUpdateServiceDialog(final Context context, CharSequence title, CharSequence content,
                                                                   CharSequence cancelTitle, CharSequence positiveTitle,
                                                                   DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                                   DialogBtnContainer.DialogBtnClickListener positiveListener) {
        ContentBottomDialog dialog = createDialog(context, title, content, cancelTitle, positiveTitle, cancelListener, positiveListener);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    context.startActivity(intent);
                }
                return false;
            }
        });
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }

    public static ContentBottomDialog showSelfUpdateServiceDialog(Context context, CharSequence title, CharSequence content,
                                                                  CharSequence cancelTitle, CharSequence positiveTitle,
                                                                  DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                                  DialogBtnContainer.DialogBtnClickListener positiveListener) {
        ContentBottomDialog dialog = createDialog(context, title, content, cancelTitle, positiveTitle, cancelListener, positiveListener);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }


    public static ContentBottomDialog createDialog(Context context, CharSequence title, CharSequence content,
                                                   CharSequence cancelTitle, CharSequence positiveTitle, CharSequence firstTitle,
                                                   DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                   DialogBtnContainer.DialogBtnClickListener positiveListener,
                                                   DialogBtnContainer.DialogBtnClickListener firstListener){
        ContentBottomDialog dialog = showDialogBase(context, title);
        dialog.initContentContainter(content);
        dialog.initBtnContainer(cancelTitle, positiveTitle, firstTitle, cancelListener, positiveListener, firstListener);
        return dialog;
    }

    public static ContentBottomDialog showDialog(Context context, CharSequence title, CharSequence content,
                                                 CharSequence cancelTitle, CharSequence positiveTitle, CharSequence firstTitle,
                                                 DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                 DialogBtnContainer.DialogBtnClickListener positiveListener,
                                                 DialogBtnContainer.DialogBtnClickListener firstListener){
        ContentBottomDialog dialog = createDialog(context, title, content, cancelTitle, positiveTitle, firstTitle, cancelListener,
                positiveListener, firstListener);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        return dialog;
    }

    public static ContentBottomDialog createDialog(Context context, CharSequence title, CharSequence content,
                                                   int iconId,
                                                   CharSequence cancelTitle, CharSequence positiveTitle,
                                                   DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                   DialogBtnContainer.DialogBtnClickListener positiveListener){
        ContentBottomDialog dialog = showDialogBase(context, title);
        dialog.initContentContainter(content, iconId);
        dialog.initBtnContainer(cancelTitle, positiveTitle, cancelListener, positiveListener);
        return dialog;
    }

    public static ContentBottomDialog showDialog(Context context, CharSequence title, CharSequence content,
                                                 int iconId,
                                                 CharSequence cancelTitle, CharSequence positiveTitle,
                                                 DialogBtnContainer.DialogBtnClickListener cancelListener,
                                                 DialogBtnContainer.DialogBtnClickListener positiveListener){
        ContentBottomDialog dialog = createDialog(context, title, content, iconId, cancelTitle, positiveTitle, cancelListener, positiveListener);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.show();
        return dialog;
    }

    private static ContentBottomDialog showDialogBase(Context context, CharSequence title){
        ContentBottomDialog dialog = new ContentBottomDialog(context);
        dialog.setCustomeTitle(title);
        dialog.updateDividerVisibility(View.GONE);
        return dialog;
    }

    private void initContentContainter(CharSequence content){
        mContentContainer.init(content);
    }

    private void initContentContainter(CharSequence content, int iconId){
        mContentContainer.init(content, iconId);
    }
}
