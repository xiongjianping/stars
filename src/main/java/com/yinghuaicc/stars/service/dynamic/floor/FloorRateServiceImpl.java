package com.yinghuaicc.stars.service.dynamic.floor;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.dynamic.floor.FloorRateMapper;
import com.yinghuaicc.stars.repository.model.dynamic.floor.FloorRate;
import com.yinghuaicc.stars.service.dynamic.floor.dto.response.FloorRateListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;


/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
@Service
public class FloorRateServiceImpl implements FloorRateService {

    @Autowired
    FloorRateMapper floorRateMapper;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    private ExceptionUtil exceptionUtil;

    /**
     * 新增
     * @param FloorRate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFloorRate(FloorRate FloorRate) {

        floorRateMapper.deleteFloorRateById(FloorRate);
        FloorRate.setId(UuidUtil.randomUUID());
        FloorRate.setCreateTime(LocalDateTime.now());
        FloorRate.setCreateUser(aopResourceEmployeeBean.getName());
        floorRateMapper.saveFloorRate(FloorRate);
    }

    /**
     * 列表
     * @param floorRate
     * @param pageParam
     * @return
     */
    @Override
    @Transactional
    public ResultPageList<FloorRateListResponse> getFloorRateList(FloorRate floorRate, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<FloorRateListResponse> result = floorRateMapper.getFloorRateList(floorRate);
        return new ResultPageList<FloorRateListResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Override
    @Transactional
    public FloorRate getFloorRateById(String id) {
        return floorRateMapper.getFloorRateById(id);
    }

    /**
     * 更新
     * @param FloorRate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFloorRate(FloorRate FloorRate) {
        floorRateMapper.deleteFloorRateByIdAndDate(FloorRate);
        FloorRate.setModifyTime(LocalDateTime.now());
        FloorRate.setModifyUser(aopResourceEmployeeBean.getName());
        floorRateMapper.updateFloorRate(FloorRate);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFloorRate(String id) {
        floorRateMapper.deleteFloorRate(id);
    }

    /**
     * 楼层客销度
     * @param FloorRate
     * @return
     */
    @Override
    @Transactional
    public BigDecimal getSyFloorRateCount(FloorRate FloorRate) {
        String kll = floorRateMapper.getFloorRateByIdSy(FloorRate); //楼层客流量
        if(kll == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_006");
        }
        BigDecimal zkll = new BigDecimal(kll); //总客流量

        String mj = floorRateMapper.getFloorAcreageById(FloorRate.getFloorId()); //面积
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_007");
        }
        BigDecimal acreage = new BigDecimal(mj); //面积

        Duration duration = Duration.between(FloorRate.getCreateTime(),FloorRate.getModifyTime());
        BigDecimal day = new BigDecimal(duration.toDays()); //时间差

        BigDecimal rx = zkll.divide(acreage.multiply(day)); // 1 楼层客流量除以面积
        String pb = floorRateMapper.getFloorBrandById(FloorRate); // 2 楼层下所有品牌销售额
        if(pb == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal sale = new BigDecimal(pb);
        BigDecimal px = sale.divide(acreage.multiply(day)); //销售额 / 面积*时间擦

        BigDecimal kxd = rx.multiply(px);
        return kxd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }



}
