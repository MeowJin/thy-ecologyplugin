package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import com.integrate.e9java.service.impl.model.trip.Request.PassengerModel;
import lombok.Data;

import java.util.List;

@Data
public class FlightEndorsementDetailModel {
    //产品类型 1:国内机票 2：国际机票
    public String ProductType;

    //航程类型 1：单程 2：往返
    public String FlightWay;

    //出发日期的范围的起始日期
    public String DepartDateBegin;

    //出发日期的范围的结束日期
    public String DepartDateEnd;

    //返程日期范围起始日期
    public String ReturnDateBegin;

    //返程日期范围结束日期
    public String ReturnDateEnd;

    //航班起飞时间段的开始时间，格式为HH:MM
    public String TakeOffBeginTime;

    //航班起飞时间段的截止时间，格式为HH:MM
    public String TakeOffEndTime;

    //航班到达时间段的开始时间，格式为HH:MM
    public String ArrivalBeginTime;

    //航班到达时间段的截止时间，格式为HH:MM
    public String ArrivalEndTime;

    //出发城市id列表
    public String[] DepartCityIds;

    //到达城市id列表
    public String[] ArrivalCityIds;

    //出行人列表
    public List<PassengerModel> PassengerList;
}
