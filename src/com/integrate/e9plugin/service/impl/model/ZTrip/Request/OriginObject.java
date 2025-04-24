package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;


/**
 * 出差申请出发城市信息
 * adCode、Iso3166、originObject中其他参数
 * 三类数据至少传一个参数。如果以上几个参数都传，按照以上顺序作为优先级，匹配到唯一一条数据为止，若最终匹配到多条则返回报错信息，同步申请单失败。多个目的地传多条数据
 *
 * 出行城市信息，城市信息：adCode、Iso3166、cityInfo
 * 三个数据至少传一个参数。如果以上几个参数都传，按照以上顺序作为优先级，匹配到唯一一条数据为止，若最终匹配到多条则返回报错信息，同步申请单失败
 */
@Data
public class OriginObject {
    /**
     * 城市国家编码
     */
    public String adCode;
    /**
     * 城市基本信息
     */
    public CityInfo cityInfo;
    /**
     * 城市ISO3166编码
     */
    public String iso3166;
    /**
     * 城市所属国家二字码
     */
    public String countryCode;
    /**
     * 城市所属国家名字
     */
    public String countryName;
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