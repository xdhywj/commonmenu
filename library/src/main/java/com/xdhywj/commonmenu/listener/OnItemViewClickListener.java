package com.xdhywj.commonmenu.listener;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;

import com.xdhywj.commonmenu.common.CommonMenuBuilder;
import com.xdhywj.commonmenu.module.CommonMenuItem;
import com.xdhywj.commonmenu.util.CommonMenuUtils;

/**
 * Created by xdhywj on 17/5/1.
 */

public class OnItemViewClickListener implements View.OnClickListener {

    private int mIndex;
    private String mPageName;
    private Context mContext;
    private CommonMenuItem mMenuItem;
    private ICommonMenuDismissListener mDismissListener;

    public OnItemViewClickListener(Context context, String pageName, int index, CommonMenuItem menuItem, ICommonMenuDismissListener dismissListener) {
        mIndex = index;
        mContext = context;
        mPageName = pageName;
        mMenuItem = menuItem;
        mDismissListener = dismissListener;
    }

    @Override
    public void onClick(View v) {
        if (mDismissListener != null) {
            mDismissListener.onDismiss();
        }
        if (mMenuItem == null) {
            return;
        }
        if (!TextUtils.isEmpty(mMenuItem.url)) {
            Uri uri = null;
            try {
                uri = Uri.parse(mMenuItem.url);
                if (uri != null) {
                    Intent intent = CommonMenuUtils.fromUri(uri);
                    if (TextUtils.equals(mMenuItem.url, CommonMenuBuilder.JUMP_URL_INDEX)) {
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    }
                    mContext.startActivity(intent);
                }
            } catch (Exception e) {
            }
        } else {
            if (mMenuItem.listener != null) {
                mMenuItem.listener.onItemMenuClick();
            }
        }
    }
}
