package retrofit.com.retrofit;

import retrofit.com.retrofit.dominio.Posts;
import retrofit.com.retrofit.endpoint.ApiEndpoint;
import android.app.Activity;
import android.os.Bundle;
//import android.util.Log;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    private EditText entrada;
    private TextView saida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                preparacaoInicial();
//                Log.i("teste","statuscode: " + statusCode);
//                Log.i("teste", "Titulo: " + post.getTitle());
    }

//            public void onFailure(Call<Posts> call, Throwable t) {
//                // Log error here since request failed
//                Log.i("teste", t.toString());
//            }

    private void preparacaoInicial() {

        saida = findViewById(R.id.saida);
        entrada = findViewById(R.id.entrada);

        final Button botao = findViewById(R.id.botao);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifica();
            }
        });
    }

    private void verifica() {

        int id = Integer.parseInt(entrada.getText().toString());

        Gson gson = new GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create();
        Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
        ApiEndpoint apiService = retrofit.create(ApiEndpoint.class);
        Call<Posts> call = apiService.obterPost(id);

        call.enqueue(new Callback<Posts>() {
        public void onResponse(Call<Posts> call,
                Response<Posts> response) {
            int statusCode = response.code();
            Posts post = response.body();

            Log.i("teste","statuscode: " + statusCode);

            saida.setText(post.getTitle());}

            public void onFailure(Call<Posts> call, Throwable t) {
                // Log error here since request failed
                Log.i("teste",t.toString());

        }});
    }
}

