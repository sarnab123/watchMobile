package com.devtest.firstwearableproject;

/**
 * Created by global on 4/12/15.
 */

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowInsets;

import com.devtest.firstwearableproject.walletUtil.WalletResponse;
import com.devtest.sharedlibrary.SharedConstants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WalletPaging extends Activity {


    private WalletResponse walletResponse = null;

    private String walletName = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getIntent() != null && getIntent().hasExtra(SharedConstants.WALLET_INTENT))
        {
            String walletData = getIntent().getStringExtra(SharedConstants.WALLET_INTENT);
            setWalletData(walletData);
        }
        if(getIntent() != null && getIntent().hasExtra(SharedConstants.WALLET_INTENT))
        {
            walletName = getIntent().getStringExtra(SharedConstants.WALLET_ID);
        }
        setupView();
    }

    private void setWalletData(String walletData) {
        GsonBuilder builder = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        final Gson gson = builder.create();
        if (!TextUtils.isEmpty(walletData)) {
            walletResponse = gson.fromJson(walletData, WalletResponse.class);
        }
    }

    private void setupView()
    {
        final Resources res = getResources();
        final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
        pager.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() {
            @Override
            public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
                final boolean round = insets.isRound();
                int rowMargin = res.getDimensionPixelOffset(R.dimen.page_row_margin);
                int colMargin = res.getDimensionPixelOffset(round ?
                        R.dimen.page_column_margin_round : R.dimen.page_column_margin);
                pager.setPageMargins(rowMargin, colMargin);
                pager.onApplyWindowInsets(insets);
                return insets;
            }
        });
        pager.setAdapter(new WalletGridPagerAdapter(this, getFragmentManager(),walletResponse,walletName));

    }

}

