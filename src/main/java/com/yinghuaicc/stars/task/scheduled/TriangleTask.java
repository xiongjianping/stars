package com.yinghuaicc.stars.task.scheduled;

import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.controller.config.aop.pc.AopResourceEmployeeBean;
import com.yinghuaicc.stars.repository.mapper.cqrs.brand.BrandCQRSMapper;
import com.yinghuaicc.stars.repository.mapper.cqrs.contract.ContractCQRSMapper;
import com.yinghuaicc.stars.repository.mapper.triangle.TriangleMapper;
import com.yinghuaicc.stars.repository.model.contract.Contract;
import com.yinghuaicc.stars.repository.model.standard.StandardBrandSale;
import com.yinghuaicc.stars.repository.model.triangle.DayGuest;
import com.yinghuaicc.stars.repository.model.triangle.DayRent;
import com.yinghuaicc.stars.repository.model.triangle.QuarterFitted;
import com.yinghuaicc.stars.repository.model.triangle.TriangleCQRS;
import com.yinghuaicc.stars.service.contract.ContractService;
import com.yinghuaicc.stars.service.cqrs.brand.dto.response.BrandCQRSInfoResponseDTO;
import com.yinghuaicc.stars.service.cqrs.contract.dto.request.ContractTriangleCQRSListRequestDTO;
import com.yinghuaicc.stars.service.cqrs.contract.dto.response.ContractTriangleCQRSListResponseDTO;
import com.yinghuaicc.stars.service.cqrs.standard.StandardBrandSaleService;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardConditionFittedRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardConditionRentRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardConditionFittedResponseDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardConditionRentResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.DayGuestService;
import com.yinghuaicc.stars.service.cqrs.triangle.DayRentService;
import com.yinghuaicc.stars.service.cqrs.triangle.QuarterFittedService;
import com.yinghuaicc.stars.service.cqrs.triangle.TriangleCQRSService;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 三角理论定时器
 */
@Component
public class TriangleTask {

    @Autowired
    private TriangleMapper triangleMapper;

    @Autowired
    private DayRentService dayRentService;
    @Autowired
    private QuarterFittedService quarterFittedService;
    @Autowired
    private DayGuestService dayGuestService;

    @Autowired
   private ContractCQRSMapper contractCQRSMapper;

    @Autowired
    private TriangleCQRSService triangleCQRSService;
    @Autowired
    private StandardBrandSaleService standardBrandSaleService;

