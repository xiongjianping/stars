package com.yinghuaicc.stars.repository.mapper.cqrs.help;

import com.yinghuaicc.stars.service.cqrs.help.dto.request.FindHelpPlanFloorListCQRSRequestDTO;
import com.yinghuaicc.stars.service.cqrs.help.dto.request.FindHelpPlanProjectListCQRSRequestDTO;
import com.yinghuaicc.stars.service.cqrs.help.dto.request.FindHelpRequestDTO;
import com.yinghuaicc.stars.service.cqrs.help.dto.response.FindHelpPlanBusinessSpeciesListCQRSResponseDTO;
import com.yinghuaicc.stars.service.cqrs.help.dto.response.FindHelpPlanFloorListCQRSResponseDTO;
import com.yinghuaicc.stars.service.cqrs.help.dto.response.FindHelpPlanProjectListCQRSResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/20 上午10:35
 * @Description: 项目帮扶计划
 * @Modified:
 */
@Repository
public interface HelpCQRSMapper {

    /**
     *@Author:Fly Created in 2018/7/20 上午10:56
     *@Description: 项目帮扶计划列表
     */
    @Select("<script> " +
            "select " +
            "hp.project_id as projectId, " +
            "pr.name as projectName, " +
            "hp.type as helpType, " +
            "hp.yx_help_context as yx, " +
            "hp.lh_help_context as lh, " +
            "hp.ts_help_context as ts, " +
            "hp.hl_help_context as hl, " +
            "hp.ks_help_context as ks " +
            "from yhcc_help_project as hp " +
            "inner join yhcc_project as pr on hp.project_id = pr.id " +
            "inner join yhcc_area as ar on pr.area_id = ar.id " +
            "<where> " +
            "<bind name='search.areaId' value='search.areaId' /> " +
            "<bind name='search.projectId' value='search.projectId' /> " +
            "<bind name='search.helpType' value='search.helpType' /> " +
            "<if test='search.areaId != null and search.areaId !=\"\" '>AND pr.area_id = #{search.areaId}</if> " +
            "<if test='search.projectId != null and search.projectId !=\"\" '>AND hp.project_id = #{search.projectId}</if> " +
            "<if test='search.helpType != null and search.helpType !=\"\" '>AND hp.type = #{search.helpType}</if> " +
            "</where> " +
            "order by hp.modify_time desc " +
            "</script> ")
    List<FindHelpPlanProjectListCQRSResponseDTO> findHelpPlanCQRSList(@Param("search") FindHelpPlanProjectListCQRSRequestDTO findHelpPlanProjectListCQRSRequestDTO);

    /**
     *@Author:Fly Created in 2018/7/20 下午8:08
     *@Description: 层帮扶计划
     */
    @Select("<script> " +
            "select " +
            "hp.project_id as projectId, " +
            "pr.name as projectName, " +
            "fl.name as floorName, " +
            "hp.floor_id as floorId, " +
            "hp.type as helpType, " +
            "hp.yx_help_context as yx, " +
            "hp.lh_help_context as lh, " +
            "hp.ts_help_context as ts, " +
            "hp.hl_help_context as hl, " +
            "hp.ks_help_context as ks " +
            "from yhcc_help_floor as hp " +
            "inner join yhcc_project as pr on hp.project_id = pr.id " +
            "inner join yhcc_area as ar on pr.area_id = ar.id " +
            "inner join yhcc_floor as fl on hp.floor_id = fl.id " +
            "<where> " +
            "<bind name='search.areaId' value='search.areaId' /> " +
            "<bind name='search.projectId' value='search.projectId' /> " +
            "<bind name='search.floorId' value='search.floorId' /> " +
            "<bind name='search.helpType' value='search.helpType' /> " +
            "<if test='search.areaId != null and search.areaId !=\"\" '>AND pr.area_id = #{search.areaId}</if> " +
            "<if test='search.projectId != null and search.projectId !=\"\" '>AND hp.project_id = #{search.projectId}</if> " +
            "<if test='search.floorId != null and search.floorId !=\"\" '>AND hp.floor_id = #{search.floorId}</if> " +
            "<if test='search.helpType != null and search.helpType !=\"\" '>AND hp.type = #{search.helpType}</if> " +
            "</where> " +
            "order by hp.modify_time desc " +
            "</script> ")
    List<FindHelpPlanFloorListCQRSResponseDTO> findHelpPlanFloorCQRSList(@Param("search") FindHelpPlanFloorListCQRSRequestDTO findHelpPlanFloorListCQRSRequestDTO);

