package com.yinghuaicc.stars.controller.business.pc.triangle;


import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.service.cqrs.triangle.TriangleCQRSService;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.BrandTriangleRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/19 上午10:30
 * @Description: 动态三角形
 * @Modified:
 */
@RestController
@RequestMapping(value = "/triangle")
public class TriangleController {


    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private TriangleCQRSService triangleCQRSService;
    /**
     * 通过项目id查询三角理论
     * @param projectId
     * @return
     */
/*    @GetMapping(value = "/find/triangleproject/by/{projectId}")
    public JsonResult findProjectTriangleByProjectId(@PathVariable String projectId){

        return JsonResult.success(regionCQRSService.projectInfo(projectId));
    }*/

    /**
     * 通过项目id、楼层id查询三角理论
     * @param projectId
     * @return
     */
/*    @GetMapping(value = "/find/trianglefloor/by/{projectId,floorId}")
    public JsonResult findProjectTriangleByProjectId(@PathVariable String projectId){

        return JsonResult.success(regionCQRSService.projectInfo(projectId));
    }*/



    /**
     * 通过项目id、业态id、业种id查询三角理论
     * @param projectCQRSListRequestDTO
     * @param pageParam
     * @return
     */
/*    @PostMapping(value = "/find/project/list")
    public JsonResult findProjectList(@RequestBody ProjectCQRSListRequestDTO projectCQRSListRequestDTO, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                regionCQRSService.projectList(
                        projectCQRSListRequestDTO,
                        applicationContext.getBean(AopResourceEmployeeBean.class),
                        pageParam));
    }*/

    /**
     * 通过品牌id查询三角理论
     * @param brandTriangleRequestDTO
     * @param pageParam
     * @return
     */
    @PostMapping(value = "/find/brand/list")
    public JsonResult findProjectList(@RequestBody BrandTriangleRequestDTO brandTriangleRequestDTO, @ModelAttribute PageParam pageParam){

        return JsonResult.success(triangleCQRSService.findBrandTriangleByBrandId(brandTriangleRequestDTO));
    }

}
