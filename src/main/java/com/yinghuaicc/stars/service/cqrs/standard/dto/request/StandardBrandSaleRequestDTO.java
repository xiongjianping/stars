package com.yinghuaicc.stars.service.cqrs.standard.dto.request;

public class StandardBrandSaleRequestDTO {
    private String saleVessionName;
    private String contractId;
    private String projectId;

    public String getSaleVessionName() {
        return saleVessionName;
    }

    public void setSaleVessionName(String saleVessionName) {
        this.saleVessionName = saleVessionName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
