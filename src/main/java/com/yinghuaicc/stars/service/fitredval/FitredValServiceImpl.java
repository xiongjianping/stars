package com.yinghuaicc.stars.service.fitredval;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.mapper.fitredval.FitredValMapper;
import com.yinghuaicc.stars.repository.model.fitredval.FitredVal;
import com.yinghuaicc.stars.service.fitredval.dto.request.AddFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.request.GetFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.request.UpdateStatusFitredValDTO;
import com.yinghuaicc.stars.service.fitredval.dto.response.FitredValResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by 你看见过我吗？你想一想在回答。 on 2018/8/1.
 */
@Service
public class FitredValServiceImpl implements FitredValService {

    @Autowired
    FitredValMapper fitredValMapper;

    /**
     * 新增
     * @param addFitredValDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addFitrdeVal(AddFitredValDTO addFitredValDTO) {
        addFitredValDTO.setStatus("1");
        addFitredValDTO.setId(UuidUtil.randomUUID());
        FitredVal f = MapperFactoryUtil.mapperObject(addFitredValDTO,FitredVal.class);
        f.setCreateTime(LocalDateTime.now());
        f.setModifyTime(LocalDateTime.now());
        fitredValMapper.addFitrdeVal(f);
    }

    /**
     * 查询列表
     * @param getFitredValDTO
     * @param pageParam
     * @return
     */
    @Override
    public ResultPageList<FitredValResponse> getFitredValList(GetFitredValDTO getFitredValDTO, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<FitredValResponse> result = fitredValMapper.getFitrdeValList(getFitredValDTO);

        return new ResultPageList<FitredValResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    @Override
    public ResultPageList<FitredValResponse> getFitrdeValListByStatus(GetFitredValDTO getFitredValDTO, PageParam pageParam) {
        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<FitredValResponse> result = fitredValMapper.getFitrdeValListByStatus(getFitredValDTO);

        return new ResultPageList<FitredValResponse>()
                .setResultList(result)
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }

    /**
     * 根据ID查看
     * @param id
     * @return
     */
    @Override
    public FitredValResponse getFitrdeValById(String id) {
        return fitredValMapper.getFitrdeValById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFitredVal(AddFitredValDTO addFitredValDTO) {
        fitredValMapper.updateFitredVal(addFitredValDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateFitredStatus(UpdateStatusFitredValDTO updateStatusFitredValDTO) {
        fitredValMapper.updateFitredStatus(updateStatusFitredValDTO);
    }
}
