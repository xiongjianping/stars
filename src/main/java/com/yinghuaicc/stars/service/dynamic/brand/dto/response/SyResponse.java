package com.yinghuaicc.stars.service.dynamic.brand.dto.response;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/19.
 */
public class SyResponse {
    BigDecimal dtkxd;
    BigDecimal dtspz;
    BigDecimal dtyzl;

    BigDecimal bzkxd;
    BigDecimal bzspz;
    BigDecimal bzyzl;

    BigDecimal excellentPgeVal;//优秀百分比
    BigDecimal goodPgeVal;//良好百分比
    BigDecimal promotePgeVal;//提升百分比
    BigDecimal reasonablePgeVal;//合理百分比
    BigDecimal lossVal;//合理百分比

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

    public BigDecimal getDtkxd() {
        return dtkxd;
    }

    public void setDtkxd(BigDecimal dtkxd) {
        this.dtkxd = dtkxd;
    }

    public BigDecimal getDtspz() {
        return dtspz;
    }

    public void setDtspz(BigDecimal dtspz) {
        this.dtspz = dtspz;
    }

    public BigDecimal getDtyzl() {
        return dtyzl;
    }

    public void setDtyzl(BigDecimal dtyzl) {
        this.dtyzl = dtyzl;
    }

    public BigDecimal getBzkxd() {
        return bzkxd;
    }

    public void setBzkxd(BigDecimal bzkxd) {
        this.bzkxd = bzkxd;
    }

    public BigDecimal getBzspz() {
        return bzspz;
    }

    public void setBzspz(BigDecimal bzspz) {
        this.bzspz = bzspz;
    }

    public BigDecimal getBzyzl() {
        return bzyzl;
    }

    public void setBzyzl(BigDecimal bzyzl) {
        this.bzyzl = bzyzl;
    }
}
