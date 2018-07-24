package com.yinghuaicc.stars.service.cqrs.triangle;


import com.yinghuaicc.stars.repository.mapper.Intervalset.ConditionGuestIntervalMapper;
import com.yinghuaicc.stars.repository.mapper.cqrs.brand.BrandCQRSMapper;
import com.yinghuaicc.stars.repository.mapper.cqrs.contract.ContractCQRSMapper;
import com.yinghuaicc.stars.repository.mapper.standard.StandardBrandSaleMapper;
import com.yinghuaicc.stars.repository.mapper.standard.StandardConditionFittedMapper;
import com.yinghuaicc.stars.repository.mapper.standard.StandardConditionRentMapper;
import com.yinghuaicc.stars.repository.mapper.triangle.DayGuestMapper;
import com.yinghuaicc.stars.repository.mapper.triangle.DayRentMapper;
import com.yinghuaicc.stars.repository.mapper.triangle.QuarterFittedMapper;
import com.yinghuaicc.stars.repository.mapper.triangle.TriangleMapper;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.ConditionGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.ConditionGuestIntervalResponseDTO;
import com.yinghuaicc.stars.service.cqrs.brand.dto.response.BrandCQRSInfoResponseDTO;
import com.yinghuaicc.stars.service.cqrs.contract.dto.request.ContractTriangleCQRSListRequestDTO;
import com.yinghuaicc.stars.service.cqrs.contract.dto.response.ContractTriangleCQRSListResponseDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardBrandSaleRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardConditionFittedRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardConditionRentRequestDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardBrandSaleResponseDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardConditionFittedResponseDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardConditionRentResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.BrandTriangleRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.BrandTriangleResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.DayGuestResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.DayRentResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.QuarterFittedResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TriangleCQRSServiceImpl implements TriangleCQRSService{

    @Autowired
    private TriangleMapper triangleMapper;
    //品牌服务
    @Autowired
    private BrandCQRSMapper brandCQRSMapper;
    //签约服务
    @Autowired
    private ContractCQRSMapper contractCQRSMapper;
    //溢租率
    @Autowired
    private DayRentMapper dayRentMapper;
    //季度适配值
    @Autowired
    private QuarterFittedMapper quarterFittedMapper;
    //每日客销度
    @Autowired
    private DayGuestMapper dayGuestMapper;

    //标准三角形品牌溢租率
    @Autowired
    private StandardConditionRentMapper standardConditionRentMapper;
    //标准三角形品牌溢租率
    @Autowired
    private StandardConditionFittedMapper standardConditionFittedMapper;
    //标准三角形客销度
    @Autowired
    private StandardBrandSaleMapper standardBrandSaleMapper;

    //区间设置品牌客销度
    @Autowired
    private ConditionGuestIntervalMapper conditionGuestIntervalMapper;

    @Override
    public BrandTriangleResponseDTO findBrandTriangleByBrandId(BrandTriangleRequestDTO brandTriangleRequestDTO) {
        //品牌三角理论展示数据
        BrandTriangleResponseDTO brandTriangleResponseDTO = new BrandTriangleResponseDTO();

        //品牌id
        String brandId = brandTriangleRequestDTO.getBrandId();
        //通过品牌id查询品牌信息（品牌id、业态id、业种id）
        BrandCQRSInfoResponseDTO brandCQRSInfoResponseDTO = brandCQRSMapper.brandInfoCQRS(brandId);
        //业态id
        String conditionId = brandCQRSInfoResponseDTO.getBusinessFormId();
        String conditionName = brandCQRSInfoResponseDTO.getBusinessFormName();
        //业种id
        String majoId = brandCQRSInfoResponseDTO.getBusinessSpeciesId();
        String majoName = brandCQRSInfoResponseDTO.getBusinessSpeciesId();

        ContractTriangleCQRSListRequestDTO contractTriangleCQRSListRequestDTO= new ContractTriangleCQRSListRequestDTO();
        contractTriangleCQRSListRequestDTO.setProjectId(brandTriangleRequestDTO.getProjectId());
        contractTriangleCQRSListRequestDTO.setFloorId(brandTriangleRequestDTO.getFloorId());
        contractTriangleCQRSListRequestDTO.setBrandId(brandTriangleRequestDTO.getBrandId());
        contractTriangleCQRSListRequestDTO.setBusinessFormId(brandTriangleRequestDTO.getConditionId());
        //通过品牌id查询签约表（签约id、项目id、楼层id、铺位id）
        List<ContractTriangleCQRSListResponseDTO> contractCQRSList =  contractCQRSMapper.findContractTriangleCQRSListResponseDTO(contractTriangleCQRSListRequestDTO);
        ContractTriangleCQRSListResponseDTO  contractTriangleCQRSListResponseDTO = contractCQRSList.get(0);

        //项目id
        String projectId = brandTriangleRequestDTO.getProjectId();
        //签约id
        String contractId = contractTriangleCQRSListResponseDTO.getId();
        //查询面积
        BigDecimal area = contractTriangleCQRSListResponseDTO.getAcreage();

        //通过签约id+当天时间查询溢租率租费信息
        DayRentResponseDTO dayRentResponseDTO = dayRentMapper.findDayRentResponseDTOByContractId(contractId,LocalDateTime.now());
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


        //通过签约id+当天时间查询适配值信息，然后计算出品牌适配值
        QuarterFittedResponseDTO quarterFittedResponseDTO = quarterFittedMapper.findQuarterFittedResponseDTOByContractId(contractId,LocalDateTime.now());
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
        brandTriangleResponseDTO.setTriangleFitted(new BigDecimal(marketPosition+brandPosition+brandImage).multiply(phd));

        //通过签约id+当天时间查询客销度（客流量、销售额）计算客销度
        DayGuestResponseDTO dayGuestResponseDTO = dayGuestMapper.findDayGuestByDayGuestByContractId(contractId,LocalDateTime.now());

        //客流量
        BigDecimal passengerFlow = dayGuestResponseDTO.getPassengerFlow();
        //销售额
        BigDecimal saleroom = dayGuestResponseDTO.getSaleroom();
        BigDecimal triangleguest= passengerFlow.divide(area).multiply(saleroom.divide(area));
        //赋值客销度
        brandTriangleResponseDTO.setTriangleGuest(triangleguest);

        //通过项目id、业态id、业种id查询标准三角形溢租率
        StandardConditionRentRequestDTO standardConditionRentRequestDTO = new StandardConditionRentRequestDTO();
        standardConditionRentRequestDTO.setProjectId(projectId);
        standardConditionRentRequestDTO.setConditionId(conditionId);
        standardConditionRentRequestDTO.setMajoId(majoId);
        standardConditionRentRequestDTO.setConditionName(conditionName);
        standardConditionRentRequestDTO.setMajoName(majoName);
        List<StandardConditionRentResponseDTO> standardConditionRentResponseDTOS = standardConditionRentMapper.findStandardConditionRentByStandardConditionRentCQRS(standardConditionRentRequestDTO);

        //设置标准三角形溢租率
        brandTriangleResponseDTO.setStandardRent(standardConditionRentResponseDTOS.get(0).getRent());

        //通过项目id、业态id、业种id查询标准三角形适配值
        StandardConditionFittedRequestDTO standardConditionFittedRequestDTO = new  StandardConditionFittedRequestDTO();
        standardConditionFittedRequestDTO.setProjectId(projectId);
        standardConditionFittedRequestDTO.setConditionId(conditionId);
        standardConditionFittedRequestDTO.setConditionName(conditionName);
        standardConditionFittedRequestDTO.setMajoId(majoId);
        standardConditionFittedRequestDTO.setMajoName(majoName);
        List<StandardConditionFittedResponseDTO> standardConditionFittedResponseDTOS = standardConditionFittedMapper.findStandardConditionFittedByStandardConditionFittedCQRS(standardConditionFittedRequestDTO);
        //设置品牌适配值
        brandTriangleResponseDTO.setStandardFitted(standardConditionFittedResponseDTOS.get(0).getFitted());
        //通过签约id查询标准三角形客销度

        StandardBrandSaleRequestDTO standardBrandSaleRequestDTO  = new StandardBrandSaleRequestDTO();
        List<StandardBrandSaleResponseDTO> standardBrandSaleResponseDTOs =  standardBrandSaleMapper.findStandardBrandSaleByStandardBrandSaleCQRS(standardBrandSaleRequestDTO);
        //毛利率
        BigDecimal grossRate =  standardBrandSaleResponseDTOs.get(0).getGrossRate();
        //客单价
        BigDecimal perSale =  standardBrandSaleResponseDTOs.get(0).getPerSale();

        //BigDecimal jlr =净利润=销售收入*毛利率-rent.add(propertyFee).add(staffEmp).add(fitment).add(agencyFee);
        BigDecimal jlr =saleroom.multiply(grossRate).subtract(rent.add(propertyFee).add(staffEmp).add(fitment).add(agencyFee));

        //动态三角形溢租率=净利润/租费
        brandTriangleResponseDTO.setTriangleRent(jlr.divide(rent.add(propertyFee).add(fitment)));

        //销售收入
        BigDecimal saleIn = rent.add(propertyFee).add(fitment).add(staffEmp).add(agencyFee).divide(grossRate);
        //标准三角形客流量
        BigDecimal standardPassengerFlow = saleIn.divide(perSale);
        //标准三角形客销度
        brandTriangleResponseDTO.setStandarGuest(standardPassengerFlow.divide(area).multiply(saleIn.divide(area)));

        //通过项目id、品牌id、业态id、业种id 查询 客销度区间
        ConditionGuestIntervalRequestDTO conditionGuestIntervalRequestDTO = new ConditionGuestIntervalRequestDTO();
        conditionGuestIntervalRequestDTO.setProjectId(projectId);
        conditionGuestIntervalRequestDTO.setConditionId(conditionId);
        conditionGuestIntervalRequestDTO.setMajoId(majoId);
        List<ConditionGuestIntervalResponseDTO> conditionGuestIntervalResponseDTOs = conditionGuestIntervalMapper.findConditionGuestIntervalByConditionGuestIntervalCQRS(conditionGuestIntervalRequestDTO);
        //优秀
        Integer yx = conditionGuestIntervalResponseDTOs.get(0).getYx();
        //良好
        Integer lh = conditionGuestIntervalResponseDTOs.get(0).getLh();
        //提升
        Integer ts = conditionGuestIntervalResponseDTOs.get(0).getTs();
        //合格
        Integer hl = conditionGuestIntervalResponseDTOs.get(0).getHl();
        //亏损
        Integer ks = conditionGuestIntervalResponseDTOs.get(0).getKs();
        Map<String ,Integer>   guestMap = new HashMap<String ,Integer>();
        guestMap.put("yx",yx);
        guestMap.put("lh",lh);
        guestMap.put("ts",ts);
        guestMap.put("hl",hl);
        guestMap.put("ks",ks);
        //品牌客销度区间设置
        brandTriangleResponseDTO.setIntervalGuest(guestMap);

        //设置溢租率区间
        Map<String ,Integer>   rentMap = new HashMap<String ,Integer>();
        rentMap.put("yx",350);
        rentMap.put("lh",300);
        rentMap.put("ts",200);
        rentMap.put("hl",100);
        rentMap.put("ks",0);
        brandTriangleResponseDTO.setIntervalGuest(rentMap);
        //设置适配值区间
        Map<String ,Integer>   fittedMap = new HashMap<String ,Integer>();
        fittedMap.put("yx",10000);
        fittedMap.put("lh",9000);
        fittedMap.put("ts",8000);
        fittedMap.put("hl",7000);
        fittedMap.put("ks",6400);
        brandTriangleResponseDTO.setIntervalGuest(fittedMap);

        //查询帮扶计划


        return brandTriangleResponseDTO;
    }
}
