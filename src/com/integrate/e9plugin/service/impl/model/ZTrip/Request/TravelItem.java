package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;
import java.util.List;
@Data
public class TravelItem {
    /**
     * 用车申请目的地维度
     */
    public String carDestPlaceLat;
    /**
     * 用车申请目的地经度
     */
    public String carDestPlaceLon;
    /**
     * 用车申请目的地名，为空时默认取目的地名称
     */
    public String carDestPlaceName;
    /**
     * 出发时间，格式 yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss
     */
    public String departureTime;
    /**
     * 目的地的城市,支持传多个目的地，可用英文逗号隔开（举例：10001,10002）
     */
    public String destinationCityId;
    /**
     * 目的地城市对象列表
     * adCode、Iso3166、destinationObjectList中其他参数
     * 三类数据至少传一个参数。如果以上几个参数都传，按照以上顺序作为优先级，匹配到唯一一条数据为止，若最终匹配到多条则返回报错信息，同步申请单失败。多个目的地传多条数据
     */
    public List<DestinationObjectList> destinationObjectList;
    /**
     * 结束时间，格式 yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss
     */
    public String endTime;
    /**
     * 出发地城市id
     */
    public String originCityId;
    /**
     * 出差申请出发城市信息
     * adCode、Iso3166、originObject中其他参数
     * 三类数据至少传一个参数。如果以上几个参数都传，按照以上顺序作为优先级，匹配到唯一一条数据为止，若最终匹配到多条则返回报错信息，同步申请单失败。多个目的地传多条数据
     */
    public OriginObject originObject;
    /**
     * 说明
     */
    public String remark;
    /**
     * 往返程标记，1：标记此行程是往返程；为空或其他值则当前行程为单程；简要行程此字段不可标记为往返，为空即可
     * 注意：
     * 1）用车申请单，只能传单程；若传往返，时间区间可能会变为特定的两天
     * 2）当行程类型 trafficType = HOTEL 时， 此字段只能传单程，如果传了往返会生成同样的两条酒店行程
     */
    public String roundTrip;
    /**
     * 时间严格控制标记，此字段仅当roundTrip字段值为1时有效，用于自动生成往返程行程记录数据时控制行程时间，1:
     * 生成往返程的时候时间严格按出发时间、结束时间来生成；为空或为其他值则生成往返程时每程都是departureTime到endTime时间段。
     */
    public String strictTime;
    /**
     * 行程类型，取值范围：FLIGHT(飞机)/ TRAIN(火车) /HOTEL(酒店)/ UNKNOW(未定)/ CAR(用车)
     * PS:整个差旅申请中如果没有酒店行程，则在途会根据交通行程自动生成相应的酒店行程，如果有酒店行程，则在途不再生成酒店行程。用车行程不支持与其他行程同时存在一个差旅申请中。
     */
    public String trafficType;
}
