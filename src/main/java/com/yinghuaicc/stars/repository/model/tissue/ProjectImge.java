package com.yinghuaicc.stars.repository.model.tissue;

import com.yinghuaicc.stars.repository.model.region.ProjectImage;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author:Fly
 * @Date:Create in 2018/6/27 下午6:16
 * @Description: 项目详情图片
 * @Modified:
 */
public class ProjectImge {

    //id
    private String id;

    private List<ProjectImage> url;

    //项目名称
    private String name;

    //开业时间
    private String createDate;

    //面积
    private BigDecimal acreage;

    //楼层数
    private Integer floorNum;

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ProjectImage> getUrl() {
        return url;
    }

    public void setUrl(List<ProjectImage> url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }


}
