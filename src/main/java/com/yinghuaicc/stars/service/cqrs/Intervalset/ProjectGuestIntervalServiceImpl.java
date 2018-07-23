package com.yinghuaicc.stars.service.cqrs.Intervalset;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.mapper.Intervalset.ProjectGuestIntervalMapper;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.request.ProjectGuestIntervalRequestDTO;
import com.yinghuaicc.stars.service.cqrs.Intervalset.dto.response.ProjectGuestIntervalResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectGuestIntervalServiceImpl  implements ProjectGuestIntervalService{


    private ProjectGuestIntervalMapper projectGuestIntervalMapper;

    @Override
    public ResultPageList<ProjectGuestIntervalResponseDTO> findProjectGuestIntervalByProjectGuestIntervalCQRS(ProjectGuestIntervalRequestDTO projectGuestIntervalRequestDTO, PageParam pageParam) {

        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<ProjectGuestIntervalResponseDTO> result = MapperFactoryUtil.mapperList(projectGuestIntervalMapper.findProjectGuestIntervalByProjectGuestIntervalCQRS(projectGuestIntervalRequestDTO), ProjectGuestIntervalResponseDTO.class);

        return new ResultPageList<ProjectGuestIntervalResponseDTO>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }
}
