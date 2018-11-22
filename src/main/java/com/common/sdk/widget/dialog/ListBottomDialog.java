/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.common.sdk.R;

import java.util.List;

/**
 * Created by liang.wu1 on 2017/8/17.
 */

public class ListBottomDialog extends BottomDialog implements AdapterView.OnItemClickListener {
    public ListView mListView;
    protected OnDialogItemSelectListener mSelectListener;

    public ListBottomDialog(Context context) {
        super(context, R.layout.list_bottom_dialog);
        initListView();
    }

    public void initListView(){
        mListView = (ListView)findViewById(R.id.dialog_listview);
        mListView.setOnItemClickListener(this);
    }

    public void setSelectListener(OnDialogItemSelectListener selectListener) {
        this.mSelectListener = selectListener;
    }

    public static ListBottomDialog createDialog(Context context, List<String> items, CharSequence mTitle,
                                                OnDialogItemSelectListener selectListener){
        ListBottomDialog dialog = new ListBottomDialog(context);
        ArrayAdapter adapter = new ArrayAdapter(context, R.layout.dialog_text_item, android.R.id.text1, items);
        dialog.updateTitleAndDivider(mTitle);
        dialog.mListView.setAdapter(adapter);
        dialog.updateTitleViewLayoutParams();
        dialog.setSelectListener(selectListener);
        return dialog;
    }

    public static ListBottomDialog showDialog(Context context, List<String> items, CharSequence mTitle,
                                              OnDialogItemSelectListener selectListener){
        ListBottomDialog dialog = createDialog(context, items, mTitle, selectListener);
        dialog.show();
        return dialog;
    }

    //do  not set minHeight if the listview exist
    @Override
    public void initMinHeight(View parentPanel, int parentPanelPaddingButtom, int parentPanelPaddingTop) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mSelectListener != null){
            mSelectListener.onDialogItemSelect(position,parent.getAdapter().getItem(position));
        }
        this.dismiss();
    }
}
