package com.yinghuaicc.stars.service.fitredval;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.service.fitredval.dto.request.AddFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.request.GetFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.request.UpdateStatusFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.response.FitredValResponse;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 */
public interface FitredValService {

    /**
     * 新增
     * @param addFitredValDTO
     */
    void addFitrdeVal(AddFitredValDTO addFitredValDTO);

    ResultPageList<FitredValResponse> getFitredValList(GetFitredValDTO getFitredValDTO, PageParam pageParam);

    FitredValResponse getFitrdeValById(String id);

    void updateFitredVal(AddFitredValDTO addFitredValDTO);

    /**
     * 修改状态
     * @param updateStatusFitredValDTO
     */
    void updateFitredStatus(UpdateStatusFitredValDTO updateStatusFitredValDTO);
}
