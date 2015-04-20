package com.devtest.firstwearableproject.walletUtil;

import java.util.List;

/**
 * Created by global on 4/12/15.
 */
public class WalletBaseResponse extends ResponseBase
{
    private List<ErrorResponse> errors;
    private String uniqueResponseId;
    private boolean success;
    public List< ErrorResponse > getErrors()
    {
        return errors;
    }
    public void setErrors( List< ErrorResponse > errors )
    {
        this.errors = errors;
    }
    public String getUniqueResponseId()
    {
        return uniqueResponseId;
    }
    public void setUniqueResponseId( String uniqueResponseId )
    {
        this.uniqueResponseId = uniqueResponseId;
    }
    public boolean isSuccess()
    {
        return success;
    }
    public void setSuccess( boolean success )
    {
        this.success = success;
    }




}
