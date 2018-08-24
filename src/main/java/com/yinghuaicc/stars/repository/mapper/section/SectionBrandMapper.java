package com.yinghuaicc.stars.repository.mapper.section;

import com.yinghuaicc.stars.repository.model.section.SectionBrand;
import com.yinghuaicc.stars.service.section.dto.request.SectionBrandRequest;
import com.yinghuaicc.stars.service.section.dto.response.SectionBrandResponse;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/23.
 */
@Repository
public interface SectionBrandMapper {

    /**
     * 新增
     * @param sectionBrand
     */
    @Insert("insert into yhcc_section_brand(id,project_id,form_id,species_id," +
            "excellent_val,good_val,promote_val,reasonable_val,loss_val,excellent_pge_val,good_pge_val,promote_pge_val,reasonable_pge_val," +
            "effect_time,create_time,create_user)" +
            " values(#{id},#{projectId},#{formId},#{speciesId}," +
            "#{excellentVal},#{goodVal},#{promoteVal},#{reasonableVal},#{lossVal},#{excellentPgeVal},#{goodPgeVal},#{promotePgeVal},#{reasonablePgeVal}," +
            "#{effectTime},#{createTime},#{createUser})")
    void saveSectionBrand(SectionBrand sectionBrand);

    /**
     * 新增删除
     * @param sectionBrand
     */
    @Delete("delete from yhcc_section_brand where project_id = #{projectId} and form_id = #{formId} and species_id = #{speciesId} and effect_time = #{effectTime}")
    void deleteSaveSection(SectionBrand sectionBrand);

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_section_brand where id #{values} ")
    SectionBrand getSectionBrand(String id);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_section_brand where id = #{values}")
    void deleteSectionBrand(String id);


    /**
     * 查看列表
     * @param sectionBrandRequest
     * @return
     */
    @Select(" <script>" +
            " select a.*,b.name as projectName,c.name as formName,d.name as speciesName from yhcc_section_brand a on yhcc_project b on b.id = a.project_id " +
            " left join yhcc_business_form c on c.id = a.form_id " +
            " left join yhcc_business_species d on d.id = a.species_id" +
            " where 1 = 1 " +
            " <if test='projectId != null'> AND a.project_id = #{projectId} </if> " +
            " <if test='formId != null'> AND a.form_id = #{formId} </if> " +
            " <if test='speciesId != null'> AND a.species_id = #{speciesId} </if>" +
            " <if test='effectTime != null'> AND a.effect_time = #{effectTime} </if>" +
            " </script>")
    List<SectionBrandResponse> getSectionBrandList(SectionBrandRequest sectionBrandRequest);

}
