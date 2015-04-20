package com.devtest.firstwearableproject.walletUtil;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by global on 4/12/15.
 */
public class ErrorResponse implements Serializable
{

    private static final long serialVersionUID = 1L;
    @SerializedName("errorCode")
    private int errorCode;
    @SerializedName("message")
    private String message="";
    @SerializedName("id")
    private String id="";



    public void setErrorCode (int errorCode)
    {
        this.errorCode = errorCode;
    }
    public int getErrorCode()
    {
        return this.errorCode;
    }


    public void setMessage(String message)
    {
        this.message = message;
    }
    public String getMessage()
    {
        return this.message;
    }


    public void setID(String id)
    {
        this.id = id;
    }

    public String getID()
    {
        return this.id;
    }

}
