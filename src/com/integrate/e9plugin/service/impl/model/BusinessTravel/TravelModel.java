package com.integrate.e9plugin.service.impl.model.BusinessTravel;

import lombok.Data;

@Data
public class TravelModel {
    public String UserId;
    public String NewBegin;
    public String NewEnd;
    public long NewDuration;
    public String Reason;
    public String Location;
    public String SummaryInfo;
    public String SummaryInfo2;
}
