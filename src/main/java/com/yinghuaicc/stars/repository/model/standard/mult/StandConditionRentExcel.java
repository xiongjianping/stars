package com.yinghuaicc.stars.repository.model.standard.mult;


import java.math.BigDecimal;

/**
 * 导入Excel标准三角形业态溢租率
 */
public class StandConditionRentExcel {

    private String projectId;
    //项目名称
    private String projectName;
    //业态名称
    private String conditionName;
    //业种名称
    private String majoName;
    //溢租率
    private BigDecimal rent;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getConditionName() {
        return conditionName;
    }

    public void setConditionName(String conditionName) {
        this.conditionName = conditionName;
    }

    public String getMajoName() {
        return majoName;
    }

    public void setMajoName(String majoName) {
        this.majoName = majoName;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }
}
