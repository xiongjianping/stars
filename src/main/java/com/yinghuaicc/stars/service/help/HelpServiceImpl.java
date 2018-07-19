package com.yinghuaicc.stars.service.help;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yinghuaicc.stars.common.utils.exception.ExceptionUtil;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.config.page.PageParam;
import com.yinghuaicc.stars.config.page.ResultPageList;
import com.yinghuaicc.stars.repository.mapper.help.HelpMapper;
import com.yinghuaicc.stars.repository.model.help.HelpContext;
import com.yinghuaicc.stars.service.help.HelpService;
import com.yinghuaicc.stars.service.help.dto.request.EditHelpContextRequestDTO;
import com.yinghuaicc.stars.service.help.dto.request.SaveHelpContextRequestDTO;
import com.yinghuaicc.stars.service.help.dto.response.FindHelpContextListResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/19 下午4:19
 * @Description: 帮扶计划
 * @Modified:
 */
@Service
public class HelpServiceImpl implements HelpService {

    @Autowired
    private ExceptionUtil exceptionUtil;

    @Autowired
    private HelpMapper helpMapper;


    /**
     *@Author:Fly Created in 2018/7/19 下午4:25
     *@Description: 添加帮扶内容
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void saveHelpContext(SaveHelpContextRequestDTO saveHelpContextRequestDTO, String loginEmployeeId) {

        helpMapper.saveHelpContext(
                Stream.of(
                        MapperFactoryUtil.mapperObject(saveHelpContextRequestDTO, HelpContext.class)
                                .setId(UuidUtil.randomUUID())
                                .setCreateUser(loginEmployeeId)
                                .setModifyUser(loginEmployeeId)
                                .setModifyTime(LocalDateTime.now())
                                .setCreateTime(LocalDateTime.now()))
                        .collect(Collectors.toList()));
    }

    /**
     *@Author:Fly Created in 2018/7/19 下午4:25
     *@Description: 编辑帮扶内容
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editHelpContext(EditHelpContextRequestDTO editHelpContextRequestDTO, String loginEmployeeId) {

        HelpContext helpContext = helpMapper.findHelpContextById(editHelpContextRequestDTO.getId());

        if (Objects.isNull(helpContext)){

            throw exceptionUtil.throwCustomException("EDIT_HELP_CONTEXT_004");
        }

        helpMapper.editHelpContext(
                helpContext
                        .setType(editHelpContextRequestDTO.getType())
                        .setContext(editHelpContextRequestDTO.getContext())
                        .setModifyTime(LocalDateTime.now())
                        .setModifyUser(loginEmployeeId));
    }

    /**
     *@Author:Fly Created in 2018/7/19 下午4:26
     *@Description: 删除帮扶内容
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeHelpContext(String id) {

        helpMapper.removeHelpContext(id);
    }

    /**
     *@Author:Fly Created in 2018/7/19 下午4:26
     *@Description: 帮扶内容列表
     */
    @Override
    public ResultPageList<FindHelpContextListResponseDTO> findHelpContextList(PageParam pageParam, Integer type) {

        Page page = PageHelper.startPage(pageParam.getP(), pageParam.getC());

        List<HelpContext> result = helpMapper.findHelpContextList(type);

        return new ResultPageList<FindHelpContextListResponseDTO>()
                .setResultList(MapperFactoryUtil.mapperList(result, FindHelpContextListResponseDTO.class))
                .setPage(pageParam.getP())
                .setSize(pageParam.getC())
                .setCountPage(page.getPages())
                .setCountSize(page.getTotal());
    }
}
