package com.yinghuaicc.stars.controller.business.pc.assessmodel;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.service.assessmodel.AssessModelService;
import com.yinghuaicc.stars.service.assessmodel.dto.request.AddAssessModelDTO;
import com.yinghuaicc.stars.service.assessmodel.dto.request.GetAssessModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 */
@RestController
@RequestMapping(value = "/assess")
public class AssessModelController {

    @Autowired
    AssessModelService assessModelService;

    @Autowired
    ApplicationContext applicationContext;

    /**
     *@Description: 添加
     */

    @PostMapping(value = "/save/assess")
    public JsonResult addFitrdeVal(@RequestBody AddAssessModelDTO addAssessModelDTO){
        AopResourceEmployeeBean aopResourceEmployeeBean = applicationContext.getBean(AopResourceEmployeeBean.class);
        addAssessModelDTO.setCreateName(aopResourceEmployeeBean.getName());
        assessModelService.addAssessModel(addAssessModelDTO);
        return JsonResult.success("OK");
    }


    /**
     *@Description: 列表
     */
    @PostMapping(value = "/find/assess/list")
    public JsonResult getAssessModelList(@RequestBody GetAssessModelDTO getAssessModelDTO, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                assessModelService.getAssessModelList(getAssessModelDTO, pageParam));
    }

   /**
     *@Description: 详情
     */
    @GetMapping(value = "/find/assess/id/{id}")
    public JsonResult getAssessModelById(@PathVariable("id") String id){
        return JsonResult.success(
                assessModelService.getAssessModelById(id));
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update/assess")
    public JsonResult updateAssessModel(@RequestBody AddAssessModelDTO addAssessModelDTO){
        AopResourceEmployeeBean aopResourceEmployeeBean = applicationContext.getBean(AopResourceEmployeeBean.class);
        addAssessModelDTO.setUpdateName(aopResourceEmployeeBean.getName());
        assessModelService.updateAssessModel(addAssessModelDTO);
        return JsonResult.success("OK");
    }

    /**
     * 修改状态删除
     */
    @GetMapping(value = "/update/assess/del/{id}")
    public JsonResult updateAssessModelStatusDel(@PathVariable("id") String id){
        assessModelService.updateAssessModelStatus("3",id);
        return JsonResult.success("OK");
    }


    /**
     * 修改状态启用
     */
    @GetMapping(value = "/update/assess/enb/{id}")
    public JsonResult updateAssessModelStatusEnb(@PathVariable("id") String id){
        assessModelService.updateAssessModelStatus("1",id);
        return JsonResult.success("OK");
    }

    /**
     * 修改状态禁用
     */
    @GetMapping(value = "/update/assess/dis/{id}")
    public JsonResult updateAssessModelStatusDis(@PathVariable("id") String id){
        assessModelService.updateAssessModelStatus("2",id);
        return JsonResult.success("OK");
    }
}
