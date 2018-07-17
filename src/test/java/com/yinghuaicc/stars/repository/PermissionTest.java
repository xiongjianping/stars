package com.yinghuaicc.stars.repository;

import com.yinghuaicc.stars.StarsApplicationTests;
import com.yinghuaicc.stars.repository.mapper.permission.PermissionMapper;
import com.yinghuaicc.stars.repository.model.permission.Menu;
import com.yinghuaicc.stars.repository.model.permission.MenuType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author:Fly
 * @Date:Create in 2018/6/27 下午5:07
 * @Description:
 * @Modified:
 */
public class PermissionTest extends StarsApplicationTests{

    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void distinct(){

        List<Menu> menus = new ArrayList<Menu>();

        menus.add(new Menu().setId("1").setName("测试name1"));
        menus.add(new Menu().setId("1").setName("测试name2"));
        menus.add(new Menu().setId("2").setName("测试name3"));

        menus = menus.stream().distinct().collect(Collectors.toList());

        System.out.println("--------------");
    }
}
