package com.bjpowdernode.domain;

/**
 * @author gjd
 * @create 2021/10/7  16:31:19
 */
public class MyStudent {

    private Integer stuid;
    private String stuname;
    private Integer stuage;
    private String stumail;

    public Integer getStuid() {
        return stuid;
    }

    public void setStuid(Integer stuid) {
        this.stuid = stuid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public Integer getStuage() {
        return stuage;
    }

    public void setStuage(Integer stuage) {
        this.stuage = stuage;
    }

    public String getStumail() {
        return stumail;
    }

    public void setStumail(String stumail) {
        this.stumail = stumail;
    }

    @Override
    public String toString() {
        return "MyStudent{" +
                "stuid=" + stuid +
                ", stuname='" + stuname + '\'' +
                ", stuage=" + stuage +
                ", stumail='" + stumail + '\'' +
                '}';
    }
}
