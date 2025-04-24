package com.integrate.e9plugin.service.impl.model.ZTrip.Request;


import lombok.Data;

import java.util.List;

@Data
public class TravelPlanModel {
    /**
     * 申请人员工编号
     */
    public String applicant;
    /**
     * 申请时间，格式 yyyy-MM-dd HHmmss
     */
    public String applyDate;
    /**
     * 出差申请类型0：出差申请（默认）。1：用车申请
     */
    public String applyType;
    /**
     * 差旅出行人列表常住地列表：
     * 如果一个人有多个常驻地，则传多个对象到这个列表中
     * 例如[{"userId":"A","cityId":"10001"},{"userId":"A","cityId":"10001"},{"userId":"B","cityId":"20001"}]，则代表出行人中员工号为A的员工有两个常驻地，员工号为B的员工有一个常驻地，出行人中非员工不支持该功能
     */
    public List<BaseGroundList> baseGroundList;
    /**
     * 用车申请单可用车次数，必须大于等于0
     * ps：在出差类型为1的时候，carSceneCode、carCount必须有一个字段有值
     */
    public Long carCount;
    /**
     * 用车场景的code，由在途提供
     */
    public String carSceneCode;
    /**
     * 法人信息：纳税人识别号或法人名称
     */
    public String corpCode;
    /**
     * 成本中心分摊
     */
    public List<CostApportion> costApportion;
    /**
     * 费用归属成本中心编码
     */
    public String costId;
    /**
     * 费用归属成本中心所在的组织单位编码
     */
    public String costOrgId;
    /**
     * 费用归属成本中心所在的组织单位名称全路径，例如：上海振宇股份有限公司/江苏分公司/营销中心/销售一部
     * ps：costId、costOrgId、costOrgName必须有一个字段有值，如果costId无法唯一定位成本中心，则还需costOrgId字段有值。
     */
    public String costOrgName;
    /**
     * 是否创建酒店计划， 默认true: 创建， false:不创建 ps：自动创建酒店计划时，行程计划列表(travelItems)需要按照出行的先后顺序排序后传入
     */
    public boolean createHotelPlan;
    /**
     * 申请单预算/用车额度，使用场景：当申请单为出差申请单时，差旅预算总金额；当申请单为用车申请时，为打车的用车额度
     */
    public String expend;
    /**
     * 预算费用明细列表
     */
    public List<ExpenseItem> expenseItems;
    /**
     * 扩展字段，使用Json格式的字符串形式进行传递，若OA系统中有特殊字段，可以通过此字段传参数，Json中的字段可自定义。可支持多个 extendField1 ...
     * extendField10
     */
    public Extend2 extend2;
    /**
     * 行程管控次数，当次数管控类型为用户填写时（MANUAL），此参数必填
     */
    public Long manualControlTimes;
    /**
     * 申请单位编码
     */
    public String orgId;
    /**
     * 费用支付公司
     */
    public String paymentCorp;
    /**
     * 差旅单号，主键，企业内应保证唯一
     */
    public String planNo;
    /**
     * 项目分摊
     */
    public List<ProjectApportion> projectApportion;
    /**
     * 费用归属的项目编码
     */
    public String projectCode;
    /**
     * 费用归属的项目名称
     */
    public String projectName;
    /**
     * 备注
     */
    public String remark;
    /**
     * 状态值，有效：VALID；
     * 无效：INVALID；
     * 审批中：LAUNCHING_AUDIT;
     */
    public String status;
    /**
     * 行程次数管控类型，DEST_NUM：目的地个数； DEST_NUM_X2：目的地个数*2； MANUAL：用户填写
     */
    public String timesControlType;
    /**
     * 差旅行程计划列表，1)当出行方式为简要行程时，行程类型不可以包含酒店行程
     */
    public List<TravelItem> travelItems;
    /**
     * 差旅出行人列表
     */
    public List<Traveller> travellers;
    /**
     * 出行类型code，如果是多成本中心或简要行程时必填
     */
    public String travelMoldCode;
    /**
     * 出差事由
     */
    public String travelReason;
    /**
     * 分摊模式，分摊模式 0：单成本中心（默认） ，1： 多成本中心按人分摊， 2：多成本中心按订单分摊
     */
    public Long travelScene;
    /**
     * 在途系统的差旅类型编码，参见获取差旅类型列表
     */
    public String travelType;
    
}