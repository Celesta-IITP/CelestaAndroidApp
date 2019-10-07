package in.org.celesta.iitp.network;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserRoutes {

    //users
    @GET("/users")
    Call<ResponseBody> getAllUsers(@Header("Authorization") String credentials);

    @POST("/users/signIn")
    Call<ResponseBody> login(@Body RequestBody body);

    @POST("/users/signUp")
    Call<ResponseBody> register(@Body RequestBody body);

    @POST("/users/activate")
    Call<ResponseBody> verifyOtp(@Body RequestBody body);

}