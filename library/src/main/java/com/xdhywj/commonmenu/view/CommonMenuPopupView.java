package com.xdhywj.commonmenu.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xdhywj.commonmenu.R;
import com.xdhywj.commonmenu.listener.ICommonMenuDismissListener;
import com.xdhywj.commonmenu.listener.OnItemViewClickListener;
import com.xdhywj.commonmenu.module.CommonMenuItem;
import com.xdhywj.commonmenu.util.CommonMenuUtils;

import java.util.List;

/**
 * Created by xdhywj on 17/5/1.
 */

public class CommonMenuPopupView extends LinearLayout {

    private static final int TEXT_SIZE = 16;
    private static final int DRAWABLE_PADDING = 12;
    private static final int TEXT_VIEW_PADDING = 14;
    private static final int TEXT_VIEW_TOP_PADDING = 8;
    private static final int TEXT_VIEW_HEIGHT = 45;
    private static final int POP_VIEW_WIDTH = 150;
    private ICommonMenuDismissListener mDismissListener;
    private String mPageName;

    public CommonMenuPopupView(Context context) {
        this(context, null);
    }

    public CommonMenuPopupView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CommonMenuPopupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CommonMenuPopupView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();

    }

    private void initView() {
        setOrientation(VERTICAL);
        setBackground(getResources().getDrawable(R.drawable.commonmenu_popwindow_bg));
        setLayoutParams(new LayoutParams(CommonMenuUtils.dip2px(getContext(), POP_VIEW_WIDTH), ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    public void setDismissListener(ICommonMenuDismissListener dismissListener) {
        mDismissListener = dismissListener;
    }

    public void setPageName(String pageName) {
        mPageName = pageName;
    }

    public void addPopupItemView(List<CommonMenuItem> commonMenuItems) {
        if (commonMenuItems == null) {
            return;
        }
        removeAllViews();
        CommonMenuItem itemMenu = null;
        TextView itemView = null;
        View itemDivider = null;
        int size = commonMenuItems.size();
        for (int i = 0; i < size; i++) {
            itemMenu = commonMenuItems.get(i);
            if (itemMenu == null) {
                continue;
            }
            itemView = createItemView(itemMenu, i+1);
            addView(itemView);
            if (i != size - 1) {
                itemDivider = createItemDivider();
                addView(itemDivider);
            }
        }
    }

    private TextView createItemView(CommonMenuItem item, int index) {
        TextView itemView = new TextView(getContext());
        itemView.setText(item.title);
        itemView.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE);
        itemView.setTextColor(getResources().getColor(R.color.commonmenu_item_text_color));
        itemView.setGravity(Gravity.CENTER_VERTICAL);
        itemView.setSingleLine();
        itemView.setCompoundDrawablesWithIntrinsicBounds(item.drawable, null, null, null);
        itemView.setCompoundDrawablePadding(CommonMenuUtils.dip2px(getContext(), DRAWABLE_PADDING));
        itemView.setPadding(CommonMenuUtils.dip2px(getContext(), TEXT_VIEW_PADDING), index == 1 ? TEXT_VIEW_TOP_PADDING : 0 , 0, 0);
        itemView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                CommonMenuUtils.dip2px(getContext(), index == 1 ? TEXT_VIEW_HEIGHT + TEXT_VIEW_TOP_PADDING : TEXT_VIEW_HEIGHT)));
        itemView.setOnClickListener(new OnItemViewClickListener(getContext(), mPageName, index, item, mDismissListener));
        return itemView;
    }

    private View createItemDivider() {
        View divider = new View(getContext());
        divider.setBackground(getResources().getDrawable(R.color.commonmenu_item_divider_color));
        divider.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, CommonMenuUtils.dip2px(getContext(), 0.5f)));
        return divider;
    }
}
