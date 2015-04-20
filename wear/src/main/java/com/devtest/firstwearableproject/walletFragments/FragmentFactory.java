package com.devtest.firstwearableproject.walletFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;

import com.devtest.firstwearableproject.WalletGridPagerAdapter;
import com.devtest.sharedlibrary.SharedConstants;

/**
 * Created by global on 4/12/15.
 */
public class FragmentFactory {

    public static Fragment getFragment(WalletGridPagerAdapter.Page page,int row, int col,String title,String text)
    {
        switch(page.getKeyId())
        {
            case SharedConstants.welcome_wallet:
                CardFragment fragment = CardFragment.create(title, text, page.getIconRes());
                // Advanced settings
                fragment.setCardGravity(page.getCardGravity());
                fragment.setExpansionEnabled(page.isExpansionEnabled());
                fragment.setExpansionDirection(page.getExpansionDirection());
                fragment.setExpansionFactor(page.getExpansionFactor());
                return fragment;
            case SharedConstants.welcome_loyalty:
                CardFragment fragment1 = CardFragment.create(title, text, page.getIconRes());
                // Advanced settings
                fragment1.setCardGravity(page.getCardGravity());
                fragment1.setExpansionEnabled(page.isExpansionEnabled());
                fragment1.setExpansionDirection(page.getExpansionDirection());
                fragment1.setExpansionFactor(page.getExpansionFactor());
                return fragment1;
            case SharedConstants.wallet_cash:
                KohlsCashFragment kohlsCashFragment = new KohlsCashFragment();
                Bundle args = new Bundle();
                args.putStringArrayList(SharedConstants.WALLET_ID,page.getKohlsCash());
                args.putString(SharedConstants.NOTIFICATION_TITLE,title);
                kohlsCashFragment.setArguments(args);
                return kohlsCashFragment;
            case SharedConstants.wallet_id:
                WalletRewardFragment walletRewardFragment = new WalletRewardFragment();
                Bundle args2 = new Bundle();
                args2.putString(SharedConstants.WALLET_ID,page.getWalletId());
                args2.putString(SharedConstants.NOTIFICATION_TITLE,title);
                walletRewardFragment.setArguments(args2);
                return walletRewardFragment;
            case SharedConstants.loyalty_signup:
                LoyaltyEnrollmentFragment loyaltyEnrollmentFragment = new LoyaltyEnrollmentFragment();
                return loyaltyEnrollmentFragment;
            case SharedConstants.wallet_offers:
                KohlsCashFragment kohlsCashFragment1 = new KohlsCashFragment();
                Bundle args1 = new Bundle();
                args1.putStringArrayList(SharedConstants.WALLET_ID,page.getKohlsCash());
                args1.putString(SharedConstants.NOTIFICATION_TITLE,title);
                kohlsCashFragment1.setArguments(args1);
                return kohlsCashFragment1;
        }
//        if(row == 0 && col == 0 || row == 1 && col ==0)
//        {
//            CardFragment fragment = CardFragment.create(title, text, page.getIconRes());
//            // Advanced settings
//            fragment.setCardGravity(page.getCardGravity());
//            fragment.setExpansionEnabled(page.isExpansionEnabled());
//            fragment.setExpansionDirection(page.getExpansionDirection());
//            fragment.setExpansionFactor(page.getExpansionFactor());
//            return fragment;
//        }
//        else if(row == 0 && col == 1)
//        {
//            WalletRewardFragment walletRewardFragment = new WalletRewardFragment();
//            Bundle args = new Bundle();
//            args.putString(SharedConstants.WALLET_ID,page.getWalletId());
//            args.putString(SharedConstants.NOTIFICATION_TITLE,title);
//            walletRewardFragment.setArguments(args);
//            return walletRewardFragment;
//        }
//        else if(row == 0 && col == 2)
//        {
//            KohlsCashFragment kohlsCashFragment = new KohlsCashFragment();
//            Bundle args = new Bundle();
//            args.putStringArrayList(SharedConstants.WALLET_ID,page.getKohlsCash());
//            args.putString(SharedConstants.NOTIFICATION_TITLE,title);
//            return kohlsCashFragment;
//        }
//        else if(row == 0 && col == 3)
//        {
//            KohlsCashFragment kohlsCashFragment = new KohlsCashFragment();
//            Bundle args = new Bundle();
//            args.putStringArrayList(SharedConstants.WALLET_ID,page.getKohlsCash());
//            args.putString(SharedConstants.NOTIFICATION_TITLE,title);
//            return kohlsCashFragment;
//        }
//        else if(row == 1 && col == 1)
//        {
//            LoyaltyEnrollmentFragment loyaltyEnrollmentFragment = new LoyaltyEnrollmentFragment();
//            return loyaltyEnrollmentFragment;
//        }


        return null;
    }
}
