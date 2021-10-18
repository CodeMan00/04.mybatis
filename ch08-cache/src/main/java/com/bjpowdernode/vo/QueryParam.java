package com.bjpowdernode.vo;

/**
 * @author gjd
 * @create 2021/10/6  22:28:58
 */

/**
 * 定义一个类用来封装查询时需要的参数信息
 */
public class QueryParam {

    private String paramName;
    private Integer paramAge;

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Integer getParamAge() {
        return paramAge;
    }

    public void setParamAge(Integer paramAge) {
        this.paramAge = paramAge;
    }

    @Override
    public String toString() {
        return "QueryParam{" +
                "paramName='" + paramName + '\'' +
                ", paramAge=" + paramAge +
                '}';
    }
}
