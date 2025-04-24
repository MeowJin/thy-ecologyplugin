package com.integrate.e9plugin.service.impl.model.ZTrip.Response;

import lombok.Data;

@Data
public class CityListResponse {
    public Integer code;
    public String countryCode;
    public String level;

    public String nameCn;
    public String nameEn;
    public Integer parentCode;
    public Integer sort;
    public String type;
}