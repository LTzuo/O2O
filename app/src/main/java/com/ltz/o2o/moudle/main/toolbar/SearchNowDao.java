package com.ltz.o2o.moudle.main.toolbar;

import org.litepal.crud.DataSupport;

/**
 * 最近搜索
 * Created by 1 on 2018/6/26.
 */
public class SearchNowDao extends DataSupport {

    private int id;

    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
