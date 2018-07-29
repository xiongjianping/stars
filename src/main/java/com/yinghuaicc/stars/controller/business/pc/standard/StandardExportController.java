package com.yinghuaicc.stars.controller.business.pc.standard;

import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.service.cqrs.standard.StandardExportService;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardExportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/7/29.
 */
@RestController
@RequestMapping(value="/standardexport")
public class StandardExportController {

    @Autowired
    StandardExportService standardExportService;

    /**
     * 
     * @param standardExportDTO
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/excel", method = RequestMethod.POST)
    public JsonResult excel(@RequestBody StandardExportDTO standardExportDTO, HttpServletResponse response) throws Exception {
        standardExportService.getStandardExportList(response,standardExportDTO);
        return JsonResult.success("OK");
    }
}
