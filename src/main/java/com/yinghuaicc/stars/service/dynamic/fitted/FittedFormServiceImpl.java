package com.yinghuaicc.stars.service.dynamic.fitted;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.dynamic.fitted.FittedFormMapper;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedForm;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFormSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedFormListResponse;
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
public class FittedFormServiceImpl implements FittedFormService {

    @Autowired
    FittedFormMapper fittedFormMapper;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;

    @Autowired
    private ExceptionUtil exceptionUtil;


    /**
     * 新增
     * @param fittedForm
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFittedForm(FittedForm fittedForm) {
        fittedFormMapper.deleteFittedFormById(fittedForm);
        fittedForm.setId(UuidUtil.randomUUID());
        fittedForm.setCreateTime(LocalDateTime.now());
        fittedForm.setCreateUser(aopResourceEmployeeBean.getName());
        fittedFormMapper.saveFittedForm(fittedForm);
    }

    /**
     * 列表
     * @param fittedForm
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<FittedFormListResponse> getFittedFormList(FittedForm fittedForm, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<FittedFormListResponse> result =fittedFormMapper.getFittedFormList(fittedForm);
        return new ResultPageList<FittedFormListResponse>()
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
    public FittedForm getFittedFormById(String id){
        return fittedFormMapper.getFittedFormById(id);
    }

    /**
     * 更新
     * @param fittedForm
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFittedForm(FittedForm fittedForm) {
        fittedFormMapper.deleteFittedFormByIdAndDate(fittedForm);
        fittedForm.setModifyTime(LocalDateTime.now());
        fittedForm.setModifyUser(aopResourceEmployeeBean.getName());
        fittedFormMapper.updateFittedForm(fittedForm);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFittedForm(String id) {
        fittedFormMapper.deleteFittedForm(id);
    }


    /**
     * 标准三角形，适配值，业态级别
     * @param fittedForm
     * @return
     */
    @Override
    public BigDecimal getFittedForm(FittedFormSy fittedForm) {
        String val = fittedFormMapper.getFittedFormId(fittedForm);
        if(val == null){
            val = fittedFormMapper.getFittedFormIds(fittedForm);
            if(val == null){
                throw exceptionUtil.throwCustomException("RENTING_RATE_026");
            }
        }
        BigDecimal v = new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        return v;
    }
}