    /**
     *@Author:Fly Created in 2018/7/21 下午1:55
     *@Description: 业种帮扶计划
     */
    @Select("<script> " +
            "select " +
            "hp.project_id as projectId, " +
            "pr.name as projectName, " +
            "ar.id as businessFormId, " +
            "ar.name as businessFormName, " +
            "fl.id as businessSpeciesId, " +
            "fl.name as businessSpeciesName, " +
            "hp.type as helpType, " +
            "hp.yx_help_context as yx, " +
            "hp.lh_help_context as lh, " +
            "hp.ts_help_context as ts, " +
            "hp.hl_help_context as hl, " +
            "hp.ks_help_context as ks " +
            "from yhcc_help_business_species as hp " +
            "inner join yhcc_project as pr on hp.project_id = pr.id " +
            "inner join yhcc_business_form as ar on hp.business_form_id = ar.id " +
            "inner join yhcc_business_species as fl on hp.business_species_id = fl.id " +
            "<where> " +
            "<bind name='projectId' value='projectId' /> " +
            "<bind name='helpType' value='helpType' /> " +
            "<if test='projectId != null and projectId !=\"\" '>AND hp.project_id = #{projectId}</if> " +
            "<if test='helpType != null and helpType !=\"\" '>AND hp.type = #{helpType}</if> " +
            "<if test='fromId != null and fromId !=\"\" '>AND hp.business_form_id = #{fromId}</if> " +
            "<if test='speciesId != null and speciesId !=\"\" '>AND hp.business_species_id = #{speciesId}</if> " +
            "</where> " +
            "order by hp.modify_time desc " +
            "</script> ")
    List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> findHelpPlanBusinessSpeciesCQRSList(@Param("projectId") String projectId, @Param("helpType") Integer helpType,@Param("fromId") String fromId,@Param("speciesId") String speciesId);

    /**
     *@Author:Fly Created in 2018/7/21 下午1:55
     *@Description: 业态帮扶计划
     */
    @Select("<script> " +
            "select " +
            "hp.project_id as projectId, " +
            "pr.name as projectName, " +
            "ar.id as businessFormId, " +
            "ar.name as businessFormName, " +
            "hp.type as helpType, " +
            "hp.yx_help_context as yx, " +
            "hp.lh_help_context as lh, " +
            "hp.ts_help_context as ts, " +
            "hp.hl_help_context as hl, " +
            "hp.ks_help_context as ks " +
            "from yhcc_help_from_context as hp " +
            "inner join yhcc_project as pr on hp.project_id = pr.id " +
            "inner join yhcc_business_form as ar on hp.business_form_id = ar.id " +
            "<where> " +
            "<bind name='projectId' value='projectId' /> " +
            "<bind name='helpType' value='helpType' /> " +
            "<if test='projectId != null and projectId !=\"\" '>AND hp.project_id = #{projectId}</if> " +
            "<if test='helpType != null and helpType !=\"\" '>AND hp.type = #{helpType}</if> " +
            "<if test='fromId != null and fromId !=\"\" '>AND hp.business_form_id = #{fromId}</if> " +
            "</where> " +
            "order by hp.modify_time desc " +
            "</script> ")
    List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> findHelpFromCQRSList(@Param("projectId") String projectId, @Param("helpType") Integer helpType,@Param("fromId") String fromId);










    @Select("<script> " +
            "select " +
            "hp.yx_help_context as yx, " +
            "hp.lh_help_context as lh, " +
            "hp.ts_help_context as ts, " +
            "hp.hl_help_context as hl, " +
            "hp.ks_help_context as ks " +
            "from yhcc_help_project as hp " +
            "inner join yhcc_project as pr on hp.project_id = pr.id " +
            "inner join yhcc_area as ar on pr.area_id = ar.id " +
            " where 1=1  " +
            "<if test='projectId != null and projectId !=\"\" '> AND hp.project_id = #{projectId} </if> " +
            "<if test='type != null and type !=\"\" '> AND hp.type = #{type} </if> " +
            "<if test='state == 1 '> AND hp.yx_help_context is not null </if> " +
            "<if test='state == 2 '> AND hp.lh_help_context is not null </if> " +
            "<if test='state == 3 '> AND hp.ts_help_context is not null </if> " +
            "<if test='state == 4 '> AND hp.hl_help_context is not null </if> " +
            "<if test='state == 5 '> AND hp.ks_help_context is not null </if> " +
            "  " +
            "order by hp.modify_time desc limit 0,3  " +
            "</script> ")
    List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> getProjectList(FindHelpRequestDTO f);


