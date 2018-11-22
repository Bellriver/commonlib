/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.operation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.common.sdk.widget.ViewBgHelper;
import com.common.sdk.R;
import com.common.sdk.widget.operation.data.OperationItemData;
import com.common.sdk.widget.operation.view.OperationItemView;

import java.util.List;

/**
 * Created by liang.wu1 on 2017/6/30.
 */

public class OperationWindowHelper {
    private PopupWindow mPopupWindow;

    public PopupWindow createPopupWindow(Context context, List<OperationItemData> datas, OnItemClickListener listener){
        View view = LayoutInflater.from(context).inflate(R.layout.operation_window, null);
        ViewGroup rootView = (ViewGroup) view.findViewById(R.id.container);
        int size = datas.size();
        boolean isNeedOmit = size > 4;
        for(int i = 0; i < size; i++){
            if(isNeedOmit && i == 3){
                break;
            }

            rootView.addView(createChildView(context, datas.get(i), listener, rootView));
        }
        if(isNeedOmit){
            OperationItemData data = new OperationItemData(3, context.getString(R.string.more), R.drawable.ic_more);
            rootView.addView(createChildView(context, data, null, rootView));
        }
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.Operation_Animation);
        return mPopupWindow;
    }

    private View createChildView(Context context, final OperationItemData data, final OnItemClickListener listener, ViewGroup parentView){
        OperationItemView itemView = (OperationItemView)LayoutInflater.from(context).inflate(R.layout.operation_item, parentView, false);
        itemView.bindData(data);
        itemView.setEnabled(data.isIsEnable());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.isIsMoreItem()){
                    //do some
                } else{
                    if(listener != null){
                        listener.onItemClick(data.getIndex());
                    }
                }
                if(mPopupWindow != null && mPopupWindow.isShowing()){
                    mPopupWindow.dismiss();
                }
            }
        });
        itemView.setBackground(ViewBgHelper.createForOperationItem(itemView));
        return itemView;
    }

    public interface OnItemClickListener{
        void onItemClick(int index);
    }
}
