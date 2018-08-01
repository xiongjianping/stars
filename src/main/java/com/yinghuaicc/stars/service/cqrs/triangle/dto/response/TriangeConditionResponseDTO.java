package com.yinghuaicc.stars.service.cqrs.triangle.dto.response;

/**
 * 通过项目id、楼层id查询业态list--返回结果
 */
public class TriangeConditionResponseDTO {
/*
    private String conditionId;
    private String conditionName;*/
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
