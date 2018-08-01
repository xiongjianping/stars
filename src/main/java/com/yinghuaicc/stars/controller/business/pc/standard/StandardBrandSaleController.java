package com.yinghuaicc.stars.controller.business.pc.standard;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.service.cqrs.standard.StandardBrandSaleService;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardBrandSaleRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/standardbrandsale")
public class StandardBrandSaleController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private StandardBrandSaleService standardBrandSaleService;

    /**
     * 标准三角形品牌客销度
     * @param standardBrandSaleRequestDTO
     * @param pageParam
     * @return
     */
    @PostMapping(value = "/find/standardbrandsale/list")
    public JsonResult findStandardConditionFittedList(@RequestBody StandardBrandSaleRequestDTO standardBrandSaleRequestDTO, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                standardBrandSaleService.findStandardBrandSaleByStandardBrandSaleCQRS(standardBrandSaleRequestDTO, pageParam));
    }

}
