package com.xdhywj.commonmenu.common;

import android.content.Context;

import com.xdhywj.commonmenu.R;
import com.xdhywj.commonmenu.module.CommonMenuItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xdhywj on 17/5/1.
 */

public class CommonMenuBuilder {

    public static final String JUMP_URL_INDEX = "imeituan://www.meituan.com/home";
    public static final String JUMP_URL_SEARCH = "imeituan://www.meituan.com/search/home?entrance=7";
    public static final String JUMP_URL_ORDER = "imeituan://www.meituan.com/ordercenterlist";
    public static final String JUMP_URL_FAVORITE = "imeituan://www.meituan.com/collection/list";

    private Context mContext;
    private List<CommonMenuItem> mMenuItemList = new ArrayList<>();

    public CommonMenuBuilder(Context context) {
        this.mContext = context;
    }

    public CommonMenuBuilder appendIndex() {
        CommonMenuItem item = new CommonMenuItem(mContext.getResources().getDrawable(R.drawable.commonmenu_index_icon),
                mContext.getString(R.string.commonmenu_item_index), JUMP_URL_INDEX);
        mMenuItemList.add(item);
        return this;
    }

    public CommonMenuBuilder appendSearch() {
        CommonMenuItem item = new CommonMenuItem(mContext.getResources().getDrawable(R.drawable.commonmenu_search_icon),
                mContext.getString(R.string.commonmenu_item_search), JUMP_URL_SEARCH);
        mMenuItemList.add(item);
        return this;
    }

    public CommonMenuBuilder appendOrder() {
        CommonMenuItem item = new CommonMenuItem(mContext.getResources().getDrawable(R.drawable.commonmenu_order_icon),
                mContext.getString(R.string.commonmenu_item_order), JUMP_URL_ORDER);
        mMenuItemList.add(item);
        return this;
    }

    public CommonMenuBuilder appendFavorite() {
        CommonMenuItem item = new CommonMenuItem(mContext.getResources().getDrawable(R.drawable.commonmenu_favorite_icon),
                mContext.getString(R.string.commonmenu_item_favorite), JUMP_URL_FAVORITE);
        mMenuItemList.add(item);
        return this;
    }

    public CommonMenuBuilder appendCustomized(CommonMenuItem item) {
        if (item != null) {
            mMenuItemList.add(item);
        }
        return this;
    }

    public CommonMenuBuilder appendCustomizedList(List<CommonMenuItem> items) {
        if (items != null && items.size() != 0) {
            mMenuItemList.addAll(items);
        }
        return this;
    }

    public List<CommonMenuItem> builder() {
        return mMenuItemList;
    }
}
