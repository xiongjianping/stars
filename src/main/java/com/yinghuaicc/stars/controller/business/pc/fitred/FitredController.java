package com.yinghuaicc.stars.controller.business.pc.fitred;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.service.fitredval.FitredValService;
import com.yinghuaicc.stars.service.fitredval.dto.request.AddFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.request.GetFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.request.UpdateStatusFitredValDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 */
@RestController
@RequestMapping(value = "/fitred")
public class FitredController {

    @Autowired
    FitredValService fitredValService;

    /**
     *@Description: 添加公式
     */
    @PostMapping(value = "/save/fitred")
    public JsonResult addFitrdeVal(@RequestBody AddFitredValDTO addFitredValDTO){
        fitredValService.addFitrdeVal(addFitredValDTO);
        return JsonResult.success("OK");
    }

    /**
     *@Description: 列表
     */
    @PostMapping(value = "/find/fitred/list")
    public JsonResult getFitredValList(@RequestBody GetFitredValDTO getFitredValDTO, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                fitredValService.getFitredValList(getFitredValDTO, pageParam));
    }


    /**
     *@Description: 列表启用状态
     */
    @PostMapping(value = "/find/fitred/list/status")
    public JsonResult getFitrdeValListByStatus(@RequestBody GetFitredValDTO getFitredValDTO, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                fitredValService.getFitrdeValListByStatus(getFitredValDTO, pageParam));
    }

    /**
     *@Description: 详情
     */
    @GetMapping(value = "/find/fitred/id/{id}")
    public JsonResult getFitrdeValById(@PathVariable("id") String id){
        return JsonResult.success(
                fitredValService.getFitrdeValById(id));
    }

    /**
     * 修改
     */
    @PostMapping(value = "/update/fitred")
    public JsonResult updateFitredVal(@RequestBody AddFitredValDTO addFitredValDTO){
        addFitredValDTO.setModifyTime(LocalDateTime.now());
        fitredValService.updateFitredVal(addFitredValDTO);
        return JsonResult.success("OK");
    }

    /**
     * 修改状态删除
     */
    @GetMapping(value = "/update/fitred/del/{id}")
    public JsonResult updateFitredStatusDel(@PathVariable("id") String id){
        UpdateStatusFitredValDTO updateStatusFitredValDTO = new UpdateStatusFitredValDTO();
        updateStatusFitredValDTO.setStatus("3");
        updateStatusFitredValDTO.setId(id);
        fitredValService.updateFitredStatus(updateStatusFitredValDTO);
        return JsonResult.success("OK");
    }


    /**
     * 修改状态启用
     */
    @GetMapping(value = "/update/fitred/enb/{id}")
    public JsonResult updateFitredStatusEnb(@PathVariable("id") String id){
        UpdateStatusFitredValDTO updateStatusFitredValDTO = new UpdateStatusFitredValDTO();
        updateStatusFitredValDTO.setStatus("1");
        updateStatusFitredValDTO.setId(id);
        fitredValService.updateFitredStatus(updateStatusFitredValDTO);
        return JsonResult.success("OK");
    }

    /**
     * 修改状态禁用
     */
    @GetMapping(value = "/update/fitred/dis/{id}")
    public JsonResult updateFitredStatusDis(@PathVariable("id") String id){
        UpdateStatusFitredValDTO updateStatusFitredValDTO = new UpdateStatusFitredValDTO();
        updateStatusFitredValDTO.setStatus("2");
        updateStatusFitredValDTO.setId(id);
        fitredValService.updateFitredStatus(updateStatusFitredValDTO);
        return JsonResult.success("OK");
    }
}
