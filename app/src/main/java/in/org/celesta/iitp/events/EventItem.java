package in.org.celesta.iitp.events;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "events")
public class EventItem {

    @NonNull
    @PrimaryKey()
    private String id = "new_event";
    private List<String> coordinators = null;
    private long date;
    private String venue;
    private String name;
    private String description;
    private String imageUrl;
    private boolean registered = false;
    private int day;
    private int type;

    public EventItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCoordinators(List<String> coordinators) {
        this.coordinators = coordinators;
    }

    public List<String> getCoordinators() {
        return coordinators;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isRegistered() {
        return registered;
    }

    public long getDate() {
        return date;
    }

    public String getVenue() {
        return venue;
    }

    public void setRegistered(boolean registered) {
        this.registered = registered;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
