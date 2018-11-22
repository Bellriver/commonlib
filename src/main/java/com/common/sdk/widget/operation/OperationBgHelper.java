/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.operation;

import android.view.View;

import com.common.sdk.widget.ViewBgHelper;

/**
 * Created by liang.wu1 on 2017/8/23.
 */

public class OperationBgHelper {
    public static void setBg(View view){
        view.setBackground(ViewBgHelper.createForOperationItem(view));
    }
}
