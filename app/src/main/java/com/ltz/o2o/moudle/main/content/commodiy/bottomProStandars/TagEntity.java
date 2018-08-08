package com.ltz.o2o.moudle.main.content.commodiy.bottomProStandars;

/**
 * Created by 1 on 2018/8/7.
 */
public class TagEntity {

    private String title = "";

    public boolean isClick = false;

    //item是否允许点击
    public boolean itemClickable = true;

    public String getTitle() {
        return title;
    }

    public boolean isClick() {
        return isClick;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isItemClickable() {
        return itemClickable;
    }

    public void setItemClickable(boolean itemClickable) {
        this.itemClickable = itemClickable;
    }
}
