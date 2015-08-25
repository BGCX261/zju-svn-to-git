package com.jsict.jszju.constants;

import com.jsict.base.annotation.CodeKeyDef;

/**
 * <p>
 * </p>
 * 
 * @author : Feng Jingzhun
 * @version : 1.0
 * @date : 2008-9-24 9:22:38
 */
public interface CodeKey
{

    /* 01:订单类型 */
    String ORDER_TYPE_BUY = "010001"; // 求

    String ORDER_TYPE_SELL = "010002"; // 供

    /* 02:订单状态 */
    // by QL - 以下状态已废弃,新状态见28
    @Deprecated
    String ORDER_STATUS_WATTING_AUDIT = "020001"; // 待审核

    @Deprecated
    String ORDER_STATUS_AUDIT_PASS = "020002";// 审核通过

    @Deprecated
    String ORDER_STATUS_AUDIT_UNTREAD = "020003";// 审核退回

    /* 05: 交割状态 */

    String DELIVERY_STATUS_INITIALIZED = "050000"; // 新建

    String DELIVERY_STATUS_APPLICATION_SENT = "050001"; // 卖方已发出交货申请

    String DELIVERY_STATUS_APPLICATION_CONFIRMED = "050002"; // 买方已确认交货申请

    String DELIVERY_STATUS_GOODS_CONFIRMED = "050003"; // 买方已确认交付完成

    String DELIVERY_STATUS_80_PERCENT_MONEY_CASHED = "050004"; // 平台已划拨80%货款给卖方

    String DELIVERY_STATUS_INVOICE_CONFIRMED = "050005"; // 已转交增值税发票

    String DELIVERY_STATUS_100_PERCENT_MONEY_CASHED = "050006"; // 平台已划拨100%货款给卖方

    String DELIVERY_STATUS_COMPLETED = "050007"; // 已释放保证金

    String DELIVERY_STATUS_SELF_APPLICATION_SENT = "050008"; // 申请自主交割

    String DELIVERY_STATUS_SELF_APPLICATION_CONFIRMED = "050009"; // 确认自主交割申请

    /* 10:交易品种状态 */
    String GOODS_TYPE_STATUS_AVAILABLE = "100001";// 可用

    String GOODS_TYPE_STATUS_WAITING_AUDIT = "100002";// 待审核

    String GOODS_TYPE_STATUS_DISABLED = "100003";// 已注销

    String GOODS_TYPE_STATUS_AUDIT_REJECTED = "100004";// 审核被拒

    /* 14:调解类型 */
    String INTERCEDE_TYPE_CONSULTATION = "140001";// 双方协商解决

    String INTERCEDE_TYPE_CORRESPONDATION = "140002";// 中心协助解决

    String INTERCED_TYPE_BYLAW = "140003";// 法律途径解决

    /* 15：调解单审核状态 */
    String AUDIT_STATUS_WAITING = "150001";// 待审核

    String AUDIT_STATUS_AUDITED = "150002";// 通过审核

    String AUDIT_STATUS_DECLINE = "150003";// 未通过审核

    String AUDIT_STATUS_BACK = "150004";// 已取回

    /* 16:数据状态 */
    String DATA_STATUS_NORMAL = "160001"; // 正常

    String DATA_STATUS_DELETED = "160002"; // 作废

    /* 17:交易品种用途 */
    String GOODS_TYPE_USAGE_SPOT = "170001";// 现货

    String GOODS_TYPE_USAGE_FUTURES = "170002";// 远期

    /* 18:重量单位 */
    String WEIGHT_UNIT_KILOGRAM = "180001";// 千克

    String WEIGHT_UNIT_TON = "180002";// 吨

    /* 19:远期现货订单类型 */
    String FUTURES_ORDER_TYPE_BUY = "190001";// 订货买

    String FUTURES_ORDER_TYPE_SELL = "190002";// 订货卖

    String FUTURES_ORDER_TYPE_TRAN_SELL = "190003";// 转让卖

    String FUTURES_ORDER_TYPE_TRAN_BUY = "190004";// 转让买

    /* 20:远期现货订单状态 */
    String FUTURES_ORDER_STATUS_COMMITED = "200001";// 已提交