    /**
     *@Description: 获取三角理论数据,每天晚上11点59分执行
     */
//    @Scheduled(fixedRate = 200000)
    @Scheduled(cron = "0 59 23 ? * *")
    @Transactional(rollbackFor = Exception.class)
    public void getTriangle(){

        //TODO---通过签约id查询所有时间的客销度id contractId
        List<DayGuest> dayGuests = dayGuestService.findDayGuestBynull();

        for(DayGuest dayGuest: dayGuests){

            String contractId = dayGuest.getContractId();

            LocalDateTime contractTime = dayGuest.getCreateTime();

            //TODO--通过签约id 查询项目id、项目名称、楼层id、楼层 名称、业种、业态、铺位、品牌
            List<TimingByConditionResponseDTO>  timingByConditionResponseDTOS = triangleCQRSService.findByConditionId(contractId);
            TimingByConditionResponseDTO timingByConditionResponseDTO =  timingByConditionResponseDTOS.get(0);

             String projectId = timingByConditionResponseDTO.getProjectId() ;
             String projectName = timingByConditionResponseDTO.getProjectName();
             String floorId = timingByConditionResponseDTO.getFloorId();
             String floorName = timingByConditionResponseDTO.getFloorName();
            //铺位
             String roomId = timingByConditionResponseDTO.getRoomId();
             String roomName = timingByConditionResponseDTO.getRoomName();
             //业种
             String majoId = timingByConditionResponseDTO.getMajoId();
             String majoName = timingByConditionResponseDTO.getMajoName();
             //品牌
             String brandId = timingByConditionResponseDTO.getBrandId();
             String brandName = timingByConditionResponseDTO.getBrandName();
            //业态
             String conditionId = timingByConditionResponseDTO.getConditionId();
             String conditionName = timingByConditionResponseDTO.getConditionName();


            //TODO --签约id查询溢租率

            DayRentResponseDTO dayRentResponseDTO = dayRentService.findDayRentResponseDTOByContractId(contractId,contractTime);
            //租金
            BigDecimal rent = dayRentResponseDTO.getRent();
            //物业费
            BigDecimal propertyFee = dayRentResponseDTO.getPropertyFee();
            //员工成本
            BigDecimal  staffEmp = dayRentResponseDTO.getStaffEmp();
            //装修折旧
            BigDecimal fitment = dayRentResponseDTO.getFitment();
            //代理费
            BigDecimal  agencyFee = dayRentResponseDTO.getAgencyFee();

            //通过项目id、楼层id、业态id查询签约信息
            ContractTriangleCQRSListRequestDTO contractTriangleCQRSListRequestDTO = new ContractTriangleCQRSListRequestDTO();
            contractTriangleCQRSListRequestDTO.setProjectId(projectId);
            contractTriangleCQRSListRequestDTO.setFloorId(floorId);
            contractTriangleCQRSListRequestDTO.setBusinessFormId(conditionId);
            contractTriangleCQRSListRequestDTO.setBrandId(brandId);
            List<ContractTriangleCQRSListResponseDTO> contractCQRSList =  contractCQRSMapper.findContractTriangleCQRSListResponseDTO(contractTriangleCQRSListRequestDTO);
            BigDecimal area =new BigDecimal(0);
                for (ContractTriangleCQRSListResponseDTO contractTriangleCQRSListResponseDTO :contractCQRSList){
                    //品牌面积累计
                    area.add(contractTriangleCQRSListResponseDTO.getAcreage());
                }


            //查询面积
          /* BigDecimal area = contractTriangleCQRSListResponseDTO.getAcreage();*/


            //净利润-======通过签约id、projectId, conditionId,majoId查询标准三角形
            List<StandardBrandSale> standardBrandSaleList= standardBrandSaleService.findStandardBrandSaleByContractId(contractId,contractTime);
            StandardBrandSale standardBrandSale = standardBrandSaleList.get(0);
            //毛利率
             BigDecimal grossRate = standardBrandSale.getGrossRate();
            //客单价
             BigDecimal perSale = standardBrandSale.getPerSale();

            //TODO --签约id查询适配值
            QuarterFittedResponseDTO quarterFittedResponseDTO  = quarterFittedService.findQuarterFittedResponseDTOByContractId(contractId,contractTime);

            //市场定位
            Integer marketPosition = quarterFittedResponseDTO.getMarketPosition();
            //品牌定位
            Integer brandPosition = quarterFittedResponseDTO.getBrandPosition();
            //品牌形象
            Integer brandImage = quarterFittedResponseDTO.getBrandImage();
            //租费收缴率
            BigDecimal rentalRate = quarterFittedResponseDTO.getRentalRate();
            //连锁跟进度
            BigDecimal chainDegree = quarterFittedResponseDTO.getChainDegree();
            //客服投诉率
            BigDecimal complaintDegree = quarterFittedResponseDTO.getComplaintDegree();
            //企划配合度
            BigDecimal layoutDegree = quarterFittedResponseDTO.getLayoutDegree();
            //配合度
            BigDecimal phd = rentalRate.add(chainDegree).add(complaintDegree).add(layoutDegree);
            //TODO---适配值
            BigDecimal fitted = new BigDecimal(marketPosition+brandPosition+brandImage).multiply(phd);

            //TODO --签约id查询客销度
            DayGuestResponseDTO dayGuestResponseDTO = dayGuestService.findDayGuestByDayGuestByContractId(contractId,contractTime);

            //客流量
            BigDecimal passengerFlow = dayGuestResponseDTO.getPassengerFlow();
            //销售额
            BigDecimal saleroom = dayGuestResponseDTO.getSaleroom();
            //动态三角形客销度
            BigDecimal triangleguest= passengerFlow.divide(area).multiply(saleroom.divide(area));

            //BigDecimal jlr =净利润=销售收入*毛利率-rent.add(propertyFee).add(staffEmp).add(fitment).add(agencyFee);
            BigDecimal jlr =saleroom.multiply(grossRate).subtract(rent.add(propertyFee).add(staffEmp).add(fitment).add(agencyFee));


            BigDecimal triangleRent = new BigDecimal(0);
            triangleRent = jlr.divide(rent.add(propertyFee));
            //-----新增 ----------------------------
            TriangleCQRS triangleCQRS = new TriangleCQRS();
            triangleCQRS.setId(UuidUtil.randomUUID());
            triangleCQRS.setRent(triangleRent);
            triangleCQRS.setGuest(triangleguest);
            triangleCQRS.setFitted(fitted);
            //签约id
            triangleCQRS.setContractId(contractId);
           // triangleCQRS.setArea();//TODO
            triangleCQRS.setProjectId(projectId);
            triangleCQRS.setProjectName(projectName);
            triangleCQRS.setFloorId(floorId);
            triangleCQRS.setFloorName(floorName);
            //品牌id
            triangleCQRS.setBrandId(brandId);
            triangleCQRS.setBrandName(brandName);
            triangleCQRS.setConditionId(conditionId);
            //业态名称
            triangleCQRS.setConditionName(conditionName);
            //业种id
            triangleCQRS.setMajoId(majoId);
            triangleCQRS.setMajoName(majoName);

            triangleCQRS.setRoomId(roomId);
            //铺位名称
            triangleCQRS.setRoomName(roomName);
            //签约时间
            triangleCQRS.setContractTime(LocalDateTime.now());
            triangleCQRS.setCreateTime(LocalDateTime.now());
            triangleCQRS.setModifyTime(LocalDateTime.now());
            triangleCQRS.setCreateUser("System");
            triangleCQRS.setModifyUser("System");

            //TODO --签约id查询三角理论是否存在
            List<TriangleCQRS> triangleCQRSList = triangleMapper.findTriangleByTriangleId(contractId,contractTime);
            if(triangleCQRSList==null || triangleCQRSList.size()<=0){
                //TODO --新增三角理论
                triangleMapper.saveTriangleCQRS(triangleCQRS);
            }else{
                //TODO --修改三角理论
                triangleCQRS.setId(triangleCQRSList.get(0).getId());
                triangleMapper.editTriangleCQRS(triangleCQRS);
            }
        }

    }


}
