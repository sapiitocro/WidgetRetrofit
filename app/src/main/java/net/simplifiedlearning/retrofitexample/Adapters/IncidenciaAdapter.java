package net.simplifiedlearning.retrofitexample.Adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.simplifiedlearning.retrofitexample.Models.Incidencia;
import net.simplifiedlearning.retrofitexample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gian Franco on 3/12/2017.
 */

public class IncidenciaAdapter extends RecyclerView.Adapter<IncidenciaAdapter.ViewHolder>{


    private static final String TAG = IncidenciaAdapter.class.getSimpleName();
    public List<Incidencia> incidencia;
    private Activity activity;
    public IncidenciaAdapter(Activity activity){
        this.incidencia=new ArrayList<>();
        this.activity=activity;
    }


    public void setIncidencia(List<Incidencia> incidencia){
        this.incidencia=incidencia;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name_text;
        public TextView desc_text;
        public ViewHolder(View itemView) {
            super(itemView);
            name_text=(TextView) itemView.findViewById(R.id.name_text);
            desc_text=(TextView) itemView.findViewById(R.id.desc_text);
        }
    }
    @Override
    public IncidenciaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_incidencia, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(IncidenciaAdapter.ViewHolder holder, int position) {
        Incidencia incidencia = this.incidencia.get(position);
        holder.name_text.setText(incidencia.getName());
        holder.desc_text.setText(incidencia.getDesc());
    }

    @Override
    public int getItemCount() {
        return this.incidencia.size();
    }
}
