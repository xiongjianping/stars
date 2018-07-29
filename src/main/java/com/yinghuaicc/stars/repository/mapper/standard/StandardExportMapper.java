package com.yinghuaicc.stars.repository.mapper.standard;

import com.yinghuaicc.stars.service.cqrs.standard.dto.request.StandardExportDTO;
import com.yinghuaicc.stars.service.cqrs.standard.dto.response.StandardExportResponseDTO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/7/29.
 */
@Repository
public interface StandardExportMapper {
    @Select("<script> select a.contract_id as contractId ," +
            "a.project_name as projectName," +
            "c.name as floorName," +
            "d.name as roomName," +
            "e.name as brandName," +
            "f.name as businessFormName," +
            "case when e.state = 1 then '已签约' else '未签约' end as state " +
            "from yhcc_standard_condition_sale a " +
            "LEFT JOIN yhcc_contract b on b.id = a.contract_id " +
            "LEFT JOIN yhcc_floor c on c.id = b.floor_id " +
            "LEFT JOIN yhcc_room d on d.id = b.room_id " +
            "LEFT JOIN yhcc_brand e on e.id = b.brand_id " +
            "LEFT JOIN yhcc_business_form f on f.id = e.business_form_id" +
            " <where> 1=1 " +
            "<if test='projectId != null'>AND a.project_id = #{projectId}</if> " +
            "<if test='floorId != null'>AND b.floor_id = #{floorId}</if> " +
            "</where> " +
            "</script>")
    List<StandardExportResponseDTO> getStandardExportList(StandardExportDTO standardExportDTO);
}
