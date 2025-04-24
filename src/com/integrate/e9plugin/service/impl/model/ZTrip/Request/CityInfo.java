package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;

/**
 * 城市基本信息
 *
 * 出行地点，出行城市信息
 */
@Data
public class CityInfo {
    /**
     * 国家二字码
     *
     * 城市所属国家二字码
     */
    public String countryCode;
    /**
     * 国家名字
     *
     * 城市所属国家名字
     */
    public String countryName;
    /**
     * 当前城市的层级枚举
     * 0-国际，
     * 1-国家，
     * 2-省份，
     * 3-城市，
     * 4-区县
     *
     * 城市的层级枚举 0-国际，1-国家，2-省份，3-城市，4-区县
     */
    public String level;
    /**
     * 当前城市的城市名称
     *
     * 城市名称
     */
    public String name;
    /**
     * 当前城市的上级ISO3166
     *
     * 城市的上级ISO3166
     */
    public String parentIso3166;
    /**
     * 当前城市的上级城市名称
     *
     * 城市的上级城市名称
     */
    public String parentName;
}
