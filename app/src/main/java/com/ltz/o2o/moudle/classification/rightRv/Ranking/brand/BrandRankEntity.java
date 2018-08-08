package com.ltz.o2o.moudle.classification.rightRv.ranking.brand;

/**
 * Created by 1 on 2018/8/3.
 */
public class BrandRankEntity {

    private String brandsName;
    private String brandsLogo;
    private String count;
    private String brandsId;
    private String brandsMess;

    public String getBrandsMess() {
        return brandsMess;
    }

    public void setBrandsMess(String brandsMess) {
        this.brandsMess = brandsMess;
    }

    public String getBrandsName() {
        return brandsName;
    }

    public void setBrandsName(String brandsName) {
        this.brandsName = brandsName;
    }

    public String getBrandsLogo() {
        return brandsLogo;
    }

    public void setBrandsLogo(String brandsLogo) {
        this.brandsLogo = brandsLogo;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getBrandsId() {
        return brandsId;
    }

    public void setBrandsId(String brandsId) {
        this.brandsId = brandsId;
    }
}
