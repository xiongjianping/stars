package com.yinghuaicc.stars.repository.mapper.triangle;

import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.GuestVerssionRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.GuestVerssionResponseDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 */
@Repository
public interface GuestVerssionMapper {
    @Select("<script>select * from yhcc_guest_verssion " +
            " <where> " +
            "<bind name='search.guestVerssionName' value=\"'%' + search.guestVerssionName + '%'\" /> " +
            "<bind name='search.different' value=\"'%' + search.different + '%'\" /> " +
            "<bind name='search.createTimeBegin' value='search.createTimeBegin ' /> " +
            "<bind name='search.createTimeEnd' value='search.createTimeEnd ' /> " +
            "<if test='search.guestVerssionName != null and search.guestVerssionName !=\"\" '>AND guest_verssion_name like #{search.guestVerssionName}</if> " +
            "<if test='search.different != null and search.guestVerssionName !=\"\" '>AND different like #{search.different}</if> " +
            "<if test='search.createTimeBegin != null'>AND create_time &gt; #{search.createTimeBegin}</if> " +
            "<if test='search.createTimeEnd != null'>AND create_time &lt; #{search.createTimeEnd}</if> " +
            "</where> " +
            "</script>")
    List<GuestVerssionResponseDTO> findGuestVerssionByGuestVerssionCQRS(@Param("search")GuestVerssionRequestDTO guestVerssionRequestDTO);
}
