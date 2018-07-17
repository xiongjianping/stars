package com.yinghuaicc.stars.controller.business.pc.tissue;

import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.service.tissue.TissueService;
import com.yinghuaicc.stars.service.tissue.dto.request.EditDepartmentRequestDTO;
import com.yinghuaicc.stars.service.tissue.dto.request.EditEmployeeProjectDataRequestDTO;
import com.yinghuaicc.stars.service.tissue.dto.request.EmployeeLoginRequestDTO;
import com.yinghuaicc.stars.service.tissue.dto.request.SaveDepartmentRequestDTO;
import com.yinghuaicc.stars.service.tissue.dto.response.ConfEmployeeRoleRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



/**
 * @Author:Fly
 * @Date:Create in 2018/7/2 上午1:44
 * @Description: 组织
 * @Modified:
 */
@RestController
@RequestMapping(value = "/tissue")
public class TissueController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    private TissueService tissueService;

    /**
     *@Author:Fly Created in 2018/7/2 上午3:53
     *@Description: 设置员工角色
     */
    @PostMapping(value = "/conf/employee/role")
    public JsonResult confEmployeeRole(@Validated @RequestBody ConfEmployeeRoleRequestDTO confEmployeeRoleRequestDTO){

        tissueService.confEmployeeRole(
                confEmployeeRoleRequestDTO, applicationContext.getBean(AopResourceEmployeeBean.class).getId());

        return JsonResult.success("OK");
    }

    /**
     *@Author:Fly Created in 2018/7/2 下午7:02
     *@Description: 员工登录
     */
    @PostMapping(value = "/employee/login")
    public JsonResult employeeLogin(@Validated @RequestBody EmployeeLoginRequestDTO employeeLoginRequestDTO){

        return JsonResult.success(tissueService.employeeLogin(employeeLoginRequestDTO));
    }

    /**
     *@Author:Fly Created in 2018/7/3 上午10:11
     *@Description: 用户信息
     */
    @GetMapping(value = "/employee/info/{loginEmployeeId}")
    public JsonResult employeeInfo(@PathVariable String loginEmployeeId){

        return JsonResult.success(tissueService.employeeInfo(loginEmployeeId));
    }

    /**
     *@Author:Fly Created in 2018/7/3 下午2:04
     *@Description: 员工退出登录
     */
    @GetMapping(value = "/employee/out/login")
    public JsonResult employeeOutLogin(){

        tissueService.employeeOutLogin(
                applicationContext.getBean(AopResourceEmployeeBean.class).getId());

        return JsonResult.success("OK");
    }

    /**
     *@Author:Fly Created in 2018/7/5 下午3:57
     *@Description: 查询员工项目数据权限
     */
    @GetMapping(value = "/find/employee/project/data/{employeeId}")
    public JsonResult findEmployeeProjectData(@PathVariable String employeeId){

        return JsonResult.success(tissueService.findEmployeeProjectDataByEmployeeId(employeeId));
    }

    /**
     *@Author:Fly Created in 2018/7/5 下午4:01
     *@Description: 给员工设置数据权限
     */
    @PostMapping(value = "/edit/employee/project/data")
    public JsonResult editEmployeeProjectData(@Validated @RequestBody EditEmployeeProjectDataRequestDTO editEmployeeProjectDataRequestDTO){

        tissueService.editEmployeeProjectData(
                editEmployeeProjectDataRequestDTO, applicationContext.getBean(AopResourceEmployeeBean.class).getId());

        return JsonResult.success("OK");
    }

    /**
     *@Author:Fly Created in 2018/7/6 下午4:26
     *@Description: 添加部门
     */
    @PostMapping(value = "/save/department")
    public JsonResult saveDepartment(@Validated @RequestBody SaveDepartmentRequestDTO saveDepartmentRequestDTO){

        tissueService.saveDepartment(
                saveDepartmentRequestDTO, applicationContext.getBean(AopResourceEmployeeBean.class).getId());

        return JsonResult.success("OK");
    }

    /**
     *@Author:Fly Created in 2018/7/6 下午4:29
     *@Description: 编辑部门
     */
    @PostMapping(value = "/edit/department")
    public JsonResult edit(@Validated @RequestBody EditDepartmentRequestDTO editDepartmentRequestDTO){

        tissueService.editDepartment(
                editDepartmentRequestDTO, applicationContext.getBean(AopResourceEmployeeBean.class).getId());

        return JsonResult.success("OK");
    }

    /**
     *@Author:Fly Created in 2018/7/6 下午4:30
     *@Description: 删除部门
     */
    @GetMapping(value = "/remove/department/{departmentId}")
    public JsonResult removeDepartment(@PathVariable String departmentId){

        tissueService.removeDepartment(departmentId);

        return JsonResult.success("OK");
    }
}
