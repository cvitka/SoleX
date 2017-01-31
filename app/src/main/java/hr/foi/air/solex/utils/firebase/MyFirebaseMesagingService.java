package hr.foi.air.solex.utils.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.Map;
import java.util.Random;

import hr.foi.air.solex.R;
import hr.foi.air.solex.activities.common.LoginActivity;

public class MyFirebaseMesagingService extends FirebaseMessagingService {
    private String title;
    private String body;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        title = remoteMessage.getData().get("title"); /** dohvacanje naslova od push notifikacije sa webservisa */
        body = remoteMessage.getData().get("body");  /**  dohvacanje  tijela poruke */
        showNotification(title,body);
    }

    private void showNotification(String title,String body) {

        Intent i = new Intent(this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT); /** koja se aktivnost otvara*/
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION); /** zvuk*/

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.applogo) /** ikona */
                .setContentTitle(title)  /** naslov*/
                .setContentText(body)  /** tijelo poruke */
                .setAutoCancel(true)
                .setSound(notificationSound)  /** postavljanje notifikacijskog zvuga*/
                .setVibrate(new long[] { 1000, 1000}) /** postavljanje vibracije*/
                .setContentIntent(pendingIntent);  /** postavljanje aktivnosti koja se otvara */

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

}
