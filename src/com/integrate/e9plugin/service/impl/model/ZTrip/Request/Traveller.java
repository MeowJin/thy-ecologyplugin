package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;

@Data
public class Traveller {
    /**
     * 证件号，出行人证件号 (非员工)
     */
    public String cardNo;
    /**
     * 法人社会信用代码，多成本中心情况下，如果不填会默认获取成本中心对应的法人
     */
    public String corpCode;
    /**
     * 成本中心编码，如果是多成本中心，必填
     */
    public String costId;
    /**
     * 所属部门的编码(如果是多成本中心，必填)
     */
    public String costOrgId;
    /**
     * 出行人姓名
     */
    public String fullname;
    /**
     * 手机号，出行人手机号 (非员工)
     */
    public String mobile;
    /**
     * 出行人职级
     */
    public String rank;
    /**
     * 员工编号，出行人非员工时可为空
     */
    public String userId;
}
