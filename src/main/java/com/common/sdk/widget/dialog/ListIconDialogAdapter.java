/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.common.sdk.R;
import com.common.sdk.widget.dialog.view.DialogListIconItem;

/**
 * Created by liang.wu1 on 2017/9/1.
 */

public abstract class ListIconDialogAdapter extends BaseAdapter{
    private LayoutInflater mInflater;

    public ListIconDialogAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater.inflate( R.layout.dialog_icon_singlechoice_item, parent, false);
        } else {
            view = convertView;
        }
        if(view instanceof DialogListIconItem){
            DialogListIconItem listIconItem = (DialogListIconItem) view;
            bindView(position, listIconItem);
        }
        return view;
    }

    public abstract void bindView(int position, DialogListIconItem listIconItem);
}
