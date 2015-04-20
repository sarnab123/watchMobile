package com.devtest.firstwearableproject.walletUtil;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by global on 4/12/15.
 */
public class WalletItemResponse implements Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String id;
    private String description;
    private String effectiveStartDate;
    private String effectiveEndDate;
    private String value;
    private String valueAsOf;
    private String typeCode;
    private String pin;
    private String disclaimer;
    private String promoCode;
    private boolean shareable;
    private String giftable;
    private String notifications;
    private String deleted;
    private int couponState;
    private boolean isNewItem = false;
    private boolean isAnimationPending = true;
    private boolean isShortTermOffer = false;
    private boolean isCardFlipped = false;

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getEffectiveStartDate() {
        return effectiveStartDate;
    }
    public void setEffectiveStartDate(String effectiveStartDate) {
        this.effectiveStartDate = effectiveStartDate;
    }
    public String getEffectiveEndDate() {
        return effectiveEndDate;
    }
    public void setEffectiveEndDate(String effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }
    public String getValue() {
        if(!TextUtils.isEmpty(value))
            return value;
            //TODO value is null. Last minute fix to avoid null pointer
        else
            return "0.00";
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getDeleted() {
        return deleted;
    }
    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }
    public String getNotifications() {
        return notifications;
    }
    public void setNotifications(String notifications) {
        this.notifications = notifications;
    }
    public String getValueAsOf() {
        if(!TextUtils.isEmpty( valueAsOf ))
            return valueAsOf;
            //TODO valueasof is null.  Last minute fix to avoid null pointer
        else
            return "0.00";
    }
    public void setValueAsOf(String valueAsOf) {
        this.valueAsOf = valueAsOf;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getDisclaimer() {
        return disclaimer;
    }
    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }
    public String getPromoCode() {
        return promoCode;
    }
    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }
    public boolean getShareable() {
        return shareable;
    }
    public void setShareable(boolean shareable) {
        this.shareable = shareable;
    }
    public String getGiftable() {
        return giftable;
    }
    public void setGiftable(String giftable) {
        this.giftable = giftable;
    }
    public int getCouponState()
    {
        return couponState;
    }
    public void setCouponState( int couponState )
    {
        this.couponState = couponState;
    }
    public boolean isNewItem()
    {
        return isNewItem;
    }
    public void setNewItem( boolean isNewItem )
    {
        this.isNewItem = isNewItem;
    }
    public boolean isAnimationPending()
    {
        return isAnimationPending;
    }
    public void setAnimationPending( boolean isAnimationPending )
    {
        this.isAnimationPending = isAnimationPending;
    }
    public boolean isShortTermOffer()
    {
        return isShortTermOffer;
    }
    public void setShortTermOffer( boolean isShortTermOffer )
    {
        this.isShortTermOffer = isShortTermOffer;
    }
    public boolean isCardFlipped()
    {
        return isCardFlipped;
    }
    public void setCardFlipped( boolean isCardFlipped )
    {
        this.isCardFlipped = isCardFlipped;
    }
    public String getPin() {
        return pin;
    }
    public void setPin(String pin) {
        this.pin = pin;
    }

}
