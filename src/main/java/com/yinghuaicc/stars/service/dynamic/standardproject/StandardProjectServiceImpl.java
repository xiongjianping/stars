package com.yinghuaicc.stars.service.dynamic.standardproject;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.dynamic.standardproject.StandardProjectMapper;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardProject;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardProjectSy;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardProjectListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
@Service
public class StandardProjectServiceImpl implements StandardProjectService {

    @Autowired
    StandardProjectMapper standardProjectMapper;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;



    @Autowired
    private ExceptionUtil exceptionUtil;

    /**
     * 新增项目级别
     * @param standardProject
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveStandardProject(StandardProject standardProject) {

        standardProjectMapper.deleteStandardProjectById(standardProject);
        standardProject.setId(UuidUtil.randomUUID());
        standardProject.setCreateTime(LocalDateTime.now());
        standardProject.setCreateUser(aopResourceEmployeeBean.getName());
        standardProjectMapper.saveStandardProject(standardProject);
    }

    /**
     * 列表
     * @param standardProject
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<StandardProjectListResponse> getStandardProjectList(StandardProject standardProject, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<StandardProjectListResponse> result = standardProjectMapper.getStandardProjectList(standardProject);
        return new ResultPageList<StandardProjectListResponse>()
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
    public StandardProject getStandardProjectById(String id) {
        return standardProjectMapper.getStandardProjectById(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteStandardProject(String id) {
        standardProjectMapper.deleteStandardProject(id);
    }

    /**
     * 修改
     * @param standardProject
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatStandardProject(StandardProject standardProject) {
        standardProjectMapper.deleteStandardProjectByIdAndDate(standardProject);
        standardProject.setModifyTime(LocalDateTime.now());
        standardProject.setModifyUser(aopResourceEmployeeBean.getName());
        standardProjectMapper.updatStandardProject(standardProject);
    }

    /**
     * 首页标准三角形溢租率 项目
     * @param standardProject
     * @return
     */
    @Override
    public BigDecimal getSyProjectCount(StandardProjectSy standardProject) {
        String val = standardProjectMapper.getStandardProjectId(standardProject);
        if(val == null){
            val = standardProjectMapper.getStandardProjectIds(standardProject);
            if(val == null){
                throw exceptionUtil.throwCustomException("RENTING_RATE_011");
            }
        }
        BigDecimal v = new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_UP);
        return v;
    }

    @Override
    public List<String> getSyWtProjectCount(StandardProjectSy standardProject) {
        List<String> list = new ArrayList<>();
        String val = standardProjectMapper.getStandardProjectId(standardProject);
        if(val == null){
            val = standardProjectMapper.getStandardProjectIds(standardProject);
            if(val == null){
                list.add("该项目下，缺失标准三角形溢租率信息");
            }
        }
        return list;
    }
}
