package com.yinghuaicc.stars.controller.business.common.test;

import com.yinghuaicc.stars.common.utils.excel.ExcelImportUtil;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.controller.business.common.test.multi.ListTestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/13 上午10:55
 * @Description: ExcelController
 * @Modified:
 */
@RestController
@RequestMapping(value = "/excel")
public class ExcelController {

    @Autowired
    private ExcelImportUtil excelImportUtil;

    /**
     *@Author:Fly Created in 2018/7/13 上午10:57
     *@Description: 导入
     */
    @PostMapping(value = "/excel/import")
    public JsonResult excelImport(@RequestParam("file")MultipartFile file){

        List<TestDTO> result = excelImportUtil.getExcelDataToList(file, TestDTO.class);

        return JsonResult.success(result);
    }

    /**
     *@Author:Fly Created in 2018/7/22 下午4:59
     *@Description: 多Sheet导入
     */
    @PostMapping(value = "/excel/import/sheet")
    public JsonResult excelImportSheet(@RequestParam("file")MultipartFile file){

        return JsonResult.success(excelImportUtil.getExcelDataToListMultiSheet(file, ListTestDTO.class));
    }
}
