package com.company.transport.driverterminal.notifications;

import com.company.transport.driverterminal.R;
import com.company.transport.driverterminal.ui.main.view.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Pavel on 11/4/2018.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        sendNotification(remoteMessage.getNotification().getBody());

    }
    //Here we can do everything what we want
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.leak_canary_icon)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.leak_canary_icon))
                .setContentTitle(this.getString(R.string.app_name))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
}