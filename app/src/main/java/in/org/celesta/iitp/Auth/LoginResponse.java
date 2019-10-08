package in.org.celesta.iitp.Auth;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public LoginMessage message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LoginMessage getMessage() {
        return message;
    }

    public void setMessage(LoginMessage message) {
        this.message = message;
    }
}
