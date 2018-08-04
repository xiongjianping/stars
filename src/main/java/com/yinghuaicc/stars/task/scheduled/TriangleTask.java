package com.yinghuaicc.stars.task.scheduled;

import com.yinghuaicc.stars.repository.mapper.triangle.TriangleMapper;
import com.yinghuaicc.stars.repository.model.triangle.TriangleCQRS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 三角理论定时器
 */
@Component
public class TriangleTask {

    @Autowired
    private TriangleMapper triangleMapper;

    /**
     *@Description: 获取三角理论数据,每天晚上11点59分执行
     */
//    @Scheduled(fixedRate = 200000)
    @Scheduled(cron = "0 59 23 ? * *")
    @Transactional(rollbackFor = Exception.class)
    public void getTriangle(){


        //TODO---查询

        //TODO --查询所有签约id
        //TODO --签约id查询溢租率
        //TODO --签约id查询适配值
        //TODO --签约id查询客销度


        //TODO --签约id查询三角理论是否存在
   /*     TriangleCQRS triangleCQRS = triangleMapper.findTriangleByTriangleId();
        if(triangleCQRS==null){
            //TODO --新增三角理论
            triangleMapper.saveTriangleCQRS();
        }else{
            //TODO --修改三角理论
            triangleMapper.editTriangleCQRS();
        }*/




    }

}
