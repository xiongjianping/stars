package com.yinghuaicc.stars.service.dynamic.floor;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.floor.FloorRate;
import com.yinghuaicc.stars.repository.model.dynamic.floor.FloorRateSy;
import com.yinghuaicc.stars.service.dynamic.floor.dto.response.FloorRateListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
public interface FloorRateService {

    void saveFloorRate(FloorRate FloorRate);

    ResultPageList<FloorRateListResponse> getFloorRateList(FloorRate FloorRate, PageParam pageParam);

    FloorRate getFloorRateById(String id);

    void updateFloorRate(FloorRate FloorRate);

    void deleteFloorRate(String id);

    public BigDecimal getSyFloorRateCount(FloorRateSy FloorRate);
}
