package com.testnotification;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.netcore.android.smartechpush.SmartPush;

import java.lang.ref.WeakReference;
import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        SmartPush.getInstance(new WeakReference<Context>(this)).setDevicePushToken(s);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        boolean pushHandledBySmartech = SmartPush.getInstance(new WeakReference<>(getApplicationContext())).handlePushNotification(remoteMessage.getData().toString());
        if (!pushHandledBySmartech){
            // Handle notification from other sources.
        }
    }
}
