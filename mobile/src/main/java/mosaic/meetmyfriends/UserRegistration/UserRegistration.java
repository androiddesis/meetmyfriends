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
import android.widget.Toast;
import com.squareup.okhttp.OkHttpClient;
import mosaic.meetmyfriends.R;
import mosaic.meetmyfriends.UserRegistration.model.Users;
import mosaic.meetmyfriends.UserRegistration.service.UserRegistrationAPI;
import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


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
        final String Success = getResources().getString(R.string.success);
        String PrimaryKeyError = getResources().getString(R.string.PrimaryKeyError);
        String PhoneError = getResources().getString(R.string.PhoneError);
        String ConnectionError = getResources().getString(R.string.ConnectionError);


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
                Response resp = null;
                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(getResources().getString(R.string.endpoint))
                        .setLogLevel(RestAdapter.LogLevel.FULL)
                        .setClient(new OkClient(new OkHttpClient()))
                        .setErrorHandler(new ErrorHandler() {
                            @Override
                            public Throwable handleError(RetrofitError cause) {
                                String body = new String(((TypedByteArray) cause.getResponse().getBody()).getBytes());
                                throw new RuntimeException(body);
                            }
                        })
                        .build();

                try {
                    UserRegistrationAPI add_user = restAdapter.create(UserRegistrationAPI.class);
                    Users user = new Users(uname, password, firstname, lastname, email, country, phone);
                    resp = add_user.addUser(user);
                    result = Success;
                }
                catch(Exception e){
                    result = e.getMessage();
                }
            }
        };

        Thread getThread = new Thread(runnable);

        try {
            getThread.start();
            getThread.join();
            if(result != Success){
                if(result.contains(PrimaryKeyError)){
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.UsernameExists), Toast.LENGTH_LONG).show();
                }
                else if(result.contains(ConnectionError)){
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.ServerError), Toast.LENGTH_LONG).show();
                }
                else if(result.contains(PhoneError)){
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.PhoneExists), Toast.LENGTH_LONG).show();
                }
                else{
                    //should not come here
                    Toast.makeText(getBaseContext(), result, Toast.LENGTH_LONG);
                }
            }
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
