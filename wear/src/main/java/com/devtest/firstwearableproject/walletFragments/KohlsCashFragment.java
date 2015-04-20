package com.devtest.firstwearableproject.walletFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.devtest.firstwearableproject.R;
import com.devtest.sharedlibrary.SharedConstants;

import java.util.List;

/**
 * Created by global on 4/13/15.
 */
public class KohlsCashFragment extends Fragment {

    private View mParent;

    private List<String> walletId;

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
        mParent = inflater.inflate(R.layout.cash_fragment, container, false);

        if(getArguments() != null) {
            walletId = getArguments().getStringArrayList(SharedConstants.WALLET_ID);
            title = getArguments().getString(SharedConstants.NOTIFICATION_TITLE);
        }

        TextView titleView = (TextView) mParent.findViewById(R.id.id_title);
        titleView.setText(title);

        ListView listView1 = (ListView) mParent.findViewById(R.id.listView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, walletId.toArray(new String[walletId.size()]));

        listView1.setAdapter(adapter);

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
