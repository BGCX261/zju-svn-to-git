/**
 * UserService.java 2008-11-10 下午03:39:57 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.service.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.providers.encoding.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseService;
import com.jsict.base.dao.EntityFilter;
import com.jsict.base.dao.PagedList;
import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.base.util.ConvertUtil;
import com.jsict.base.util.IMailManager;
import com.jsict.base.util.ListUtil;
import com.jsict.platform.domain.CompanyDomain;
import com.jsict.platform.domain.RoleDomain;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.entity.Company;
import com.jsict.platform.entity.Subsystem;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.entity.UserRole;
import com.jsict.platform.repository.ICompanyRepository;
import com.jsict.platform.repository.IRoleRepository;
import com.jsict.platform.repository.ISubsystemRepository;
import com.jsict.platform.repository.IUserAccountRepository;
import com.jsict.platform.repository.IUserProfileRepository;
import com.jsict.platform.service.IUserService;
import com.jsict.platform.util.IPasswordGenerator;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
@Transactional
public class UserService extends BaseService implements IUserService
{
    public IUserAccountRepository userAccountRepository;

    private IUserProfileRepository userProfileRepository;

    private ICompanyRepository companyRepository;

    private IRoleRepository roleRepository;

    private ISubsystemRepository subsystemRepository;

    public IPasswordGenerator passwordGenerator;

    public PasswordEncoder passwordEncoder;

    public IMailManager mailManager;

    @Required
    public void setSubsystemRepository(ISubsystemRepository subsystemRepository)
    {
        this.subsystemRepository = subsystemRepository;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param mailManager
     *            The mailManager to set.
     */
    public void setMailManager(IMailManager mailManager)
    {
        this.mailManager = mailManager;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param passwordGenerator
     *            The passwordGenerator to set.
     */
    @Required
    public void setPasswordGenerator(IPasswordGenerator passwordGenerator)
    {
        this.passwordGenerator = passwordGenerator;
    }

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param passwordEncoder
     *            The passwordEncoder to set.
     */
    @Required
    public void setPasswordEncoder(PasswordEncoder passwordEncoder)
    {
        this.passwordEncoder = passwordEncoder;
    }

    public void deleteUser(UserAccountDomain userAccountDomain)
    {
        Long userAccountId = Long.parseLong(userAccountDomain.getId());

        UserAccount userAccount = userAccountRepository
                .getUserAccount(userAccountId);

        userAccountRepository.delete(userAccount);
    }

    public List<UserAccountDomain> getUserAccountListByRole(
            Serializable roleId, Serializable companyId, String userName)
        throws ApplicationException, SystemException
    {
        return (List<UserAccountDomain>) convertUserListWithRoles(userAccountRepository
                .getUserAccountListByRole(roleId, companyId, userName));
    }

    public List<UserAccountDomain> getOwnUserAccountListByRole(
            Serializable roleId, Serializable companyId, String userName)
        throws ApplicationException, SystemException
    {
        return (List<UserAccountDomain>) convertUserListWithRoles(userAccountRepository
                .getOwnUserAccountListByRole(roleId, companyId, userName));
    }

    public UserAccountDomain getUserAccountWithoutRoles(
            Serializable userAccountId) throws ApplicationException,
        SystemException
    {
        return convertUserWithoutRoles(userAccountRepository
                .getUserAccount(userAccountId));
    }

    public UserAccountDomain getUserAccountWithRoles(Serializable userAccountId)
        throws ApplicationException, SystemException
    {
        return convertUserWithRoles(userAccountRepository
                .getUserAccount(userAccountId));
    }

    public PagedList<UserAccountDomain> getUserAccountList(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException
    {

        return (PagedList<UserAccountDomain>) convertUserListWithoutRoles(userAccountRepository
                .getUserAccountList(entityFilter, pageNo, pageSize));
    }

    public PagedList<UserAccountDomain> getOwnUserAccountList(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException
    {

        return (PagedList<UserAccountDomain>) convertUserListWithoutRoles(userAccountRepository
                .getOwnUserAccountList(entityFilter, pageNo, pageSize));
    }

    public PagedList<UserAccountDomain> getUserAccountListWithRoles(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException
    {

        return (PagedList<UserAccountDomain>) convertUserListWithRoles(userAccountRepository
                .getUserAccountList(entityFilter, pageNo, pageSize));
    }

    public PagedList<UserAccountDomain> getOwnUserAccountListWithRoles(
            EntityFilter entityFilter, Integer pageNo, Integer pageSize)
        throws ApplicationException, SystemException
    {

        return (PagedList<UserAccountDomain>) convertUserListWithRoles(userAccountRepository
                .getOwnUserAccountList(entityFilter, pageNo, pageSize));
    }

    public List<UserAccountDomain> getUserAccountListByCompany(
            Serializable companyId) throws ApplicationException,
        SystemException
    {
        return (List<UserAccountDomain>) convertUserListWithoutRoles(userAccountRepository
                .getUserAccountListByCompany(companyId));

    }

    public List<UserAccountDomain> getOwnUserAccountListByCompany(
            Serializable companyId) throws ApplicationException,
        SystemException
    {
        return (List<UserAccountDomain>) convertUserListWithoutRoles(userAccountRepository
                .getOwnUserAccountListByCompany(companyId));

    }

    public List<UserAccountDomain> getUserAccountListByRole(Serializable roleId)
        throws ApplicationException, SystemException
    {
        return (List<UserAccountDomain>) convertUserListWithRoles(userAccountRepository
                .getUserAccountListByRole(roleId));
    }

    public List<UserAccountDomain> getOwnUserAccountListByRole(
            Serializable roleId) throws ApplicationException, SystemException
    {
        return (List<UserAccountDomain>) convertUserListWithRoles(userAccountRepository
                .getOwnUserAccountListByRole(roleId));
    }

    public UserAccountDomain save(final UserAccountDomain userAccountDomain)
        throws SystemException, ApplicationException
    {
        // encode password
        final String rawPassword = passwordGenerator.genPassword();
        //final String rawPassword = "123456";

        // send email
        if(mailManager != null)
        {
            try
            {
                new Thread()
                {
                    @Override
                    public void run()
                    {
                        // TODO Auto-generated method stub
                        try
                        {
                            mailManager.sendSSLMail(userAccountDomain
                                    .getEmail(), "您的密码", rawPassword);
                        }
                        catch (MessagingException e)
                        {

                            throw new RuntimeException(e);
                        }
                    }

                }.run();

            }
            catch (Exception e)
            {

            }

        }
        userAccountDomain.setPassword(passwordEncoder.encodePassword(
            rawPassword, null));

        return convertFromUserDomain(userAccountDomain, new UserAccount());
    }

    public UserAccountDomain update(UserAccountDomain userAccountDomain,
            Serializable... roleIds) throws SystemException,
        ApplicationException
    {
        return convertFromUserDomain(userAccountDomain, userAccountRepository
                .getUserAccount(Long.parseLong(userAccountDomain.getId())));
    }

    private UserAccountDomain convertUserWithoutRoles(UserAccount userAccount)
        throws ApplicationException, SystemException
    {
        if(userAccount == null)
        {
            return null;
        }
        UserAccountDomain userAccountDomain = new UserAccountDomain();

        ConvertUtil.entity2domain(userAccount, userAccountDomain);

        CompanyDomain companyDomain = new CompanyDomain();
        ConvertUtil.entity2domain(userAccount.getCompany(), companyDomain);

        userAccountDomain.setCompanyDomain(companyDomain);

        return userAccountDomain;
    }

    private UserAccountDomain convertUserWithRoles(UserAccount userAccount)
        throws ApplicationException, SystemException
    {
        UserAccountDomain userAccountDomain = convertUserWithoutRoles(userAccount);

        Collection<UserRole> userRoles = userAccount.getUserRoles();

        RoleDomain roleDomain;
        for (UserRole userRole : userRoles)
        {
            roleDomain = new RoleDomain();
            ConvertUtil.entity2domain(userRole.getRole(), roleDomain);

            userAccountDomain.addRole(roleDomain);
        }

        return userAccountDomain;
    }

    public Collection<UserAccountDomain> convertUserListWithRoles(
            Collection<UserAccount> userAccountList)
        throws ApplicationException, SystemException
    {
        if(userAccountList == null)
        {
            return null;
        }

        List<UserAccountDomain> domainList;
        if(userAccountList instanceof PagedList)
        {
            domainList = new PagedList<UserAccountDomain>();
            ListUtil.clonePagedInfo((PagedList<UserAccountDomain>) domainList,
                (PagedList<UserAccount>) userAccountList);
        }
        else
        {
            domainList = new ArrayList<UserAccountDomain>();
        }

        for (UserAccount userAccount : userAccountList)
        {
            domainList.add(convertUserWithRoles(userAccount));
        }

        return domainList;

    }

    private Collection<UserAccountDomain> convertUserListWithoutRoles(
            Collection<UserAccount> userAccountList)
        throws ApplicationException, SystemException
    {
        if(userAccountList == null)
        {
            return null;
        }

        List<UserAccountDomain> domainList;
        if(userAccountList instanceof PagedList)
        {
            domainList = new PagedList<UserAccountDomain>();
            ListUtil.clonePagedInfo((PagedList<UserAccountDomain>) domainList,
                (PagedList<UserAccount>) userAccountList);
        }
        else
        {
            domainList = new ArrayList<UserAccountDomain>();
        }

        for (UserAccount userAccount : userAccountList)
        {
            domainList.add(convertUserWithoutRoles(userAccount));
        }

        return domainList;
    }

    private UserAccountDomain convertFromUserDomain(
            UserAccountDomain userAccountDomain, UserAccount userAccount)
        throws SystemException, ApplicationException
    {

        boolean hasError = ConvertUtil.domain2entity(userAccountDomain,
            userAccount);

        if(hasError)
        {
            throw new ApplicationException();
        }

        Company company = companyRepository.getCompany(Long
                .parseLong(userAccountDomain.getCompanyDomain().getId()));
        UserAccount creator = userAccountRepository.getUserAccount(this
                .getUserId());

        Subsystem subsystem = subsystemRepository
                .getSubsystem(userAccountDomain.getSubsystem().getId());
        userAccount.setCompany(company);
        userAccount.setCreator(creator);
        userAccount.setSubsystem(subsystem);

        userAccount = userAccountRepository.save(userAccount);

        return convertUserWithoutRoles(userAccount);

    }

    public void freezeUser(Serializable userId) throws ApplicationException
    {
        userAccountRepository.freeze(userAccountRepository.getUserAccount(Long
                .parseLong(userId.toString())));

    }

    public void disableUser(Serializable userId)
    {
        userAccountRepository.disable(userAccountRepository.getUserAccount(Long
                .parseLong(userId.toString())));

    }

    public void enableUser(Serializable userId)
    {
        userAccountRepository.enable(userAccountRepository.getUserAccount(Long
                .parseLong(userId.toString())));
    }

    public void resetPassword(Serializable userId)
    {
        UserAccount userAccount = userAccountRepository.getUserAccount(Long
                .parseLong(userId.toString()));
        String newRawPassword = passwordGenerator.genPassword();
        String encodedPassword = passwordEncoder.encodePassword(newRawPassword,
            null);
        // change password
        userAccount.setPassword(encodedPassword);
        userAccountRepository.save(userAccount);

        // send email
        if(mailManager != null)
        {
            try
            {
                mailManager.sendSSLMail(userAccount.getEmail(), "您的重置密码",
                    newRawPassword);
            }
            catch (MessagingException e)
            {

                throw new RuntimeException(e);
            }
        }
    }

    public void resetPassword(Serializable userId, String oldpassword,
            String newpassword)
    {
        UserAccount userAccount = userAccountRepository.getUserAccount(Long
                .parseLong(userId.toString()));

        String newRawPassword = newpassword;
        String encodedPassword = passwordEncoder.encodePassword(newRawPassword,
            null);
        // change password
        userAccount.setPassword(encodedPassword);
        userAccountRepository.save(userAccount);

    }

    public void recordLoginInfo(Serializable userAccountId, String loginIp,
            Date loginTime, boolean success)
    {
        userAccountRepository.recordLoginInfo(userAccountId, loginIp,
            loginTime, success);
    }

    @Required
    public void setUserAccountRepository(
            IUserAccountRepository userAccountRepository)
    {
        this.userAccountRepository = userAccountRepository;
    }

    @Required
    public void setUserProfileRepository(
            IUserProfileRepository userProfileRepository)
    {
        this.userProfileRepository = userProfileRepository;
    }

    @Required
    public void setCompanyRepository(ICompanyRepository companyRepository)
    {
        this.companyRepository = companyRepository;
    }

    @Required
    public IRoleRepository getRoleRepository()
    {
        return roleRepository;
    }

}
