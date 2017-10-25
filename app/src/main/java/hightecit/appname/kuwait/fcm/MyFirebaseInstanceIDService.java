package hightecit.appname.kuwait.fcm;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import hightecit.appname.kuwait.Utils;


/**
 * Created by Shikha
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";
    public static final String ACTION_FCM_TOKEN_GENERATED = "hightecit.hallmanagement.kuwait.FCM_TOKEN";

    @Override
    public void onTokenRefresh() {
        //Getting registration token
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Utils.setGCMToken(this, refreshedToken);
        Intent intent = new Intent(ACTION_FCM_TOKEN_GENERATED);
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(intent);
    }
}