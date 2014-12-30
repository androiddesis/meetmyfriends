package mosaic.meetmyfriends;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by jumde on 12/29/14.
 */
public class MapUtils implements OnMapReadyCallback {

    private double lat;
    private double lng;
    public MapFragment mMapFragment;
    public Activity activity = null;

     public MapUtils(Activity act){
        activity = act;
     }

     public void createMap(){
         mMapFragment = MapFragment.newInstance();
         FragmentTransaction fragmentTransaction = activity.getFragmentManager().beginTransaction();
         fragmentTransaction.add(R.id.fragment, mMapFragment);
         fragmentTransaction.commit();
     }

    public void placeMarker(){
        mMapFragment.getMapAsync(this);
    }

    public void setLatLng(double latitude, double longitude){
        lat = latitude;
        lng = longitude;
    }

    public void onMapReady(GoogleMap map) {
        LatLng location = new LatLng(lat, lng);

        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 13));

        map.addMarker(new MarkerOptions()
                .title("My Location")
                .snippet("Mera Ghar")
                .position(location));
    }

}
