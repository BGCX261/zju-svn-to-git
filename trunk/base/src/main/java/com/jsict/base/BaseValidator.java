package com.jsict.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;

import com.jsict.base.security.User;

public class BaseValidator<Service extends IBaseService> implements
        IBaseValidator<Service>
{

    /**
     * <p>
     * Description:[方法功能中文描述]
     * </p>
     * 
     * @param service
     * @return
     * @author:[创建者中文名字]
     * @update:[日期YYYY-MM-DD] [更改人姓名][变更描述]
     */

    @SuppressWarnings("unchecked")
    public boolean supports(IBaseService service)
    {
        Class<Service> clazz = (Class<Service>) ((ParameterizedType) this
                .getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        return clazz.isAssignableFrom(service.getClass());
    }

    protected Serializable getUserId()
    {
        User user = getUser();

        return user == null ? null : user.getUserId();
    }

    protected User getUser()
    {
        SecurityContext context = SecurityContextHolder.getContext();
        return (User) context.getAuthentication().getPrincipal();

    }

}
