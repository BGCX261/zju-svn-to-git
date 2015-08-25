/**
 * @(#)CodeKey.java 2008-10-9 14:35:03 Administrator 版权所有 (c) 2007-2008
 *                  江苏鸿信系统集成有限公司
 */
package com.jsict.platform.constants;

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
    @CodeKeyDef("可用")
    String USER_STATUS_AVAILABLE = "310001";

    @CodeKeyDef("不可用")
    String USER_STATUS_UNAVAILABLE = "310002";

    @CodeKeyDef("已冻结")
    String USER_STATUS_FROZEN = "310003";

    @CodeKeyDef("可用")
    String COMPANY_STATUS_AVAILABLE = "320001";

    @CodeKeyDef("不可用")
    String COMPANY_STATUS_UNAVAILABLE = "320002";

    @CodeKeyDef("菜单")
    String PERMISSION_TYPE_MENU = "100000";

    @CodeKeyDef("元素")
    String PERMISSION_TYPE_ITEM = "330002";

    @CodeKeyDef("电子交易平台")
    String PLF_PERMISSION_PLATFORM_ETM = "990001";

    @CodeKeyDef("物流配载平台")
    String PLF_PERMISSION_PLATFORM_LCA = "990002";

    @CodeKeyDef("仓储平台")
    String PLF_PERMISSION_PLATFORM_WMS = "990003";

    @CodeKeyDef("公共平台")
    String PLF_PERMISSION_PLATFORM_PLF = "990004";

    @CodeKeyDef("门户平台")
    String PLF_PERMISSION_PLATFORM_PTL = "990005";

    @CodeKeyDef("用户管理")
    String PLF_PERMISSION_USER_MANAGEMENT = "990006";

    @CodeKeyDef("公司管理")
    String PLF_PERMISSION_COMPANY_MANAGEMENT = "990007";

    @CodeKeyDef("授权管理")
    String PLF_PERMISSION_AUTH_MANAGEMENT = "990008";

    @CodeKeyDef("系统日志")
    String PLF_PERMISSION_SYSTEM_LOG = "990009";

    @CodeKeyDef("操作日志")
    String PLF_PERMISSION_OPERATION_LOG = "990010";

    @CodeKeyDef("数据日志")
    String PLF_PERMISSION_BUSINESS_LOG = "990011";

    @CodeKeyDef("新增")
    String PLF_BUSINESSLOG_TYPE_NEW = "940001";

    @CodeKeyDef("删除")
    String PLF_BUSINESSLOG_TYPE_DELETE = "940002";

    @CodeKeyDef("修改")
    String PLF_BUSINESSLOG_TYPE_MODIFY = "940003";

    @CodeKeyDef("未读")
    String PLF_MESSAGES_TYPE_UNREAD = "930001";

    @CodeKeyDef("已读")
    String PLF_MESSAGES_TYPE_READ = "930002";

    @CodeKeyDef("删除")
    String PLF_MESSAGES_TYPE_DELETED = "930003";

    @CodeKeyDef("未回复")
    String MESSAGES_REPLY_STATUS_NOT_REPLIED = "920001";

    @CodeKeyDef("已回复")
    String MESSAGES_REPLY_STATUS_ALREADY_REPLIED = "920002";

    @CodeKeyDef("未标记删除")
    String MESSAGES_DELETE_INDICATOR_NOTDELETED = "910001";

    @CodeKeyDef("标记删除")
    String MESSAGES_DELETE_INDICATOR_DELETED = "910002";

    @CodeKeyDef("不要求阅读通知")
    String MESSAGES_READNOTICE_NOTREQUIRED = "900001";

    @CodeKeyDef("要求阅读通知")
    String MESSAGES_READNOTICE_REQUIRED = "900002";

    @CodeKeyDef("远期现货品种")
    String ETM_PERMISSION_FUTURES_GOODS_TYPE = "950001";

}
