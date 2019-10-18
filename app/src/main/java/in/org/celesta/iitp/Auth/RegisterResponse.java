package in.org.celesta.iitp.Auth;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RegisterResponse {

    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private List<String> message;

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
}
