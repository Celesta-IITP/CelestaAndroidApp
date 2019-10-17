package in.org.celesta.iitp.network;

import java.util.List;

import in.org.celesta.iitp.events.EventItem;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EventsRoutes {

    @GET("backend/admin/functions/events_api.php")
    Call<List<EventItem>> getAllEvents();

}
