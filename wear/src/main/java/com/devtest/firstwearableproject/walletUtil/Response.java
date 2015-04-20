package com.devtest.firstwearableproject.walletUtil;

/**
 * Created by global on 4/12/15.
 */
public class Response {
    // private static final String TAG = Response.class.getSimpleName();

    public enum Status {
        OK,
        ERROR,
        UNKNOWN
    }

    public enum ErrorType {
        HTTP_ERROR,
        API_ERROR,
        CONNECTION_ERROR,
        AUTHENTICATION_ERROR,
        PARSE_ERROR,
        NOT_FOUND,
        BAD_REQUEST
    }
}