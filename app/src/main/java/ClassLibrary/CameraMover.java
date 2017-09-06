package ClassLibrary;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Hesen on 9/5/2017.
 */

public class CameraMover {

    private GoogleMap map;
    private int zoom_level;
    private LatLng position;
    private CameraUpdate cameraUpdate;
    private CameraPosition cameraPosition;
    private CameraPosition.Builder builder;

    public void moveAnimated(float zoom, LatLng pos){
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(pos, zoom);
        map.animateCamera(cameraUpdate);
    }

    public void move(float zoom, LatLng pos){
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(pos, zoom);
        map.moveCamera(cameraUpdate);
    }

    public CameraMover(GoogleMap map) {
        this.map = map;

    }
}
