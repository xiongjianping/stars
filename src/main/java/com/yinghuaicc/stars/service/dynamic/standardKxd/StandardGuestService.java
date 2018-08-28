package com.yinghuaicc.stars.service.dynamic.standardKxd;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuest;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuestSy;
import com.yinghuaicc.stars.service.dynamic.standardKxd.dto.StandardGuestListResponse;

import java.math.BigDecimal;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/20.
 */
public interface StandardGuestService {

    void saveStandardGuest(StandardGuest standardGuest);

    ResultPageList<StandardGuestListResponse> geStandardGuestList(StandardGuest standardGuest, PageParam pageParam);

    StandardGuest getStandardGuestById(String id);

    void deleteStandardGuestById(String id);

    void updateStandardGuest(StandardGuest standardGuest);

    BigDecimal getSyStandardProjectGuestCount(StandardGuestSy standardGuest);

    BigDecimal getSyStandardFloorGuestCount(StandardGuestSy standardGuest);

    BigDecimal getSyStandardFormGuestCount(StandardGuestSy standardGuest);

    BigDecimal getSyStandardBrandGuestCount(StandardGuestSy standardGuest);

}
