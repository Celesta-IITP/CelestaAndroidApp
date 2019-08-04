package in.org.celesta.iitp.events;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import in.org.celesta.iitp.database.AppDatabase;

public class EventsRepository {

    private EventsDao eventsDao;
    private LiveData<List<EventItem>> allEvents;

    EventsRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        eventsDao = db.eventsDao();
        allEvents = eventsDao.loadAllEvents();
    }

    LiveData<List<EventItem>> loadAllEvents() {
        return allEvents;
    }

    public void insert(EventItem eventItem) {
        new insertAsyncTask(eventsDao).execute(eventItem);
    }

    EventItem loadEventById(String id) {
        getEventById task = new getEventById(eventsDao);
        try {
            return task.execute(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static class insertAsyncTask extends AsyncTask<EventItem, Void, Void> {
        private EventsDao mAsyncTaskDao;

        insertAsyncTask(EventsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final EventItem... params) {
            mAsyncTaskDao.insertEvent(params[0]);
            return null;
        }
    }

    private static class getEventById extends AsyncTask<String, Void, EventItem> {
        private EventsDao mAsyncTaskDao;

        getEventById(EventsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected EventItem doInBackground(String... params) {
            return mAsyncTaskDao.loadEventById(params[0]);
        }
    }

}