    @Select("<script> " +
            "select " +
            "hp.yx_help_context as yx, " +
            "hp.lh_help_context as lh, " +
            "hp.ts_help_context as ts, " +
            "hp.hl_help_context as hl, " +
            "hp.ks_help_context as ks " +
            "from yhcc_help_floor as hp " +
            "inner join yhcc_project as pr on hp.project_id = pr.id " +
            "inner join yhcc_area as ar on pr.area_id = ar.id " +
            "inner join yhcc_floor as fl on hp.floor_id = fl.id " +
            " where 1=1 " +
            "<if test='projectId != null and projectId !=\"\" '>AND hp.project_id = #{projectId}</if> " +
            "<if test='floorId != null and floorId !=\"\" '>AND hp.floor_id = #{floorId}</if> " +
            "<if test='type != null and type !=\"\" '>AND hp.type = #{type}</if> " +
            "<if test='state == 1 '> AND hp.yx_help_context is not null </if> " +
            "<if test='state == 2 '> AND hp.lh_help_context is not null </if> " +
            "<if test='state == 3 '> AND hp.ts_help_context is not null </if> " +
            "<if test='state == 4 '> AND hp.hl_help_context is not null </if> " +
            "<if test='state == 5 '> AND hp.ks_help_context is not null </if> " +
            "order by hp.modify_time desc  limit 0,3" +
            "</script> ")
    List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> getfloorList(FindHelpRequestDTO f);

    @Select("<script> " +
            "select " +
            "hp.yx_help_context as yx, " +
            "hp.lh_help_context as lh, " +
            "hp.ts_help_context as ts, " +
            "hp.hl_help_context as hl, " +
            "hp.ks_help_context as ks " +
            "from yhcc_help_from_context as hp " +
            "inner join yhcc_project as pr on hp.project_id = pr.id " +
            "inner join yhcc_business_form as ar on hp.business_form_id = ar.id " +
            " where 1=1  " +
            "<if test='projectId != null and projectId !=\"\" '>AND hp.project_id = #{projectId}</if> " +
            "<if test='type != null and type !=\"\" '>AND hp.type = #{type}</if> " +
            "<if test='formId != null and formId !=\"\" '>AND hp.business_form_id = #{formId}</if> " +
            "<if test='state == 1 '> AND hp.yx_help_context is not null </if> " +
            "<if test='state == 2 '> AND hp.lh_help_context is not null </if> " +
            "<if test='state == 3 '> AND hp.ts_help_context is not null </if> " +
            "<if test='state == 4 '> AND hp.hl_help_context is not null </if> " +
            "<if test='state == 5 '> AND hp.ks_help_context is not null </if> " +
            "order by hp.modify_time desc  limit 0,3 " +
            "</script> ")
    List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> getformList(FindHelpRequestDTO f);


    @Select("<script> " +
            "select " +
            "hp.yx_help_context as yx, " +
            "hp.lh_help_context as lh, " +
            "hp.ts_help_context as ts, " +
            "hp.hl_help_context as hl, " +
            "hp.ks_help_context as ks " +
            "from yhcc_help_business_species as hp " +
            "inner join yhcc_project as pr on hp.project_id = pr.id " +
            "inner join yhcc_business_form as ar on hp.business_form_id = ar.id " +
            "inner join yhcc_business_species as fl on hp.business_species_id = fl.id " +
            " where 1=1 " +
            "<if test='projectId != null and projectId !=\"\" '>AND hp.project_id = #{projectId}</if> " +
            "<if test='type != null and type !=\"\" '>AND hp.type = #{type}</if> " +
            "<if test='formId != null and formId !=\"\" '>AND hp.business_form_id = #{formId}</if> " +
            "<if test='speciesId != null and speciesId !=\"\" '>AND hp.business_species_id = #{speciesId}</if> " +
            "<if test='state == 1 '> AND hp.yx_help_context is not null </if> " +
            "<if test='state == 2 '> AND hp.lh_help_context is not null </if> " +
            "<if test='state == 3 '> AND hp.ts_help_context is not null </if> " +
            "<if test='state == 4 '> AND hp.hl_help_context is not null </if> " +
            "<if test='state == 5 '> AND hp.ks_help_context is not null </if> " +
            "order by hp.modify_time desc limit 0,3 " +
            "</script> ")
    List<FindHelpPlanBusinessSpeciesListCQRSResponseDTO> getspeciesList(FindHelpRequestDTO f);
}
