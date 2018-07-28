package com.yinghuaicc.stars.repository.mapper.cqrs.brand;

import com.yinghuaicc.stars.service.cqrs.brand.dto.request.BrandCQRSListRequestDTO;
import com.yinghuaicc.stars.service.cqrs.brand.dto.response.BrandCQRSInfoResponseDTO;
import com.yinghuaicc.stars.service.cqrs.brand.dto.response.BrandCQRSListResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/12 下午2:25
 * @Description: 品牌
 * @Modified:
 */
@Repository
public interface BrandCQRSMapper {

    /**
     *@Author:Fly Created in 2018/7/12 下午2:42
     *@Description: 品牌列表
     */
    @Select("<script> " +
            "select " +
            "br.id as id, " +
            "br.name as name, " +
            "br.num as num, " +
            "bu.name as businessFormName, " +
            "bs.name as businessSpeciesName, " +
            "br.brand_type as brandType, " +
            "br.state as state, " +
            "br.create_time as createTime, " +
            "br.modify_time as modifyTime " +
            "from yhcc_brand as br " +
            "inner join yhcc_business_form as bu on br.business_form_id = bu.id " +
            "inner join yhcc_business_species as bs on br.business_species_id = bs.id " +
            "<where> " +
            "<bind name='search.name' value=\"'%' + search.name + '%'\" /> " +
            "<bind name='search.businessFormId' value='search.businessFormId' /> " +
            "<bind name='search.businessSpeciesId' value='search.businessSpeciesId' /> " +
            "<bind name='search.createTimeBegin' value='search.createTimeBegin' /> " +
            "<bind name='search.createTimeEnd' value='search.createTimeEnd' /> " +
            "<if test='search.name != null and search.name !=\"\" '>AND br.name like #{search.name}</if> " +
            "<if test='search.businessFormId != null and search.businessFormId !=\"\" '>AND br.business_form_id = #{search.businessFormId}</if> " +
            "<if test='search.businessSpeciesId != null and search.businessSpeciesId !=\"\" '>AND bs.id = #{search.businessSpeciesId}</if> " +
            "<if test='search.createTimeBegin != null '>AND br.create_time &gt; #{search.createTimeBegin}</if> " +
            "<if test='search.createTimeEnd != null '>AND br.create_time &lt; #{search.createTimeEnd}</if> " +
            "</where> " +
            "order by br.modify_time desc" +
            "</script> ")
    List<BrandCQRSListResponseDTO> brandListCQRS(@Param("search") BrandCQRSListRequestDTO brandCQRSListRequestDTO);

    /**
     *@Author:Fly Created in 2018/7/12 下午3:59
     *@Description: 品牌详情
     */
    @Select("<script> " +
            "select " +
            "br.id as id, " +
            "br.name as name, " +
            "br.business_form_id as businessFormId, " +
            "bu.name as businessFormName, " +
            "br.business_species_id as businessFormName, " +
            "bs.name as businessSpeciesName, " +
            "br.brand_type as brandType, " +
            "br.state as state, " +
            "br.create_time as createTime, " +
            "br.modify_time as modifyTime, " +
            "br.create_user as createUser, " +
            "br.modify_user as modifyUser, " +
            "em.name as createUserName, " +
            "emp.name as modifyUserName " +
            "from yhcc_brand as br " +
            "inner join yhcc_business_form as bu on br.business_form_id = bu.id " +
            "inner join yhcc_business_species as bs on br.business_species_id = bs.id " +
            "inner join yhcc_employee as em on br.create_user = em.id " +
            "inner join yhcc_employee as emp on br.modify_user = emp.id " +
            "where br.id = #{id}" +
            "</script> ")
    BrandCQRSInfoResponseDTO brandInfoCQRS(String id);
}
