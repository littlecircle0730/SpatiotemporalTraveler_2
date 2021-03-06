package nathanielwendt.mpc.ut.edu.paco;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Map<String, String> data = remoteMessage.getData();

            try {

                //showToastInIntentService(data.toString());
                Intent TheIntent = new Intent();
                TheIntent.putExtra("FirebaseData", data.toString());
                TheIntent.setAction("NOW");
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(TheIntent);

            } catch (Exception e) {
                Log.e("ErrorResponse", e.getMessage());
            }

        }

    }

    private void showToastInIntentService(final String sText) {
        final Context MyContext = this;

        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast toast1 = Toast.makeText(MyContext, sText, Toast.LENGTH_LONG);
                toast1.show();
            }
        });
    };
}