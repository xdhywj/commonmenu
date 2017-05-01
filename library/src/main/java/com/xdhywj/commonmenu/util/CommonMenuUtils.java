package com.xdhywj.commonmenu.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

/**
 * Created by xdhywj on 17/5/1.
 */

public class CommonMenuUtils {

    /**
     * dip转换成px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px转换成dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static Intent fromUri(Uri uri) {
        return fromUri(uri, null);
    }

    public static Intent fromUri(Uri uri, Bundle bundle) {
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        return intent;
    }
}
