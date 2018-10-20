package com.yinghuaicc.stars.service.dynamic.rentingRate;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.model.dynamic.rentingRate.RentingRate;
import com.yinghuaicc.stars.repository.model.dynamic.standardkxd.StandardGuestSy;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.request.getRentingRateListRequest;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.response.RentingRateDetailResponse;
import com.yinghuaicc.stars.service.dynamic.rentingRate.dto.response.RentingRateListResponse;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/17.
 */
public interface RentingRateService {
    void saveRentingRate(RentingRate rentingRate);

    ResultPageList<RentingRateListResponse> getRentingRateList(getRentingRateListRequest getRentingRateListRequest, PageParam pageParam);

    RentingRateDetailResponse getRentingRateById(String id);

    void updateRentingRate(RentingRate rentingRate);

    void deleteRentingRateById(String id);

    BigDecimal getSyProjectRentingRateCount(StandardGuestSy standardGuest);

    List<String> getSyWtProjectRentingRateCount(StandardGuestSy standardGuest);

    BigDecimal getSyFloorRentingRateCount(StandardGuestSy standardGuest);

    List<String> getSyWtFloorRentingRateCount(StandardGuestSy standardGuest);

    BigDecimal getSyFromRentingRateCount(StandardGuestSy standardGuest);

    List<String> getSyWtFromRentingRateCount(StandardGuestSy standardGuest);

    BigDecimal getSyBrandRentingRateCount(StandardGuestSy standardGuest);

    List<String> getSyWtBrandRentingRateCount(StandardGuestSy standardGuest);
}
