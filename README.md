# commonmenu
一个通用的commonmenu，类似popupwindow
# Gradle

    compile ('com.xdhywj.commonmenu:commonmenu:0.0.1')

# 使用方式(actionBar)

    <menu xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        tools:context=".MainActivity">
        <item
            android:id="@+id/commonmenu_more"
            app:actionProviderClass="com.meituan.android.commonmenu.CommonMenuActionProvider"
            app:showAsAction="always"
            android:title="" />
    </menu>

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // 获取到我们添加的MenuItem
        MenuItem menuItem = menu.findItem(R.id.commonmenu_more);
        // 获取到ActionProvider
        mActionProvider = (CommonMenuActionProvider) MenuItemCompat.getActionProvider(menuItem);
        // 设置menu点击后展示的menu列表(默认为：首页/搜索/我的订单/我的收藏)
        mActionProvider.setDefaultPopupMenu(PAGE_NAME);
        return true;
    }

# 使用方式(自定义view)

    CommonMenuItemView mCommonMenuView = (CommonMenuItemView) findViewById(R.id.commonmenu_itemview);
    mCommonMenuView.setDefaultPopupMenu(PAGE_NAME);

    // pageName为mge2.0埋点的lab，需要业务方自己传参，drawable为点击的图片，业务方可以自己配置
    setDefaultPopupMenu(String pageName)
    setDefaultPopupMenu(String pageName, Drawable drawable)

# 效果图
![image](https://github.com/xdhywj/commonmenu/blob/master/screenshots/commonmenu_actionbar_default.png "actionBar Menu 默认")
![image](https://github.com/xdhywj/commonmenu/blob/master/screenshots/commonmenu_actionbar_customized.png "actionBar Menu 自定义")
![image](https://github.com/xdhywj/commonmenu/blob/master/screenshots/commonmenu_view_default.png "自定义view")