package com.yinghuaicc.stars.service.dynamic.fitted;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.dynamic.fitted.FittedProjectMapper;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedProject;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedProjectSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedProjectListResponse;
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
public class FittedProjectServiceImpl implements FittedProjectService {

    @Autowired
    FittedProjectMapper fittedProjectMapper;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;



    @Autowired
    private ExceptionUtil exceptionUtil;

    /**
     * 新增项目级别
     * @param fittedProject
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveFittedProject(FittedProject fittedProject) {

        fittedProjectMapper.deleteFittedProjectById(fittedProject);
        fittedProject.setId(UuidUtil.randomUUID());
        fittedProject.setCreateTime(LocalDateTime.now());
        fittedProject.setCreateUser(aopResourceEmployeeBean.getName());
        fittedProjectMapper.saveFittedProject(fittedProject);
    }

    /**
     * 列表
     * @param fittedProject
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<FittedProjectListResponse> getFittedProjectList(FittedProject fittedProject, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<FittedProjectListResponse> result = fittedProjectMapper.getFittedProjectList(fittedProject);
        return new ResultPageList<FittedProjectListResponse>()
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
    public FittedProject getFittedProjectById(String id) {
        return fittedProjectMapper.getFittedProjectById(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteFittedProject(String id) {
        fittedProjectMapper.deleteFittedProject(id);
    }

    /**
     * 修改
     * @param fittedProject
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatFittedProject(FittedProject fittedProject) {
        fittedProjectMapper.deleteFittedProjectByIdAndDate(fittedProject);
        fittedProject.setModifyTime(LocalDateTime.now());
        fittedProject.setModifyUser(aopResourceEmployeeBean.getName());
        fittedProjectMapper.updatFittedProject(fittedProject);
    }


    /**
     * 标准三角形，适配值，项目级别
     * @param fittedProject
     * @return
     */
    @Override
    public BigDecimal getFittedProject(FittedProjectSy fittedProject) {
        String val = fittedProjectMapper.getFittedProjectId(fittedProject);
        if(val == null){
            val = fittedProjectMapper.getFittedProjectIds(fittedProject);
            if(val == null){
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
        }
        BigDecimal v = new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        return v;
    }
}
