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

    public void moveAnimated(int zoom, LatLng pos){
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(pos, 15);
        map.animateCamera(cameraUpdate);
    }

    public CameraMover(GoogleMap map) {
        this.map = map;

    }
}
