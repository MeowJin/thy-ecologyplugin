package com.integrate.e9plugin.service.impl.model.ZTrip.Request;


import lombok.Data;

@Data
public class TripModel {
    //身份信息
    public CityModel Auth;

    // 审批单号
    public String ApprovalNumber;

    // 审批状态 0:无效 1：有效
    public Integer Status;

    // 员工编号
    public String EmployeeID;

    //审批单机票信息
    public FlightEndorsementDetailModel FlightEndorsementDetails;

    //审批单酒店信息
    public HotelEndorsementDetailModel HotelEndorsementDetails;

    //审批单火车票信息
    public TrainEndorsementDetailModel TrainEndorsementDetails;
}

