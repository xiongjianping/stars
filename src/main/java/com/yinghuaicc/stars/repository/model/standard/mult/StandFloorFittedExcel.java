package com.yinghuaicc.stars.repository.model.standard.mult;

import java.math.BigDecimal;

/**
 * 导入Excel标准三角形楼层适配值
 */
public class StandFloorFittedExcel {
    //改为签约id
    private String contractId;
    //项目名称
    private String projectName;
    //楼层名称
    private String floorName;
    //适配值
    private BigDecimal fitted;

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public BigDecimal getFitted() {
        return fitted;
    }

    public void setFitted(BigDecimal fitted) {
        this.fitted = fitted;
    }
}