    String FUTURES_ORDER_STATUS_PREPARE = "200002";// 预备指令

    String FUTURES_ORDER_STATUS_DEALED = "200003";// 已成交

    String FUTURES_ORDER_STATUS_PARTDEALED = "200004";// 部分成交

    String FUTURES_ORDER_STATUS_DISABLED = "200005";// 已撤单

    String FUTURES_ORDER_STATUS_CANCEL = "200006";// 已取消

    /* 21:远期现货订单是否平仓 */
    String SQUARE_POSITION_YES = "210001";// 是

    String SQUARE_POSITION_NO = "210002";// 是

    /* 22:货物种类 */
    String SPOT_GOODS = "220001";// 现货

    String FUTURES_GOODS = "220002";// 远期

    /* 仓单状态 */
    // * 03:仓单状态
    String WAREHOUSE_RECEIPT_STATUS_UNAUDITED = "030001";// 未审核

    String WAREHOUSE_RECEIPT_STATUS_AUDITED = "030002";// 已审核

    String WAREHOUSE_RECEIPT_STATUS_REFUSED = "030003";// 已驳回

    String WAREHOUSE_RECEIPT_STATUS_FROZEN = "030004";// 已冻结

    String WAREHOUSE_RECEIPT_STATUS_NULLIFIED = "030005";// 已注销

    /* 竞价记录状态 */
    // TODO: assign code No
    String BARGAIN_RECORD_STATUS_WAITING = "0";// 竞价中

    String BARGAIN_RECORD_STATUS_SELLER_CONFIRMED = "1";// 卖家以确认

    String BARGAIN_RECORD_STATUS_BUYER_CONFIRMED = "2";// 买家以确认

    String BARGAIN_RECORD_STATUS_BOTH_CONFIRMED = "3";// 双方已确认

    /* 06 :合同类型 */

    /* 23:转账类型 */
    String TRANSFER_IN = "230001"; // 转入

    String TRANSFER_OUT = "230002"; // 转出

    /* 24:转账审核状态 */
    String TRANSFER_STATUS_UNAUDITED = "240001"; // 未审核

    String TRANSFER_STATUS_AUDITED = "240002"; // 已审核

    String TRANSFER_STATUS_REFUSED = "240003"; // 已驳回

    /* 25:资金流转用途 */
    String CASH_FLOW_USAGE_TRANSFER_IN = "250001"; // 转入

    String CASH_FLOW_USAGE_TRANSFER_OUT = "250002"; // 转出

    String CASH_FLOW_USAGE_TAX = "250003"; // 税率

    String CASH_FLOW_USAGE_COMMISSION = "250004"; // 交易手续费

    String CASH_FLOW_USAGE_COMMISSION_FU = "250013"; // 远期交易手续费

    String CASH_FLOW_USAGE_CHARGE = "250009";// 交收手续费

    String CASH_FLOW_USAGE_CHARGE_FU = "250014"; // 远期交收手续费

    String CASH_FLOW_USAGE_SUBSCRIPTION_FROZEN = "250005"; // 订单保证金冻结

    String CASH_FLOW_USAGE_SUBSCRIPTION_ICEOUT = "250016"; // 订单保证金解冻

    String CASH_FLOW_USAGE_ICEOUT = "250006"; // 解冻

    String CASH_FLOW_USAGE_BALANCE_DIFF = "250007"; // 账面价差

    String CASH_FLOW_USAGE_TRANSFER_IN_DIFF_ADD = "250008";// 买入转让差价增加

    String CASH_FLOW_USAGE_TRANSFER_OUT_DIFF_MIN = "250010";// 卖出转让差价减少

    String CASH_FLOW_USAGE_TRANSFER_IN_DIFF_MIN = "250011";// 买入转让差价减少

    String CASH_FLOW_USAGE_TRANSFER_OUT_DIFF_ADD = "250012";// 卖出转让差价增加

    String CASH_FLOW_USAGE_ORDER_FROZEN_FU = "250015"; // 远期现货订单保证金冻结

    String CASH_FLOW_USAGE_SPOT_CONTRACT_DEPOSIT_SELLER = "250017"; // 冻结卖方现货合同保证金

    String CASH_FLOW_USAGE_SPOT_CONTRACT_DEPOSIT_BUYER = "250018"; // 冻结买方现货合同保证金

