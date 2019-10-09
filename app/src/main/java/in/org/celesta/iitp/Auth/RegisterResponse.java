package in.org.celesta.iitp.Auth;

import com.google.gson.annotations.SerializedName;

public class RegisterResponse {

    @SerializedName("status")
    public String status;
    @SerializedName("message")
    public RegisterMessage message;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RegisterMessage getMessage() {
        return message;
    }

    public void setMessage(RegisterMessage message) {
        this.message = message;
    }
}
