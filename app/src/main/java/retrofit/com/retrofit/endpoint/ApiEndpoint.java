package retrofit.com.retrofit.endpoint;

import retrofit.com.retrofit.dominio.Posts;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoint {

    @GET("posts/{id}")
    Call<Posts> obterPost(@Path("id") int postID);
}