    String CASH_FLOW_USAGE_SPOT_CONTRACT_DEPOSIT_ICEOUT_SELLER = "250019"; // 释放卖方现货合同保证金

    String CASH_FLOW_USAGE_SPOT_CONTRACT_DEPOSIT_ICEOUT_BUYER = "250020"; // 释放买方现货合同保证金

    String CASH_FLOW_USAGE_SPOT_GOODS_PAYMENT = "250021"; // 现货货款支付

    String CASH_FLOW_USAGE_FUTURES_GOODS_PAYMENT = "250022"; // 远期货款支付

    String CASH_FLOW_USAGE_SPOT_GOODS_PAYMENT_RECEIVE = "250023"; // 现货货款卖方收入

    String CASH_FLOW_USAGE_FUTURES_GOODS_PAYMENT_RECEIVE = "250024"; // 远期货款卖方收入

    /* 26:交易商状态 */
    String PARTICIPANT_STATUS_ENABLED = "260001"; // 正常

    String PARTICIPANT_STATUS_DISABLED = "260002"; // 注销

    /* 27:交易席位状态 */

    String TRADESEAT_STATUS_ENABLED = "270001"; // 正常

    String TRADESEAT_STATUS_DISABLED = "270002"; // 已禁用

    String TRADESEAT_STATUS_CANCELLED = "270003"; // 已注销

    /* 37:有无状态类型 */
    /* 无状态 */
    String NULL_STATUS = "NaNNaN";

    /* 已建立状态 */
    String HAVE_STATUS = "alrhav";

    /* 28: 订单状态 */
    String SPOT_ORDER_WAITING_QUOTED = "280001"; // 待挂牌

    String SPOT_ORDER_QUOTED = "280002"; // 已挂牌

    String SPOT_ORDER_REJECTED = "280003"; // 已驳回

    String SPOT_ORDER_CANCELLED = "280004"; // 已撤牌

    String SPOT_ORDER_QUITTED = "280005"; // 已退牌

    String SPOT_ORDER_WAITING_AUDIT = "280006"; // 待审核

    /* 29: 市场状态 */
    String MARKET_STATUS_AVAILABLE = "290001";// 可用

    String MARKET_STATUS_UNAVAILABLE = "290002";// 不可用

    String MARKET_STATUS_PAUSED = "290003";// 暂停交易

    /* 30: 通用是否状态 */
    String YES = "300001";

    String NO = "300002";

    /* 38: 通用可用状态 */
    String AVAILABLE = "380001";

    String UNAVAILABLE = "380002";

    /* 39: 合同交收申请状态 */
    String CONTRACT_DELIVERY_APPLY_APPLIED = "390001"; // 已申请

    String CONTRACT_DELIVERY_APPLY_UNAPPLIED = "390002"; // 未申请

    /* 40: 合同状态 */
    String CONTRACT_STATUS_NEW = "400001";// 新的

    String CONTRACT_STATUS_PARTIAL_TRANSFERRED = "400002";// 部分转让

    String CONTRACT_STATUS_TRANSFERRED = "400003";// 已转让

    String CONTRACT_STATUS_TERMINATED = "400004";// 已解除

    /* 34: 合同违约状态 */

    String VIOLATE_STATUS_SELLER = "340002"; // 卖家违约

    String VIOLATE_STATUS_BUYER = "340003"; // 买家违约

    String VIOLATE_STATUS_BOTH = "340004"; // 双方违约

    /* 35: 交收申请状态 */
    String DELIVERY_STATUS_APPLIED = "350001";// 已提交

    String DELIVERY_STATUS_MATCHED = "350002";// 已匹配

    String DELIVERY_STATUS_MATCHING = "350003";// 匹配中

    String DELIVERY_STATUS_CANCELLED = "350004";// 已取回

    String DELIVERY_STATUS_PARTIAL_MATCHED = "350005";// 已部分匹配

    /* 36: 属性类型 */
    String PROPERTY_TYPE_TEXT = "360001";// 文本

    String PROPERTY_TYPE_NUMBER = "360002";// 数值

    String PROPERTY_TYPE_DATE = "360003";// 日期

