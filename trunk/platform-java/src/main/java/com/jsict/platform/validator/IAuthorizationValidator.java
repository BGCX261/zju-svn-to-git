package com.jsict.platform.validator;

import java.io.Serializable;

import com.jsict.base.exception.ApplicationException;
import com.jsict.base.exception.SystemException;
import com.jsict.platform.domain.RoleDomain;

public interface IAuthorizationValidator
{

    void updateRole(RoleDomain roleDomain) throws ApplicationException;

    void saveRole(RoleDomain roleDomain) throws ApplicationException;

    void deleteRole(RoleDomain roleDomain) throws ApplicationException;

    void updateParticialUserRoles(Serializable userId,
            Serializable masterUserId, Serializable subsystemId,
            Serializable[] roleIds) throws ApplicationException,
        SystemException;
}
