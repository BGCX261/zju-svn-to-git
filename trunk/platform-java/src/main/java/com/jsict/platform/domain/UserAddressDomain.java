/**
 * UserAddressDomain.java 2008-12-1 下午03:55:22 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.domain;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class UserAddressDomain
{
    private UserAccountDomain userAccountDomain;

    private String firstPinyin;

    public UserAccountDomain getUserAccountDomain()
    {
        return userAccountDomain;
    }

    public void setUserAccountDomain(UserAccountDomain userAccountDomain)
    {
        this.userAccountDomain = userAccountDomain;
    }

    public String getFirstPinyin()
    {
        return firstPinyin;
    }

    public void setFirstPinyin(String firstPinyin)
    {
        this.firstPinyin = firstPinyin;
    }

    public UserAddressDomain()
    {

    }

    public UserAddressDomain(String firstPinyin,
            UserAccountDomain userAccountDomain)
    {
        super();
        this.firstPinyin = firstPinyin;
        this.userAccountDomain = userAccountDomain;
    }

}
