package com.ltz.o2o;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import com.ltz.o2o.base.RxBaseActivity;
import com.ltz.o2o.moudle.classification.ClassificationFragment;
import com.ltz.o2o.moudle.HomeViewPagerAdapter;
import com.ltz.o2o.moudle.collage.CollageFragment;
import com.ltz.o2o.moudle.main.MainFragment;
import com.ltz.o2o.moudle.mine.MineFragment;
import com.ltz.o2o.moudle.shopping_cart.Shop_CartFragment;
import com.ltz.o2o.utils.SnackbarUtil;
import com.ltz.o2o.widget.TabbarHelper.BottomNavigationViewHelper;
import com.ltz.o2o.widget.TabbarHelper.NoScrollViewPager;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 主宿主页面
 */
public class HomeActivity extends RxBaseActivity {

    private NoScrollViewPager viewPager;
    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;
    private static Boolean isExit = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        viewPager = (NoScrollViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_main:
                                viewPager.setCurrentItem(0,false);
                                break;
                            case R.id.item_classify:
                                viewPager.setCurrentItem(1,false);
                                break;
                            case R.id.item_collage:
                                viewPager.setCurrentItem(2,false);
                                break;
                            case R.id.item_shop:
                                viewPager.setCurrentItem(3,false);
                                break;
                            case R.id.item_mine:
                                viewPager.setCurrentItem(4,false);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position);
                menuItem.setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        HomeViewPagerAdapter adapter = new HomeViewPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(4);
        adapter.addFragment(MainFragment.newInstance());
        adapter.addFragment(ClassificationFragment.newInstance());
        adapter.addFragment(CollageFragment.newInstance());
        adapter.addFragment(Shop_CartFragment.newInstance());
        adapter.addFragment(MineFragment.newInstance());
        viewPager.setAdapter(adapter);
    }

    /**
     * 双击退出App
     */
    private void exitApp() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            SnackbarUtil.showMessage(this.bottomNavigationView, "再按一次退出");
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    public void initToolBar() {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitApp(); // 调用双击退出函数
        }
        return false;
    }


}
