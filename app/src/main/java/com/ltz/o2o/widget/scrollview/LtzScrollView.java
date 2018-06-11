package com.ltz.o2o.widget.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * 结合纵向滑动ViewPager使用，滑动事件分发
 * Created by 1 on 2018/6/4.
 */
public class LtzScrollView extends ScrollView {
    public LtzScrollView(Context context) {
        super(context);
    }

    public LtzScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LtzScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private int downY = 0;//初始化按下时Y坐标变量

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        View childView = getChildAt(0);//获取孩子控件
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录按下时的y坐标(用来判断是向下还是向上滑动的操作)
                downY = (int) ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //判断向上滑动的情况,滑动到底部
                // 当朝着Y方向滑动到底部的坐标(即scrollview的最大滑动量)+scrollview本身的高度大于或等于了孩子控件的高度
                //说明这时要进行页面切换了,scrollview就返回false,不去进行触摸操作,给viewpager去向下翻页
                if (downY > ev.getY() && childView != null && getScrollY() + getMeasuredHeight()
                        >= childView.getMeasuredHeight()) {
                    return false;
                }
                //判断向下滑动的情况,到顶部
                //如果朝着Y方向滑动的坐标是小于等于0,说明是向上翻页的趋势,这时也返回false,给viewpager去翻页
                if (downY < ev.getY() && getScrollY() <= 0) {
                    return false;
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
}
