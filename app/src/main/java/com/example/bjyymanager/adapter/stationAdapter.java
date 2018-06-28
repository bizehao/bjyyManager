package com.example.bjyymanager.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bjyymanager.MainActivity;
import com.example.bjyymanager.PptnActivity;
import com.example.bjyymanager.R;
import com.example.bjyymanager.RiverActivity;
import com.example.bjyymanager.RsvrActivity;
import com.example.bjyymanager.entity.Station;
import com.example.bjyymanager.stationActivity;
import java.util.List;


/**
 * Created by Administrator on 2017\11\16 0016.
 */

public class stationAdapter extends RecyclerView.Adapter<stationAdapter.ViewHolder> {

    private List<Station> stations;
    private int ta;
    private int userId;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView stationName;
        View stationView;

        public ViewHolder(View view) {
            super(view);
            stationView = view;
            stationName = view.findViewById(R.id.tx_station);
        }
    }

    public stationAdapter(List<Station> stations, int ta, int userId) {
        this.stations = stations;
        this.ta = ta;
        this.userId = userId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.station, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.stationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Station station = stations.get(position);
                if(ta == 1){
                    Intent intent = new Intent(view.getContext(), RiverActivity.class);
                    intent.putExtra("extra_stcd",station.getStcd());
                    intent.putExtra("extra_id", userId);
                    view.getContext().startActivity(intent);
                }else if(ta == 2){
                    Intent intent = new Intent(view.getContext(), RsvrActivity.class);
                    intent.putExtra("extra_stcd",station.getStcd());
                    Log.d("abc", "onClick: "+station.getStcd());
                    intent.putExtra("extra_id", userId);
                    Log.d("abc", "onClick: "+userId);
                    view.getContext().startActivity(intent);
                }else{
                    Intent intent = new Intent(view.getContext(), PptnActivity.class);
                    intent.putExtra("extra_stcd",station.getStcd());
                    intent.putExtra("extra_id", userId);
                    view.getContext().startActivity(intent);
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Station station = stations.get(position);
        holder.stationName.setText("站名："+station.getRtuNM()+":("+station.getCount()+")条");
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }
}
