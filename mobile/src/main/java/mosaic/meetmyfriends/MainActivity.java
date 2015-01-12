package mosaic.meetmyfriends;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

import mosaic.meetmyfriends.UserRegistration.UserRegistration;


public class MainActivity extends Activity {

    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (Button)findViewById(R.id.sign_up);
        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                launchSignUp();
            }
        });


        /*
        MapUtils map = new MapUtils(this);
        GpsUtils mGps = new GpsUtils(this);
        double lat = mGps.getLatitude();
        double lng = mGps.getLongitude();
        map.setLatLngFromAddress("601 Townsend Street San Francisco CA United States 94116");
        map.createMap();
        map.placeMarker();
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void launchSignUp(){
        Intent sign_up = new Intent(this, UserRegistration.class);
        startActivity(sign_up);
    }

}
