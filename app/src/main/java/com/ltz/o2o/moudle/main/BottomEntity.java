package com.ltz.o2o.moudle.main;

/**
 * 首页(全球热卖、特色推荐、显示拼团等最外层实体)
 * Created by 1 on 2018/7/24.
 */
public class BottomEntity {

    String dataType = "";
    String proList = "";
    String titleName = "";
    String nextGetHotTime = "";
    String showBtnImg = "";

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getProList() {
        return proList;
    }

    public void setProList(String proList) {
        this.proList = proList;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public String getNextGetHotTime() {
        return nextGetHotTime;
    }

    public void setNextGetHotTime(String nextGetHotTime) {
        this.nextGetHotTime = nextGetHotTime;
    }

    public String getShowBtnImg() {
        return showBtnImg;
    }

    public void setShowBtnImg(String showBtnImg) {
        this.showBtnImg = showBtnImg;
    }
}
