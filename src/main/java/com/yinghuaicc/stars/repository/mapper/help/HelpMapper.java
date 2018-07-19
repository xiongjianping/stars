package com.yinghuaicc.stars.repository.mapper.help;

import com.yinghuaicc.stars.repository.model.help.HelpContext;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/19 下午3:34
 * @Description: 帮扶计划
 * @Modified:
 */
@Repository
public interface HelpMapper {

    /**
     *@Author:Fly Created in 2018/7/19 下午4:00
     *@Description: 添加帮扶内容
     */
    @Insert("<script> " +
            "insert into yhcc_help_context values " +
            "<foreach item = 'item' index = 'index' collection='list' separator=','> " +
            "(#{item.id}, #{item.context}, #{item.type}, #{item.createTime}, #{item.modifyTime}, " +
            "#{item.createUser}, #{item.modifyUser} )" +
            "</foreach> " +
            "</script> ")
    void saveHelpContext(List<HelpContext> helpContext);

    /**
     *@Author:Fly Created in 2018/7/19 下午4:06
     *@Description: 编辑帮扶内容
     */
    @Update("update yhcc_help_context set " +
            "context = #{context}, type = #{type}, create_time = #{createTime}, modify_time = #{modifyTime}, " +
            "create_user = #{createUser}, modify_user = #{modifyUser} " +
            "where id = #{id} ")
    void editHelpContext(HelpContext helpContext);

    /**
     *@Author:Fly Created in 2018/7/19 下午4:12
     *@Description: 删除帮扶内容
     */
    @Delete("delete from yhcc_help_context where id = #{id}")
    void removeHelpContext(String id);

    /**
     *@Author:Fly Created in 2018/7/19 下午4:14
     *@Description: 查询帮扶内容列表
     */
    @Select("<script> " +
            "select * from yhcc_help_context " +
            "<where> " +
            "<bind name='search' value='search' />" +
            "<if test='search != null '>AND yhcc_help_context.type = #{search}</if> " +
            "</where> " +
            "</script>")
    List<HelpContext> findHelpContextList(@Param("search") Integer type);

    /**
     *@Author:Fly Created in 2018/7/19 下午4:31
     *@Description: 根据id查询帮扶内容
     */
    @Select("select * from yhcc_help_context where id = #{id}")
    HelpContext findHelpContextById(String id);
}
