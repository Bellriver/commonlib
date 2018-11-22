/* Top Secret, Copyright (C) TRANSSION HOLDINGS */
package com.common.sdk.widget.floatbtn;

import android.animation.AnimatorInflater;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import com.common.sdk.R;

/**
 * Created by liang.wu1 on 2017/7/19.
 */

public class ShadowBgView extends View {
    private int mShadowColor;
    private int mShadowOffsetY;
    private int mShadowRadius;
    private int mMarginLeft;
    private int mCircleRadius;

    private Paint mShadowPaint;

    public ShadowBgView(Context context, FloatBtnData data){
        super(context);
        init(data);
    }

    private void init(FloatBtnData data){
        int floatBtnWidth = data.getFloatBtnWidth();
        mCircleRadius = floatBtnWidth / 2;
        this.mShadowOffsetY = data.getShadowOffsetY();
        this.mShadowColor = data.getShadowColor();
        this.mShadowRadius = data.getShadowRadius();
        mShadowPaint = new Paint();
        mShadowPaint.setColor(mShadowColor);
        mShadowPaint.setMaskFilter(new BlurMaskFilter(mShadowRadius, BlurMaskFilter.Blur.NORMAL));
        setLayerType(LAYER_TYPE_SOFTWARE, mShadowPaint);
        mMarginLeft = data.getMarginLeft();
        this.setStateListAnimator(AnimatorInflater.loadStateListAnimator(this.getContext(), R.animator.btn_shadow_anim));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(mCircleRadius + mMarginLeft, mCircleRadius + 2 + mShadowOffsetY, mCircleRadius, mShadowPaint);
        super.onDraw(canvas);
    }
}
