package in.org.celesta.iitp.events;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "events")
public class EventItem {

    @SerializedName("id")
    @Expose
    @NonNull
    @PrimaryKey
    private String id;
    @SerializedName("ev_id")
    @Expose
    private String evId;
    @SerializedName("ev_category")
    @Expose
    private String evCategory;
    @SerializedName("ev_name")
    @Expose
    private String evName;
    @SerializedName("ev_description")
    @Expose
    private String evDescription;
    @SerializedName("ev_organiser")
    @Expose
    private String evOrganiser;
    @SerializedName("ev_club")
    @Expose
    private String evClub;
    @SerializedName("ev_org_phone")
    @Expose
    private String evOrgPhone;
    @SerializedName("ev_poster_url")
    @Expose
    private String evPosterUrl;
    @SerializedName("ev_rule_book_url")
    @Expose
    private String evRuleBookUrl;
    @SerializedName("ev_date")
    @Expose
    private String evDate;
    @SerializedName("ev_start_time")
    @Expose
    private String evStartTime;
    @SerializedName("ev_end_time")
    @Expose
    private String evEndTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEvId() {
        return evId;
    }

    public void setEvId(String evId) {
        this.evId = evId;
    }

    public String getEvCategory() {
        return evCategory;
    }

    public void setEvCategory(String evCategory) {
        this.evCategory = evCategory;
    }

    public String getEvName() {
        return evName;
    }

    public void setEvName(String evName) {
        this.evName = evName;
    }

    public String getEvDescription() {
        return evDescription;
    }

    public void setEvDescription(String evDescription) {
        this.evDescription = evDescription;
    }

    public String getEvOrganiser() {
        return evOrganiser;
    }

    public void setEvOrganiser(String evOrganiser) {
        this.evOrganiser = evOrganiser;
    }

    public String getEvClub() {
        return evClub;
    }

    public void setEvClub(String evClub) {
        this.evClub = evClub;
    }

    public String getEvOrgPhone() {
        return evOrgPhone;
    }

    public void setEvOrgPhone(String evOrgPhone) {
        this.evOrgPhone = evOrgPhone;
    }

    public String getEvPosterUrl() {
        return evPosterUrl;
    }

    public void setEvPosterUrl(String evPosterUrl) {
        this.evPosterUrl = evPosterUrl;
    }

    public String getEvRuleBookUrl() {
        return evRuleBookUrl;
    }

    public void setEvRuleBookUrl(String evRuleBookUrl) {
        this.evRuleBookUrl = evRuleBookUrl;
    }

    public String getEvDate() {
        return evDate;
    }

    public void setEvDate(String evDate) {
        this.evDate = evDate;
    }

    public String getEvStartTime() {
        return evStartTime;
    }

    public void setEvStartTime(String evStartTime) {
        this.evStartTime = evStartTime;
    }

    public String getEvEndTime() {
        return evEndTime;
    }

    public void setEvEndTime(String evEndTime) {
        this.evEndTime = evEndTime;
    }
}
