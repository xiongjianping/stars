package com.yinghuaicc.stars.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author:Fly
 * @Date:Create in 2018/8/5 下午4:45
 * @Description:
 * @Modified:
 */
public class BeanUtils <T>{

    public List<T> ListMap2JavaBean(List<Map<String, Object>> datas, Class<T> beanClass) {
        // 返回数据集合
        List<T> list = null;
        // 对象字段名称
        String fieldname = "";
        // 对象方法名称
        String methodname = "";
        // 对象方法需要赋的值
        Object methodsetvalue = "";
        try {
            list = new ArrayList<T>();
            // 得到对象所有字段
            Field fields[] = beanClass.getDeclaredFields();
            // 遍历数据
            for (Map<String, Object> mapdata : datas) {
                // 创建一个泛型类型实例
                T t = beanClass.newInstance();
                // 遍历所有字段，对应配置好的字段并赋值
                for (Field field : fields) {
                    if(null != field) {
                        Object o = mapdata.get(field.getName());
                        if (Objects.nonNull(o)){

                            if (field.getType().toString().endsWith("BigDecimal")){
                                field.setAccessible(true);
                                field.set(t, new BigDecimal(o.toString()));
                            }

                            if (field.getType().toString().endsWith("Double")){
                                field.setAccessible(true);
                                field.set(t, new Double(o.toString()));
                            }

                            if (field.getType().toString().endsWith("String")){
                                field.setAccessible(true);
                                field.set(t, new String(o.toString()));
                            }

                            if (field.getType().toString().endsWith("Long")){
                                field.setAccessible(true);
                                field.set(t, new Long(o.toString()));
                            }

                            if (field.getType().toString().endsWith("Float")){
                                field.setAccessible(true);
                                field.set(t, new Float(o.toString()));
                            }

                            if (field.getType().toString().endsWith("Integer")){
                                field.setAccessible(true);

                                field.set(t, Math.round(new Float(o.toString())));
                            }

                            if (field.getType().toString().endsWith("LocalDateTime")){
                                field.setAccessible(true);
                                field.set(t, LocalDateTime.parse(o.toString().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                            }
                        }
//                        // 全部转化为大写
//                        String dbfieldname = field.getName().toUpperCase();
//                        // 获取字段名称
//                        fieldname = field.getName();
//                        // 拼接set方法
//                        methodname = "set" + StrUtil.capitalize(fieldname);
//                        // 获取data里的对应值
//                        methodsetvalue = mapdata.get(dbfieldname);
//                        // 赋值给字段
//                        Method m = beanClass.getDeclaredMethod(methodname, field.getType());
//                        m.invoke(t, methodsetvalue);
                    }
                }
                // 存入返回列表
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 返回
        return list;
    }

}
