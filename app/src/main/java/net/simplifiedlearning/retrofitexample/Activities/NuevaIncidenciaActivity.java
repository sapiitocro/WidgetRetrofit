package net.simplifiedlearning.retrofitexample.Activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.simplifiedlearning.retrofitexample.Services.ApiService;
import net.simplifiedlearning.retrofitexample.Services.ApiServiceGenerator;
import net.simplifiedlearning.retrofitexample.R;
import net.simplifiedlearning.retrofitexample.Services.ResponseMessage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevaIncidenciaActivity extends AppCompatActivity {

    private EditText namenew,descnew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_incidencia);
        namenew=(EditText)findViewById(R.id.namenew);
        descnew=(EditText)findViewById(R.id.descnew);
    }
    public void callRegister(View view) {

        String name = namenew.getText().toString();
        String desc = descnew.getText().toString();


        if (name.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "Debe completar todos los campos!!!", Toast.LENGTH_SHORT).show();
            return;
        }

        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<ResponseMessage> call = null;


        // Si no se incluye imagen hacemos un envío POST simple
        call = service.createIncidencia(name, desc);


        call.enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                try {

                    int statusCode = response.code();
                    Log.d("TAG", "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        ResponseMessage responseMessage = response.body();
                        Log.d("TAG", "responseMessage: " + responseMessage);

                        Toast.makeText(NuevaIncidenciaActivity.this, responseMessage.getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(NuevaIncidenciaActivity.this, MainActivity.class);
                        startActivity(intent);

                    } else {
                        Log.e("TAG", "onError: " + response.errorBody().string());
                        throw new Exception("Error en el servicio");
                    }

                } catch (Throwable t) {
                    try {
                        Log.e("TAG", "onThrowable: " + t.toString(), t);
                        Toast.makeText(NuevaIncidenciaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
                    } catch (Throwable x) {
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.toString());
                Toast.makeText(NuevaIncidenciaActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }

        });
    }
}


