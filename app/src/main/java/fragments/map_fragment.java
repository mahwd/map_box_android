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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import java.util.List;
import ClassLibrary.CameraMover;
import ClassLibrary.MapDrawer;
import Model.latlng_dto;
import code.test.MapActivity;
import code.test.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class map_fragment extends Fragment implements OnMapReadyCallback {


    private BroadcastReceiver receiver;
    private GoogleMap map;
    private MapDrawer drawer;
    private CameraMover cameraMover;
    private LatLng current_position;



    @Override
    public void onResume() {
        super.onResume();

        if (receiver == null){
            receiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d("::",intent.getExtras().getString("lat")+intent.getExtras().getString("long"));
                    // receiving location updates every 2 sec
                    double lat = Double.parseDouble(intent.getExtras().getString("lat"));
                    double lng = Double.parseDouble(intent.getExtras().getString("long"));
                    current_position = new LatLng(lat,lng);
                    // method to run for tracking
                    drawer.place_user_marker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.map_marker)),current_position);
                    cameraMover.moveAnimated(18,current_position);
                    Bundle bundle = intent.getExtras().getBundle("bundle");
                    List<Double> lat_list = (List<Double>) bundle.getSerializable("lat_list");
                    List<Double> lng_list = (List<Double>) bundle.getSerializable("lng_list");
                    start_tracking(latlng_dto.double_joiner(lat_list,lng_list));
                }
            };
        }

        getActivity().registerReceiver(receiver,new IntentFilter("location_update"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View _view = inflater.inflate(R.layout.fragment_map, container,false);

        setMap();
        return _view;
    }

    private void setMap(){
        SupportMapFragment smf = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        smf.getMapAsync(this);



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
        if (map == null){
            map = googleMap;
        }

        drawer = new MapDrawer(map);
        cameraMover = new CameraMover(map);

    }
    private void start_tracking(List<LatLng> lst){
        if (MapActivity.isTracking){
            drawer.place_route(new PolylineOptions().color(R.color.colorPrimaryDark).width(5),lst);
        }
    }
}
