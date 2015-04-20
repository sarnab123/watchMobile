package com.devtest.firstwearableproject;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.wearable.view.DelayedConfirmationView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.devtest.sharedlibrary.SharedConstants;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;


public class WearActivity extends Activity implements
        DelayedConfirmationView.DelayedConfirmationListener,
        GoogleApiClient.OnConnectionFailedListener{

    private TextView mTextView;
    private static final int NUM_SECONDS = 5;

    private static final String TAG = WearActivity.class.getSimpleName();

    private DelayedConfirmationView delayedConfirmationView;
    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customview_accept);
        delayedConfirmationView = (DelayedConfirmationView) findViewById(R.id.delayed_confirmation);
        delayedConfirmationView.setTotalTimeMs(NUM_SECONDS * 1000);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Wearable.API)
                .addOnConnectionFailedListener(this)
                .build();
        IntentFilter messageFilter = new IntentFilter(Intent.ACTION_SEND);
        MessageReceiver messageReceiver = new MessageReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(messageReceiver, messageFilter);

        TextView startSync = (TextView)findViewById(R.id.id_startsync);
        startSync.performClick();

    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!mGoogleApiClient.isConnected()) {
            mGoogleApiClient.connect();
        }
    }

    public class MessageReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent != null && intent.hasExtra(SharedConstants.WALLET_INTENT) && intent.hasExtra(SharedConstants.WALLET_ID))
            {
                openWalletDetails(intent.getStringExtra(SharedConstants.WALLET_INTENT),intent.getStringExtra(SharedConstants.WALLET_ID));
            }
        }
    }

    private void openWalletDetails(String data,String walletId)
    {
        Intent walletIntent = new Intent(this,WalletPaging.class);
        walletIntent.putExtra(SharedConstants.WALLET_INTENT,data);
        walletIntent.putExtra(SharedConstants.WALLET_ID,walletId);
        startActivity(walletIntent);
    }

    @Override
    protected void onDestroy() {
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
        super.onDestroy();
    }


    /**
     * Starts the DelayedConfirmationView when user presses "Start Timer" button.
     */
    public void onStartTimer(View view) {
        delayedConfirmationView.start();
        delayedConfirmationView.setListener(this);
    }

    @Override
    public void onTimerSelected(View v) {
        v.setPressed(true);
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_timer_selected))
                .build();
        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(0, notification);
        sendMessageToCompanion(SharedConstants.TIMER_SELECTED_PATH);
        // Prevent onTimerFinished from being heard.
        ((DelayedConfirmationView) v).setListener(null);
        finish();
    }

    @Override
    public void onTimerFinished(View v) {
        Notification notification = new Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_timer_finished))
                .build();
        ((NotificationManager) getSystemService(NOTIFICATION_SERVICE)).notify(0, notification);
        sendMessageToCompanion(SharedConstants.TIMER_FINISHED_PATH);
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.e(TAG, "Failed to connect to Google Api Client");
    }

    private void sendMessageToCompanion(final String path) {
        Wearable.NodeApi.getConnectedNodes(mGoogleApiClient).setResultCallback(
                new ResultCallback<NodeApi.GetConnectedNodesResult>() {
                    @Override
                    public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                        for (final Node node : getConnectedNodesResult.getNodes()) {
                            Wearable.MessageApi.sendMessage(mGoogleApiClient, node.getId(), path,
                                    new byte[0]).setResultCallback(getSendMessageResultCallback());
                        }
                    }
                }
        );

    }

    private ResultCallback<MessageApi.SendMessageResult> getSendMessageResultCallback() {
        return new ResultCallback<MessageApi.SendMessageResult>() {
            @Override
            public void onResult(MessageApi.SendMessageResult sendMessageResult) {
                if (!sendMessageResult.getStatus().isSuccess()) {
                    Log.e(TAG, "Failed to connect to Google Api Client with status "
                            + sendMessageResult.getStatus());
                }
            }
        };
    }

}
