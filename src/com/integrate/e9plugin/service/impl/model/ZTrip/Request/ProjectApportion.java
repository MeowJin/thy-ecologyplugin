package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;

@Data

public class ProjectApportion {
    /**
     * 法人社会信用代码，如果不传默认获取项目对应的法人
     */
    public String corpCode;
    /**
     * 是否主项目，必须存在一个主项目
     */
    public boolean major;
    /**
     * 项目编码
     */
    public String projectAttachCode;
    /**
     * 所属部门的编码，如果不传默认获取项目对应的组织
     */
    public String projectOrgId;
    /**
     * 分摊比例，加起来必须等于100
     */
    public long shareRate;
}