    /* 41: 仓库类型 */
    String WAREHOUSE_TYPE_SELF = "410001";// 自备库

    String WAREHOUSE_TYPE_SHARE = "410002"; // 公共仓库

    /* 42: 计费类型 */
    String CHARGE_TYPE_FIX = "420001";// 定额

    String CHARGE_TYPE_RATIO = "420002";// 比例

    /* 43: 交易日类型 */
    @CodeKeyDef("开市")
    String TRADEDATE_STATUS_OPEN = "430001";

    @CodeKeyDef("闭市")
    String TRADEDATE_STATUS_CLOSE = "430002";

    /* 44: 交收类型 */
    String DELIVERY_TYPE_FUTURES = "440001"; // 远期交收记录

    String DELIVERY_TYPE_SPOT = "440002";// 现货交收记录

    /* 45：评价等级 */
    String EVALUATION_GRADE_GOOD = "450001";// 好评

    String EVALUATION_GRADE_COMMON = "450002";// 中评

    String EVALUATION_GRADE_BAD = "450003";// 差评

    /* 46: 资金流向类型 */
    String CASH_FLOW_TYPE_SPOT = "460001";// 现货资金流向

    String CASH_FLOW_TYPE_FUTURES = "460002"; // 远期资金流向

    String CASH_FLOW_TYPE_NULL_TYPE = "460003"; // 无类型资金流向

    /* 47: 现货合同状态 */
    String SPOT_CONTRACT_STATUS_NEW = "470001"; // 新建

    /* 48：远期交易品种状态 */
    String F_GOODSTYPE_STATUS_USABLE = "480001";// 可用

    String F_GOODSTYPE_STATUS_DISABLE = "480002";// 禁用

    String F_GOODSTYPE_STATUS_DROP = "480003";// 作废

    // 期货电子交易 added by sz 编号 60-75 被预定
    /* 60：买卖方向 */
    String F_ORDER_BUY = "600001"; // 买 买卖方向

    String F_ORDER_SELL = "600002"; // 卖 买卖方向

    /* 61: 委托订单状态 */
    String F_ORDER_STATUS_UNDEAL = "610001"; // 未成交

    String F_ORDER_STATUS_PARTDEALED = "610002"; // 部分成交

    String F_ORDER_STATUS_DEALED = "610003"; // 已完全成交

    String F_ORDER_STATUS_PREPARE = "610004"; // 未挂单 预备指令状态

    String F_ORDER_STATUS_RETRIVE = "610005"; // 已撤单

    /* 62: 委托订单类型 */
    String F_ORDER_TRANSAC_TYPE_NEW = "620001"; // 订立

    String F_ORDER_TRANSAC_TYPE_TRANSFER = "620002"; // 转让

    /* 63: 订单订货明细状态 */
    String F_ORDER_INFO_STATE_UNTRANSFER = "630001"; // 未转让

    String F_ORDER_INFO_STATE_PARTTRANSFER = "630002"; // 部分转让

    String F_ORDER_INFO_STATE_TRANSFERRED = "630003"; // 完全转让

    /* 64: 订单订货交收申请状态 */
    String F_ORDER_INFO_DELI_APP_STAT_UNAPPLY = "640001"; // 未申请

    String F_ORDER_INFO_DELI_APP_STAT_APPLIED = "640002"; // 已申请

    String F_ORDER_INFO_DELI_APP_STAT_MATCHED = "640003"; // 已匹配

    String F_ORDER_INFO_DELI_APP_STAT_REFUSED = "640004"; // 审核不通过

    String F_ORDER_INFO_DELI_APP_STAT_CANCELLED = "640005"; // 已取消

    /* 65: 订单订货交收状态 */
    String F_ORDER_INFO_DELI_STAT_UNDELIVERY = "650001"; // 未交收

    String F_ORDER_INFO_DELI_STAT_READY = "650002"; // 交收准备

    String F_ORDER_INFO_DELI_STAT_MATCHED = "650006"; // 匹配完成

    String F_ORDER_INFO_DELI_STAT_WARNING = "650003"; // 交收警告

    String F_ORDER_INFO_DELI_STAT_FINISH = "650004"; // 交收完成

    String F_ORDER_INFO_DELI_STAT_VIOLATE = "650005"; // 违约结束

