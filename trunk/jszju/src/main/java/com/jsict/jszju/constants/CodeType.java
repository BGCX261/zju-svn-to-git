package com.jsict.jszju.constants;

import com.jsict.base.annotation.CodeTypeDef;

/**
 * <p>
 * </p>
 * 
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2008-9-23 20:44:20
 */
public interface CodeType
{

    String ORDER_TYPE = "01";// 订单类型

    // 此类型从02 -> 28
    String ORDER_STATE = "28";// 订单状态

    String WAREHOUSE_RECEIPT_STATUS = "03";// 仓单状态

    String DELIVERY_STATUS = "05"; // 交割状态

    String DICKER_ORDER_TYPE = "04";// 还价类型

    String DICKER_ORDER_STATE = "05";// 还价单状态

    String CONTRACT_TYPE = "06";// 合同类型

    String BALANCE_TYPE = "07";// 贷款结算方式(结算类型)

    String PROD_TYPE = "08";// 产品类型

    String DEAL_ACTION = "09";// 操作类型

    String GOODS_TYPE_STATUS = "10";// 交易品种状态

    String PARTY_TYPE = "11";// 交易商类型

    String MSG_STATE = "12";// 消息状态

    String CREDENTIALS_TYPE = "13";// 证件类型

    String INTERCEDE_TYPE = "14";// 调解单类型

    String AUDIT_STATE = "15";// 审核状态

    String DATA_STATE = "16"; // 数据状态

    String GOODS_TYPE_USAGE = "17";// 交易品种用途

    String WEIGHT_UNIT = "18"; // 重量单位

    String FUTURES_ORDER_TYPE = "19";// 远期现货订单类型

    String FUTURES_ORDER_STATE = "20";// 远期现货订单状态

    String SQUARE_POSITION = "21"; // 远期订单是否平仓

    String GOODS_TYPE = "22"; // 货物类型

    String TRANSFER_TYPR = "23"; // 转账类型

    String TRANSFER_STATUS = "24"; // 转账状态

    String CASH_FLOW_USAGE = "25"; // 资金流转用途

    String PARTICIPANT_STATUS = "26"; // 交易商状态

    String TRADESEAT_STATUS = "27"; // 交易席位状态

    String MARKET_STATUS = "29";// 市场状态

    String YES_NO = "30";// 通用是否状态

    String VIOLATE_STATUS = "34";// 合同违约状态

    String DELIVERY_APPLY_STATUS = "35";// 交收申请状态

    String PROPERTY_TYPE = "36";// 属性类型

    String MISC_STATUS = "37";// 有无状态类型

    String AVAIL_UNAVAIL = "38"; // 通用可用状态

    String CONTRACT_DELIVERY_APPLY_STATUS = "39"; // 合同交收申请状态

    String CONTRACT_STATUS = "40";// 合同状态

    String WAREHOUSE_TYPE = "41"; // 仓库类型

    String CHARGE_TYPE = "42";// 计费类型

    @CodeTypeDef("交易日类型")
    String TRADEDATE_STATUS = "43";// 交易日设置

    String DELIVERY_TYPE = "44";// 交收类型

    String EVALUATE_GRADE = "45";// 评价等级

    String CASH_FLOW_TYPE = "46"; // 资金流向类型

    String SPOT_CONTRACT_STATUS = "47"; // 现货合同状态

    String F_GOODSTYPE_STATUS = "48";// 远期交易品种状态

    // 期货电子交易 added by sz 编号 60-75 被预定
    String BUY_OR_SELL = "60"; // 期货 买卖方向

    String F_ORDER_STATUS = "61"; // 期货 委托订单状态

    String F_ORDER_TRANSAC_TYPE = "62"; // 期货 订单交易类型

    String F_ORDER_INFO_STATE = "63"; // 期货 订单订货明细状态

    String F_ORDER_INFO_DELI_APP_STAT = "64";// 期货 订单订货交收申请状态

    String F_ORDER_INFO_DELI_STAT = "65"; // 期货 订单订货交收状态

    String F_ORDER_INFO_ARCHIVE_STAT = "66"; // 期货 订单订货归档状态

    String F_DAILY_BALANCE_STATE = "67"; // 期货 日结算单结算状态

    // 期货电子交易 added by sz

    // 76,77 add by JinLiang
    // 席位风险控制状态 见：38
    // 货物品种风险控制状态 见：38

    String F_GOODS_TYPE_GRADE = "78";// 远期交易品种

    String CASH_TRANSFER_STATUS = "79";// 出入金状态

    String F_GOODSTYPE_DELIVERY_MATCH_STATUS = "80";// 远期交易品种交收匹配状态

    // 81 added by CaiE
    String F_SEAT_RISK_GRADE = "81";// 席位风险控制等级

    String F_DELIVERY_CONTRACT_SEAT_STATUS = "82";// 远期交收合同签章状态

    //83 远期交收申请审核状态  added by Joy
    String F_DELIVERY_APPLY_AUDIT_STATUS = "83";

    //84 远期交收合同状态
    String F_DELIVERY_CONTRACT_STATUS = "84";//

}