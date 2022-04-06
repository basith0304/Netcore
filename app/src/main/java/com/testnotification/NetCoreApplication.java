package com.testnotification;

import android.app.Application;
import android.app.NotificationManager;
import android.os.Debug;
import android.util.Log;

import com.netcore.android.Smartech;
import com.netcore.android.logger.SMTDebugLevel;
import com.netcore.android.smartechpush.SmartPush;
import com.netcore.android.smartechpush.notification.channel.SMTNotificationChannel;

import java.lang.ref.WeakReference;

import io.hansel.core.logger.HSLLogLevel;


public class NetCoreApplication extends Application {
    private static final String TAG = "NetCoreApplication";
    @Override public void onCreate() {
        super.onCreate();
        Smartech.getInstance(new WeakReference<>(this)).initializeSdk(this);// Initializing the SDK.
        Smartech.getInstance(new WeakReference<>(this)).setDebugLevel(SMTDebugLevel.Level.VERBOSE);// // Enabling Smartech logs for testing.
        // enabeling Hansel Logs
        HSLLogLevel.all.setEnabled(true);
        HSLLogLevel.mid.setEnabled(true);
        HSLLogLevel.debug.setEnabled(true);
        try {
            SmartPush smartPush = SmartPush.getInstance(new WeakReference<>(this));
            smartPush.fetchAlreadyGeneratedTokenFromFCM();
        } catch (Exception e) {
            Log.e(TAG, "Fetching FCM token failed.");
        }
        Smartech.getInstance(new WeakReference<>(this)).trackAppInstallUpdateBySmartech();// Tracking app install/update event.

    }

    public void SetUpChannels(){
        try{
            SMTNotificationChannel.Builder smtBuilder = new SMTNotificationChannel.Builder("001", "ParkingNotification",
                    NotificationManager.IMPORTANCE_HIGH);

//To set the description to the channel add below method.
            smtBuilder.setChannelDescription("<Channel_Description>");

//To set the group ID to the channel add below method. (Make sure that before setting group ID here, that group must be created before. Check the code below)
            smtBuilder.setChannelGroupId("G001");

//To set sound to channel, add below method. (Note that sound name must be without extention.)
            smtBuilder.setNotificationSound("<Sound_File_Name_Without_Extenstion>");

            SMTNotificationChannel smtNotificationChannel = smtBuilder.build();

            SmartPush.getInstance(new WeakReference<>(this)).createNotificationChannel(smtNotificationChannel);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}