package ClassLibrary;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

/**
 * Created by Hesen on 9/5/2017.
 */

public class MapDrawer {

    GoogleMap map;
    Polyline route;
    Marker user_marker;

    // CONSTRUCTOR
    public MapDrawer(GoogleMap map) {
        this.map = map;
    }

    // DRAWING POLYLINE ON FOLLOWING MAP
    public void place_route(PolylineOptions polylineOptions, List<LatLng> way_list){
        if(route != null)
            clear_route(route);
        route = map.addPolyline(polylineOptions.addAll(way_list));
    }

    // PLACING MARKER ON FOLLOWING MAP
    public void place_user_marker(MarkerOptions markerOptions,LatLng postition){
        if(user_marker != null)
            clear_marker(user_marker);
        user_marker = map.addMarker(markerOptions.position(postition));
    }

    //CLEARS ALL POLYLINE AND MARKERS FROM MAP
    private void clear_map_all(){
        if(route != null && route != null) {
            user_marker.remove();
            route.remove();
        }
    }

    // CLEARS ONLY MARKERS FROM MAP
    private void clear_marker(Marker marker){
        marker.remove();
    }

    // CLEARS ONLY POLYLINE FROM MAP
    private void clear_route(Polyline polyline){
        polyline.remove();
    }


}
