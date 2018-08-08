package com.ltz.o2o.moudle.main.content.commodiy;

import android.os.Parcel;
import android.os.Parcelable;
/**
 * 商品列表（请求参数封装）
 * Created by 1 on 2018/8/2.
 */
public class CommdityRequestEntity implements Parcelable{

    private String title = "";

    private String searchType = "";

    private String searchStr = "";

    private String otherId = "";

    public String getOtherId() {
        return otherId;
    }



    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getSearchStr() {
        return searchStr;
    }

    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CommdityRequestEntity> CREATOR = new Creator<CommdityRequestEntity>() {
        @Override
        public CommdityRequestEntity createFromParcel(Parcel in) {

            CommdityRequestEntity bean=new CommdityRequestEntity();
            bean.title=in.readString();
            bean.searchType =in.readString();
            bean.searchStr = in.readString();
            bean.otherId = in.readString();
            return bean;

        }

        @Override
        public CommdityRequestEntity[] newArray(int size) {
            return new CommdityRequestEntity[size];
        }
    };

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(searchType);
        parcel.writeString(searchStr);
        parcel.writeString(otherId);
    }
}
