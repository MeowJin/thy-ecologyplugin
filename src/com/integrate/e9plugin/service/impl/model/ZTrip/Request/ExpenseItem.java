package com.integrate.e9plugin.service.impl.model.ZTrip.Request;

import lombok.Data;

@Data
public class ExpenseItem {
    /**
     * 金额
     */
    public String amount;
    /**
     * 费用类型编码
     */
    public String expenseCode;
    /**
     * 费用类型名称
     */
    public String expenseName;
}

