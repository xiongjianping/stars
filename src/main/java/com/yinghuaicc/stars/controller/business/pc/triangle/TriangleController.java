package com.yinghuaicc.stars.controller.business.pc.triangle;


import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.triangle.TriangleMapper;
import com.yinghuaicc.stars.repository.model.triangle.TriangleCQRS;
import com.yinghuaicc.stars.service.cqrs.brand.BrandCQRSService;
import com.yinghuaicc.stars.service.cqrs.brand.dto.request.AppBrandCQRSListRequestDTO;
import com.yinghuaicc.stars.service.cqrs.region.RegionCQRSService;
import com.yinghuaicc.stars.service.cqrs.triangle.TriangleCQRSService;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.BrandTriangleRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.TriangeConditionRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.TriangleCQRSRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.BrandTriangleResponseDTO;
import com.yinghuaicc.stars.service.region.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/19 上午10:30
 * @Description: 动态三角形
 * @Modified:
 */
@RestController
@RequestMapping(value = "/pctriangle")
public class TriangleController {


    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TriangleCQRSService triangleCQRSService;

    @Autowired
    private RegionService regionService;
    @Autowired
    private RegionCQRSService regionCQRSService;

    @Autowired
    private BrandCQRSService brandCQRSService;



    /**
     * 根据项目id、楼层id查询项目下业态
     * @param triangeConditionRequestDTO
     * @return
     */
    @PostMapping(value = "/find/Conditionlist/project")
    public  JsonResult findConditionlistByOtherId(@RequestBody TriangeConditionRequestDTO triangeConditionRequestDTO){
        return   JsonResult.success(regionService.findConditionlistByOtherId(triangeConditionRequestDTO));
    }


    /**
     * 通过项目id查询三角理论
     * @param brandTriangleRequestDTO
     * @return
     */
    @PostMapping(value = "/find/triangleproject/byprojectId")
    public JsonResult findProjectTriangleByProjectId(@RequestBody BrandTriangleRequestDTO brandTriangleRequestDTO){

        return JsonResult.success(triangleCQRSService.findBrandTriangleByProjectId(brandTriangleRequestDTO));
    }

    /**
     * 通过项目id、楼层id查询三角理论
     * @param brandTriangleRequestDTO
     * @return
     */
    @PostMapping(value = "/find/trianglefloor/byfloorId")
    public JsonResult findProjectTriangleByFloorId(@RequestBody BrandTriangleRequestDTO brandTriangleRequestDTO){

        return JsonResult.success(triangleCQRSService.findBrandTriangleByFloorId(brandTriangleRequestDTO));
    }

    /**
     * 通过项目id、业态id、业种id查询三角理论
     * @param brandTriangleRequestDTO
     * @return
     */
   @PostMapping(value = "/find/trianglecondition/byconditionId")
    public JsonResult findProjectTriangleByConditionId(@RequestBody BrandTriangleRequestDTO brandTriangleRequestDTO){

        return JsonResult.success(triangleCQRSService.findBrandTriangleByConditionId(brandTriangleRequestDTO));
    }

    /**
     * 通过品牌id查询三角理论
     * @param brandTriangleRequestDTO
     * @return
     */
    @PostMapping(value = "/find/trianglebrand/byBrandId")
    public JsonResult findProjectTriangleByBrandId(@RequestBody BrandTriangleRequestDTO brandTriangleRequestDTO){
        System.out.println("开始时间：" + LocalDateTime.now());
        BrandTriangleResponseDTO brandTriangleResponseDTO = triangleCQRSService.findBrandTriangleByBrandId(brandTriangleRequestDTO);
        System.out.println("结束时间：" + LocalDateTime.now());
        return JsonResult.success(brandTriangleResponseDTO);
    }


    /**
     * 查询全国各区域客流量、销售量
     */
    @GetMapping(value = "/find/salepassengerflow/all")
    public JsonResult findProjectTriangleByBrandId(){
        return JsonResult.success(triangleCQRSService.findSalePassengerFlowAll());
    }
  /*  *//**
     * 根据项目id查询项目图片
     * @param projectId
     * @return
     *//*
    @GetMapping(value = "/find/projectimage/project/{projectId}")
    public JsonResult findProjectImageByProjectId(@PathVariable String projectId){
        return JsonResult.success(regionService.findProjectImageByProjectId(projectId));
    }*/

    /**
     *@Author:Fly Created in 2018/7/4 下午1:57
     *@Description: 项目详情
     */
    @GetMapping(value = "/find/project/by/{projectId}")
    public JsonResult findProjectById(@PathVariable String projectId){

        return JsonResult.success(
                regionCQRSService.projectInfo(projectId));
    }

    /**
     *@Description: 三角理论列表
     */
    @PostMapping(value = "/find/findTriangle/byCQRS")
    public JsonResult findTriangleByCQRS(@RequestBody  TriangleCQRSRequestDTO triangleCQRSRequestDTO){
        return JsonResult.success(triangleCQRSService.findTriangleCQRSByCQRS(triangleCQRSRequestDTO));
    }

    /**
     * 手动计算动态三角形
     */
    @GetMapping(value = "/save/triangletiming/all")
    public JsonResult saveTimingTriangle(){
        try {
            triangleCQRSService.saveTimingTriangle();
        } catch (Exception e) {
            return JsonResult.success("error");
        }
        return JsonResult.success("success");
    }

    /**
     * 专用查询品牌列表
     */
    @PostMapping(value = "/find/appbrand/list")
    public JsonResult findAppBrandList(@RequestBody AppBrandCQRSListRequestDTO appBrandCQRSListRequestDTO){
        return JsonResult.success(brandCQRSService.appBrandListCQRS(appBrandCQRSListRequestDTO));
    }
}
