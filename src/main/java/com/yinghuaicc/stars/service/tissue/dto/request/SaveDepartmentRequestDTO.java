package com.yinghuaicc.stars.service.tissue.dto.request;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/6 下午3:55
 * @Description: 添加部门
 * @Modified:
 */
public class SaveDepartmentRequestDTO {

    //公司名称
    @NotEmpty(message = "TISSUE_SAVE_DEPARTMENT_001")
    private String name;

    //所属城市公司id
    @NotEmpty(message = "TISSUE_SAVE_DEPARTMENT_002")
    private String companyId;

    //父id
    private String parentId;

    public String getName() {
        return name;
    }

    public SaveDepartmentRequestDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getCompanyId() {
        return companyId;
    }

    public SaveDepartmentRequestDTO setCompanyId(String companyId) {
        this.companyId = companyId;
        return this;
    }

    public String getParentId() {
        return parentId;
    }

    public SaveDepartmentRequestDTO setParentId(String parentId) {
        this.parentId = parentId;
        return this;
    }
}
