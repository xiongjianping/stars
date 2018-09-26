package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.repository.model.triangle.DayGuest;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.DayGuestRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.AllSalePassengerFlowResponseDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.DayGuestResponseDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 每日客销度
 */
@Repository
public interface DayGuestMapper {


    @Select("<script>select * from yhcc_day_guest " +
            " <where> " +
            "<bind name='search.guestVerssionId' value='search.guestVerssionId' /> " +
            "<if test='search.guestVerssionId != null'>AND guest_verssion_id = #{search.guestVerssionId}</if> " +
            "</where> " +
            "</script>")
    List<DayGuestResponseDTO> findDayGuestByDayGuestCQRS (@Param("search")DayGuestRequestDTO dayGuestRequestDTO);

    /**
     * 通过签约id与时间查询客销度
     * @param contractId
     * @param time
     * @return
     */
    @Select("select * from yhcc_day_guest where contract_id = #{contractId} and create_time =#{createTime}")
    DayGuestResponseDTO findDayGuestByDayGuestByContractId(String contractId, LocalDateTime time);

    /**
     * 保存天客销度
     * @param dayGuest
     */
    @Insert("insert into yhcc_day_guest " +
            "values(#{id},#{guestVerssionId},#{contractId},#{projectName},#{floorName},#{roomName},#{contractName}," +
            "#{conditionName},#{majoName},#{passengerFlow},#{saleroom},#{profits},#{createTime},#{modifyTime},#{createUser},#{modifyUser},#{status})")
    void saveDayGuest(DayGuest dayGuest);


    /**
     * 查询全国客流量
     * @return
     */
    @Select("select sum(passenger_flow) from yhcc_project_rate")
    BigDecimal findPassengerFlowAll();
    /**
     * 查询全国销售额
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate")
    BigDecimal findSaleroomAll();


    /**
     * 全国区域排名销售额、客销度
     * @return
     */
/*    @Select("SELECT b.name as 'areaName',SUM(d.passenger_flow) as 'passengerFlow',SUM(d.saleroom) as 'saleroom' FROM yhcc_project a LEFT JOIN yhcc_area b ON b.id = a.area_id" +
            " LEFT JOIN yhcc_contract c ON c.project_id = a.id " +
            " LEFT JOIN yhcc_day_guest d ON d.contract_id = c.id " +
            "GROUP BY b.name")
    List<AllSalePassengerFlowResponseDTO> findSalePassengerFlowAll();*/

    @Select(" SELECT a.name as 'areaName',case when SUM(d.passenger_flow) is null then 0 else SUM(d.passenger_flow) end as 'passengerFlow',case when SUM(d.sales_volume) is null then 0 else SUM(d.sales_volume) end as 'saleroom' FROM yhcc_area a LEFT JOIN yhcc_project b ON a.id = b.area_id " +
            "    LEFT JOIN yhcc_contract c ON c.project_id = b.id " +
            "    LEFT JOIN yhcc_brand_rate d ON d.contract_id = c.contract_id " +
            "    GROUP BY a.name ")
    List<AllSalePassengerFlowResponseDTO> findSalePassengerFlowAll();



    /**
     * 查询所有的日客销度-用于定时任务
     * @return
     */
    @Select("select * from yhcc_day_guest")
    List<DayGuest>  findDayGuestBynull();



    @Select("select adddate(#{begin}, numlist.id) as date " +
            "                from (SELECT n1.i + n10.i*10 + n100.i*100 AS id FROM num n1 cross join num as n10 cross join num as n100) as numlist where  adddate(#{begin}, numlist.id) < #{end} " +
            "                GROUP BY date ")
    List<String> day(@Param("begin") String begin,@Param("end") String end);



    /**
     * 查询全国销售额7天
     * @return
     */
    @Select("select sum(sales_volume) from yhcc_brand_rate where effect_time = #{values}")
    BigDecimal findDaySaleroomAll(String day);


    /**
     * 查询全国客流量7天
     * @return
     */
    @Select("select sum(passenger_flow) from yhcc_project_rate where effect_time = #{values}")
    BigDecimal findDayPassengerFlowAll(String day);
}
