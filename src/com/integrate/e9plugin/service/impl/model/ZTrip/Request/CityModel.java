package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;

@Data
public class CityModel {
    public String countryCode;
    public Integer pageNum;

    public Integer pageSize;
}
