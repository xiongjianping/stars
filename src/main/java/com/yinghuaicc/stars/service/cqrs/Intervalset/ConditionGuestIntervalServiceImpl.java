package com.yinghuaicc.stars.service.cqrs.Intervalset;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.mapper.Intervalset.ConditionGuestIntervalMapper;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.ConditionGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.ConditionGuestIntervalResponseDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.FloorGuestIntervalResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 区间设置-业态客销度
 */
@Service
public class ConditionGuestIntervalServiceImpl implements ConditionGuestIntervalService{

    @Autowired
    private ConditionGuestIntervalMapper conditionGuestIntervalMapper;

    @Override
    public ResultPageList<ConditionGuestIntervalResponseDTO> findConditionGuestIntervalByConditionGuestIntervalCQRS(ConditionGuestIntervalRequestDTO conditionGuestIntervalRequestDTO, PageParam pageParam) {

        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<ConditionGuestIntervalResponseDTO> result = MapperFactoryUtil.mapperList(conditionGuestIntervalMapper.findConditionGuestIntervalByConditionGuestIntervalCQRS(conditionGuestIntervalRequestDTO), ConditionGuestIntervalResponseDTO.class);

        return new ResultPageList<ConditionGuestIntervalResponseDTO>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }
}
