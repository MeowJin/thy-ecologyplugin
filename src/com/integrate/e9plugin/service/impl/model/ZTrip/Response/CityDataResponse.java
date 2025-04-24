package com.integrate.e9plugin.service.impl.model.ZTrip.Response;

import lombok.Data;
import java.util.List;
import com.integrate.e9java.service.impl.model.ZTrip.Response.CityListResponse;

@Data
public class CityDataResponse {
    public List<CityListResponse> list;
    public Integer pageNum;
    public Integer pageSize;

    public Integer pages;
    public Integer total;
}