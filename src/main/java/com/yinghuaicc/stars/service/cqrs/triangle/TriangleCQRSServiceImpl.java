package com.yinghuaicc.stars.service.cqrs.triangle;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.mapper.triangle.TriangleMapper;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.request.TriangleListCQRSRequestDTO;
import com.yinghuaicc.stars.service.cqrs.triangle.dto.response.TriangleListCQRSResopnseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TriangleCQRSServiceImpl implements TriangleCQRSService{

    @Autowired
    private TriangleMapper triangleMapper;

    @Override
    public ResultPageList<TriangleListCQRSRequestDTO> findTriangleListCQRS(TriangleListCQRSRequestDTO triangleListCQRSRequestDTO, PageParam pageParam) {

        Page page = PageHelper.startPage(pageParam.getP(),pageParam.getC());

        return null;
    }
}
