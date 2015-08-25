package com.jsict.platform.validator;

import java.io.Serializable;

import com.jsict.base.IBaseValidator;
import com.jsict.base.exception.ApplicationException;
import com.jsict.platform.domain.UserAccountDomain;
import com.jsict.platform.service.IUserService;

public interface IUserValidator extends IBaseValidator<IUserService>
{
    public void save(UserAccountDomain userAccountDomain)
        throws ApplicationException;

    void deleteUser(UserAccountDomain userAccountDomain)
        throws ApplicationException;

    void freezeUser(Serializable userId) throws ApplicationException;

    void resetPassword(Serializable userId, String oldpassword,
            String newpassword) throws ApplicationException;
}