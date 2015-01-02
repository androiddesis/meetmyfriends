package mosaic.meetmyfriends;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

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

    //To-Do: Let the user to select the Address instead of picking up the first one
    public void setLatLangFromAddress(String address){
        Geocoder geocoder = new Geocoder(activity);
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocationName(address, 1);
            if (addresses.size() > 0) {
                double latitude = addresses.get(0).getLatitude();
                double longitude = addresses.get(0).getLongitude();
            }
        }
        catch (IOException e){
            Log.i("Meet My Friends", "Try Again");
        }
    }

}
