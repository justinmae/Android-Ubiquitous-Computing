package ubiquitouscomputing.udacity.com.stacknotifications;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final int NOTIFICATION_ID_1 = 1;
    public static final int NOTIFICATION_ID_2 = 2;
    public static final int NOTIFICATION_ID_3 = 3;
    public static final String NOTIFICATION_GROUP = "group_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void stackNotification(View view ) {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Action action = new NotificationCompat.Action(
                R.mipmap.ic_launcher, "Launch website", pendingIntent
        );

        NotificationCompat.WearableExtender extender = new NotificationCompat.WearableExtender()
                .addAction(action);

        NotificationCompat.Builder builder1 = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Phone Title 1")
                .setContentText("Phone Body Text 1")
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                .extend(extender)
                .setGroup(NOTIFICATION_GROUP);

        NotificationCompat.Builder builder2 = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Phone Title 2")
                .setContentText("Phone Body Text 2")
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                .extend(extender)
                .setGroup(NOTIFICATION_GROUP);

        NotificationCompat.Builder builderSummary = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.InboxStyle()
                        .addLine("Phone Body Text 1")
                        .addLine("Phone Body Text 2")
                        .setBigContentTitle("Phone Title Summary")
                        .setSummaryText("Summary Text"))
                .extend(extender)
                .setGroupSummary(true)
                .setGroup(NOTIFICATION_GROUP);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());

        notificationManagerCompat.notify(NOTIFICATION_ID_1, builder1.build());
        notificationManagerCompat.notify(NOTIFICATION_ID_2, builder2.build());
        notificationManagerCompat.notify(NOTIFICATION_ID_3, builderSummary.build());


    }
}
