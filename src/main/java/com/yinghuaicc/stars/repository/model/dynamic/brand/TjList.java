package com.yinghuaicc.stars.repository.model.dynamic.brand;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/10/9.
 */
public class TjList {
    private String id; //项目ID

    private String projectName;//项目名称

    private String xse; //销售额状态 0 正常 1缺失

    private String kll;//客流量状态 0 正常 1缺失

    private String day; //时间天数

    private String lcnum; //楼层缺失天数

    private String xmnum;//项目缺失天数

    private String ppnum;//品牌缺失天数

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getXse() {
        return xse;
    }

    public void setXse(String xse) {
        this.xse = xse;
    }

    public String getKll() {
        return kll;
    }

    public void setKll(String kll) {
        this.kll = kll;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getLcnum() {
        return lcnum;
    }

    public void setLcnum(String lcnum) {
        this.lcnum = lcnum;
    }

    public String getXmnum() {
        return xmnum;
    }

    public void setXmnum(String xmnum) {
        this.xmnum = xmnum;
    }

    public String getPpnum() {
        return ppnum;
    }

    public void setPpnum(String ppnum) {
        this.ppnum = ppnum;
    }
}
