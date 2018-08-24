package com.yinghuaicc.stars.service.dynamic.standardproject;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.dynamic.standardproject.StandardFormMapper;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardForm;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardFormListResponse;
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
public class StandardFormServiceImpl implements StandardFormService {

    @Autowired
    StandardFormMapper standardFormMapper;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    private ExceptionUtil exceptionUtil;


    /**
     * 新增
     * @param standardForm
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStandardForm(StandardForm standardForm) {
        standardFormMapper.deleteStandardFormById(standardForm);
        standardForm.setId(UuidUtil.randomUUID());
        standardForm.setCreateTime(LocalDateTime.now());
        standardForm.setCreateUser(aopResourceEmployeeBean.getName());
        standardFormMapper.saveStandardForm(standardForm);
    }

    /**
     * 列表
     * @param standardForm
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<StandardFormListResponse> getStandardFormList(StandardForm standardForm, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<StandardFormListResponse> result = standardFormMapper.getStandardFormList(standardForm);
        return new ResultPageList<StandardFormListResponse>()
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
    public StandardForm getStandardFormById(String id) {
        return standardFormMapper.getStandardFormById(id);
    }

    /**
     * 更新
     * @param standardForm
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStandardForm(StandardForm standardForm) {
        standardFormMapper.deleteStandardFormByIdAndDate(standardForm);
        standardForm.setModifyTime(LocalDateTime.now());
        standardForm.setModifyUser(aopResourceEmployeeBean.getName());
        standardFormMapper.updateStandardForm(standardForm);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStandardForm(String id) {
        standardFormMapper.deleteStandardForm(id);
    }



    /**
     * 首页标准三角形溢租率 业态
     * @param standardForm
     * @return
     */
    @Override
    public BigDecimal getSyFormCount(StandardForm standardForm) {
        String val = standardFormMapper.getStandardFormId(standardForm);
        if(val == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_011");
        }
        BigDecimal v = new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        return v;
    }
}
