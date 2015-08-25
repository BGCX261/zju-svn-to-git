/**
 * LogType.java 2008-11-26 下午02:25:48 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.annotation;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public enum LogType {
    WHEN_NONE(0x000), WHEN_DELETE(0x001), WHEN_UPDATE(0x010), WHEN_INSERT(0x100), WHEN_ALL(
            0x111);
    private int value;

    LogType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}
