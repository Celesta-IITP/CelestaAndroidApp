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

//    @GET("/users/batch/{year}")
//    Call<List<Person>> getUsersByBatch(@Header("Authorization") String credentials, @Path("year") String batch);
//
//    @GET("/users/branch/{br}")
//    Call<List<Person>> getUsersByBranch(@Header("Authorization") String credentials, @Path("br") String branch);
//
//    @GET("/users/batchAndBranch/{year}/{br}")
//    Call<List<Person>> getUsersByBatchNBranch(@Header("Authorization") String credentials, @Path("year") String batch, @Path("br") String branch);
//
//    @GET("/users/instituteId/{id}")
//    Call<Person> getUserByInstituteId(@Header("Authorization") String credentials, @Path("id") String instituteId);

}