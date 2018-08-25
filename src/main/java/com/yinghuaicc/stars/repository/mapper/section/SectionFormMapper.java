package com.yinghuaicc.stars.repository.mapper.section;

import com.yinghuaicc.stars.repository.model.section.SectionForm;
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
public interface SectionFormMapper {
    /**
     * 新增
     * @param sectionForm
     */
    @Insert("insert into yhcc_section_form(id,project_id,form_id," +
            "excellent_val,good_val,promote_val,reasonable_val,loss_val,excellent_pge_val,good_pge_val,promote_pge_val,reasonable_pge_val," +
            "create_time,create_user)" +
            " values(#{id},#{projectId},#{formId}," +
            "#{excellentVal},#{goodVal},#{promoteVal},#{reasonableVal},#{lossVal},#{excellentPgeVal},#{goodPgeVal},#{promotePgeVal},#{reasonablePgeVal}," +
            "#{createTime},#{createUser})")
    void saveSectionForm(SectionForm sectionForm);

    /**
     * 新增删除
     * @param sectionForm
     */
    @Delete("delete from yhcc_section_form where project_id = #{projectId} and form_id = #{formId}")
    void deleteSaveSection(SectionForm sectionForm);

    /**
     * 查看详情
     * @param id
     * @return
     */
    @Select("select * from yhcc_section_form where id #{values} ")
    SectionForm getSectionFormById(String id);


    /**
     * 删除
     * @param id
     */
    @Delete("delete from yhcc_section_form where id = #{values}")
    void deleteSectionForm(String id);

    /**
     * 查看列表
     * @param sectionBrandRequest
     * @return
     */
    @Select(" <script>" +
            " select a.*,b.name as projectName,c.name as formName from yhcc_section_form a on yhcc_project b on b.id = a.project_id " +
            " left join yhcc_business_form c on c.id = a.form_id " +
            " where 1 = 1 " +
            " <if test='projectId != null'> AND a.project_id = #{projectId} </if> " +
            " <if test='formId != null'> AND a.form_id = #{formId} </if> " +
            " </script>")
    List<SectionBrandResponse> getSectionFormList(SectionBrandRequest sectionBrandRequest);


    @Select(" " +
            " select a.* from yhcc_section_form a  " +
            " where " +
            "   a.project_id = #{projectId} " +
            "  AND a.form_id = #{formId} " +
            " ")
    SectionForm getSectionFormListById(SectionBrandRequest sectionBrandRequest);

}
