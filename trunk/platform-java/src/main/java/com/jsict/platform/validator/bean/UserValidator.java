package com.jsict.platform.validator.bean;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.providers.encoding.PasswordEncoder;

import com.jsict.base.BaseValidator;
import com.jsict.base.context.ProjectContext;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.util.MessageInfo;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.repository.IUserAccountRepository;
import com.jsict.platform.service.IUserService;
import com.jsict.platform.validator.IUserValidator;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
public class UserValidator extends BaseValidator<IUserService> implements
        IUserValidator
{
    private IUserAccountRepository userAccountRepository;

    private PasswordEncoder passwordEncoder;

    @Required
    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * <p>
     * Description: The setUserAccountRepository
     * </p>
     * 
     * @param userAccountRepository
     * @author: lgq
     * @update: [updatedate] [changer][change description]
     */
    @Required
    public void setUserAccountRepository(
            IUserAccountRepository userAccountRepository)
    {
        this.userAccountRepository = userAccountRepository;
    }

    public void save(UserAccountDomain userAccountDomain)
        throws ApplicationException
    {
        //检查是否登陆名重复
        //TODO need to complete error message
        String loginId = userAccountDomain.getLoginId();
        UserAccount userAccount = userAccountRepository
                .getUserAccountByLoginId(loginId);
        if(userAccount != null)
        {
            ProjectContext.getErrorList().add(
                new MessageInfo("P001", new String[]{loginId}));
            throw new ApplicationException();
        }

        //检查是否email重复
        /*
         * String email = userAccountDomain.getEmail(); UserAccount userAccount2
         * = userAccountRepository .getUserAccountByEmail(email);
         * if(userAccount2 != null) { ProjectContext.getErrorList().add( new
         * MessageInfo("002", new String[]{email})); throw new
         * ApplicationException();
         * 
         * }
         */

    }

    public void resetPassword(Serializable userId, String oldpassword,
            String newpassword) throws ApplicationException
    {
        //检查旧的密码是否合法

        UserAccount userAccount = userAccountRepository.getUserAccount(String
                .valueOf(userId));
        String encodedPassword = passwordEncoder.encodePassword(oldpassword,
            null);
        if(StringUtils.equalsIgnoreCase(encodedPassword, userAccount
                .getPassword()) == false)
        {
            ProjectContext.getErrorList().add(new MessageInfo("888"));
            throw new ApplicationException();
        }

    }

    public void deleteUser(UserAccountDomain userAccountDomain)
        throws ApplicationException
    {
        //检查删除或者冻结的对象不能为admin
        UserAccount userAccount = userAccountRepository
                .getUserAccount(userAccountDomain.getId());
        if("admin".equalsIgnoreCase(userAccount.getLoginId()))
        {
            ProjectContext.getErrorList().add(new MessageInfo("P003"));
            throw new ApplicationException();
        }
    }

    public void freezeUser(Serializable userId) throws ApplicationException
    {
        //检查删除或者冻结的对象不能为admin
        UserAccount userAccount = userAccountRepository.getUserAccount(userId);
        if("admin".equalsIgnoreCase(userAccount.getLoginId()))
        {
            ProjectContext.getErrorList().add(new MessageInfo("P003"));
            throw new ApplicationException();
        }
    }
}
