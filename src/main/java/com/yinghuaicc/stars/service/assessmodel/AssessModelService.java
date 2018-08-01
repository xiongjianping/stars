package com.yinghuaicc.stars.service.assessmodel;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.assessmodel.AssessModel;
import com.yinghuaicc.stars.service.assessmodel.dto.request.AddAssessModelDTO;
import com.yinghuaicc.stars.service.assessmodel.dto.request.GetAssessModelDTO;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 */
public interface AssessModelService {

    ResultPageList<AssessModel> getAssessModelList(GetAssessModelDTO getAssessModelDTO, PageParam pageParam);

    void addAssessModel(AddAssessModelDTO addAssessModelDTO);

    void updateAssessModel(AddAssessModelDTO addAssessModelDTO);

    AssessModel getAssessModelById(String id);

    void updateAssessModelStatus(String status,String id);
}
