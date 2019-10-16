package in.org.celesta.iitp.Auth;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoginResponse {

    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private List<String> message;
    @SerializedName("celestaid")
    private String celestaid="";
    @SerializedName("access_token")
    private String access_token="";
    @SerializedName("first_name")
    private String first_name="";
    @SerializedName("qrcode")
    private String qrcode="";

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public String getCelestaid() {
        return celestaid;
    }

    public void setCelestaid(String celestaid) {
        this.celestaid = celestaid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
