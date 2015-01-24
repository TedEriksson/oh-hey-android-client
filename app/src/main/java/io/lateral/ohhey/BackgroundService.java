package io.lateral.ohhey;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EService;
import org.androidannotations.annotations.rest.RestService;

import java.util.List;

/**
 * Created by Ted Eriksson on 24/01/15.
 */
@EService
public class BackgroundService extends Service {

    private BeaconManager beaconManager;

    @RestService
    RestClient restClient;

    PreferenceHelper preferenceHelper;

    private static final String ESTIMOTE_PROXIMITY_UUID = "b9407f30-f5f8-466e-aff9-25556b57fe6d";
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("regionId", ESTIMOTE_PROXIMITY_UUID, null, null);

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        preferenceHelper = PreferenceHelper.getInstance(this);

        beaconManager = new BeaconManager(this);

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
                Log.d("BEACONS", "Ranged beacons: " + beacons);
                for (Beacon beacon : beacons) {
                    double distance = Math.min(Utils.computeAccuracy(beacon), 6.0);
                    restCalls(distance, beacon.getMajor(), beacon.getMinor());
                }
            }
        });

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                try {
                    beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
                } catch (RemoteException e) {
                    Log.e("BEACONS", "Can't start ranging");
                }
            }
        });

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beaconManager.disconnect();
    }

    @Background
    void restCalls(double distance, int major, int minor) {
        try {
            Log.d("BEACONS", "posting user");
            restClient.postToLocation(new User(
                    preferenceHelper.getUserId(),
                    distance,
                    preferenceHelper.getTwitter(),
                    preferenceHelper.getGitHub(),
                    major,
                    minor));
        } catch (Exception e) {
            Log.e("BEACONS", "Failed to post user " + e.getMessage());
        }
    }
}
