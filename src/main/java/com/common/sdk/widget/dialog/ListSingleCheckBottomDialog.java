/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.common.sdk.R;

import java.util.List;

/**
 * Created by liang.wu1 on 2017/8/17.
 */

public class ListSingleCheckBottomDialog extends ListBottomDialog implements AdapterView.OnItemClickListener {

    public ListSingleCheckBottomDialog(Context context) {
        super(context);
    }

    @Override
    public void initListView(){
        super.initListView();
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    public void setCheckItem(int checkItem){
        if (checkItem > -1) {
            mListView.setItemChecked(checkItem, true);
            mListView.setSelection(checkItem);
        }
    }
    public void setCheckItemNoSelection(int checkItem){
        if (checkItem > -1) {
            mListView.setItemChecked(checkItem, true);
//            mListView.setSelection(checkItem);
        }
    }

    public static ListSingleCheckBottomDialog createDialog(Context context, CharSequence[] items, CharSequence mTitle,
                                                           int checkItem, OnDialogItemSelectListener selectListener){
        ListSingleCheckBottomDialog dialog = new ListSingleCheckBottomDialog(context);
        ArrayAdapter adapter = new CheckedItemAdapter(context, R.layout.dialog_singlechoice_item, android.R.id.text1, items);
        dialog.updateTitleAndDivider(mTitle);
        dialog.mListView.setAdapter(adapter);
        dialog.updateTitleViewLayoutParams();
        dialog.setSelectListener(selectListener);
        dialog.setCheckItem(checkItem);
        return dialog;
    }

    public static ListSingleCheckBottomDialog showDialog(Context context, CharSequence[] items, CharSequence mTitle,
                                                         int checkItem, OnDialogItemSelectListener selectListener){
        ListSingleCheckBottomDialog dialog = createDialog(context, items, mTitle, checkItem, selectListener);
        dialog.show();
        return dialog;
    }

    public static ListSingleCheckBottomDialog createDialog(Context context, List<String> items, CharSequence mTitle,
                                                           String checkItemStr, OnDialogItemSelectListener selectListener){
        ListSingleCheckBottomDialog dialog = new ListSingleCheckBottomDialog(context);
        ArrayAdapter adapter = new CheckedItemAdapter2(context, R.layout.dialog_singlechoice_item,
                android.R.id.text1, items);
        dialog.updateTitleAndDivider(mTitle);
        dialog.mListView.setAdapter(adapter);
        dialog.updateTitleViewLayoutParams();
        dialog.setSelectListener(selectListener);
        int checkItem = -1;
        if(!TextUtils.isEmpty(checkItemStr) && items != null && items.size() > 0){
            checkItem = items.indexOf(checkItemStr);
        }
        dialog.setCheckItem(checkItem);
        return dialog;
    }
    public static ListSingleCheckBottomDialog createTitleDialog(Context context, String lateTitle, List<String> lateItems, String listTitle, List<String> items, CharSequence mTitle,
                                                                String checkItemStr, OnDialogItemSelectListener selectListener){
        ListSingleCheckBottomDialog dialog = new ListSingleCheckBottomDialog(context);

        TitleCheckedItemAdapter adapter = new TitleCheckedItemAdapter(context,lateItems,items,lateTitle,listTitle);
        dialog.updateTitleAndDivider(mTitle);
        dialog.mListView.setAdapter(adapter);
        dialog.updateTitleViewLayoutParams();
        dialog.setSelectListener(selectListener);
        int checkItem = -1;
        if(!TextUtils.isEmpty(checkItemStr) && items != null && items.size() > 0){
            if (lateItems != null && lateItems.size() > 0){
                checkItem = lateItems.indexOf(checkItemStr);
                if (checkItem == -1){
                    checkItem = items.indexOf(checkItemStr);
                    checkItem += lateItems.size()+1;
                }else{
                    checkItem += 1;
                }
            }else{
                checkItem = items.indexOf(checkItemStr);
            }


        }
        if (lateItems == null || lateItems.size() == 0) {
            dialog.setCheckItem(checkItem);
        }else{
            dialog.setCheckItemNoSelection(checkItem);
        }
        return dialog;
    }

    public static ListSingleCheckBottomDialog showDialog(Context context, List<String> items, CharSequence mTitle,
                                                         String checkItemStr, OnDialogItemSelectListener selectListener){
        ListSingleCheckBottomDialog dialog = createDialog(context, items, mTitle, checkItemStr, selectListener);
        dialog.show();
        return dialog;
    }
    public static ListSingleCheckBottomDialog showTitleDialog(Context context, String lateTitle, List<String> lateItems, String listTitle, List<String> items, CharSequence mTitle,
                                                              String checkItemStr, OnDialogItemSelectListener selectListener){
        ListSingleCheckBottomDialog dialog = createTitleDialog(context,lateTitle,lateItems,listTitle, items, mTitle, checkItemStr, selectListener);
        dialog.show();
        return dialog;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(mSelectListener != null){
            mSelectListener.onDialogItemSelect(position,parent.getAdapter().getItem(position));
        }
        dismissDialog();
    }

    private static class CheckedItemAdapter extends ArrayAdapter<CharSequence> {
        public CheckedItemAdapter(Context context, int resource, int textViewResourceId,
                                  CharSequence[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
    }

    private static class CheckedItemAdapter2 extends ArrayAdapter<String> {

        public CheckedItemAdapter2(Context context, int resource, int textViewResourceId,
                                  List<String> objects) {
            super(context, resource, textViewResourceId, objects);
        }
        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }
    private static class TitleCheckedItemAdapter extends BaseAdapter {
        private final LayoutInflater inflater;
        private Context mContext;
        private List<String> lateList;
        private List<String> allList;
        private String lateTitle;
        private String listTitle;

        private final String TITLE = "Title";

        private static class ViewHolder {
            TextView  ContentTv;
        }

        public TitleCheckedItemAdapter(Context context,List<String> lateList,List<String> allList,String lateTitle,String listTitle) {
            mContext = context;
            this.lateList = lateList;
            this.allList = allList;
            this.lateTitle = lateTitle;
            this.listTitle = listTitle;
            inflater = LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            if (lateList != null){
                return lateList.size() + allList.size();
            }else{
                return allList.size();
            }
        }

        @Override
        public Object getItem(int position) {
            String item = "";
            if (lateList == null || lateList.size() == 0) {
                item = allList.get(position);
            }else if(position != 0 && position <= lateList.size()){
                item = lateList.get(position - 1);
            }else if (position + 1 > lateList.size()){
                item = allList.get(position - lateList.size()- 2);
            }
            Log.d("","position = " +  position + "item = " + item);
            return item;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null || !(convertView.getTag() instanceof ViewHolder)){
                viewHolder = new ViewHolder();
                convertView = inflater.inflate(R.layout.dialog_singlechoice_item,null);
                viewHolder.ContentTv = convertView.findViewById(android.R.id.text1);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }
            if (lateList != null && lateList.size() != 0 && position <= lateList.size()){
                if (position != 0){
                    viewHolder.ContentTv.setText(lateList.get(position - 1));
                    return convertView;
                }
            }else {
                if (lateList == null || lateList.size() == 0){
                    viewHolder.ContentTv.setText(allList.get(position));
                    return convertView;
                }else if (position != lateList.size() + 1){
                    viewHolder.ContentTv.setText(allList.get(position - lateList.size() - 2));
                    return convertView;
                }
            }
            String titelText ;
            if (position == 0){
                titelText = lateTitle;
            }else{
                titelText = listTitle;
            }
            convertView = inflater.inflate(R.layout.dialog_singlechoice_title_item,null);
            TextView textView = convertView.findViewById(R.id.text_title);
            textView.setText(titelText);
            textView.setOnClickListener(null);
            convertView.setTag(TITLE);
            return convertView;
        }
    }

    public void dismissDialog(){
        mListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mContext != null){
                    if(isFinished()){
                        return;
                    }
                    if(ListSingleCheckBottomDialog.this.isShowing()){
                        ListSingleCheckBottomDialog.this.dismiss();
                    }
                }
            }
        }, 360);
    }

    private boolean  isFinished(){
        if(mContext instanceof Activity){
            Activity activity = (Activity) mContext;
            return activity.isFinishing() || activity.isDestroyed();
        }
        return false;
    }
    public static boolean dialogIsShow(Dialog dialog){

        if (dialog != null && dialog.isShowing()){
            Log.d("BottomDialog","dialogIsShow " + true);
            return true;
        }

        return  false;
    }
}
