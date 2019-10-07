package in.org.celesta.iitp.events;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface EventsDao {

    @Query("SELECT * FROM events WHERE evCategory = 'Events' ORDER BY evStartTime ASC")
    LiveData<List<EventItem>> loadAllEvents();

    @Query("SELECT evClub FROM events WHERE evCategory = 'Events'")
    LiveData<List<String>> loadAllClubs();

    @Query("select * from events where id = :id")
    EventItem loadEventById(String id);

    @Insert(onConflict = REPLACE)
    void insertEvent(EventItem eventItem);

    @Insert(onConflict = REPLACE)
    void insertOrReplaceEvent(EventItem... eventItems);

    @Query("DELETE FROM events")
    void deleteAllEvents();

}
