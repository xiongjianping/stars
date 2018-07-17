package com.yinghuaicc.stars.common.utils.mapper;

import com.yinghuaicc.stars.common.config.orika.LocalDateTimeConverter;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import java.util.List;
import java.util.Objects;

/**
 * @Author:Fly
 * @Date:Create in 2018/5/27 下午4:19
 * @Description: po -> vo转换工具
 * @Modified:
 */
public class MapperFactoryUtil{

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    /**
     *@Author:Fly Created in 2018/5/27 下午4:39
     *@Description: 单一Object转换
     */
    public static <S, D> D mapperObject(S source, Class<D> target){

        if (Objects.nonNull(source)){

            getMapperFacade(source, target);

            return mapperFactory.getMapperFacade().map(source, target);
        }

        return null;
    }

    /**
     *@Author:Fly Created in 2018/5/28 下午11:18
     *@Description: List转换
     */
    public static  <S, D>List<D> mapperList(Iterable<S> source, Class<D> destinationClass){

        if (Objects.nonNull(source)){

            getMapperFacade(source, destinationClass);

            return mapperFactory.getMapperFacade().mapAsList(source,destinationClass);
        }

        return null;
    }

    /**
     *@Author:Fly Created in 2018/5/29 上午9:30
     *@Description: 注册Orika MapperFacade
     */
    private static MapperFactory getMapperFacade(Object source, Class target){

        mapperFactory.getConverterFactory().registerConverter(new LocalDateTimeConverter());

        mapperFactory.classMap(source.getClass(),target).mapNulls(false).byDefault().register();

        return mapperFactory;
    }
}
