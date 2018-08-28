package com.yinghuaicc.stars.service.dynamic.brand.dto.response;

import java.math.BigDecimal;

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
    private BigDecimal StandardFitted;
    //标准三角形客销度
    private BigDecimal StandardGuest;

    BigDecimal excellentPgeVal;//优秀百分比
    BigDecimal goodPgeVal;//良好百分比
    BigDecimal promotePgeVal;//提升百分比
    BigDecimal reasonablePgeVal;//合理百分比
    BigDecimal lossVal;//合理百分比

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
        return StandardFitted;
    }

    public void setStandardFitted(BigDecimal standardFitted) {
        StandardFitted = standardFitted;
    }

    public BigDecimal getStandardGuest() {
        return StandardGuest;
    }

    public void setStandardGuest(BigDecimal standardGuest) {
        StandardGuest = standardGuest;
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
