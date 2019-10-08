package in.org.celesta.iitp.Auth;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginMessage {
    @SerializedName("celestaid")
    public String celestaid;
    @SerializedName("first_name")
    public String first_name;
    @SerializedName("last_name")
    public String last_name;
    @SerializedName("email")
    public String email;
    @SerializedName("phone")
    public String phone;
    @SerializedName("qrcode")
    public String qrcode;
    @SerializedName("events_registered")
    public ArrayList<String> events_registered;
    @SerializedName("events_participated")
    public ArrayList<String> events_participated;
    @SerializedName("access_token")
    public String access_token;

    public String getCelestaid() {
        return celestaid;
    }

    public void setCelestaid(String celestaid) {
        this.celestaid = celestaid;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public ArrayList<String> getEvents_registered() {
        return events_registered;
    }

    public void setEvents_registered(ArrayList<String> events_registered) {
        this.events_registered = events_registered;
    }

    public ArrayList<String> getEvents_participated() {
        return events_participated;
    }

    public void setEvents_participated(ArrayList<String> events_participated) {
        this.events_participated = events_participated;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

}
