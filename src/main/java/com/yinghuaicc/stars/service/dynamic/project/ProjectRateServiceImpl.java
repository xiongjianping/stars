package com.yinghuaicc.stars.service.dynamic.project;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.dynamic.project.ProjectRateMapper;
import com.yinghuaicc.stars.repository.model.dynamic.project.ProjectRate;
import com.yinghuaicc.stars.service.dynamic.project.dto.response.ProjectRateListResponse;
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
public class ProjectRateServiceImpl implements ProjectRateService {

    @Autowired
    ProjectRateMapper projectRateMapper;

    @Autowired
    AopResourceEmployeeBean aopResourceEmployeeBean;



    @Autowired
    private ExceptionUtil exceptionUtil;

    /**
     * 新增
     * @param projectRate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveProjectRate(ProjectRate projectRate) {

        projectRateMapper.deleteProjectRateById(projectRate);
        projectRate.setId(UuidUtil.randomUUID());
        projectRate.setCreateTime(LocalDateTime.now());
        projectRate.setCreateUser(aopResourceEmployeeBean.getName());
        projectRateMapper.saveProjectRate(projectRate);
    }


    /**
     * 列表
     * @param projectRate
     * @param pageParam
     * @return
     */
    @Override
    @Transactional
    public ResultPageList<ProjectRateListResponse> getProjectRateList(ProjectRate projectRate, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<ProjectRateListResponse> result = projectRateMapper.getProjectRateList(projectRate);
        return new ResultPageList<ProjectRateListResponse>()
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
    @Transactional
    public ProjectRate getProjectRateById(String id) {
        return projectRateMapper.getProjectRateById(id);
    }

    /**
     * 更新
     * @param projectRate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateProjectRate(ProjectRate projectRate) {
        projectRateMapper.deleteProjectRateByIdAndDate(projectRate);
        projectRate.setModifyTime(LocalDateTime.now());
        projectRate.setModifyUser(aopResourceEmployeeBean.getName());
        projectRateMapper.updateProjectRate(projectRate);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteProjectRate(String id) {
        projectRateMapper.deleteProjectRate(id);
    }


    /**
     * 首页项目客销度
     * @param projectRate
     * @return
     */
    @Override
    @Transactional
    public BigDecimal getSyProjectCount(ProjectRate projectRate) {
        String kll = projectRateMapper.getProjectRateByIdSy(projectRate); //项目客流量
        if(kll == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_004");
        }
        BigDecimal zkll = new BigDecimal(kll); //总客流量

        String mj = projectRateMapper.getProjectacreageById(projectRate.getProjectId()); //面积
        if(mj == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_005");
        }
        BigDecimal acreage = new BigDecimal(mj); //面积

        Duration duration = Duration.between(projectRate.getCreateTime(),projectRate.getModifyTime());

        BigDecimal day = new BigDecimal(duration.toDays()); //时间差

        BigDecimal rx = zkll.divide(acreage.multiply(day)); // 1 项目客流量除以面积
        String pb = projectRateMapper.getProjectBrandById(projectRate); // 2 项目下所有品牌销售额
        if(pb == null){
            throw exceptionUtil.throwCustomException("RENTING_RATE_005");
        }
        BigDecimal sale = new BigDecimal(pb);

        BigDecimal px = sale.divide(acreage.multiply(day)); //销售额 / 面积

        BigDecimal kxd = rx.multiply(px);
        return kxd.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
