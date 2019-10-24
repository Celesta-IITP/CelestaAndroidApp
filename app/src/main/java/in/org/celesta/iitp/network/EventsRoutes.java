package in.org.celesta.iitp.network;

import java.util.List;

import in.org.celesta.iitp.Auth.LogoutResponse;
import in.org.celesta.iitp.events.EventItem;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EventsRoutes {

    @GET("backend/admin/functions/events_api.php")
    Call<List<EventItem>> getAllEvents();

    @POST("backend/admin/functions/register_event.php")
    Call<LogoutResponse> registerSoloEvent(@Body RequestBody body);

    @POST("backend/admin/functions/reg_team_event.php")
    Call<LogoutResponse> registerTeamEvent(@Body RequestBody body);
}
