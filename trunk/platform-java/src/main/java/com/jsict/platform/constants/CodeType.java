/**
 * @(#)CodeType.java 2008-10-9 14:36:16 Administrator 版权所有 (c) 2007-2008
 *                   江苏鸿信系统集成有限公司
 */
package com.jsict.platform.constants;

import com.jsict.base.annotation.CodeTypeDef;

/**
 * <p>
 * </p>
 * 
 * @author : 沈政
 * @version : 1.0
 * @date : 2008-9-23 20:44:20
 */
public interface CodeType
{
    @CodeTypeDef("用户状态")
    String USER_STATUS = "31";

    @CodeTypeDef("公司状态")
    String COMPANY_STATUS = "32";

    @CodeTypeDef("权限类型")
    String PERMISSION_TYPE = "33";

    @CodeTypeDef("公共平台权限")
    String PERMISSION_CODE_ETM = "95";

    @CodeTypeDef("公共平台权限")
    String PERMISSION_CODE_LCA = "96";

    @CodeTypeDef("公共平台权限")
    String PERMISSION_CODE_WMS = "97";

    @CodeTypeDef("公共平台权限")
    String PERMISSION_CODE_PTL = "98";

    @CodeTypeDef("公共平台权限")
    String PERMISSION_CODE_PLF = "99";

    @CodeTypeDef("日志记录类型")
    String BUSINESSLOG_TYPE_PLF = "94";

    @CodeTypeDef("消息状态")
    String MESSAGES_STATUS_PLF = "93";

    @CodeTypeDef("消息回复状态")
    String MESSAGES_REPLY_STATUS = "92";

    @CodeTypeDef("消息删除状态")
    String MESSAGES_DELETE_INDICATOR = "91";

    @CodeTypeDef("阅读通知")
    String MESSAGES_READNOTICE = "90";

}
