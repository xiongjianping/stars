package com.yinghuaicc.stars.service.dynamic.standardproject;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.dynamic.standardproject.StandardFloorMapper;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFloor;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFloorSy;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardFloorListResponse;
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
public class StandardFloorServiceImpl implements StandardFloorService {

    @Autowired
    StandardFloorMapper standardFloorMapper;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    private ExceptionUtil exceptionUtil;


    /**
     * 保存
     * @param standardFloor
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStandardFloor(StandardFloor standardFloor) {
        standardFloorMapper.deleteStandardFloorById(standardFloor);
        standardFloor.setId(UuidUtil.randomUUID());
        standardFloor.setCreateTime(LocalDateTime.now());
        standardFloor.setCreateUser(aopResourceEmployeeBean.getName());
        standardFloorMapper.saveStandardFloor(standardFloor);
    }

    /**
     * 列表
     * @param standardFloor
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<StandardFloorListResponse> getStandardFloorList(StandardFloor standardFloor, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<StandardFloorListResponse> result = standardFloorMapper.getStandardFloorList(standardFloor);
        return new ResultPageList<StandardFloorListResponse>()
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
    public StandardFloor getStandardFloorById(String id) {
        return standardFloorMapper.getStandardFloorById(id);
    }

    /**
     * 修改
     * @param standardFloor
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStandardFloor(StandardFloor standardFloor) {
        standardFloorMapper.deleteStandardFloorByIdAndDate(standardFloor);
        standardFloor.setModifyTime(LocalDateTime.now());
        standardFloor.setModifyUser(aopResourceEmployeeBean.getName());
        standardFloorMapper.updateStandardFloor(standardFloor);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStandardFloor(String id) {
        standardFloorMapper.deleteStandardFloor(id);
    }


    /**
     * 首页标准三角形溢租率 楼层
     * @param standardFloor
     * @return
     */
    @Override
    public BigDecimal getSyFloorCount(StandardFloorSy standardFloor) {
        String val = standardFloorMapper.getStandardFloorId(standardFloor);
        if(val == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal v = new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        return v;
    }
}
