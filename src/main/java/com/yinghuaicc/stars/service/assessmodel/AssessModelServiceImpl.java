package com.yinghuaicc.stars.service.assessmodel;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.mapper.assessmodel.AssessModelMapper;
import com.yinghuaicc.stars.repository.mapper.fitredval.FitredValMapper;
import com.yinghuaicc.stars.repository.model.assessmodel.AssessFitredVal;
import com.yinghuaicc.stars.repository.model.assessmodel.AssessModel;
import com.yinghuaicc.stars.service.assessmodel.dto.request.AddAssessModelDTO;
import com.yinghuaicc.stars.service.assessmodel.dto.request.GetAssessModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 */
@Service
public class AssessModelServiceImpl implements AssessModelService {
    @Autowired
    AssessModelMapper assessModelMapper;

    @Autowired
    FitredValMapper fitredValMapper;

    /**
     * 查看列表
     * @param getAssessModelDTO
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<AssessModel> getAssessModelList(GetAssessModelDTO getAssessModelDTO, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<AssessModel> result = assessModelMapper.getAssessModelList(getAssessModelDTO);

        return new ResultPageList<AssessModel>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    /**
     * 新增
     * @param addAssessModelDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addAssessModel(AddAssessModelDTO addAssessModelDTO) {
        addAssessModelDTO.setId(UuidUtil.randomUUID());
        addAssessModelDTO.setStatus("1");
        addAssessModelDTO.setCreateTime(LocalDateTime.now());
        addAssessModelDTO.setModifyTime(LocalDateTime.now());
        assessModelMapper.addAssessModel(addAssessModelDTO);

        //新增另外的配置，并且计算想
        addAssessModelDTO.getAddFitredvalList().forEach(p->{
            AssessFitredVal a = new AssessFitredVal();
            a.setId(UuidUtil.randomUUID());
            a.setAssessId(addAssessModelDTO.getId());
            a.setFitredId(p.getFitredId());
            assessModelMapper.addAssessFitredVal(a);

        });

    }

    /**
     * 修改
     * @param addAssessModelDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAssessModel(AddAssessModelDTO addAssessModelDTO) {
        addAssessModelDTO.setModifyTime(LocalDateTime.now());
        assessModelMapper.updateAssessModel(addAssessModelDTO);

        assessModelMapper.updateAssessFitredVal(addAssessModelDTO.getId());
        //新增另外的配置，并且计算想
        addAssessModelDTO.getAddFitredvalList().forEach(p->{
            AssessFitredVal a = new AssessFitredVal();
            a.setId(UuidUtil.randomUUID());
            a.setAssessId(addAssessModelDTO.getId());
            a.setFitredId(p.getFitredId());
            assessModelMapper.addAssessFitredVal(a);

        });

    }

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Override
    public AssessModel getAssessModelById(String id) {
        AssessModel a = assessModelMapper.getAssessModelById(id);
        a.setFitredVals(assessModelMapper.getFitredValList(id));
        return a;
    }

    //更新状态
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAssessModelStatus(String status, String id) {
        assessModelMapper.updateAssessModelStatus(status,id);
    }


}
