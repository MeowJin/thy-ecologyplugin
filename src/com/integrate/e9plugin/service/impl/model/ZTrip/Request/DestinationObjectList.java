package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;


/**
 * 到达城市信息
 */
@Data
public class DestinationObjectList {
    /**
     * 城市国家编码
     */
    public String adCode;
    /**
     * 城市所属国家二字码
     */
    public String countryCode;
    /**
     * 城市所属国家名字
     */
    public String countryName;
    /**
     * 城市ISO3166编码
     */
    public String iso3166;
    /**
     * 城市的层级枚举 0-国际，1-国家，2-省份，3-城市，4-区县
     */
    public String level;
    /**
     * 城市名称
     */
    public String name;
    /**
     * 城市的上级ISO3166
     */
    public String parentIso3166;
    /**
     * 城市的上级城市名称
     */
    public String parentName;
}

