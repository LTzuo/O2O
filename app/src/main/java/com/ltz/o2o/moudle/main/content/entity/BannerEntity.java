package com.ltz.o2o.moudle.main.content.entity;

/**
 * 广告实体
 * Created by 1 on 2018/7/24.
 */
public class BannerEntity {

    String adId = "";
    String imgPath = "";
    String linkUrl = "";

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
}
