/**
 * PasswordGenerator.java 2008-11-12 上午08:59:25 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.util;

import java.util.Random;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class PasswordGenerator implements IPasswordGenerator
{
    private String defaultPassword = "123456";

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param defaultPassword
     *            The defaultPassword to set.
     */
    public void setDefaultPassword(String defaultPassword)
    {
        this.defaultPassword = defaultPassword;
    }

    /**
     * <p>
     * Description: The genPassword
     * </p>
     * 
     * @return
     * @author: lgq
     * @update: 2008-11-12-上午09:01:03 [变更人姓名] [变更内容]
     */

    public String genPassword()
    {
        Random random = new Random();
        return String.valueOf(random.nextInt(1000000) + 1000000);
    }
}
