package in.org.celesta.iitp.special;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import in.org.celesta.iitp.events.EventItem;
import in.org.celesta.iitp.events.EventsRepository;

public class SpecialViewModel extends AndroidViewModel {

    private EventsRepository eventsRepository;
    private LiveData<List<EventItem>> allExhibitions;
    private LiveData<List<EventItem>> allOzoneEvents;
    private LiveData<List<EventItem>> allLectures;
    private LiveData<List<EventItem>> allSchoolEvents;
    private LiveData<List<EventItem>> allWorkshops;

    public SpecialViewModel(@NonNull Application application) {
        super(application);
        eventsRepository = new EventsRepository(application);
        allExhibitions = eventsRepository.loadAllExhibitions();
        allLectures = eventsRepository.loadAllLectures();
        allOzoneEvents = eventsRepository.loadAllOzoneEvents();
        allSchoolEvents = eventsRepository.loadAllSchoolEvents();
        allWorkshops = eventsRepository.loadAllWorkshops();
    }

    LiveData<List<EventItem>> loadAllExhibitions() {
        return allExhibitions;
    }
    LiveData<List<EventItem>> loadAllGuestLectures() {
        return allLectures;
    }
    LiveData<List<EventItem>> loadAllOzoneEvents() {
        return allOzoneEvents;
    }
    LiveData<List<EventItem>> loadAllSchoolEvents() {
        return allSchoolEvents;
    }
    LiveData<List<EventItem>> loadAllWorkshops() {
        return allWorkshops;
    }

    public void insert(EventItem eventItem) {
        eventsRepository.insert(eventItem);
    }

    public void deleteEvents() {
        eventsRepository.deleteEvents();
    }

}
