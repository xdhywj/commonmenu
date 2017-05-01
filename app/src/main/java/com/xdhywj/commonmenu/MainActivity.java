package com.xdhywj.commonmenu;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.xdhywj.commonmenu.common.CommonMenuBuilder;
import com.xdhywj.commonmenu.listener.ICommonMenuItemClickListener;
import com.xdhywj.commonmenu.module.CommonMenuItem;
import com.xdhywj.commonmenu.view.CommonMenuItemView;
import com.xdhywj.mycommonmenu.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String PAGE_NAME = "美食频道页";
    private CommonMenuActionProvider mActionProvider;
    private Button mDefaultBtn;
    private Button mTakeoutBtn;
    private Button mCustomizedOneBtn;
    private Button mCustomizedListBtn;
    private Button mCustomizedSortBtn;
    private CommonMenuItemView mCommonMenuView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDefaultBtn = (Button) findViewById(R.id.default_menu_btn);
        mTakeoutBtn = (Button) findViewById(R.id.takeout_menu_btn);
        mCustomizedOneBtn = (Button) findViewById(R.id.customized_one_menu_btn);
        mCustomizedListBtn = (Button) findViewById(R.id.customized_list_menu_btn);
        mCustomizedSortBtn = (Button) findViewById(R.id.customized_sort_menu_btn);
        mDefaultBtn.setOnClickListener(this);
        mTakeoutBtn.setOnClickListener(this);
        mCustomizedOneBtn.setOnClickListener(this);
        mCustomizedListBtn.setOnClickListener(this);
        mCustomizedSortBtn.setOnClickListener(this);
        mCommonMenuView = (CommonMenuItemView) findViewById(R.id.commonmenu_itemview);
        mCommonMenuView.setDefaultPopupMenu(PAGE_NAME, R.mipmap.action_share);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.commonmenu_more);
        mActionProvider = (CommonMenuActionProvider) MenuItemCompat.getActionProvider(menuItem);
        mActionProvider.setDefaultPopupMenu(PAGE_NAME, getResources().getDrawable(R.mipmap.action_share));
//        mActionProvider.setTakeoutPopupMenu(PAGE_NAME, getResources().getDrawable(R.drawable.commonmenu_normal_more_icon));
        return true;
    }

    @Override
    public void onClick(View v) {
        if (mActionProvider == null) {
            return;
        }
        int id = v.getId();
        switch (id) {
            case R.id.default_menu_btn:
                mActionProvider.setDefaultPopupMenu(PAGE_NAME);
                mCommonMenuView.setDefaultPopupMenu(PAGE_NAME);
                break;
            case R.id.takeout_menu_btn:
                mActionProvider.setTakeoutPopupMenu(PAGE_NAME);
                mCommonMenuView.setTakeoutPopupMenu(PAGE_NAME);
                break;
            case R.id.customized_one_menu_btn:
                setCustomizedMenu();
                break;
            case R.id.customized_list_menu_btn:
                setCustomizedListMenu();
                break;
            case R.id.customized_sort_menu_btn:
                setCustomizedSortListMenu();
                break;
        }
    }

    /**
     * 定制化一个：在默认的后面添加一个
     */
    private void setCustomizedMenu() {
        CommonMenuItem commonMenuItem = new CommonMenuItem();
        commonMenuItem.drawable = getResources().getDrawable(R.drawable.commonmenu_order_icon);
        commonMenuItem.title = "最近浏览";
        commonMenuItem.url = "";
        mActionProvider.setCustomizedPopupMenu(PAGE_NAME, commonMenuItem);
        mCommonMenuView.setCustomizedPopupMenu(PAGE_NAME, commonMenuItem);
    }

    /**
     * 定制化多个：可以自己定制，并且自己实现点击事件
     */
    private void setCustomizedListMenu() {
        List<CommonMenuItem> menuItems = new ArrayList<>();
        CommonMenuItem commonMenuItem = null;
        for (int i = 0; i < 3; i++) {
            commonMenuItem = new CommonMenuItem();
            commonMenuItem.title = "menu" + (i + 1);
            commonMenuItem.drawable = getResources().getDrawable(R.drawable.commonmenu_order_icon);
            commonMenuItem.listener = new CommonMenuClickListenerListener(i);
            menuItems.add(commonMenuItem);
        }
        mActionProvider.setCustomizedListPopupMenu(PAGE_NAME, menuItems);
        mCommonMenuView.setCustomizedListPopupMenu(PAGE_NAME, menuItems);
    }

    /**
     * 定制化多个：可以使用默认的，改变展现的顺序
     */
    private void setCustomizedSortListMenu() {
        List<CommonMenuItem> menuItems = new CommonMenuBuilder(this).appendFavorite().appendIndex().appendOrder().builder();
        mActionProvider.setCustomizedListPopupMenu(PAGE_NAME, menuItems);
        mCommonMenuView.setCustomizedListPopupMenu(PAGE_NAME, menuItems);
    }

    private class CommonMenuClickListenerListener implements ICommonMenuItemClickListener {
        private int index;
        public CommonMenuClickListenerListener(int index) {
            this.index = index;
        }

        @Override
        public void onItemMenuClick() {
            Toast.makeText(getApplicationContext(), "click:" + (index + 1) + "menu", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}
