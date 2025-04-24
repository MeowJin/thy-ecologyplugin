package com.integrate.e9plugin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.integrate.e9java.service.impl.model.trip.Request.FlightEndorsementDetailModel;
import com.integrate.e9java.service.impl.model.trip.Request.TripModel;
import com.integrate.e9java.service.impl.utils.TripUtil;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocReplaceComponent;
import com.weaverboot.tools.logTools.LogTools;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.util.ArrayList;
import java.util.List;

@WeaIocReplaceComponent
public class BusinessTravel extends BaseBean implements Action {
    /**
     * 流程路径节点后选择aciton后,会在节点提交后执行此方法。
     */
    public String execute(RequestInfo request) {
        try{
            String requestId = request.getRequestid();
            String mainTableName = request.getRequestManager().getBillTableName(); //主表表名

            RecordSet rs = new RecordSet();

            //获取主表数据
            rs.execute("select * from " + mainTableName + " where requestid =  " + requestId);
            rs.next();

            //主表ID
            String mainId = rs.getString("id");
            //申请人
            String sqr = rs.getString("sqr");

            //流程编码
            //String lcbh = rs.getString("lcbh");

            //获取用户信息
            rs.execute("SELECT loginid FROM HrmResource WHERE id= " + sqr);
            rs.next();
            //员工工号
            String employeeNo = rs.getString("loginid");

            LogTools.info("主数据========" +mainId + "," + sqr + "," + employeeNo);

            //出差行程表名
//            String dt2TableName = mainTableName + "_dt2";
//
//            // 根据mainId查询明细表数据
//            rs.execute("select * from " + dt2TableName + " where mainid =  " + mainId);
//
//            List<TripModel> tripModels = new ArrayList<>();
//
//            // 遍历明细表的数据
//            while (rs.next()) {
//                //开始日期
//                String YCFRQ = rs.getString("YCFRQ");
//                //结束日期
//                String YGLRQ = rs.getString("YGLRQ");
//                //交通工具 0:飞机 1：火车 2：出租车 3：汽车 4：自驾
//                String jtgj1 = rs.getString("jtgj1");
//
//                //开始时间段
//                //0: 上午(00:00-12:00)
//                //1:下午(12:00-18:00)
//                //2:夜晚(18:00-23:59)
//                //3:全天(00:00-23:59)
//                String kssjd = rs.getString("kssjd");
//                //结束时间段
//                String jssjd = rs.getString("jssjd");
//                //行程类型
//                String xclx = rs.getString("xclx");
//                //是否抛转成功
//                Integer xcpzsfcg=rs.getInt("xcpzsfcg");
//
//                //出差天数
//                String sc = rs.getString("sc");
//
//                LogTools.info("明细行数据========" + YCFRQ + "," + YGLRQ + "," + jtgj1 + "," + kssjd + "," + jssjd + "," + xclx + "," + sc);
//
//                //构建携程提前审批对象----开始----
//
//                if(xcpzsfcg!=1) {
//                    TripModel tripModel = new TripModel();
//                    tripModel.ApprovalNumber = lcbh + "_" + YCFRQ; //审批单号
//                    tripModel.Status = 1;
//                    tripModel.EmployeeID = employeeNo;
//
//                    FlightEndorsementDetailModel flightModel = new FlightEndorsementDetailModel();
//
//                    flightModel.ProductType = "1";
//                    flightModel.FlightWay = "2";
//                    flightModel.DepartDateBegin = YCFRQ;
//
//                    tripModel.FlightEndorsementDetails = flightModel;
//
//                    tripModels.add(tripModel);
//                }
//                //构建携程提前审批对象----结束----
//            }
//
//            LogTools.info(JSONObject.toJSONString(tripModels));
//
//            TripUtil tripUtil=new TripUtil();
//
//            for (TripModel model : tripModels) {
//               String appNum= tripUtil.SendApproval(model);
//                LogTools.info("发送成功："+appNum);
//               //如成功则回写成功标记
//                if(appNum!=null){
//                    rs.execute("update " + dt2TableName + " set xcpzsfcg=1 where mainid =  " + mainId);
//                    rs.next();
//                }
//            }

            return Action.SUCCESS;

        } catch (Exception e){
            request.getRequestManager().setMessagecontent("抛转携程商旅订单信息异常终止流程提交："+e.getMessage());
            LogTools.error("抛转携程商旅订单信息异常：" + e.getMessage());
            return Action.FAILURE_AND_CONTINUE;
        }
    }
}
