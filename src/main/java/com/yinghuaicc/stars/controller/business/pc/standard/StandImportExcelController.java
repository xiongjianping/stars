package com.yinghuaicc.stars.controller.business.pc.standard;

import com.yinghuaicc.stars.common.utils.excel.ExcelImportUtil;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.standard.mult.*;
import com.yinghuaicc.stars.service.cqrs.standard.StandardFloorFittedService;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardFittedImportExcelResponseDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardRentImportExcelResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 标准三角形导入
 */
@RestController
@RequestMapping(value = "/standimport")
public class StandImportExcelController {

    @Autowired
    private ExcelImportUtil excelImportUtil;
    /**
     *@Description: 标准三角形适配值多Sheet导入
     */
    @PostMapping(value = "/excel/standfittedimport/sheet")
    public JsonResult standFittedExcelImportSheet(@RequestParam("file")MultipartFile file) {

        StandardFittedImportExcelResponseDTO standardFittedImportExcelResponseDTO = excelImportUtil.getExcelDataToListMultiSheet(file, StandardFittedImportExcelResponseDTO.class);
        //项目适配值
        List<StandProjectFittedExcel> standardProjectFitteds = standardFittedImportExcelResponseDTO.getStandardProjectFitteds();

        //TODO---新增项目记录

        //楼层适配值
        List<StandFloorFittedExcel> standardFloorFitteds = standardFittedImportExcelResponseDTO.getStandardFloorFitteds();



        //TODO---新增楼层记录
        //业态适配值
        List<StandConditionFittedExcel> standardConditionFitteds = standardFittedImportExcelResponseDTO.getStandardConditionFitteds();

        //TODO---新增业态记录

        return JsonResult.success("success");
    }

    /**
     *@Description: 标准三角形溢租率多Sheet导入
     */
    @PostMapping(value = "/excel/standrentimport/sheet")
    public JsonResult standRentExcelImportSheet(@RequestParam("file")MultipartFile file) {

        StandardRentImportExcelResponseDTO standardRentImportExcelResponseDTO = excelImportUtil.getExcelDataToListMultiSheet(file, StandardRentImportExcelResponseDTO.class);
        //项目适配值
        List<StandProjectRentExcel> standardProjectFitteds = standardRentImportExcelResponseDTO.getStandProjectRentExcels();

        //TODO---新增项目记录

        //楼层适配值
        List<StandFloorRentExcel> standardFloorFitteds = standardRentImportExcelResponseDTO.getStandFloorRentExcels();

        //TODO---新增楼层记录
        //业态适配值
        List<StandConditionRentExcel> standardConditionFitteds = standardRentImportExcelResponseDTO.getStandConditionRentExcels();

        //TODO---新增业态记录

        return JsonResult.success("success");
    }

    /**
     *@Description: 标准三角形客销度导入
     */
    @PostMapping(value = "/excel/standguestimport/sheet")
    public JsonResult standGuestExcelImportSheet(@RequestParam("file")MultipartFile file) {

        List<StandConditionGuestExcel> standConditionGuestExcels = excelImportUtil.getExcelDataToList(file, StandConditionGuestExcel.class);

        //TODO---新增标准三角形客销度记录


        //TODO---新增客销度版本记录

        return JsonResult.success("success");
    }


}
