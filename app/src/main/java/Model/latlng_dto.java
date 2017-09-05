package Model;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hesen on 9/4/2017.
 */

public class latlng_dto {

    public double latitude;
    public double longitude;

    public latlng_dto(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // CONVERTS LatLng TO latlng_dto OBJECT
    public static latlng_dto to_latlng_dto(LatLng l){
        return new latlng_dto(l.latitude,l.longitude);
    }

    // CONVERTS latlng_dto TO LatLng OBJECT
    public static LatLng to_LatLng(latlng_dto l){
        return new LatLng(l.latitude,l.longitude);
    }

    // CONVERTS LatLng TO latlng_dto LIST
    public static List<latlng_dto> to_latlng_dto_list(List<LatLng> lst){
        return lst.stream().map(a -> to_latlng_dto(a)).collect(Collectors.toList());
    }

    // CONVERTS latlng_dto TO LatLng LIST
    public static List<LatLng> to_LatLng_list(List<latlng_dto> lst){
        return lst.stream().map(a -> to_LatLng(a)).collect(Collectors.toList());
    }

    //  CONVERTS DOUBLE LIST TO LATLNG LIST
    public static List<LatLng> double_joiner(List<Double> lat_list, List<Double> lng_list){
        List<LatLng> new_list = new ArrayList<LatLng>();
        if (lat_list.size() == lng_list.size()){
            for (int i =0 ; i<lat_list.size();i++) {
                new_list.add(new LatLng(lat_list.get(i),lng_list.get(i)));
            }
        }
        return new_list;
    }

}

