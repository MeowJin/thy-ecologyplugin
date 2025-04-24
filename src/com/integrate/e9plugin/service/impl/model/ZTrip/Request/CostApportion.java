package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;

@Data
public class CostApportion {
    /**
     * 法人社会信用代码（如果不传默认获取成本中心对应的法人）
     */
    public String corpCode;
    /**
     * 成本中心编码
     */
    public String costId;
    /**
     * 所属部门的编码
     */
    public String costOrgId;
    /**
     * 是否主成本中心（必须存在一个主成本中心）
     */
    public boolean major;
    /**
     * 分摊比例(加起来必须等于100)
     */
    public long shareRate;
}

