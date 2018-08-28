package com.yinghuaicc.stars.service.dynamic.fitted;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.dynamic.fitted.FittedFloorMapper;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFloor;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFloorSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedFloorListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@Service
public class FittedFloorServiceImpl implements FittedFloorService {

    @Autowired
    FittedFloorMapper fittedFloorMapper ;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    private ExceptionUtil exceptionUtil;


    /**
     * 保存
     * @param fittedFloor
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFittedFloor(FittedFloor fittedFloor) {
        fittedFloorMapper.deleteFittedFloorById(fittedFloor);
        fittedFloor.setId(UuidUtil.randomUUID());
        fittedFloor.setCreateTime(LocalDateTime.now());
        fittedFloor.setCreateUser(aopResourceEmployeeBean.getName());
        fittedFloorMapper.saveFittedFloor(fittedFloor);
    }

    /**
     * 列表
     * @param fittedFloor
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<FittedFloorListResponse> getFittedFloorList(FittedFloor fittedFloor, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<FittedFloorListResponse> result = fittedFloorMapper.getFittedFloorList(fittedFloor);
        return new ResultPageList<FittedFloorListResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @Override
    public FittedFloor getFittedFloorById(String id) {
        return fittedFloorMapper.getFittedFloorById(id);
    }

    /**
     * 修改
     * @param fittedFloor
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFittedFloor(FittedFloor fittedFloor) {
        fittedFloorMapper.deleteFittedFloorByIdAndDate(fittedFloor);
        fittedFloor.setModifyTime(LocalDateTime.now());
        fittedFloor.setModifyUser(aopResourceEmployeeBean.getName());
        fittedFloorMapper.updateFittedFloor(fittedFloor);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFittedFloor(String id) {
        fittedFloorMapper.deleteFittedFloor(id);
    }


    /**
     * 标准三角形，适配值，楼层级别
     * @param fittedFloor
     * @return
     */
    @Override
    public BigDecimal getFittedFloor(FittedFloorSy fittedFloor) {
        String val = fittedFloorMapper.getFittedFloorId(fittedFloor);
        if(val == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal v = new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        return v;
    }
}
