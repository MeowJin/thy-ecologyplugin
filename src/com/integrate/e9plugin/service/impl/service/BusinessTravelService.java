package com.integrate.e9plugin.service.impl.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.integrate.e9java.service.impl.model.ZTrip.Request.TravelPlanModel;
import com.integrate.e9java.service.impl.model.ZTrip.Response.ZTripResponse;
import com.integrate.e9plugin.service.impl.model.BusinessTravel.TravelModel;
import com.integrate.e9plugin.service.impl.utils.OkHttpUtils;
import com.weaverboot.tools.logTools.LogTools;

import java.util.List;

public class BusinessTravelService {
    private String url = "http://10.1.10.111:3000/api/Wework/ApprovalEvection";

    /**
     * 推送出差申请
     */
    public String SynchronizeTravel(List<TravelModel> list) {

        String json = JSONObject.toJSONString(list);

        LogTools.info("发送出差申请单参数：" + json);

        // 发送HTTP POST请求
        String response = OkHttpUtils.httpPostJson(this.url, json);

        LogTools.info("获取到的响应：" + response);

        if(response!="success"){
            LogTools.error("发送出差申请单同步失败：" + response);
            throw new RuntimeException(response);
        }

        return "success";
    }

}
