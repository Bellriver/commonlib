/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget;

import android.content.res.Resources;
import android.graphics.Color;
import android.view.View;

import com.common.sdk.R;
import com.common.sdk.widget.drawable.CustomePressedDrawable;

/**
 * Created by liang.wu1 on 2017/7/24.
 */

public class ViewBgHelper {
    public static CustomePressedDrawable create(View view){
        Resources resources = view.getContext().getResources();
        return new CustomePressedDrawable(view, CustomePressedDrawable.BG_RECT_FOREBG_RECT,
                -1,
                resources.getColor(R.color.btn_bg_normal_color),
                Color.BLACK,
                resources.getInteger(R.integer.operation_item_bg_animation_duration),
                resources.getInteger(R.integer.operation_item_bg_animation_startalpha),
                resources.getInteger(R.integer.operation_item_bg_animation_endalpha)
        );
    }

    public static CustomePressedDrawable createForOperationItem(View view){
        Resources resources = view.getContext().getResources();
        return new CustomePressedDrawable(view, CustomePressedDrawable.BG_RECT_FOREBG_CIRCLE,
                resources.getDimensionPixelSize(R.dimen.operation_item_bg_radius),
                resources.getColor(R.color.btn_bg_normal_color),
                resources.getColor(R.color.operation_item_rippleColor),
                resources.getInteger(R.integer.operation_item_bg_animation_duration),
                resources.getInteger(R.integer.operation_item_bg_animation_startalpha),
                resources.getInteger(R.integer.operation_item_bg_animation_endalpha)
        );
    }
}
