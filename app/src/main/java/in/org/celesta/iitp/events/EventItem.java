package in.org.celesta.iitp.events;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Keep
@Entity(tableName = "events")
public class EventItem {

    @SerializedName("id")
    @Expose
    @NonNull
    @PrimaryKey
    private String id = "";
    @SerializedName("ev_id")
    @Expose
    private String evId;
    @SerializedName("ev_category")
    @Expose
    private String evCategory;
    @SerializedName("ev_name")
    @Expose
    private String evName;
    @SerializedName("ev_venue")
    @Expose
    private String evVenue;
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
    @SerializedName("ev_amount")
    @Expose
    private String evAmount;
    @SerializedName("is_team_event")
    @Expose
    private String isTeamEvent;
    @SerializedName("map_url")
    @Expose
    private String mapUrl;
    @SerializedName("team_members")
    @Expose
    private String teamMembers;

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

    public String getEvVenue() {
        return evVenue;
    }

    public void setEvVenue(String evVenue) {
        this.evVenue = evVenue;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getEvAmount() {
        return evAmount;
    }

    public void setEvAmount(String evAmount) {
        this.evAmount = evAmount;
    }

    public String getIsTeamEvent() {
        return isTeamEvent;
    }

    public void setIsTeamEvent(String isTeamEvent) {
        this.isTeamEvent = isTeamEvent;
    }

    public String getMapUrl() {
        if (mapUrl == null || mapUrl.isEmpty())
            return "https://www.google.com/maps/d/viewer?mid=1NVE_tnItehFaMbEWddjL786SKtuCtq4X";
        return mapUrl;
    }

    public void setMapUrl(String mapUrl) {
        this.mapUrl = mapUrl;
    }

    public String getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(String teamMembers) {
        this.teamMembers = teamMembers;
    }
}
