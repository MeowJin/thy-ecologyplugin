package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;

@Data
public class BaseGroundList {
    /**
     * 城市id
     */
    public String cityId;
    /**
     * 员工编号，不支持非员工
     */
    public String userId;
}
