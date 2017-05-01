package com.xdhywj.commonmenu.common;

import android.content.Context;

import com.xdhywj.commonmenu.module.CommonMenuItem;

import java.util.List;

/**
 * Created by xdhywj on 17/5/1.
 */

public class CommonMenuListManager {

    public static final int STYLE_DEFAULT = 0;
    public static final int STYLE_TAKEOUT = 1;
    public static final int STYLE_CUSTOMIZED_ONE = 2;
    public static final int STYLE_CUSTOMIZED_LIST = 3;

    public static List<CommonMenuItem> getDefaultMenu(Context context) {
        return new CommonMenuBuilder(context).appendIndex().appendSearch().
                appendOrder().appendFavorite().builder();
    }

    public static List<CommonMenuItem> getTakeoutMenu(Context context) {
        return new CommonMenuBuilder(context).appendIndex().appendOrder().
                appendFavorite().builder();
    }

    public static List<CommonMenuItem> getCustomizedMenu(Context context, CommonMenuItem otherMenu) {
        return new CommonMenuBuilder(context).appendIndex().appendSearch().
                appendOrder().appendFavorite().appendCustomized(otherMenu).builder();
    }

    public static List<CommonMenuItem> getCustomizedMenuList(Context context, List<CommonMenuItem> items) {
        return new CommonMenuBuilder(context).appendCustomizedList(items).builder();
    }

    public static List<CommonMenuItem> getCommonMenuList(Context context, int style) {
        return getCommonMenuList(context, style, null, null);
    }

    public static List<CommonMenuItem> getCommonMenuList(Context context, int style, CommonMenuItem item) {
        return getCommonMenuList(context, style, item, null);
    }

    public static List<CommonMenuItem> getCommonMenuList(Context context, int style, List<CommonMenuItem> items) {
        return getCommonMenuList(context, style, null, items);
    }

    private static List<CommonMenuItem> getCommonMenuList(Context context, int style, CommonMenuItem item, List<CommonMenuItem> items) {
        List<CommonMenuItem> menuItems = null;
        switch (style) {
            case CommonMenuListManager.STYLE_DEFAULT:
                menuItems = getDefaultMenu(context);
                break;
            case CommonMenuListManager.STYLE_TAKEOUT:
                menuItems = getTakeoutMenu(context);
                break;
            case CommonMenuListManager.STYLE_CUSTOMIZED_ONE:
                menuItems = getCustomizedMenu(context, item);
                break;
            case CommonMenuListManager.STYLE_CUSTOMIZED_LIST:
                menuItems = getCustomizedMenuList(context, items);
                break;
            default:
                break;
        }
        return menuItems;
    }
}