    /* 66: 归档状态 */
    String F_ORDER_INFO_ARCHIVE_STAT_UNARCH = "660001"; // 未归档

    String F_ORDER_INFO_ARCHIVE_STAT_ARCHED = "660002"; // 已归档

    /* 67: 结算状态 */
    String F_DAILY_BALANCE_STATE_UNBILL = "670001"; // 未结算

    String F_DAILY_BALANCE_STATE_BILLED = "670002"; // 已结算

    // 期货电子交易 added by sz
    /* 76: 席位风险控制状态 见：38 */

    /* 77: 货物品种风险控制状态 见：38 */

    /* 78: 远期交易品种等级 */
    String F_GOODS_TYPE_GRADE_0 = "780000";// 特级

    String F_GOODS_TYPE_GRADE_1 = "780001";// 一级

    String F_GOODS_TYPE_GRADE_2 = "780002";// 二级

    String F_GOODS_TYPE_GRADE_3 = "780003";// 三级

    /* 79：出入金状态 */
    String CASH_TRANSFER_STATUS_IN = "790001";// 可入金

    String CASH_TRANSFER_STATUS_CANNOTIN = "790002";// 禁止入金

    String CASH_TRANSFER_STATUS_OUT = "790003";// 可出金

    String CASH_TRANSFER_STATUS_CANNOTOUT = "790004";// 禁止出金

    /* 远期交易品种交收匹配状态 */
    String F_GOODSTYPE_DELIVERY_MATCH_STATUS_1 = "800001";// 未匹配

    String F_GOODSTYPE_DELIVERY_MATCH_STATUS_2 = "800002";// 完全匹配

    String F_GOODSTYPE_DELIVERY_MATCH_STATUS_3 = "800003";// 部分匹配

    String F_GOODSTYPE_DELIVERY_MATCH_STATUS_4 = "800004";// 正在匹配

    /* 席位风险参数等级 ADDED BY CAI E */
    String F_SEAT_RISK_GRADE_0 = "810000";// 特级

    String F_SEAT_RISK_GRADE_1 = "810001";// 一级

    String F_SEAT_RISK_GRADE_2 = "810002";// 二级

    String F_SEAT_RISK_GRADE_3 = "810003";// 三级

    /* 远期交收合同签章状态 */
    String F_DELIVERY_CONTRACT_SEAT_STATUS_0 = "820000";// 双方均未签章

    String F_DELIVERY_CONTRACT_SEAT_STATUS_1 = "820001";// 买方已签章

    String F_DELIVERY_CONTRACT_SEAT_STATUS_2 = "820002";// 卖方已签章

    String F_DELIVERY_CONTRACT_SEAT_STATUS_3 = "820003";// 双方均签章

    /* 远期交易交收申请审核状态 */
    String F_DELIVERY_APPLY_AUDIT_STATUS_1 = "830001";//正常通过

    String F_DELIVERY_APPLY_AUDIT_STATUS_2 = "830002";//风险通过

    String F_DELIVERY_APPLY_AUDIT_STATUS_3 = "830003";//仓单不匹配

    String F_DELIVERY_APPLY_AUDIT_STATUS_4 = "830004";//资金不足

    String F_DELIVERY_APPLY_AUDIT_STATUS_5 = "830005";//风险拒绝

    String F_DELIVERY_APPLY_AUDIT_STATUS_6 = "830006";//暂无卖家

    String F_DELIVERY_APPLY_AUDIT_STATUS_7 = "830007";//暂无买家

    String F_DELIVERY_APPLY_AUDIT_STATUS_8 = "830008";//中心暂停提前交收

    String F_DELIVERY_APPLY_AUDIT_STATUS_9 = "830009";//尚未审核

    String F_DELIVERY_APPLY_AUDIT_STATUS_10 = "830010";//其他

    /* 远期交收合同状态 */
    String F_DELIVERY_CONTRACT_STATUS_1 = "840001";//未评估

    String F_DELIVERY_CONTRACT_STATUS_2 = "840002";//已评估

    String F_DELIVERY_CONTRACT_STATUS_3 = "840003";//已确认

    String F_DELIVERY_CONTRACT_STATUS_4 = "840004";//已归档

}
