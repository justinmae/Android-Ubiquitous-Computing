package ubiquitouscomputing.udacity.com.basicnotifications_practice;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendNotification(View view) {
        // need pednign intent for user to laucnh
        // need a notification to builder to build upon
        // need notification manager to join id and builder and launch notifiy

        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.google.com"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        builder.setContentTitle("Title");
        builder.setContentText("Text");
        builder.setSubText("SubText");

        // Action
        builder.addAction(R.mipmap.ic_launcher, "action1", pendingIntent);
        builder.addAction(R.mipmap.ic_launcher, "action2", pendingIntent);

        // Specific action for wearable
        NotificationCompat.Action action =
                new NotificationCompat.Action.Builder(R.mipmap.ic_launcher,
                        "action3", pendingIntent).build();
        builder.extend(new NotificationCompat.WearableExtender().addAction(action));

        // big text style
        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.bigText("This is the longer event description");

        builder.setStyle(bigTextStyle);

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(getApplicationContext());

        notificationManagerCompat.notify(NOTIFICATION_ID, builder.build());
    }
}
