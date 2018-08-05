package com.yinghuaicc.stars.controller.business.pc.intervalset;

import com.yinghuaicc.stars.common.utils.excel.ExcelImportUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.response.JsonResult;
import com.yinghuaicc.stars.repository.model.Intervalset.ConditionGuestInterval;
import com.yinghuaicc.stars.repository.model.Intervalset.FloorGuestInterval;
import com.yinghuaicc.stars.repository.model.Intervalset.ProjectGuestInterval;
import com.yinghuaicc.stars.repository.model.Intervalset.mult.IntervalConditionGuestImportExcel;
import com.yinghuaicc.stars.repository.model.Intervalset.mult.IntervalFloorGuestImportExcel;
import com.yinghuaicc.stars.repository.model.Intervalset.mult.IntervalProjectGuestImportExcel;
import com.yinghuaicc.stars.repository.model.contract.Contract;
import com.yinghuaicc.stars.service.cqrs.Intervalset.ConditionGuestIntervalService;
import com.yinghuaicc.stars.service.cqrs.Intervalset.FloorGuestIntervalService;
import com.yinghuaicc.stars.service.cqrs.Intervalset.ProjectGuestIntervalService;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.IntervalGuestImportExcelResponseDTO;
import com.yinghuaicc.stars.service.cqrs.contract.ContractCQRSService;
import com.yinghuaicc.stars.service.cqrs.contract.dto.request.ContractTriangleCQRSListRequestDTO;
import com.yinghuaicc.stars.service.cqrs.contract.dto.response.ContractTriangleCQRSListResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.TriangleCQRSService;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.TimingByConditionResponseDTO;
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
    @Autowired
    private ContractCQRSService contractCQRSService;
    @Autowired
    private TriangleCQRSService triangleCQRSService;

    /**
     *@Description: 区间设置客销度多Sheet导入
     */
    @PostMapping(value = "/excel/intervalguestimport/sheet")
    public JsonResult IntervalGuestExcelImportSheet(@RequestParam("file") MultipartFile file) {

        IntervalGuestImportExcelResponseDTO intervalGuestImportExcelResponseDTO = excelImportUtil.getExcelDataToListMultiSheet(file,IntervalGuestImportExcelResponseDTO.class);
        /*intervalGuestImportExcelResponseDTO = excelImportUtil.getExcelDataToListMultiSheet(file, IntervalGuestImportExcelResponseDTO.class);*/

        List<IntervalProjectGuestImportExcel> intervalProjectGuestImportExcels =  intervalGuestImportExcelResponseDTO.getIntervalProjectGuestImportExcelList();

        for(IntervalProjectGuestImportExcel intervalProjectGuestImportExcel:intervalProjectGuestImportExcels){
            /*if(intervalProjectGuestImportExcel==null) continue;*/
            //id
             String constractId = intervalProjectGuestImportExcel.getConstractId();
            ProjectGuestInterval projectGuestInterval = new ProjectGuestInterval();
            //通过签约id查询签约信息
            List<TimingByConditionResponseDTO> timingByConditionResponseDTOS = triangleCQRSService.findByConditionId(constractId);
            TimingByConditionResponseDTO timingByConditionResponseDTO = timingByConditionResponseDTOS.get(0);

            projectGuestInterval = projectGuestIntervalService.findProjectByProjectId(timingByConditionResponseDTO.getProjectId());

            if(projectGuestInterval==null){
                projectGuestInterval = new ProjectGuestInterval();
                projectGuestInterval.setId(UuidUtil.randomUUID());
                //TODO---项目名称
                projectGuestInterval.setProjectId(timingByConditionResponseDTO.getProjectId());
                projectGuestInterval.setContractId(constractId);
                //项目名称
                String projcetName = intervalProjectGuestImportExcel.getProjcetName();
                //区间最大值
                BigDecimal maxvalue = intervalProjectGuestImportExcel.getMaxvalue();
                //区间增长百分比
                BigDecimal intervalRate = intervalProjectGuestImportExcel.getIntervalRate();
                projectGuestInterval.setProjectName(projcetName);
                projectGuestInterval.setMaxvalue(maxvalue);
                projectGuestInterval.setIntervalRate(intervalRate);
                //优秀
                projectGuestInterval.setYx(maxvalue.intValue());
                //良好
                projectGuestInterval.setLh(maxvalue.multiply(
                        intervalRate.add(new BigDecimal(40))
                                .divide(
                                        new BigDecimal(100)
                                )).intValue()
                );
                //提升
                projectGuestInterval.setTs(maxvalue.multiply(
                        intervalRate.add(new BigDecimal(25))
                                .divide(
                                        new BigDecimal(100)
                                )).intValue());
                //合格
                projectGuestInterval.setHl(maxvalue.multiply(
                        intervalRate.add(new BigDecimal(10))
                                .divide(
                                        new BigDecimal(100)
                                )).intValue());
                //亏损
                projectGuestInterval.setKs(0);
                projectGuestIntervalService.saveProject(projectGuestInterval);

            }else{
                //TODO---项目名称
                projectGuestInterval.setProjectId(constractId);
                //项目名称
                String projcetName = intervalProjectGuestImportExcel.getProjcetName();
                //区间最大值
                BigDecimal maxvalue = intervalProjectGuestImportExcel.getMaxvalue();
                //区间增长百分比
                BigDecimal intervalRate = intervalProjectGuestImportExcel.getIntervalRate();
                //优秀
                projectGuestInterval.setYx(maxvalue.intValue());
                //良好
                projectGuestInterval.setLh(maxvalue.multiply(
                        intervalRate.add(new BigDecimal(40))
                                .divide(
                                        new BigDecimal(100)
                                )).intValue()
                );
                //提升
                projectGuestInterval.setTs(maxvalue.multiply(
                        intervalRate.add(new BigDecimal(25))
                                .divide(
                                        new BigDecimal(100)
                                )).intValue());
                //合格
                projectGuestInterval.setHl(maxvalue.multiply(
                        intervalRate.add(new BigDecimal(10))
                                .divide(
                                        new BigDecimal(100)
                                )).intValue());
                //亏损
                projectGuestInterval.setKs(0);


                projectGuestInterval.setProjectName(projcetName);
                projectGuestInterval.setMaxvalue(maxvalue);
                projectGuestInterval.setIntervalRate(intervalRate);
                projectGuestIntervalService.editProject(projectGuestInterval);
            }

        }
        List<IntervalFloorGuestImportExcel> intervalFloorGuestImportExcels =  intervalGuestImportExcelResponseDTO.getIntervalFloorGuestImportExcelList();

        for(IntervalFloorGuestImportExcel intervalFloorGuestImportExcel:intervalFloorGuestImportExcels){

            //id
            String constractId = intervalFloorGuestImportExcel.getConstractId();
            //通过签约id查询签约信息
            Contract contract =  contractCQRSService.findContractById(constractId);

            FloorGuestInterval floorGuestInterval = floorGuestIntervalService.findFloorGuestIntervalByProjectIdAndFloorId(contract.getProjectId(),contract.getFloorId());
            if(floorGuestInterval ==null ){
                floorGuestInterval = new FloorGuestInterval();
            }
            //项目名称
            String projcetName = intervalFloorGuestImportExcel.getProjcetName();
            floorGuestInterval.setProjectId(contract.getProjectId());
            floorGuestInterval.setFloorId(contract.getFloorId());
            //区间最大值
            BigDecimal maxvalue = intervalFloorGuestImportExcel.getMaxvalue();
            //区间增长百分比
            BigDecimal intervalRate = intervalFloorGuestImportExcel.getIntervalRate();
            floorGuestInterval.setProjectName(projcetName);
            floorGuestInterval.setMaxvalue(maxvalue);
            floorGuestInterval.setIntervalRate(intervalRate);

            //优秀
            floorGuestInterval.setYx(maxvalue.intValue());
            //良好
            floorGuestInterval.setLh(maxvalue.multiply(
                    intervalRate.add(new BigDecimal(40))
                            .divide(
                                    new BigDecimal(100)
                            )).intValue()
            );
            //提升
            floorGuestInterval.setTs(maxvalue.multiply(
                    intervalRate.add(new BigDecimal(25))
                            .divide(
                                    new BigDecimal(100)
                            )).intValue());
            //合格
            floorGuestInterval.setHl(maxvalue.multiply(
                    intervalRate.add(new BigDecimal(10))
                            .divide(
                                    new BigDecimal(100)
                            )).intValue());
            //亏损
            floorGuestInterval.setKs(0);


            if(floorGuestInterval!=null){
                floorGuestIntervalService.editFloorGuestInterval(floorGuestInterval);
            }else{
                floorGuestInterval.setId(UuidUtil.randomUUID());
                floorGuestIntervalService.saveFloorGuestInterval(floorGuestInterval);
            }
       // });
        }
        List<IntervalConditionGuestImportExcel> intervalConditionGuestImportExcelLists =  intervalGuestImportExcelResponseDTO.getIntervalConditionGuestImportExcelList();
        //TODO----
        for(IntervalConditionGuestImportExcel intervalConditionGuestImportExcelList:intervalConditionGuestImportExcelLists){
            //id
            String constractId = intervalConditionGuestImportExcelList.getConstractId();

            ConditionGuestInterval conditionGuestInterval = conditionGuestIntervalService.findConditionByContractId(constractId);
            Contract contract = contractCQRSService.findContractById(constractId);

            ContractTriangleCQRSListRequestDTO   contractTriangleCQRSListRequestDTO = new ContractTriangleCQRSListRequestDTO();
            contractTriangleCQRSListRequestDTO.setProjectId(contract.getProjectId());
            contractTriangleCQRSListRequestDTO.setBrandId(contract.getBrandId());
            List<ContractTriangleCQRSListResponseDTO> contractTriangleCQRSListResponseDTOS = contractCQRSService.findContractTriangleCQRSListResponseDTO( contractTriangleCQRSListRequestDTO);
            ContractTriangleCQRSListResponseDTO contractTriangleCQRSListResponseDTO = contractTriangleCQRSListResponseDTOS.get(0);
            //项目名称
            String projcetName = intervalConditionGuestImportExcelList.getProjcetName();
            //区间最大值
            BigDecimal maxvalue = intervalConditionGuestImportExcelList.getMaxvalue();
            //区间增长百分比
            BigDecimal intervalRate = intervalConditionGuestImportExcelList.getIntervalRate();
            conditionGuestInterval.setProjectId(conditionGuestInterval.getProjectId());
            conditionGuestInterval.setProjectName(projcetName);
            conditionGuestInterval.setConditionId(contractTriangleCQRSListResponseDTO.getBusinessFormId());
            conditionGuestInterval.setConditionName(contractTriangleCQRSListResponseDTO.getBusinessFormName());
            conditionGuestInterval.setMajoId(contractTriangleCQRSListResponseDTO.getBusinessSpeciesId());
            conditionGuestInterval.setMajoName(contractTriangleCQRSListResponseDTO.getBusinessSpeciesName());
            conditionGuestInterval.setMaxvalue(maxvalue);
            conditionGuestInterval.setIntervalRate(intervalRate);
            //优秀
            conditionGuestInterval.setYx(maxvalue.intValue());
            //良好
            conditionGuestInterval.setLh(maxvalue.multiply(
                    intervalRate.add(new BigDecimal(40))
                            .divide(
                                    new BigDecimal(100)
                            )).intValue()
            );
            //提升
            conditionGuestInterval.setTs(maxvalue.multiply(
                    intervalRate.add(new BigDecimal(25))
                            .divide(
                                    new BigDecimal(100)
                            )).intValue());
            //合格
            conditionGuestInterval.setHl(maxvalue.multiply(
                    intervalRate.add(new BigDecimal(10))
                            .divide(
                                    new BigDecimal(100)
                            )).intValue());
            //亏损
            conditionGuestInterval.setKs(0);
            if(conditionGuestInterval!=null){
                conditionGuestIntervalService.editConditionGuestInterval(conditionGuestInterval);
            }else{
                conditionGuestIntervalService.saveConditionGuestInterval(conditionGuestInterval);
            }
        }
        return JsonResult.success("success");
       /* return JsonResult.success(intervalGuestImportExcelResponseDTO);*/
    }


}
