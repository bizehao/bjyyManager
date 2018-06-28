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
import com.example.bjyymanager.entity.Quyu_Rsvr;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017\11\15 0015.
 */

public class rsvrAdapter extends ArrayAdapter<Quyu_Rsvr> {

    private int resourceId;


    public rsvrAdapter(@NonNull Context context, int resource, @NonNull List<Quyu_Rsvr> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Quyu_Rsvr rsvr = getItem(position);//获取当前项Quyu_Pptn实例
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.rtu = view.findViewById(R.id.text_rsvr_rtu);
            viewHolder.tm = view.findViewById(R.id.text_rsvr_tm);
            viewHolder.rz = view.findViewById(R.id.text_rsvr_rz);
            viewHolder.w = view.findViewById(R.id.text_rsvr_w);
            view.setTag(viewHolder);//将ViewHolder存储在view中
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        viewHolder.rtu.setText(rsvr.getRtunm());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        viewHolder.tm.setText(df.format( rsvr.getTm()));
        viewHolder.rz.setText(String.valueOf(rsvr.getRz()));
        viewHolder.w.setText(String.valueOf(rsvr.getW()));
        return view;
    }

    class ViewHolder{
        TextView rtu;
        TextView tm;
        TextView rz;
        TextView w;
    }
}
