package in.org.celesta.iitp.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.concurrent.Future;

import in.org.celesta.iitp.R;

public class FcmService extends FirebaseMessagingService {

    private static final String LOG_TAG = FcmService.class.getSimpleName();
    private int notificationId;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.e(LOG_TAG, "FROM: " + remoteMessage.getFrom());
        Log.e(LOG_TAG, remoteMessage.getSentTime() + " ");

        notificationId = NotificationId.getID();

        Map<String, String> data = remoteMessage.getData();

        if (data.containsKey("notify")) {
            if ("1".equals(data.get("notify"))) {

                String imageUri;
                String messageBody = remoteMessage.getData().get("body");
                String messageTitle = remoteMessage.getData().get("title");
                String link = remoteMessage.getData().get("link");

                Bitmap bitmap = null;
                if (data.containsKey("image_uri")) {
                    imageUri = remoteMessage.getData().get("image_uri");
                    Future<Bitmap> futureTarget = Glide.with(this)
                            .asBitmap()
                            .load(imageUri)
                            .submit();
                    try {
                        bitmap = futureTarget.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                sendNotification(messageTitle, messageBody, bitmap, link);
            }
        }

    }

    private void sendNotification(String title, String body, Bitmap image, String link) {
        createNotificationChannel(getApplicationContext());

        String data = getResources().getString(R.string.celesta_app) + "notification";

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setPackage(getPackageName());
        intent.setData(Uri.parse(data));

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))
                .setSmallIcon(R.mipmap.celesta_icon_round)
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setCategory(NotificationCompat.CATEGORY_EVENT);

        if (image == null) {
            notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(body));
        } else {
            notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image));
            notificationBuilder.setLargeIcon(image);
        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(notificationId, notificationBuilder.build());

    }

    public static void createNotificationChannel(Context context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(context.getString(R.string.default_notification_channel_id), name, importance);
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.setDescription(context.getString(R.string.default_notification_channel_description));
            channel.enableVibration(true);
            channel.setShowBadge(true);

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
