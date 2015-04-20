package com.devtest.firstwearableproject.walletUtil;

import java.util.List;

/**
 * Created by global on 4/12/15.
 */
public class WalletResponse extends WalletBaseResponse
{


    //private String uniqueResponseId;
    //private List<ErrorResponse> errors;
    private List<WalletItemResponse> addedItems;
    private List<WalletItemResponse> currentWalletItems;
    private List<WalletItemResponse> deletedWalletItems;
    private List<WalletItemResponse> currentItems;
    private String walletId;
    private String walletEmail;
    //private boolean success;

    /*
        public String getUniqueResponseId()
        {
            return uniqueResponseId;
        }

        public void setUniqueResponseId(String uniqueResponseId) {
            this.uniqueResponseId = uniqueResponseId;
        }
    */
    public List<WalletItemResponse> getWalletItems() {
        return currentWalletItems;
    }

    public void setWalletItems(List<WalletItemResponse> walletItems) {
        this.currentWalletItems = walletItems;
    }

    public String getWalletId() {
        return walletId;
    }

    public void setWalletId(String walletId) {
        this.walletId = walletId;
    }

    public String getWalletEmail() {
        return walletEmail;
    }

    public void setWalletEmail(String walletEmail) {
        this.walletEmail = walletEmail;
    }
    /*
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public List<ErrorResponse> getErrors() {
            return errors;
        }

        public void setErrors(List<ErrorResponse> errors) {
            this.errors = errors;
        }
    */
    public List<WalletItemResponse> getDeletedWalletItems() {
        return deletedWalletItems;
    }

    public void setDeletedWalletItems(List<WalletItemResponse> deletedWalletItems) {
        this.deletedWalletItems = deletedWalletItems;
    }

    public List<WalletItemResponse> getAddedItems()
    {
        return addedItems;
    }

    public void setAddedItems( List<WalletItemResponse> addedItems )
    {
        this.addedItems = addedItems;
    }

    public List<WalletItemResponse> getCurrentItems()
    {
        return currentItems;
    }

    public void setCurrentItems( List<WalletItemResponse> currentItems )
    {
        this.currentItems = currentItems;

    }

}

