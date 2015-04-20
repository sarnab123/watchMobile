package com.devtest.firstwearableproject;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.util.Log;
import android.view.Gravity;

import com.devtest.firstwearableproject.walletFragments.FragmentFactory;
import com.devtest.firstwearableproject.walletUtil.WalletResponse;
import com.devtest.firstwearableproject.walletUtil.WalletUtils;
import com.devtest.sharedlibrary.SharedConstants;

import java.util.ArrayList;

public class WalletGridPagerAdapter extends FragmentGridPagerAdapter {

    private final Context mContext;
    private WalletResponse walletResponse;

    public WalletGridPagerAdapter(Context ctx, FragmentManager fm, WalletResponse walletResponse, String walletName) {
        super(fm);
        mContext = ctx;
        this.walletResponse = walletResponse;
    }

    static final int[] BG_IMAGES = new int[]{
            R.drawable.debug_background_1,
            R.drawable.debug_background_2,
            R.drawable.debug_background_3,
            R.drawable.debug_background_4,
            R.drawable.debug_background_5
    };

    /**
     * A simple container for static data in each page
     */
    public static class Page {
        int titleRes;
        int textRes;
        int iconRes;
        int keyId;
        int cardGravity = Gravity.BOTTOM;
        boolean expansionEnabled = true;
        float expansionFactor = 1.0f;
        int expansionDirection = CardFragment.EXPAND_DOWN;
        String walletId;
        ArrayList<String> kohlsCash;

        public Page(int titleRes, int textRes, boolean expansion, int keyId) {
            this(titleRes, textRes, keyId);
            this.expansionEnabled = expansion;
        }

        public Page(int titleRes, int textRes, int keyId) {
            this.titleRes = titleRes;
            this.textRes = textRes;
            this.keyId = keyId;
        }

        public Page(int titleRes, String walletId, int keyId) {
            this.titleRes = titleRes;
            this.walletId = walletId;
            this.keyId = keyId;
        }

        public Page(int titleRes, ArrayList<String> kohlsCash, int keyId) {
            this.titleRes = titleRes;
            this.kohlsCash = kohlsCash;
            this.keyId = keyId;

        }

        public int getCardGravity() {
            return this.cardGravity;
        }

        public float getExpansionFactor() {
            return this.expansionFactor;
        }

        public boolean isExpansionEnabled() {
            return this.expansionEnabled;
        }

        public int getIconRes() {
            return this.iconRes;
        }

        public int getExpansionDirection() {
            return this.expansionDirection;
        }

        public String getWalletId() {
            return this.walletId;
        }

        public ArrayList<String> getKohlsCash()
        {
            return this.kohlsCash;
        }

        public void setKohlsCash(ArrayList<String> kohlsCash)
        {
            this.kohlsCash = kohlsCash;
        }

        public void setWalletId(String walletId)
        {
            this.walletId = walletId;
        }

        public int getKeyId()
        {
            return this.keyId;
        }

    }

    private final Page[][] PAGES = {
            {
                    new Page(R.string.wallet_welcome, R.string.swipe_loyalty,false, SharedConstants.welcome_wallet),
                    new Page(R.string.wallet_id,"Id",SharedConstants.wallet_id),
                    new Page(R.string.wallet_kohlsCash, new ArrayList<String>(),SharedConstants.wallet_cash),
                    new Page(R.string.wallet_kohlsOffers, new ArrayList<String>(),SharedConstants.wallet_offers),
            },
            {
                    new Page(R.string.loyalty_welcome, R.string.swipe_wallet, false, SharedConstants.welcome_loyalty),
                    new Page(R.string.loyalty_details, R.string.backgrounds_text, false, SharedConstants.loyalty_signup),
            },

    };

    @Override
    public Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];
        String title = page.titleRes != 0 ? mContext.getString(page.titleRes) : null;
        String text = page.textRes != 0 ? mContext.getString(page.textRes) : null;
        Log.d("gripadapter1111",row+","+col);
        if(title.equals(mContext.getString(R.string.wallet_kohlsCash)))
        {
            page.setKohlsCash(WalletUtils.getKohlsCash(walletResponse));
        }
        else if(title.equals(mContext.getString(R.string.wallet_id)))
        {
            page.setWalletId(walletResponse.getWalletId());

        }else if(title.equals(mContext.getString(R.string.wallet_kohlsOffers)))
        {
            page.setKohlsCash(WalletUtils.getKohlsOffers(walletResponse));

        }
        return FragmentFactory.getFragment(page,row,col,title,text);
    }

    @Override
    public Drawable getBackgroundForPage(int row, int column) {
        return mContext.getResources().getDrawable(BG_IMAGES[row % BG_IMAGES.length]);
    }

    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    @Override
    public int getColumnCount(int rowNum) {
        return PAGES[rowNum].length;
    }
}
