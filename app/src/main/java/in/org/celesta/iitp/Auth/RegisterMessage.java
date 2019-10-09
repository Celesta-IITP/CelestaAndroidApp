package in.org.celesta.iitp.Auth;

import com.google.gson.annotations.SerializedName;

public class RegisterMessage {
    @SerializedName("celestaid")
    public String celestaid;
    @SerializedName("qrcode")
    public String qrcode;

    public String getCelestaid() {
        return celestaid;
    }

    public void setCelestaid(String celestaid) {
        this.celestaid = celestaid;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
}
