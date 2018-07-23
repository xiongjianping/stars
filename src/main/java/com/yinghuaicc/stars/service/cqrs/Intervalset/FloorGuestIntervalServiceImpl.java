package com.yinghuaicc.stars.service.cqrs.Intervalset;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.mapper.Intervalset.FloorGuestIntervalMapper;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.FloorGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.FloorGuestIntervalResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorGuestIntervalServiceImpl implements FloorGuestIntervalService {

    private FloorGuestIntervalMapper floorGuestIntervalMapper;
    @Override
    public ResultPageList<FloorGuestIntervalResponseDTO> findFloorGuestIntervalByFloorGuestIntervalCQRS(FloorGuestIntervalRequestDTO floorGuestIntervalRequestDTO, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<FloorGuestIntervalResponseDTO> result = MapperFactoryUtil.mapperList(floorGuestIntervalMapper.findFloorGuestIntervalByFloorGuestIntervalCQRS(floorGuestIntervalRequestDTO), FloorGuestIntervalResponseDTO.class);

        return new ResultPageList<FloorGuestIntervalResponseDTO>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }
}
