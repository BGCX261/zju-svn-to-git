package com.jsict.base.exception;

@SuppressWarnings("serial")
public class FilterFormatNotValidException extends ApplicationException
{
    public FilterFormatNotValidException()
    {
        super();
    }
    
    public FilterFormatNotValidException(String filter)
    {
        super(filter);
    }
}
