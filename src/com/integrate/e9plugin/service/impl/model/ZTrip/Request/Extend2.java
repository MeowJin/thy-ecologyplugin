package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;
/**
 * 扩展字段，使用Json格式的字符串形式进行传递，若OA系统中有特殊字段，可以通过此字段传参数，Json中的字段可自定义。可支持多个 extendField1 ...
 * extendField10
 */
@Data
public class Extend2 {
    /**
     * 扩展字段1
     */
    public String extendField1;
    /**
     * 扩展字段2
     */
    public String extendField2;
}
