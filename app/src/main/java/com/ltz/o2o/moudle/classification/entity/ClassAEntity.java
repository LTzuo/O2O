package com.ltz.o2o.moudle.classification.entity;

/**
 * 分类一级实体
 * Created by 1 on 2018/7/31.
 */
public class ClassAEntity {

    String ID = "";
    String className = "";
    String classPic = "";
    String fatherId ="";
    String fatherName = "";

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getClassPic() {
        return classPic;
    }

    public void setClassPic(String classPic) {
        this.classPic = classPic;
    }

    public String getFatherId() {
        return fatherId;
    }

    public void setFatherId(String fatherId) {
        this.fatherId = fatherId;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }




}
