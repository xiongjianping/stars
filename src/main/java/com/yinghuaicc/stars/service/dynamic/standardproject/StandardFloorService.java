package com.yinghuaicc.stars.service.dynamic.standardproject;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFloor;
import com.yinghuaicc.stars.repository.model.dynamic.standardproject.StandardFloorSy;
import com.yinghuaicc.stars.service.dynamic.standardproject.dto.response.StandardFloorListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public interface StandardFloorService {

    void saveStandardFloor(StandardFloor standardFloor);

    ResultPageList<StandardFloorListResponse> getStandardFloorList(StandardFloor standardFloor, PageParam pageParam);

    StandardFloor getStandardFloorById(String id);

    void updateStandardFloor(StandardFloor standardFloor);

    void deleteStandardFloor(String id);

    BigDecimal getSyFloorCount(StandardFloorSy standardFloor);

}
