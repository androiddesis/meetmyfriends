package mosaic.meetmyfriends.UserRegistration.model;

/**
 * Created by jumde on 1/11/15.
 *
 */

import com.google.gson.Gson;

public class Users {

    private String username="";
    private String password="";
    private String firstname="";
    private String lastname="";
    private String email="";
    private String country="";
    private String phone="";

    public Users(String username, String password, String firstname, String lastname, String email, String country, String phone) {
        this.username = username;
        this.password = password;
        this .firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.country = country;
        this.phone = phone;
    }

    public void setphone(String phone) {
        this.phone = phone;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public void setpassword(String password) {
        this.password = password;
    }

    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setlastname(String lastname) {
        this.lastname = lastname;
    }

    public void setemail(String email) {
        this.email = email;
    }

    public void setcountry(String country) {
        this.country = country;
    }

    public String getusername() {
        return username;
    }

    public String getpassword() {
        return password;
    }

    public String getfirstname(){
        return firstname;
    }

    public String getlastname(){
        return lastname;
    }

    public String getemail(){
        return email;
    }

    public String getcountry(){
        return country;
    }

    public String getphone() {
        return phone;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
}
