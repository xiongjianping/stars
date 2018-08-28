package com.yinghuaicc.stars.service.dynamic.fitted;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFloor;
import com.yinghuaicc.stars.repository.model.dynamic.fitted.FittedFloorSy;
import com.yinghuaicc.stars.service.dynamic.fitted.dto.response.FittedFloorListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public interface FittedFloorService {

    void saveFittedFloor(FittedFloor fittedFloor);

    ResultPageList<FittedFloorListResponse> getFittedFloorList(FittedFloor fittedFloor , PageParam pageParam);

    FittedFloor getFittedFloorById(String id);

    void updateFittedFloor(FittedFloor fittedFloor);

    void deleteFittedFloor(String id);

    BigDecimal getFittedFloor(FittedFloorSy fittedFloor);

}
