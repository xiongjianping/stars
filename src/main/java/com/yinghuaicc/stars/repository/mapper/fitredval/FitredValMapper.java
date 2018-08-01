package com.yinghuaicc.stars.repository.mapper.fitredval;

import com.yinghuaicc.stars.repository.model.fitredval.FitredVal;
import com.yinghuaicc.stars.service.fitredval.dto.request.AddFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.request.GetFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.request.UpdateStatusFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.response.FitredValResponse;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 * 适合都
 */
@Repository
public interface FitredValMapper {

    /**
     * 查看列表
     * @return
     */
    @Select("<script>  select * from yhcc_fitred_val where status != 3 " +
            "<if test='fitredType != null '>AND fitred_type = #{fitredType}</if> " +
            "<if test='name != null '>AND name  like '%' + #{name} + '%'</if> " +
            " </script> ")
    List<FitredValResponse> getFitrdeValList(GetFitredValDTO getFitredValDTO);


    /**
     * 新增
     */
    @Insert(" insert into yhcc_fitred_val(id,fitred_type,name,val,create_time,modify_time,status)" +
            " values(#{id},#{fitredType},#{name},#{val},#{createTime},#{modifyTime},#{status})")
    void addFitrdeVal(FitredVal f);



    /**
     * 查看
     * @return
     */
    @Select("  select * from yhcc_fitred_val where id = #{values}")
    FitredValResponse getFitrdeValById(String id);


    @Update("<script>" +
            " update yhcc_fitred_val set modify_time = #{modifyTime} " +
            "<if test='fitredType != null '>,fitred_type = #{fitredType}</if> " +
            "<if test='name != null '>,name = #{name}</if> " +
            "<if test='val != null '>,val = #{val}</if> " +
            " where id = #{id} " +
            "</script>")
    void updateFitredVal(AddFitredValDTO addFitredValDTO);


    /**
     * 修改状态
     * @param updateStatusFitredValDTO
     */
    @Update(" update yhcc_fitred_val set status = #{status} " +
            " where id = #{id}")
    void updateFitredStatus(UpdateStatusFitredValDTO updateStatusFitredValDTO);

}
