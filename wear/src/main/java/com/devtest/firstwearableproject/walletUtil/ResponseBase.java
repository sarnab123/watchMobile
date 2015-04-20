package com.devtest.firstwearableproject.walletUtil;

import org.apache.http.HttpResponse;

/**
 * Created by global on 4/12/15.
 */
public abstract class ResponseBase {
    //private static final String TAG = ResponseBase.class.getSimpleName();

    private String status;
    private int code;

    private Error error;
    private String requestType = REQUEST_TYPE_POST;
    private transient Response.ErrorType errorType;



    public static final String REQUEST_TYPE_GET = "get_";

    public static final String REQUEST_TYPE_POST = "post_";

    private transient int httpStatusCode = -1;

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

    /**
     * Class which contains the error object received
     * from server
     *
     */
    public class Error {
        private String code;
        private String message;

        public String getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }

    /**
     * Checks weather the call was successful
     * @return
     */
    public boolean successful() {
        return code == 200 && getStatus().equals(Response.Status.OK);
    }

    /**
     * Gets the status of API Response
     * @return
     */
    public Response.Status getStatus() {
        // We need to have no existing errors
        if(errorType != null) {
            return Response.Status.ERROR;
        }

        // The http response needs to be OK
        if(!(httpStatusCode >= 200 && httpStatusCode <= 204)) {
            errorType = Response.ErrorType.API_ERROR;
            return Response.Status.ERROR;
        }

        // Looks like things went well
        if(status.trim().equalsIgnoreCase("OK")) {
            return Response.Status.OK;
        }

        // The API is indicating an ERROR
        if(status.trim().equalsIgnoreCase("ERROR")) {
            if(errorType == null) {
                // Make sure we have set the error type
                errorType = Response.ErrorType.API_ERROR;
            }
            return Response.Status.ERROR;
        }

        return Response.Status.UNKNOWN;
    }

    public boolean hasError() {
        return (error != null && error.getMessage() != null);
    }

    public Response.ErrorType getErrorType() {
        return errorType;
    }

    public Error getError() {
        return error;
    }


    public void setConnectionError() {
        errorType = Response.ErrorType.CONNECTION_ERROR;
    }

    public void setApiApplicationError(HttpResponse response) {
        errorType = Response.ErrorType.CONNECTION_ERROR;
        httpStatusCode = response.getStatusLine().getStatusCode();
    }

    public void setAuthenticationError(HttpResponse response) {
        errorType = Response.ErrorType.AUTHENTICATION_ERROR;
        httpStatusCode = response.getStatusLine().getStatusCode();

    }

    public void setJsonError(HttpResponse response) {
        errorType = Response.ErrorType.PARSE_ERROR;
        httpStatusCode = response.getStatusLine().getStatusCode();
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.httpStatusCode = httpStatusCode;

        if(!(httpStatusCode >= 200 && httpStatusCode <= 204) && errorType == null) {
            switch(httpStatusCode) {
                // Bad Request - Missing Parameters, etc (possible json)
                case 400:
                    errorType = Response.ErrorType.API_ERROR;

                    break;
                // Authentication Required
                case 401:
                case 403:
                    errorType = Response.ErrorType.AUTHENTICATION_ERROR;
                    break;
                // All others, including 404 (no json expected in these situations)
                default:
                    errorType = Response.ErrorType.CONNECTION_ERROR;
            }
        }
    }

    public int getHttpStatusCode()
    {
        return this.httpStatusCode;

    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }


}
