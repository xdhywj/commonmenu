package com.xdhywj.commonmenu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.xdhywj.commonmenu.common.CommonMenuListManager;
import com.xdhywj.commonmenu.common.CommonMenuPopWindowManager;
import com.xdhywj.commonmenu.module.CommonMenuItem;

import java.util.List;

/**
 * Created by xdhywj on 17/5/1.
 */

public class CommonMenuActionProvider extends ActionProvider implements View.OnClickListener {

    private Context mContext;
    private ImageView mMoreImg;
    private Drawable mMoreDrawable;
    private CommonMenuPopWindowManager mPopupWindowManager;

    /**
     * Creates a new instance.
     *
     * @param context Context for accessing resources.
     */
    public CommonMenuActionProvider(Context context) {
        super(context);
        mContext = context;
        mPopupWindowManager = new CommonMenuPopWindowManager(context);
    }

    @Override
    public View onCreateActionView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.commonmenu_more_layout, null);
        mMoreImg = (ImageView) view.findViewById(R.id.commonmenu_more_btn);
        if (mMoreDrawable != null) {
            mMoreImg.setImageDrawable(mMoreDrawable);
        }
        mMoreImg.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.showPopupWindow(v);
        }
    }

    private void setmMoreImgDrawable(Drawable drawable) {
        this.mMoreDrawable = drawable;
    }

    public void setDefaultPopupMenu(String pageName) {
        setDefaultPopupMenu(pageName, null);
    }

    /**
     * 业务方可以自己设置点击的图片
     * @param pageName pageName
     * @param drawable drawable
     */
    public void setDefaultPopupMenu(String pageName, Drawable drawable) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.setParams(pageName, CommonMenuListManager.getCommonMenuList(mContext, CommonMenuListManager.STYLE_DEFAULT));
        }
        if (drawable != null) {
            setmMoreImgDrawable(drawable);
        }
    }

    public void setTakeoutPopupMenu(String pageName) {
        setTakeoutPopupMenu(pageName, null);
    }

    /**
     * 业务方可以自己设置点击的图片
     * @param pageName pageName
     * @param drawable drawable
     */
    public void setTakeoutPopupMenu(String pageName, Drawable drawable) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.setParams(pageName, CommonMenuListManager.getCommonMenuList(mContext, CommonMenuListManager.STYLE_TAKEOUT));
        }
        if (drawable != null) {
            setmMoreImgDrawable(drawable);
        }
    }


    public void setCustomizedPopupMenu(String pageName, CommonMenuItem item) {
        setCustomizedPopupMenu(pageName, item, null);
    }

    /**
     * 业务方可以自己设置点击的图片
     * @param pageName pageName
     * @param item     item
     * @param drawable drawable
     */
    public void setCustomizedPopupMenu(String pageName, CommonMenuItem item, Drawable drawable) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.setParams(pageName, CommonMenuListManager.getCommonMenuList(mContext, CommonMenuListManager.STYLE_CUSTOMIZED_ONE, item));
        }
        if (drawable != null) {
            setmMoreImgDrawable(drawable);
        }
    }

    public void setCustomizedListPopupMenu(String pageName, List<CommonMenuItem> items) {
        setCustomizedListPopupMenu(pageName, items, null);
    }

    /**
     * 业务方可以自己设置点击的图片
     * @param pageName pageName
     * @param items    items
     * @param drawable drawable
     */
    public void setCustomizedListPopupMenu(String pageName, List<CommonMenuItem> items, Drawable drawable) {
        if (mPopupWindowManager != null) {
            mPopupWindowManager.setParams(pageName, CommonMenuListManager.getCommonMenuList(mContext, CommonMenuListManager.STYLE_CUSTOMIZED_LIST, items));
        }
        if (drawable != null) {
            setmMoreImgDrawable(drawable);
        }
    }
}