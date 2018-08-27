package retrofit.com.retrofit;

import retrofit.com.retrofit.dominio.Posts;
import retrofit.com.retrofit.endpoint.ApiEndpoint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiEndpoint apiService = retrofit.create(ApiEndpoint.class);
        Call<Posts> call = apiService.obterPost(3);

        call.enqueue(new Callback<Posts>() {
            public void onResponse(Call<Posts> call,
                                   Response<Posts> response) {
                int statusCode = response.code();
                Posts post = response.body();
                Log.i("teste","statuscode: " + statusCode);
                Log.i("teste", "Titulo: " + post.getTitle());
            }

            public void onFailure(Call<Posts> call, Throwable t) {
                // Log error here since request failed
                Log.i("teste", t.toString());
            }
        });
    }
}
