package code.test;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hesen on 9/4/2017.
 */

public class Gps_Service extends Service{

    private LocationListener listener;
    private LocationManager manager;
    private List<Double> lat_list;
    private List<Double> lng_list;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("serviceLOG","on");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        lat_list = new ArrayList<Double>();
        lng_list = new ArrayList<Double>();
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Intent i = new Intent("location_update");
                i.putExtra("lat",location.getLatitude()+"");
                i.putExtra("long",location.getLongitude()+"");

                lat_list.add(location.getLatitude());
                lng_list.add(location.getLongitude());
                Bundle bundle = new Bundle();
                bundle.putSerializable("lat_list",(Serializable)lat_list);
                bundle.putSerializable("lng_list",(Serializable)lng_list);
                i.putExtra("bundle",bundle);
                sendBroadcast(i);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        };
        manager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        Log.d("serviceLOG","on_create");
        //noinspection MissingPermission
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,0,listener);
    }

    @Override
    public void onDestroy() {
        Log.d("serviceLOG","off");
        super.onDestroy();
        if(manager != null)
            manager.removeUpdates(listener);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("serviceLOG","bind");
        return null;
    }
}
