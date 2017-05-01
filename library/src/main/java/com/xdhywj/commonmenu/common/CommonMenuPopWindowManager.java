package com.xdhywj.commonmenu.common;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.xdhywj.commonmenu.R;
import com.xdhywj.commonmenu.listener.ICommonMenuDismissListener;
import com.xdhywj.commonmenu.module.CommonMenuItem;
import com.xdhywj.commonmenu.util.CommonMenuUtils;
import com.xdhywj.commonmenu.view.CommonMenuPopupView;

import java.util.List;

/**
 * Created by xdhywj on 17/5/1.
 */

public class CommonMenuPopWindowManager implements ICommonMenuDismissListener {

    private Context mContext;
    private String mPageName;
    private PopupWindow mPopupWindow;
    private CommonMenuPopupView mPopupItemView;
    private List<CommonMenuItem> mItemList;
    private static final int POPUP_WINDOW_X_OFFSET = 123;
    private static final int POPUP_WINDOW_SHOW_WIDTH = 150;
    private static final int POPUP_WINDOW_Y_OFFSET = 6;

    public CommonMenuPopWindowManager(Context context) {
        mContext = context;
    }

    public void setParams(String pageName, List<CommonMenuItem> menuItems) {
        mPageName = pageName;
        mItemList = menuItems;
    }

    private void initPopupWindow() {
        mPopupItemView = new CommonMenuPopupView(mContext);
        mPopupItemView.setDismissListener(this);
        mPopupWindow = new PopupWindow(mPopupItemView, CommonMenuUtils.dip2px(mContext, POPUP_WINDOW_SHOW_WIDTH), ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0));
        mPopupWindow.setAnimationStyle(R.style.CommonMenuAnimation);
    }

    public void showPopupWindow(View v) {
        if (mPopupWindow == null || mPopupItemView == null) {
            initPopupWindow();
        }
        mPopupItemView.addPopupItemView(mItemList);
        mPopupItemView.setPageName(mPageName);
        int[] viewLocation = new int[2];
        v.getLocationOnScreen(viewLocation);
        mPopupWindow.showAtLocation(v, 0, viewLocation[0] - CommonMenuUtils.dip2px(mContext, POPUP_WINDOW_X_OFFSET),
                viewLocation[1] + v.getHeight() + CommonMenuUtils.dip2px(mContext, POPUP_WINDOW_Y_OFFSET));
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
    }

    @Override
    public void onDismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }
}
