package com.integrate.e9plugin.service.impl;

import com.integrate.e9plugin.service.impl.model.BusinessTravel.TravelModel;
import com.integrate.e9plugin.service.impl.service.BusinessTravelService;
import com.weaverboot.frame.ioc.anno.classAnno.WeaIocReplaceComponent;
import com.weaverboot.tools.logTools.LogTools;
import weaver.conn.RecordSet;
import weaver.general.BaseBean;
import weaver.interfaces.workflow.action.Action;
import weaver.soa.workflow.request.RequestInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
//            String mainTableName = request.getRequestManager().getBillTableName(); //主表表名

            RecordSet rs1 = new RecordSet();

            String sql= "SELECT  bill.id,bill.tablename FROM    workflow_requestbase reqbase   LEFT JOIN workflow_base base ON reqbase.workflowid = base.id  LEFT JOIN workflow_bill bill ON base.formid = bill.id WHERE   requestid = "+ requestId;

            rs1.execute(sql);
            rs1.next();

            //主表表名
           String mainTableName= rs1.getString("tablename");
           String billId= rs1.getString("id");

            RecordSet rs = new RecordSet();
            //获取主表数据
            rs.execute("select * from " + mainTableName + " where requestid =  " + requestId);
            rs.next();

            //主表ID
            String mainId = rs.getString("id");
            //申请人
            String sqr = rs.getString("sqr");
            //出差地点
            String ccdd = rs.getString("ccdd");
            //开始日期
            String ksrq = rs.getString("ksrq");
            //开始时间
            String kssj = rs.getString("kssj");
            //结束日期
            String jsrq = rs.getString("jsrq");
            //结束时间
            String jssj = rs.getString("jssj");
            //出差事由
            String ccsy = rs.getString("ccsy2");
            //协助人1
            String 	xzr1 = rs.getString("xzr1");
            //协助人2
            String 	xzr2 = rs.getString("xzr2");
            //协助人3
            String 	xzr3 = rs.getString("xzr3");
            LogTools.info("申请人：" + sqr+",协助人1:"+xzr1+",协助人2:"+xzr2+",协助人3:"+xzr3);
            RecordSet rs3 = new RecordSet();
            String sql3="SELECT  t1.selectname " +
                    "FROM    workflow_SelectItem t1 " +
                    "        LEFT JOIN workflow_billfield t2 ON t1.fieldid = t2.id " +
                    "WHERE   t2.billid = "+billId+
                    "        AND t2.fieldname = 'ccsy2' " +
                    "        AND selectvalue = "+ccsy;

            rs3.execute(sql3);
            rs3.next();

            String 	ccsyName = rs3.getString("selectname");

            List<TravelModel> travelModels = new ArrayList<TravelModel>();

//            rs.execute("SELECT loginid FROM HrmResource WHERE id= " + sqr);
//            rs.next();
//            String employeeNo = rs.getString("loginid");

            String NewBegin=ksrq+' '+kssj;
            String NewEnd=jsrq+ ' '+ jssj;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startDateTime = LocalDateTime.parse(NewBegin, formatter);
            LocalDateTime endDateTime = LocalDateTime.parse(NewEnd, formatter);
            Duration duration = Duration.between(startDateTime, endDateTime);
            long durationInSeconds = duration.getSeconds();

            TravelModel travel =new TravelModel();
            travel.UserId=sqr;
            travel.NewBegin=NewBegin;
            travel.NewEnd=NewEnd;
            travel.NewDuration=durationInSeconds;
            travel.Reason=ccsyName;
            travel.Location=ccdd;
            travelModels.add(travel);

            LogTools.info("出差申请：" + travelModels);
            if(xzr1!=null&&!xzr1.isEmpty()){
                TravelModel travel1 =new TravelModel();
                travel1.UserId=xzr1;
                travel1.NewBegin=NewBegin;
                travel1.NewEnd=NewEnd;
                travel1.NewDuration=durationInSeconds;
                travel1.Reason=ccsyName;
                travel1.Location=ccdd;
                travelModels.add(travel1);
            }
            LogTools.info("出差申请1：" + travelModels);
            if(xzr2!=null&&!xzr2.isEmpty()){
                TravelModel travel2 =new TravelModel();
                travel2.UserId=xzr2;
                travel2.NewBegin=NewBegin;
                travel2.NewEnd=NewEnd;
                travel2.NewDuration=durationInSeconds;
                travel2.Reason=ccsyName;
                travel2.Location=ccdd;
                travelModels.add(travel2);
            }
            LogTools.info("出差申请2：" + travelModels);
            if(xzr3!=null&&!xzr3.isEmpty()){
                TravelModel travel3 =new TravelModel();
                travel3.UserId=xzr3;
                travel3.NewBegin=NewBegin;
                travel3.NewEnd=NewEnd;
                travel3.NewDuration=durationInSeconds;
                travel3.Reason=ccsyName;
                travel3.Location=ccdd;
                travelModels.add(travel3);
            }
            LogTools.info("出差申请3：" + travelModels);
            BusinessTravelService service=new BusinessTravelService();
            service.SynchronizeTravel(travelModels);

            return Action.SUCCESS;

        } catch (Exception e){
            request.getRequestManager().setMessagecontent("抛转出差申请异常终止流程提交："+e.getMessage());
            LogTools.error("抛转出差申请异常终止流程提交：" + e.getMessage());
            return Action.FAILURE_AND_CONTINUE;
        }
    }
}
