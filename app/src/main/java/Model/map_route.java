package Model;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Date;
import java.util.List;

/**
 * Created by Hesen on 9/4/2017.
 */

public class map_route {

    String route_id;
    String route_name;
    Long route_distance;
    Date route_date;
    List<latlng_dto> route_waypoints;

}
