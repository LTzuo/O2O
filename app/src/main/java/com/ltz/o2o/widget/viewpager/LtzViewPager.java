package com.ltz.o2o.widget.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 了解决ViewPager不能响应onTouchListener的问题
 *主要是复写了其中的dispatchTouchEvent函数，然后在分发之前，先自己做点事情
 * Created by 1 on 2018/6/2.
 */
public class LtzViewPager extends ViewPager {

    public LtzViewPager(Context context) {
        super(context);
    }

    public LtzViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//  @Override
//  public boolean onInterceptTouchEvent(MotionEvent arg0) {
//      return true;
//  }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (listener != null) {
                    listener.onTouchDown();
                }
                break;
            case MotionEvent.ACTION_UP:
                if (listener != null) {
                    listener.onTouchUp();
                }
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    private OnViewPagerTouchEvent listener;

    public void setOnViewPagerTouchEventListener(OnViewPagerTouchEvent l){
        listener = l;
    }

    public interface OnViewPagerTouchEvent{
        void onTouchDown();
        void onTouchUp();
    }
}
