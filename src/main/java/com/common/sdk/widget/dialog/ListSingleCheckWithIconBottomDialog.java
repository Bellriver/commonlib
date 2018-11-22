/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import com.common.sdk.R;
import com.common.sdk.widget.dialog.data.DialogIconItemData;
import com.common.sdk.widget.dialog.view.DialogListIconItem;

import java.util.List;

/**
 * Created by liang.wu1 on 2017/8/17.
 */

public class ListSingleCheckWithIconBottomDialog extends ListSingleCheckBottomDialog {

    public ListSingleCheckWithIconBottomDialog(Context context) {
        super(context);
    }

    public static ListSingleCheckWithIconBottomDialog createIconListDialog(Context context, List<DialogIconItemData> items,
                                                                           int checkItem,
                                                                           CharSequence mTitle, OnDialogItemSelectListener selectListener){
        ListSingleCheckWithIconBottomDialog dialog = new ListSingleCheckWithIconBottomDialog(context);
        ArrayAdapter adapter = new CheckedItemAdapter(context, items);
        dialog.updateTitleAndDivider(mTitle);
        dialog.mListView.setAdapter(adapter);
        dialog.updateTitleViewLayoutParams();
        dialog.setSelectListener(selectListener);
        dialog.setCheckItem(checkItem);
        return dialog;
    }

    public static ListSingleCheckWithIconBottomDialog showIconListDialog(Context context, List<DialogIconItemData> items,
                                                                         int checkItem,
                                                                         CharSequence mTitle, OnDialogItemSelectListener selectListener){
        ListSingleCheckWithIconBottomDialog dialog = createIconListDialog(context, items, checkItem, mTitle, selectListener);
        dialog.show();
        return dialog;
    }

    public static ListSingleCheckWithIconBottomDialog createIconListDialog(Context context, BaseAdapter adapter,
                                                                           int checkItem,
                                                                           CharSequence mTitle, OnDialogItemSelectListener selectListener){
        ListSingleCheckWithIconBottomDialog dialog = new ListSingleCheckWithIconBottomDialog(context);
        dialog.updateTitleAndDivider(mTitle);
        dialog.mListView.setAdapter(adapter);
        dialog.updateTitleViewLayoutParams();
        dialog.setSelectListener(selectListener);
        dialog.setCheckItem(checkItem);
        return dialog;
    }

    public static ListSingleCheckWithIconBottomDialog showIconListDialog(Context context, BaseAdapter adapter,
                                                                         int checkItem,
                                                                         CharSequence mTitle, OnDialogItemSelectListener selectListener){
        ListSingleCheckWithIconBottomDialog dialog = createIconListDialog(context, adapter, checkItem, mTitle, selectListener);
        dialog.show();
        return dialog;
    }

    private static class CheckedItemAdapter extends ArrayAdapter<DialogIconItemData> {
        private LayoutInflater mInflater;

        public CheckedItemAdapter(Context context, List datas) {
            super(context, -1, datas);
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            DialogIconItemData data = getItem(position);
            if(data == null) return null;
            View view;
            if (convertView == null) {
                view = newView(parent);
            } else {
                view = convertView;
            }
            if(view instanceof DialogListIconItem){
                DialogListIconItem listIconItem = (DialogListIconItem) view;
                listIconItem.bindView(data);
            }
            return view;
        }

        private View newView(ViewGroup parent){
            return mInflater.inflate( R.layout.dialog_icon_singlechoice_item, parent, false);
        }
    }
}
