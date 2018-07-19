package com.yinghuaicc.stars.controller.business.pc.help;

import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.service.help.HelpService;
import com.yinghuaicc.stars.service.help.dto.request.EditHelpContextRequestDTO;
import com.yinghuaicc.stars.service.help.dto.request.SaveHelpContextRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/19 下午5:11
 * @Description: 帮扶计划Controller
 * @Modified:
 */
@RestController
@RequestMapping(value = "/help")
public class HelpController {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private HelpService helpService;

    /**
     *@Author:Fly Created in 2018/7/19 下午5:12
     *@Description: 添加帮扶内容
     */
    @PostMapping(value = "/save/help/context")
    public JsonResult saveHelpContext(@Validated @RequestBody SaveHelpContextRequestDTO saveHelpContextRequestDTO){

        helpService.saveHelpContext(
                saveHelpContextRequestDTO, applicationContext.getBean(AopResourceEmployeeBean.class).getId());

        return JsonResult.success("OK");
    }

    /**
     *@Author:Fly Created in 2018/7/19 下午5:16
     *@Description: 编辑帮扶内容
     */
    @PostMapping(value = "/edit/help/context")
    public JsonResult editHelpContext(@Validated @RequestBody EditHelpContextRequestDTO editHelpContextRequestDTO){

        helpService.editHelpContext(
                editHelpContextRequestDTO, applicationContext.getBean(AopResourceEmployeeBean.class).getId());

        return JsonResult.success("OK");
    }

    /**
     *@Author:Fly Created in 2018/7/19 下午5:17
     *@Description: 删除帮扶内容
     */
    @GetMapping(value = "/remove/help/context/{id}")
    public JsonResult removeHelpContext(@PathVariable String id){

        helpService.removeHelpContext(id);

        return JsonResult.success("OK");
    }

    /**
     *@Author:Fly Created in 2018/7/19 下午5:20
     *@Description: 查询帮扶内容列表
     */
    @PostMapping(value = "/find/help/context/list")
    public JsonResult findHelpContextList(@RequestBody Map map, @ModelAttribute PageParam pageParam){

        return JsonResult.success(
                helpService.findHelpContextList(pageParam,
                        Objects.nonNull(map.get("type")) ? Integer.parseInt(map.get("type").toString()) : null));
    }

}