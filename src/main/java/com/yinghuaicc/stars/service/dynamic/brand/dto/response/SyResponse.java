package com.yinghuaicc.stars.service.dynamic.brand.dto.response;

import com.yinghuaicc.stars.service.cqrs.help.dto.response.FindHelpPlanBusinessSpeciesListCQRSResponseDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/19.
 */
public class SyResponse {
    //动态三角形溢租率
    private BigDecimal triangleRent;
    //动态三角形适配值
    private BigDecimal triangleFitted;
    //动态三角形客销度
    private BigDecimal triangleGuest;
    //标准三角形溢租率
    private BigDecimal standardRent;
    //标准三角形适配值
    private BigDecimal standardFitted;
    //标准三角形客销度
    private BigDecimal standardGuest;

    BigDecimal excellentPgeVal;//优秀百分比
    BigDecimal goodPgeVal;//良好百分比
    BigDecimal promotePgeVal;//提升百分比
    BigDecimal reasonablePgeVal;//合理百分比
    BigDecimal lossVal;//亏损百分比

    List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> yzl;//溢租率 1
    List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> kxd;//客销度 2
    List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> spz;//适配值 3

    private Integer yzlState;
    private Integer kxdState;
    private Integer spzState;

    public Integer getYzlState() {
        return yzlState;
    }

    public void setYzlState(Integer yzlState) {
        this.yzlState = yzlState;
    }

    public Integer getKxdState() {
        return kxdState;
    }

    public void setKxdState(Integer kxdState) {
        this.kxdState = kxdState;
    }

    public Integer getSpzState() {
        return spzState;
    }

    public void setSpzState(Integer spzState) {
        this.spzState = spzState;
    }

    public List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> getYzl() {
        return yzl;
    }

    public void setYzl(List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> yzl) {
        this.yzl = yzl;
    }

    public List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> getKxd() {
        return kxd;
    }

    public void setKxd(List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> kxd) {
        this.kxd = kxd;
    }

    public List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> getSpz() {
        return spz;
    }

    public void setSpz(List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> spz) {
        this.spz = spz;
    }

    public BigDecimal getTriangleRent() {
        return triangleRent;
    }

    public void setTriangleRent(BigDecimal triangleRent) {
        this.triangleRent = triangleRent;
    }

    public BigDecimal getTriangleFitted() {
        return triangleFitted;
    }

    public void setTriangleFitted(BigDecimal triangleFitted) {
        this.triangleFitted = triangleFitted;
    }

    public BigDecimal getTriangleGuest() {
        return triangleGuest;
    }

    public void setTriangleGuest(BigDecimal triangleGuest) {
        this.triangleGuest = triangleGuest;
    }

    public BigDecimal getStandardRent() {
        return standardRent;
    }

    public void setStandardRent(BigDecimal standardRent) {
        this.standardRent = standardRent;
    }

    public BigDecimal getStandardFitted() {
        return standardFitted;
    }

    public void setStandardFitted(BigDecimal standardFitted) {
        this.standardFitted = standardFitted;
    }

    public BigDecimal getStandardGuest() {
        return standardGuest;
    }

    public void setStandardGuest(BigDecimal standardGuest) {
        this.standardGuest = standardGuest;
    }

    public BigDecimal getExcellentPgeVal() {
        return excellentPgeVal;
    }

    public void setExcellentPgeVal(BigDecimal excellentPgeVal) {
        this.excellentPgeVal = excellentPgeVal;
    }

    public BigDecimal getGoodPgeVal() {
        return goodPgeVal;
    }

    public void setGoodPgeVal(BigDecimal goodPgeVal) {
        this.goodPgeVal = goodPgeVal;
    }

    public BigDecimal getPromotePgeVal() {
        return promotePgeVal;
    }

    public void setPromotePgeVal(BigDecimal promotePgeVal) {
        this.promotePgeVal = promotePgeVal;
    }

    public BigDecimal getReasonablePgeVal() {
        return reasonablePgeVal;
    }

    public void setReasonablePgeVal(BigDecimal reasonablePgeVal) {
        this.reasonablePgeVal = reasonablePgeVal;
    }

    public BigDecimal getLossVal() {
        return lossVal;
    }

    public void setLossVal(BigDecimal lossVal) {
        this.lossVal = lossVal;
    }
}
