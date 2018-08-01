package com.yinghuaicc.stars.repository.mapper.assessmodel;

import com.yinghuaicc.stars.repository.model.assessmodel.AssessFitredVal;
import com.yinghuaicc.stars.repository.model.assessmodel.AssessModel;
import com.yinghuaicc.stars.repository.model.fitredval.FitredVal;
import com.yinghuaicc.stars.service.assessmodel.dto.request.AddAssessModelDTO;
import com.yinghuaicc.stars.service.assessmodel.dto.request.GetAssessModelDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 */
@Repository
public interface AssessModelMapper {

    /**
     * 查看模板列表
     * @param getAssessModelDTO
     * @return
     */
    @Select("<script>  select * from yhcc_assess_model where status != 3 " +
            "<if test='name != null '>AND name  like '%' + #{name} + '%'</if> " +
            " </script> ")
    List<AssessModel> getAssessModelList(GetAssessModelDTO getAssessModelDTO);


    /**
     * 新增
     */
    @Insert(" insert into yhcc_assess_model(id,name,number,status,create_name,update_name,create_time,modify_time)" +
            " values(#{id},#{name},#{number},#{status},#{createName},#{updateName},#{createTime},#{modifyTime})")
    void addAssessModel(AddAssessModelDTO addAssessModelDTO);


    @Insert(" insert into yhcc_assess_fitredval(id,fitred_id,assess_id)" +
            " values(#{id},#{fitredId},#{assessId})")
    void addAssessFitredVal(AssessFitredVal assessFitredVal);

    /**
     * 删除自信息
     * @param id
     */
    @Update("delete from yhcc_assess_fitredval  where assess_id = #{id} ")
    void updateAssessFitredVal(@Param("id") String id);

    /**
     * 修改
     */
    @Update("<script>" +
            " update yhcc_assess_model set modify_time = #{modifyTime} " +
            "<if test='name != null '>,name = #{name}</if> " +
            "<if test='number != null '>,number = #{number}</if> " +
            "<if test='updateName != null '>,update_name = #{updateName}</if> " +
            " where id = #{id} " +
            "</script>")
    void updateAssessModel(AddAssessModelDTO addAssessModelDTO);


    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_assess_model where id = #{values}")
    AssessModel getAssessModelById(String id);

    /**
     * 修改状态
     * @param status
     * @param id
     */
    @Update("update yhcc_assess_model set status = #{status} where id = #{id} ")
    void updateAssessModelStatus(@Param("status") String status,@Param("id") String id);


    @Select("select b.* from yhcc_assess_fitredval a LEFT JOIN yhcc_fitred_val b on b.id = a.fitred_id where a.assess_id = #{values} ")
    List<FitredVal> getFitredValList(String id);
}
