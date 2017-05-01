package com.xdhywj.commonmenu.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.util.AttributeSet;
import android.view.View;

import com.xdhywj.commonmenu.R;
import com.xdhywj.commonmenu.common.CommonMenuListManager;
import com.xdhywj.commonmenu.common.CommonMenuPopWindowManager;
import com.xdhywj.commonmenu.module.CommonMenuItem;
import com.xdhywj.commonmenu.util.CommonMenuUtils;

import java.util.List;

/**
 * Created by xdhywj on 17/5/1.
 */

public class CommonMenuItemView extends View implements View.OnClickListener{

    private Bitmap mBitmap;
    private Paint mPaint;
    private Matrix mMatrix;
    private Context mContext;
    private int mWidth;
    private int mHeight;
    private CommonMenuPopWindowManager mPopupWindowManager;
    private static final int VIEW_WIDTH = 24;
    private static final int VIEW_HEIGHT = 24;

    public CommonMenuItemView(Context context) {
        this(context, null);
    }

    public CommonMenuItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonMenuItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CommonMenuItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        mContext = context;
        mPaint = new Paint();
        mMatrix = new Matrix();
        setOnClickListener(this);
        mPopupWindowManager = new CommonMenuPopWindowManager(context);
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.commonmenu_normal_more_icon);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            mWidth = widthSize;
        } else {
            mWidth = CommonMenuUtils.dip2px(getContext(), VIEW_WIDTH);
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            mHeight = heightSize;
        } else {
            mHeight = CommonMenuUtils.dip2px(getContext(), VIEW_HEIGHT);
        }
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBitmap, mMatrix, mPaint);
    }

    @Override
    public void onClick(View v) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.showPopupWindow(v);
        }
    }

    private void setmBitmap(@DrawableRes int drawableResId) {
        if (drawableResId != 0) {
            mBitmap = BitmapFactory.decodeResource(getResources(), drawableResId);
        }
    }

    public void setDefaultPopupMenu(String pageName) {
        setDefaultPopupMenu(pageName, 0);
    }

    /**
     * 业务方可以自己设置点击的图片
     * @param pageName
     * @param drawableResId
     */
    public void setDefaultPopupMenu(String pageName, @DrawableRes int drawableResId) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.setParams(pageName, CommonMenuListManager.getCommonMenuList(mContext, CommonMenuListManager.STYLE_DEFAULT));
        }
        setmBitmap(drawableResId);
    }

    public void setTakeoutPopupMenu(String pageName) {
        setTakeoutPopupMenu(pageName, 0);
    }

    /**
     * 业务方可以自己设置点击的图片
     * @param pageName
     * @param drawableResId
     */
    public void setTakeoutPopupMenu(String pageName, @DrawableRes int drawableResId) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.setParams(pageName, CommonMenuListManager.getCommonMenuList(mContext, CommonMenuListManager.STYLE_TAKEOUT));
        }
        setmBitmap(drawableResId);
    }

    public void setCustomizedPopupMenu(String pageName, CommonMenuItem item) {
        setCustomizedPopupMenu(pageName, item, 0);
    }

    /**
     * 业务方可以自己设置点击的图片
     * @param pageName
     * @param item
     * @param drawableResId
     */
    public void setCustomizedPopupMenu(String pageName, CommonMenuItem item, @DrawableRes int drawableResId) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.setParams(pageName, CommonMenuListManager.getCommonMenuList(mContext, CommonMenuListManager.STYLE_CUSTOMIZED_ONE, item));
        }
        setmBitmap(drawableResId);
    }

    public void setCustomizedListPopupMenu(String pageName, List<CommonMenuItem> items) {
        setCustomizedListPopupMenu(pageName, items, 0);
    }

    /**
     * 业务方可以自己设置点击的图片
     * @param pageName
     * @param items
     * @param drawableResId
     */
    public void setCustomizedListPopupMenu(String pageName, List<CommonMenuItem> items, @DrawableRes int drawableResId) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.setParams(pageName, CommonMenuListManager.getCommonMenuList(mContext, CommonMenuListManager.STYLE_CUSTOMIZED_LIST, items));
        }
        setmBitmap(drawableResId);
    }
}
