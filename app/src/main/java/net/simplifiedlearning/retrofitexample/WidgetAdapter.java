package net.simplifiedlearning.retrofitexample;


import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import net.simplifiedlearning.retrofitexample.Activities.MainActivity;
import net.simplifiedlearning.retrofitexample.Adapters.IncidenciaAdapter;
import net.simplifiedlearning.retrofitexample.Models.Incidencia;
import net.simplifiedlearning.retrofitexample.Services.ApiService;
import net.simplifiedlearning.retrofitexample.Services.ApiServiceGenerator;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class WidgetAdapter implements RemoteViewsService.RemoteViewsFactory {
    Context context;
    String[] listArray = getin();
    public String[] getin(){
        String[] Inci = {"1", "2", "3", "4", "5"};
        ApiService service = ApiServiceGenerator.createService(ApiService.class);

        Call<List<Incidencia>> call = service.getIncidencia();
   
        try {
            Response<List<Incidencia>> response = call.execute();
            int statusCode = response.code();
            Log.d("CODE STATUS", "HTTP status code: " + statusCode);

            if (response.isSuccessful()) {

                List<Incidencia> incidencias = response.body();
                Log.d("Incidencia", "incidencia: " + incidencias);
                Inci = new String[incidencias.size()];
                for (int i = 0; i < incidencias.size(); i++) {
                    Inci[i] = incidencias.get(i).getName();
                }

            } else {
                Log.e("Servidor", "onError: " + response.errorBody().string());
                throw new Exception("Error en el servicio");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
/*
        call.enqueue(new Callback<List<Incidencia>>() {
                public String[] Inci;


            @Override
            public void onResponse(Call<List<Incidencia>> call, Response<List<Incidencia>> response) {
                try {

                    int statusCode = response.code();
                    Log.d("CODE STATUS", "HTTP status code: " + statusCode);

                    if (response.isSuccessful()) {

                        List<Incidencia> incidencias = response.body();
                        Log.d("Incidencia", "incidencia: " + incidencias);
                         Inci = new String[incidencias.size()];
                        for (int i = 0; i < incidencias.size(); i++) {
                            Inci[i] = incidencias.get(i).getName();
                        }

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
*/
        return Inci;
    }




    public WidgetAdapter(Context context)  {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return listArray.length;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.list_item);
        remoteViews.setTextViewText(R.id.textView, listArray[position]);

        Intent intent = new Intent();
        intent.setAction(WidgetProvider.TOAST_ACTION);
        intent.putExtra(WidgetProvider.KEY_ITEM, listArray[position]);
        remoteViews.setOnClickFillInIntent(R.id.list_item, intent);

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
