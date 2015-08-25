/**
 * ApplicationSecurityListener.java 2008-12-16 上午11:26:43 lgq 版权所有 (c) 2007-2008
 * 江苏鸿信系统集成有限公司
 */

package com.jsict.platform.listener;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.Authentication;
import org.springframework.security.event.authentication.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.event.authentication.AuthenticationSuccessEvent;
import org.springframework.security.event.authorization.AuthorizationFailureEvent;
import org.springframework.security.event.authorization.AuthorizedEvent;
import org.springframework.security.ui.WebAuthenticationDetails;

import com.jsict.platform.service.IUserService;

public class ApplicationSecurityListener implements ApplicationListener
{
    private IUserService userService;

    @Required
    public void setUserService(IUserService userService)
    {
        this.userService = userService;
    }

    public void onApplicationEvent(ApplicationEvent event)
    {
        if(event instanceof AuthorizedEvent)
        {
            AuthorizedEvent authorizedEvent = (AuthorizedEvent) event;
            System.out.println("authorized:" + authorizedEvent);
        }
        else if(event instanceof AuthorizationFailureEvent)
        {

        }
        else if(event instanceof AuthenticationFailureBadCredentialsEvent)
        {
            AuthenticationFailureBadCredentialsEvent badCredentialsEvent = (AuthenticationFailureBadCredentialsEvent) event;
            Object principal = ((AuthenticationFailureBadCredentialsEvent) event)
                    .getAuthentication().getPrincipal();
            //            if(principal instanceof User)
            //            {
            //                User user = (User) principal;
            //                if(user != null)
            //                {
            //                    userService.recordLoginInfo(user.getUserId(), null,
            //                        new Date(), false);
            //                }
            //            }

            System.out.println("badCredentials:" + badCredentialsEvent);
        }
        else if(event instanceof AuthenticationSuccessEvent)
        {
            AuthenticationSuccessEvent authenticationSuccessEvent = (AuthenticationSuccessEvent) event;
            Authentication userAuth = authenticationSuccessEvent
                    .getAuthentication();
            WebAuthenticationDetails details = (WebAuthenticationDetails) userAuth
                    .getDetails();
            String remoteAddress = details.getRemoteAddress();
            //            if(userAuth.getPrincipal() instanceof User)
            //            {
            //                User user = (User) userAuth.getPrincipal();
            //                userService.recordLoginInfo(user.getUserId(), remoteAddress,
            //                    new Date(), true);
            //            }
            System.out.println("authSuccess:" + authenticationSuccessEvent);
        }
        else
        {
            System.out.println("undefined: " + event.getClass().getName());
        }
    }
}
