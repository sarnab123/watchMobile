package com.devtest.firstwearableproject.walletFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devtest.firstwearableproject.R;
import com.devtest.firstwearableproject.walletUtil.BarcodeCreator;
import com.devtest.sharedlibrary.SharedConstants;

/**
 * Created by global on 4/12/15.
 */
public class WalletRewardFragment extends Fragment {

    private View mParent;

    private String walletId;

    private String title;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if(getArguments() != null) {
            walletId = getArguments().getString(SharedConstants.WALLET_ID);
            Log.d("WalletRewards",walletId);
            title = getArguments().getString(SharedConstants.NOTIFICATION_TITLE);
        }

        mParent = inflater.inflate(R.layout.barcode_fragment, container, false);
        TextView walletText = (TextView)mParent.findViewById(R.id.id_details);
        Log.d("WalletRewards1111",walletId);
        walletText.setText("Wallet id:"+walletId);
        ImageView walletBarcode = (ImageView)mParent.findViewById(R.id.id_barcode);
        walletBarcode.setImageBitmap(new BarcodeCreator().renderBarcode(walletId,
                500,
                90,
                BarcodeCreator.BLACK).get());
        return mParent;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
