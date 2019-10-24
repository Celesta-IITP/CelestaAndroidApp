package in.org.celesta.iitp.Auth;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Keep
public class LoginResponse {

    @SerializedName("status")
    private Integer status;
    @SerializedName("message")
    private List<String> message;
    @SerializedName("celestaid")
    private String celestaId ="";
    @SerializedName("access_token")
    private String accessToken ="";
    @SerializedName("first_name")
    private String firstName ="";
    @SerializedName("qrcode")
    private String qrCode ="";

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

    public String getCelestaId() {
        return celestaId;
    }

    public void setCelestaId(String celestaId) {
        this.celestaId = celestaId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
