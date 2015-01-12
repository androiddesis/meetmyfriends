package mosaic.meetmyfriends.UserRegistration.service;

import org.json.JSONObject;

import mosaic.meetmyfriends.UserRegistration.model.Users;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by jumde on 1/10/15.
 */
public interface UserRegistrationAPI {
    @POST("/meetmyfriends/users/")
    public Response addUser(@Body Users user);

}
