package fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;

import code.test.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class map_fragment extends Fragment implements OnMapReadyCallback {


    private BroadcastReceiver receiver;
    private GoogleMap map;

    @Override
    public void onResume() {
        super.onResume();

        if (receiver == null){
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    // receiving location updates every 2 sec
                    // method to run
                    //Toast.makeText(getContext(),intent.getExtras().getString("coordinates"),Toast.LENGTH_SHORT).show();
                    Log.d("receiver::",intent.getExtras().getString("coordinates"));
                }
            };
        }
        getActivity().registerReceiver(receiver,new IntentFilter("location_update"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, null);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if (receiver != null)
//            getActivity().unregisterReceiver(receiver);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (map != null){
            map = googleMap;
        }




    }
}
