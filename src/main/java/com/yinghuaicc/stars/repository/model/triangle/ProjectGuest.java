package com.yinghuaicc.stars.repository.model.triangle;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 项目客销度
 */
public class ProjectGuest {

    //id
    private String id;
    //签约id
    private String contractId;
    //项目id
    private String projectId;
    //项目名称
    private String projectName;
    //签约状态
    private String  contractStatus;
    //客销度版本id
    private String guestVerssionId;
    //客流量
    private BigDecimal passengerFlow;
    private LocalDateTime createTime;
    private LocalDateTime modifyTime;
    private String createUser;
    private  String modifyUser;
    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
