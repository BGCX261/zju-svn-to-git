package com.jsict.platform.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.jsict.base.BaseService;
import com.jsict.base.IBaseService;
import com.jsict.base.security.User;
import com.jsict.base.util.HttpUtils;
import com.jsict.base.util.StringUtil;
import com.jsict.platform.constants.CodeKey;
import com.jsict.platform.entity.Permission;
import com.jsict.platform.entity.UserAccount;
import com.jsict.platform.entity.UserRole;
import com.jsict.platform.repository.IPermissionRepository;
import com.jsict.platform.repository.IUserAccountRepository;

@Transactional
public class LoginService extends BaseService implements UserDetailsService,
        IBaseService
{
    private IUserAccountRepository userAccountRepository;

    private IPermissionRepository permissionRepository;

    public UserDetails loadUserByUsername(String loginId)
        throws UsernameNotFoundException, DataAccessException
    {
        //modify by JinLiang  对loginId串进行处理
        UserAccount userAccount = userAccountRepository
                .getUserAccountByLoginId(StringUtil.replaceBlank(loginId));

        if(userAccount == null)
        {
            throw new UsernameNotFoundException(String.format(
                "login id not found : %s", loginId));
        }
        List<GrantedAuthority> authArray = new ArrayList<GrantedAuthority>();

        List<Permission> permissions = permissionRepository
                .getPermissionListByUser(userAccount.getId());

        List<String> roles = new ArrayList<String>();

        authArray.add(new GrantedAuthorityImpl("ROLE_LOGIN"));

        //        //TODO: 加入ptl的首页权限，否则用户在ptl登陆后首页看不到任何东西
        //        authArray.add(new GrantedAuthorityImpl("/98/UserInfo.do"));
        for (Permission o : permissions)
        {
            if(o.getType().equals(CodeKey.PERMISSION_TYPE_ITEM))
            {
                authArray.add(new GrantedAuthorityImpl(o.getCode()));
                if(StringUtils.isBlank(o.getUrl()) == false)
                {
                }
            }
            if(o.getType().equals(CodeKey.PERMISSION_TYPE_MENU)
                    && StringUtils.isBlank(o.getUrl()) == false)
            {
                if(StringUtils.isBlank(o.getUrl()) == false)
                {
                }
            }
        }

        for (UserRole userRole : userAccount.getUserRoles())
        {
            roles.add(userRole.getRole().getBCode());
        }

        GrantedAuthority[] authorities = authArray
                .toArray(new GrantedAuthority[0]);

        //TODO need to complete login verification
        UserDetails user = createUser(userAccount.getId(), userAccount
                .getCompany().getId(), loginId, userAccount.getPassword(),
            true, true, true, true, authorities, roles.toArray(new String[roles
                    .size()]), userAccount.getLoginIp(), userAccount
                    .getLoginTime());
        if(user instanceof User)
        {
            ((User) user).setCompanyName(userAccount.getCompany().getName());
            ((User) user).setUserDisplayName(userAccount.getName());
        }
        return user;
    }

    @Required
    public void setUserAccountRepository(
            IUserAccountRepository userAccountRepository)
    {
        this.userAccountRepository = userAccountRepository;
    }

    @Required
    public void setPermissionRepository(
            IPermissionRepository permissionRepository)
    {
        this.permissionRepository = permissionRepository;
    }

    protected UserDetails createUser(Serializable userId,
            Serializable companyId, String username, String password,
            boolean enabled, boolean accountNonExpired,
            boolean credentialsNonExpired, boolean accountNonLocked,
            GrantedAuthority[] authorities, String[] roles, String loginIp,
            Date loginDate)
    {
        return new User(userId, companyId, username, password, enabled,
            accountNonExpired, credentialsNonExpired, accountNonLocked,
            authorities, roles, loginIp, loginDate);
    }

}
