package in.org.celesta.iitp.events;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EventsViewModel extends AndroidViewModel {

    private EventsRepository eventsRepository;
    private LiveData<List<EventItem>> allEvents;
    private LiveData<List<String>> allClubs;

    public EventsViewModel(@NonNull Application application) {
        super(application);
        eventsRepository = new EventsRepository(application);
        allEvents = eventsRepository.loadAllEvents();
        allClubs = eventsRepository.loadAllClubs();
    }

    LiveData<List<EventItem>> loadAllEvents() {
        return allEvents;
    }

    public LiveData<List<String>> loadAllClubs() {
        return allClubs;
    }

    public void insert(EventItem eventItem) {
        eventsRepository.insert(eventItem);
    }

    public EventItem getEventById(String id) {
        return eventsRepository.loadEventById(id);
    }

    public void deleteEvents() {
        eventsRepository.deleteEvents();
    }

}
