package com.yinghuaicc.stars.controller.business.pc.intervalset;

import com.yinghuaicc.stars.common.utils.excel.ExcelImportUtil;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.Intervalset.ConditionGuestInterval;
import com.yinghuaicc.stars.repository.model.Intervalset.FloorGuestInterval;
import com.yinghuaicc.stars.repository.model.Intervalset.ProjectGuestInterval;
import com.yinghuaicc.stars.repository.model.Intervalset.mult.IntervalConditionGuestImportExcel;
import com.yinghuaicc.stars.repository.model.Intervalset.mult.IntervalFloorGuestImportExcel;
import com.yinghuaicc.stars.repository.model.Intervalset.mult.IntervalProjectGuestImportExcel;
import com.yinghuaicc.stars.service.cqrs.Intervalset.ConditionGuestIntervalService;
import com.yinghuaicc.stars.service.cqrs.Intervalset.FloorGuestIntervalService;
import com.yinghuaicc.stars.service.cqrs.Intervalset.ProjectGuestIntervalService;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.IntervalGuestImportExcelResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

/**
 * 区间设置导入Controller
 */
@RestController
@RequestMapping(value="/intervalimportexcel")
public class IntervalImportExcelController {

    @Autowired
    private ExcelImportUtil excelImportUtil;

    @Autowired
    private ProjectGuestIntervalService projectGuestIntervalService;

    @Autowired
    private FloorGuestIntervalService floorGuestIntervalService;

    @Autowired
    private ConditionGuestIntervalService conditionGuestIntervalService;

    /**
     *@Description: 区间设置客销度多Sheet导入
     */
    @PostMapping(value = "/excel/intervalguestimport/sheet")
    public JsonResult IntervalGuestExcelImportSheet(@RequestParam("file") MultipartFile file) {

        IntervalGuestImportExcelResponseDTO intervalGuestImportExcelResponseDTO = excelImportUtil.getExcelDataToListMultiSheet(file,IntervalGuestImportExcelResponseDTO.class);
        /*intervalGuestImportExcelResponseDTO = excelImportUtil.getExcelDataToListMultiSheet(file, IntervalGuestImportExcelResponseDTO.class);*/

        List<IntervalProjectGuestImportExcel> intervalProjectGuestImportExcels =  intervalGuestImportExcelResponseDTO.getIntervalProjectGuestImportExcelList();
        for(IntervalProjectGuestImportExcel intervalProjectGuestImportExcel:intervalProjectGuestImportExcels){
            if(intervalProjectGuestImportExcel==null) continue;
      //  intervalProjectGuestImportExcels.forEach(intervalProjectGuestImportExcel->{
            //id
             String constractId = intervalProjectGuestImportExcel.getConstractId();
            ProjectGuestInterval projectGuestInterval = new ProjectGuestInterval();
            projectGuestInterval = projectGuestIntervalService.findProjectByProjectId(constractId);
            if(projectGuestInterval==null) continue;
            //TODO---项目名称
            //项目名称
             String projcetName = intervalProjectGuestImportExcel.getProjcetName();
            //区间最大值
             BigDecimal maxvalue = intervalProjectGuestImportExcel.getMaxvalue();
            //区间增长百分比
            BigDecimal intervalRate = intervalProjectGuestImportExcel.getIntervalRate();
            projectGuestInterval.setProjectName(projcetName);
            projectGuestInterval.setMaxvalue(maxvalue);
            projectGuestInterval.setIntervalRate(intervalRate);
            if(projectGuestInterval!=null){
                projectGuestIntervalService.editProject(projectGuestInterval);
            }else{
                projectGuestIntervalService.saveProject(projectGuestInterval);
            }
      //  });
        }
        List<IntervalFloorGuestImportExcel> intervalFloorGuestImportExcels =  intervalGuestImportExcelResponseDTO.getIntervalFloorGuestImportExcelList();

        for(IntervalFloorGuestImportExcel intervalFloorGuestImportExcel:intervalFloorGuestImportExcels){

        //intervalFloorGuestImportExcels.forEach(intervalFloorGuestImportExcel->{
            if(intervalFloorGuestImportExcel==null) continue;
            //id
            String constractId = intervalFloorGuestImportExcel.getConstractId();

            FloorGuestInterval floorGuestInterval = floorGuestIntervalService.findProjectByProjectId(constractId);
            if(floorGuestInterval==null) continue;
            //项目名称
            String projcetName = intervalFloorGuestImportExcel.getProjcetName();

            //区间最大值
            BigDecimal maxvalue = intervalFloorGuestImportExcel.getMaxvalue();
            //区间增长百分比
            BigDecimal intervalRate = intervalFloorGuestImportExcel.getIntervalRate();
            floorGuestInterval.setProjectName(projcetName);
            floorGuestInterval.setMaxvalue(maxvalue);
            floorGuestInterval.setIntervalRate(intervalRate);
            if(floorGuestInterval!=null){
                floorGuestIntervalService.editFloorGuestInterval(floorGuestInterval);
            }else{
                floorGuestIntervalService.saveFloorGuestInterval(floorGuestInterval);
            }
       // });
        }
        List<IntervalConditionGuestImportExcel> intervalConditionGuestImportExcelLists =  intervalGuestImportExcelResponseDTO.getIntervalConditionGuestImportExcelList();
        //TODO----
        for(IntervalConditionGuestImportExcel intervalConditionGuestImportExcelList:intervalConditionGuestImportExcelLists){
            if(intervalConditionGuestImportExcelList==null) continue;
        //intervalConditionGuestImportExcelLists.forEach(intervalConditionGuestImportExcelList->{
            //id
            String constractId = intervalConditionGuestImportExcelList.getConstractId();

            ConditionGuestInterval conditionGuestInterval = conditionGuestIntervalService.findConditionById(constractId);
            if(conditionGuestInterval==null) continue;
            //项目名称
            String projcetName = intervalConditionGuestImportExcelList.getProjcetName();
            //区间最大值
            BigDecimal maxvalue = intervalConditionGuestImportExcelList.getMaxvalue();
            //区间增长百分比
            BigDecimal intervalRate = intervalConditionGuestImportExcelList.getIntervalRate();
            conditionGuestInterval.setProjectName(projcetName);
            conditionGuestInterval.setMaxvalue(maxvalue);
            conditionGuestInterval.setIntervalRate(intervalRate);
            if(conditionGuestInterval!=null){
                conditionGuestIntervalService.editConditionGuestInterval(conditionGuestInterval);
            }else{
                conditionGuestIntervalService.saveConditionGuestInterval(conditionGuestInterval);
            }
       // });

        }
        return JsonResult.success("success");
       /* return JsonResult.success(intervalGuestImportExcelResponseDTO);*/
    }


}
