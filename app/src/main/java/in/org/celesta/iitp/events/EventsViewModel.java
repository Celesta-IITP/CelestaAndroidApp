package in.org.celesta.iitp.events;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class EventsViewModel extends AndroidViewModel {

    private EventsRepository eventsRepository;
    private LiveData<List<EventItem>> allEvents;

    public EventsViewModel(@NonNull Application application) {
        super(application);
        eventsRepository = new EventsRepository(application);
        allEvents = eventsRepository.loadAllEvents();

    }

    LiveData<List<EventItem>> loadAllEvents() {
        return allEvents;
    }

    public void insert(EventItem eventItem) {
        eventsRepository.insert(eventItem);
    }

    EventItem getEventById(String id) {
        return eventsRepository.loadEventById(id);
    }

}
