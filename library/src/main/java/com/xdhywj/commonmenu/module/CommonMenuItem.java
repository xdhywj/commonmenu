package com.xdhywj.commonmenu.module;

import android.graphics.drawable.Drawable;

import com.xdhywj.commonmenu.listener.ICommonMenuItemClickListener;


/**
 * Created by xdhywj on 17/5/1.
 */

public class CommonMenuItem {

    public Drawable drawable;
    public String title;
    public String url;
    public ICommonMenuItemClickListener listener;

    public CommonMenuItem() {

    }

    public CommonMenuItem(Drawable drawable, String title, String url) {
        this.drawable = drawable;
        this.title = title;
        this.url = url;
    }

    public CommonMenuItem(Drawable drawable, String title, ICommonMenuItemClickListener listener) {
        this.drawable = drawable;
        this.title = title;
        this.listener = listener;
    }
}
