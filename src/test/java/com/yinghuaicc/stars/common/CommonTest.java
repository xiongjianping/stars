package com.yinghuaicc.stars.common;

import com.yinghuaicc.stars.StarsApplicationTests;
import com.yinghuaicc.stars.common.utils.date.LocalDateTimeUtils;
import com.yinghuaicc.stars.common.utils.mapper.MapperFactoryUtil;
import com.yinghuaicc.stars.common.utils.uuid.UuidUtil;
import com.yinghuaicc.stars.controller.config.utils.EndecryptUtil;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:Fly
 * @Date:Create in 2018/6/27 下午4:20
 * @Description:
 * @Modified:
 */
public class CommonTest extends StarsApplicationTests{

    @Test
    public void uuidTest(){

        System.out.println(UuidUtil.randomUUID());

        System.out.println("-------------------");

        System.out.println("YHCC_EMP_ADMIN_42882f3e82cb415d9398117ccf7855e2".length());

        UuidUtil.batchRandomUUID(14).stream().forEach(s -> System.out.println(s));
    }

    @Test
    public void dateTest(){

        System.out.println(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.of(LocalDate.now().getYear(),LocalDate.now().getMonth(),LocalDate.now().getDayOfMonth(),0,0,0));

        long value = LocalDateTimeUtils.betweenTwoTime(
                LocalDateTime.parse("2018-07-03 12:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                LocalDateTime.now(),
                ChronoUnit.MINUTES);

        System.out.println(value);

        System.out.println(LocalDateTimeUtils.formatNow("yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testJexlEngine(){

        String c = "a+b";
        Map<String, Object> values = new HashMap<String, Object>();

        values.put("a",1);
        values.put("b",2);

        JexlEngine jexl = new JexlEngine();
        Expression e = jexl.createExpression(c);
        JexlContext jc = new MapContext();

        values.keySet().stream().forEach(str -> {

            jc.set(str, values.get(str));
        });

        Integer result = (Integer)e.evaluate(jc);

        System.out.println(result);
    }

    @Test
    public void testSso(){

        String privateKey = "tje1QgPaUbUWqNY41PKd";//密钥
        String userName = "dingguanghuai";//用户登录名
        /**
         * 加密方法
         */
        String userId = new EndecryptUtil().get3DESEncrypt(userName,privateKey);//加密后的登陆名
        System.out.println(userId);
        /**
         *	解秘方法
         */
        String loginName = new EndecryptUtil().get3DESDecrypt(userId,privateKey);//解密后的登陆名

        System.out.println(loginName);
    }

    /**
     *@Author:Fly Created in 2018/8/2 下午10:58
     *@Description:
     */
    @Test
    public void testOrika(){

        TestA a = new TestA();

        List<TestA> as = new ArrayList<TestA>();
        as.add(a);

        a.setAdate(LocalDateTime.now());

        TestB b = MapperFactoryUtil.mapperObject(a, TestB.class);

        List<TestB> bs = MapperFactoryUtil.mapperList(as, TestB.class);

        System.out.println(b.getAdate());
    }

}
