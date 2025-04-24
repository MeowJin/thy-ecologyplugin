package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import com.integrate.e9java.service.impl.model.trip.Request.PassengerModel;
import lombok.Data;

import java.util.List;

@Data
public class HotelEndorsementDetailModel {
    //产品类型 3:国内酒店 4：海外酒店
    public String ProductType;

    //入住开始日期
    public String CheckInDateBegin;

    //入住截止日期
    public String CheckInDateEnd;

    //离店开始日期
    public String CheckOutDateBegin;

    //离店截止日期
    public String CheckOutDateEnd;

    //入住城市ID
    public String[] CheckInCityCodes;

    //出行人列表
    public List<PassengerModel> PassengerList;
}
