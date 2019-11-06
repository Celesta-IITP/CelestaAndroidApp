package in.org.celesta.iitp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import in.org.celesta.iitp.events.EventItem;
import in.org.celesta.iitp.events.EventsDao;
import in.org.celesta.iitp.gallery.GalleryDao;
import in.org.celesta.iitp.gallery.Image;
import in.org.celesta.iitp.utils.Converters;

@Database(entities = {EventItem.class, Image.class}, version = 3, exportSchema = false)
@TypeConverters(Converters.class)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract EventsDao eventsDao();
    public abstract GalleryDao galleryDao();

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
