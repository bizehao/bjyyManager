package com.example.bjyymanager.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.bjyymanager.R;
import com.example.bjyymanager.entity.Quyu_Pptn;
import com.example.bjyymanager.entity.Quyu_River;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017\11\15 0015.
 */

public class riverAdapter extends ArrayAdapter<Quyu_River> {

    private int resourceId;


    public riverAdapter(@NonNull Context context, int resource, @NonNull List<Quyu_River> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Quyu_River river = getItem(position);//获取当前项Quyu_Pptn实例
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.rtu = view.findViewById(R.id.text_river_rtu);
            viewHolder.tm = view.findViewById(R.id.text_river_tm);
            viewHolder.q = view.findViewById(R.id.text_river_q);
            viewHolder.z = view.findViewById(R.id.text_river_z);
            view.setTag(viewHolder);//将ViewHolder存储在view中
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        viewHolder.rtu.setText(river.getRtunm());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        viewHolder.tm.setText(df.format( river.getTm()));
        viewHolder.q.setText(String.valueOf(river.getQ()));
        viewHolder.z.setText(String.valueOf(river.getZ()));
        return view;
    }

    class ViewHolder{
        TextView rtu;
        TextView tm;
        TextView q;
        TextView z;

    }
}
