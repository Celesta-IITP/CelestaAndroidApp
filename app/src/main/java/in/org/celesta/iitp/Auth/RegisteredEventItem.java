package in.org.celesta.iitp.Auth;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;

@Keep
public class RegisteredEventItem {

    @SerializedName("ev_id")
    private String eventId;
    @SerializedName("ev_name")
    private String eventName;
    @SerializedName("amount")
    private int amountPaid;
    @SerializedName("team_name")
    private String teamName;

    private int amountToBePaid;

    public int getAmountToBePaid() {
        return amountToBePaid;
    }

    public void setAmountToBePaid(int amountToBePaid) {
        this.amountToBePaid = amountToBePaid;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(int amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
