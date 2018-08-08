package com.ltz.o2o.moudle.main.content.commodiy;

/**
 * Created by 1 on 2018/8/1.
 */
public class CommddityAEntity {

    private String ID;
    private String longName;
    private String sellCount;
    private String runState;
    private String maxPrice;
    private String minPrice;
    private String picPath;
    private String userIsCollect;

    public String getSellCount() {
        return sellCount;
    }

    public void setSellCount(String sellCount) {
        this.sellCount = sellCount;
    }

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(String maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getID() {

        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    public String getUserIsCollect() {
        return userIsCollect;
    }

    public void setUserIsCollect(String userIsCollect) {
        this.userIsCollect = userIsCollect;
    }
}
