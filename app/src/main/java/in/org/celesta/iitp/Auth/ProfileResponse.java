package in.org.celesta.iitp.Auth;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Keep
public class ProfileResponse {

    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("profile")
    @Expose
    private Profile profile;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;
    @SerializedName("message")
    @Expose
    private List<String> message;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<String> getMessage() {
        if (message == null) {
            message = new ArrayList<>();
            message.add("");
        }
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Keep
    public class Profile {

        @SerializedName("celestaid")
        @Expose
        private String celestaId;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("phone")
        @Expose
        private String phone;
        @SerializedName("qrcode")
        @Expose
        private String qrCode;
        @SerializedName("events_registered")
        @Expose
        private String eventsRegistered;
        @SerializedName("access_token")
        @Expose
        private String accessToken;
        @SerializedName("amount_paid")
        @Expose
        private String amountPaid;



        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
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

        public String getEventsRegistered() {
            return eventsRegistered;
        }

        public void setEventsRegistered(String eventsRegistered) {
            this.eventsRegistered = eventsRegistered;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAmountPaid() {
            return amountPaid;
        }

        public void setAmountPaid(String amountPaid) {
            this.amountPaid = amountPaid;
        }

        public String getCelestaId() {
            return celestaId;
        }

        public void setCelestaId(String celestaId) {
            this.celestaId = celestaId;
        }

        public String getQrCode() {
            return qrCode;
        }

        public void setQrCode(String qrCode) {
            this.qrCode = qrCode;
        }
    }

    @Keep
    public class Event {

        @SerializedName("ev_id")
        @Expose
        private String evId;
        @SerializedName("ev_amount")
        @Expose
        private String evAmount;

        public String getEvId() {
            return evId;
        }

        public void setEvId(String evId) {
            this.evId = evId;
        }

        public String getEvAmount() {
            return evAmount;
        }

        public void setEvAmount(String evAmount) {
            this.evAmount = evAmount;
        }

    }

}
