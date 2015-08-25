package com.jsict.base;

public interface IBaseValidator<Service extends IBaseService>
{
    @SuppressWarnings("unchecked")
    public abstract boolean supports(IBaseService service);

}