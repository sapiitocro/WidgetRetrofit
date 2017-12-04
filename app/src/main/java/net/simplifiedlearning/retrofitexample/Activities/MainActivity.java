package net.simplifiedlearning.retrofitexample.Activities;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import net.simplifiedlearning.retrofitexample.Services.ApiService;
import net.simplifiedlearning.retrofitexample.Services.ApiServiceGenerator;
import net.simplifiedlearning.retrofitexample.Models.Incidencia;
import net.simplifiedlearning.retrofitexample.Adapters.IncidenciaAdapter;
import net.simplifiedlearning.retrofitexample.R;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    private RecyclerView incidenciaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NuevaIncidenciaActivity.class);
                startActivity(intent);
            }
        });
        //calling the method to display the heroes
        incidenciaList = (RecyclerView) findViewById(R.id.recyclerview);
        incidenciaList.setLayoutManager(new LinearLayoutManager(this));
        incidenciaList.setAdapter(new IncidenciaAdapter(this));
        getIncidencia();
    }




    public void getIncidencia() {
        ApiService service = ApiServiceGenerator.createService(ApiService.class);
        Call<List<Incidencia>> call = service.getIncidencia();

        call.enqueue(new Callback<List<Incidencia>>() {
            @Override
            public void onResponse(Call<List<Incidencia>> call, Response<List<Incidencia>> response) {
                try {

                    int statusCode = response.code();
                    Log.d("CODE STATUS", "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Incidencia> incidencias = response.body();
                        Log.d("Incidencia", "incidencia: " + incidencias);

                        IncidenciaAdapter adapter = (IncidenciaAdapter) incidenciaList.getAdapter();
                        adapter.setIncidencia(incidencias);
                        adapter.notifyDataSetChanged();

                    } else {
                        Log.e("Servidor", "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e("t", "onThrowable: " + t.toString(), t);
                        Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    }catch (Throwable x){}
                }
            }

            @Override
            public void onFailure(Call<List<Incidencia>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
