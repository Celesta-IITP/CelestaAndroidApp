package in.org.celesta.iitp.network;

import in.org.celesta.iitp.gallery.Gallery;
import retrofit2.Call;
import retrofit2.http.GET;

public interface OtherRoutes {

    @GET("gallery/gallery.json")
    Call<Gallery> getImages();

}
