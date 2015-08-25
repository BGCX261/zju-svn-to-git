/**
 * DomainAspect.java 2008-12-13 下午09:52:04 lgq 版权所有 (c) 2007-2008 江苏鸿信系统集成有限公司
 */

package com.jsict.base.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.jsict.base.annotation.Allow;
import com.jsict.base.security.User;

/**
 * <p>
 * Description: [描述该类概要功能介绍]
 * </p>
 * 
 * @author <a href="mailto: xxx@neusoft.com">作者中文名</a>
 * @version $Revision$
 */
@Aspect
public class DomainAspect
{
    //    @Pointcut("execution(boolean com.jsict.*.domain.*Domain.is*(..))")
    //    private void canOperationOnDomain()
    //    {
    //
    //    }
    //
    //    @Pointcut("execution(boolean com.jsict.*.domain.*Domain.get*(..))")
    //    private void canOperationOnDomainString()
    //    {
    //
    //    }

    @Pointcut("@annotation(allow)")
    private void allowAnnotation(Allow allow)
    {

    }

    @Around("allowAnnotation(allow)")
    public Object doPermissionCheck(ProceedingJoinPoint pjp, Allow allow)
        throws Throwable
    {
        Object proceed = pjp.proceed();

        Signature signature = pjp.getSignature();

        Class returnType = null;
        if(signature instanceof MethodSignature)
        {
            returnType = ((MethodSignature) signature).getReturnType();
        }

        if(allow != null)
        {
            String permissionCode = allow.permissionCode();
            SecurityContext context = SecurityContextHolder.getContext();
            User u = (User) context.getAuthentication().getPrincipal();

            Boolean booleanvalue = Boolean.valueOf(proceed == null ? "true"
                    : proceed.toString());
            Boolean rvt = booleanvalue && u.hasPermission(permissionCode);

            if(proceed instanceof String || String.class.equals(returnType))
            {
                return rvt.toString();
            }
            else
            {
                return rvt;
            }

        }
        else
        {
            return proceed;
        }
    }
}
