package com.devtest.firstwearableproject;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.devtest.sharedlibrary.SharedConstants;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.PutDataMapRequest;
import com.google.android.gms.wearable.PutDataRequest;
import com.google.android.gms.wearable.Wearable;
import com.google.android.gms.wearable.WearableListenerService;

import java.util.concurrent.TimeUnit;

/**
 * Created by global on 4/12/15.
 */
public class DataListenerService extends WearableListenerService {

    private final String LOG_TAG = DataListenerService.class.getSimpleName();

    private static GoogleApiClient googleApiClient;

    private static final long CONNECTION_TIME_OUT_MS = 100;
    private String nodeId;

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if (messageEvent.getPath().equals(SharedConstants.TIMER_FINISHED_PATH)) {
            nodeId = messageEvent.getSourceNodeId();
            Log.v(LOG_TAG, "Node ID of watch: " + nodeId);
            showToast(messageEvent.getPath());

            Intent messageIntent = new Intent();
            messageIntent.setAction(Intent.ACTION_SEND);
            messageIntent.putExtra("message", messageEvent.getData());
            LocalBroadcastManager.getInstance(this).sendBroadcast(messageIntent);
            reply(messageEvent.getPath());
        }
        else if (messageEvent.getPath().equals(SharedConstants.LOYALTY_ENROLLMENT))
        {
            showToast(messageEvent.getPath());
            Intent messageIntent = new Intent();
            messageIntent.setAction(Intent.ACTION_SEND);
            messageIntent.putExtra("loyalty", messageEvent.getPath());
            LocalBroadcastManager.getInstance(this).sendBroadcast(messageIntent);
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void reply(final String path) {
        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .addApi(Wearable.API)
                .build();

        Log.v(LOG_TAG, "In reply()");
        Log.v(LOG_TAG, "Path: " + path);

        if (googleApiClient != null && !(googleApiClient.isConnected() || googleApiClient.isConnecting()))
            googleApiClient.blockingConnect(CONNECTION_TIME_OUT_MS, TimeUnit.MILLISECONDS);

        PutDataMapRequest dataMapRequest = PutDataMapRequest.create(SharedConstants.WALLET_PATH);
        dataMapRequest.getDataMap().putDouble(SharedConstants.NOTIFICATION_TIMESTAMP, System.currentTimeMillis());
        dataMapRequest.getDataMap().putString(SharedConstants.NOTIFICATION_TITLE, "Sarnab Poddar");
        dataMapRequest.getDataMap().putString(SharedConstants.NOTIFICATION_CONTENT, GenericUtils.loadJSONFromAsset(this,"wallet_data"));
        PutDataRequest putDataRequest = dataMapRequest.asPutDataRequest();
        Wearable.DataApi.putDataItem(googleApiClient, putDataRequest);
    }
}
