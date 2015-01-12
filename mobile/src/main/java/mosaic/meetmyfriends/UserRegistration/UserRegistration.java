package mosaic.meetmyfriends.UserRegistration;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.squareup.okhttp.OkHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import mosaic.meetmyfriends.R;
import mosaic.meetmyfriends.UserRegistrationInterface;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.client.Response;


public class UserRegistration extends ActionBarActivity {

    String uname = "";
    String password = "";
    String firstname = "";
    String lastname = "";
    String country = "";
    String email = "";
    String phone = "";
    String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        Button register = (Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                createUser();
            }
        });
    }

    public void createUser(){
        TelephonyManager tMgr = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        firstname = ((EditText) findViewById(R.id.first_name)).getText().toString();
        lastname =  ((EditText) findViewById(R.id.last_name)).getText().toString();
        email = ((EditText) findViewById(R.id.email)).getText().toString();
        country = ((EditText) findViewById(R.id.country)).getText().toString();
        password = ((EditText) findViewById(R.id.password)).getText().toString();
        uname = ((EditText) findViewById(R.id.uname)).getText().toString();
        phone = tMgr.getLine1Number();

        Runnable runnable = new Runnable() {
        public void run() {
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(getResources().getString(R.string.endpoint))
                    .setClient(new OkClient(new OkHttpClient()))
                    .build();

            UserRegistrationInterface add_user = restAdapter.create(UserRegistrationInterface.class);
            Response resp = add_user.addUser(uname, password, firstname, lastname, email, country, phone);
            byte buffer[] = new byte[1000];
            try {
                resp.getBody().in().read(buffer);
            }
            catch (Exception e){
                Log.i("Error", e.getMessage());
            }
            result = new String(buffer);
            }
        };

        Thread getThread = new Thread(runnable);
        try {
            getThread.start();
            getThread.join();
            ((TextView)findViewById(R.id.textView)).setText(result);
        }
        catch(Exception e){
            Log.i("TAG:", e.getMessage());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_registration, menu);
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

}
