package com.yinghuaicc.stars.repository.mapper.contract;

import com.yinghuaicc.stars.repository.model.contract.Contract;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/15 上午11:34
 * @Description: 签约
 * @Modified:
 */
@Repository
public interface ContractMapper {

    /**
     *@Author:Fly Created in 2018/7/16 下午1:52
     *@Description: 添加签约信息
     */
    @Insert("<script> " +
            "insert into yhcc_contract(id,project_id,floor_id,room_id,brand_id,status,create_time,modify_time,create_user,modify_user,effect_time,contract_id) values " +
            "<foreach item = 'item' index = 'index' collection='list' separator=','> " +
            "(#{item.id}, #{item.projectId}, #{item.floorId}, #{item.roomId}, " +
            "#{item.brandId}, #{item.status}, #{item.createTime}, #{item.modifyTime}, " +
            "#{item.createUser}, #{item.modifyUser}, #{item.effectTime}, #{item.contractId})" +
            "</foreach> " +
            "</script>")
    void saveContract(List<Contract> contracts);

    /**
     *@Author:Fly Created in 2018/7/16 下午1:58
     *@Description: 查询铺位是否已经签约
     */
    @Select("select count(*) from yhcc_contract where room_id = #{roomId}")
    Integer countContractByRoomId(String roomId);

    /**
     *@Author:Fly Created in 2018/7/16 下午4:24
     *@Description: 编辑签约信息
     */
    @Update("update yhcc_contract set " +
            "project_id = #{projectId}, floor_id = #{floorId}, room_id = #{roomId}, " +
            "brand_id = #{brandId}, status = #{status}, create_time = #{createTime}, " +
            "modify_time = #{modifyTime}, create_user = #{createUser}, modify_user = #{modifyUser},invalid_time = #{invalidTime} " +
            "where contract_id = #{contractId} ")
    void editContractById(Contract contract);

    /**
     *@Author:Fly Created in 2018/7/16 下午4:24
     *@Description: 签约详情
     */
    @Select("select * from yhcc_contract where id = #{id}")
    Contract findContractById(String id);


    @Select("select * from yhcc_contract where id = #{id}")
    List<Contract> findContractByIds(String id);

    @Select("selecct * from yhcc_contract")
    List<Contract> getContractAllBy();


    /**
     *@Author:Fly Created in 2018/7/16 下午4:24
     *@Description: 签约详情
     */
    @Select("select * from yhcc_contract where contract_id = #{id}")
    List<Contract> findContractByContractId(String id);



    /**
     *@Author:Fly Created in 2018/7/16 下午1:58
     *@Description: 查询品牌是否已经签约
     */
    @Select("select contract_id from yhcc_contract where brand_id = #{brandId} and project_id = #{projectId} group by contract_id ")
    String countContractByBrandIdId(@Param("brandId") String brandId,@Param("projectId") String projectId);

    @Select("select count(*) from yhcc_contract where brand_id = #{brandId} and project_id = #{projectId} and status = true ")
    Integer countContractByBrandIdIdCount(@Param("brandId") String brandId,@Param("projectId") String projectId);


    /**
     *@Author:Fly Created in 2018/7/16 下午4:24
     *@Description: 签约信息是否在生效日期内
     */
    @Select("select count(*) from yhcc_contract where contract_id = #{contractId} and effect_time,7  <= #{effectTime} and (case when invalid_time is null then '9999-12-12' else invalid_time end)  >=  #{effectTime} ")
    Integer getContractByContractIdAndDate(@Param("contractId") String contractId,@Param("effectTime") String effectTime);

    @Select("select count(*) from yhcc_contract where contract_id = #{contractId} and left(effect_time,7)  <= #{effectTime} and left((case when invalid_time is null then '9999-12-12' else invalid_time end),7)  >=  #{effectTime} ")
    Integer getContractByContractIdAndDates(@Param("contractId") String contractId,@Param("effectTime") String effectTime);

}
