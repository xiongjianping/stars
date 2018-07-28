package com.yinghuaicc.stars.task.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author:Fly
 * @Date:Create in 2018/7/28 下午10:05
 * @Description: 手动Gc
 * @Modified:
 */
@Component
public class Gc {

    /**
     *@Author:Fly Created in 2018/7/28 下午10:07
     *@Description: 手动Gc
     */
    @Scheduled(fixedRate = 300000)
    public void gc(){

        System.gc();
        System.out.println("GC-------------------------");
    }
}
