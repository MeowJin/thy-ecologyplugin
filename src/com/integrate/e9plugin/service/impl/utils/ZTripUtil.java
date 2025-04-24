package com.integrate.e9plugin.service.impl.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.integrate.e9java.service.impl.model.ZTrip.Response.ZTripResponse;
import com.integrate.e9java.service.impl.model.ZTrip.Response.TokenResponse;
import com.integrate.e9java.service.impl.model.ZTrip.Request.CityModel;
import com.integrate.e9java.service.impl.model.ZTrip.Request.TravelPlanModel;
import com.weaverboot.tools.logTools.LogTools;
import java.util.List;
import com.integrate.e9java.service.impl.model.ZTrip.Response.CityDataResponse;
import com.integrate.e9java.service.impl.model.ZTrip.Response.CityListResponse;

public class ZTripUtil {
    private String host = "https://openapi-uat.z-trip.cn";
    private String grant_type = "client_credentials";
    private String client_id = "J5ZFQ5DLTguryTbrLtzUfQ";
    private String client_secret = "ntzSMB72QpG2AiG9dIOHzQ";
    private  String Token="";

    //获取Token
    private String TripUtil = host+"/bin/token?grant_type=" + grant_type + "&client_id=" + client_id + "&client_secret=" + client_secret;

    //出差申请单
    private String TravelPlanUrl = "https://openapi-uat.z-trip.cn/tmc/v1/travel-plan/save?access_token=";

    //在途城市列表
    private String CityListUrl = "https://openapi-uat.z-trip.cn/tmc/v1/travel-plan/save?access_token=";

    /**
     * 获取Token
     */
    public String GetToken() {
        String response = OkHttpUtils.httpGet(TripUtil);

        if(response != null&& !response.isEmpty()) {
            TokenResponse tokenResponse =  JSON.parseObject(response, TokenResponse.class);
            if(!tokenResponse.access_token.isEmpty() && Token!=null) {
                return tokenResponse.access_token;
            }else{
                throw new RuntimeException(tokenResponse.token_type);
            }
        }
        return null;
    }

    /**
     * 出差申请单同步
     */
    public String SynchronizeTravelPlan(TravelPlanModel model) {
        if(this.Token==null||this.Token.isEmpty()){
            // 获取Token
            this.Token = this.GetToken();

            LogTools.info("获取到的Token：" + this.Token);
        }

        String json = JSONObject.toJSONString(model);

        LogTools.info("发送出差申请单参数：" + json);

        // 发送HTTP POST请求
        String response = OkHttpUtils.httpPostJson(this.TravelPlanUrl+this.Token, json);

        LogTools.info("获取到的响应：" + response);
        if(response != null&& !response.isEmpty()) {
            ZTripResponse zTripResponse = JSON.parseObject(response, ZTripResponse.class);
            LogTools.info("===========：" + zTripResponse.error_description);
            if (zTripResponse.error.equals("0")) {
                return zTripResponse.data;
            } else {
                LogTools.error("发送出差申请单同步失败：" + zTripResponse.error_description);
                throw new RuntimeException(zTripResponse.error_description);
            }
        }

        return null;
    }

    /**
     * 获取在途城市列表
     */
    public List<CityListResponse> SynchronizeCityList() {
        if(this.Token==null||this.Token.isEmpty()){
            // 获取Token
            this.Token = this.GetToken();

            LogTools.info("获取到的Token：" + this.Token);
        }
        CityModel cityRequest = new CityModel();
        cityRequest.pageNum = 1;
        cityRequest.pageSize = 9999;

        String json = JSONObject.toJSONString(cityRequest);
        // 发送HTTP POST请求
        String response = OkHttpUtils.httpPostJson(this.CityListUrl+this.Token,json);

        LogTools.info("获取到的响应：" + response);
        if(response != null&& !response.isEmpty()) {
            ZTripResponse zTripResponse = JSON.parseObject(response, ZTripResponse.class);
            LogTools.info("===========：" + zTripResponse.error_description);
            if (zTripResponse.error.equals("0")) {
                CityDataResponse cityData = JSON.parseObject(response, CityDataResponse.class);

                return cityData.list;
            } else {
                LogTools.error("获取在途城市列表失败：" + zTripResponse.error_description);
                throw new RuntimeException(zTripResponse.error_description);
            }
        }

        return null;
    }
}
