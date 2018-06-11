package com.ltz.o2o.widget.viewpager;

import android.content.Context;
import android.graphics.Point;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.WindowManager;

/**
 * Created by 1 on 2018/6/4.
 */
public class IdeaViewPager extends ViewPager {

    private Point point;

    public IdeaViewPager(Context context) {
        this(context,null);
    }

    public IdeaViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        point = new Point();
        windowManager.getDefaultDisplay().getSize(point);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(point.x,point.x);
    }
}
