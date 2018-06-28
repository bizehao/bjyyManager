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

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017\11\15 0015.
 */

public class pptnAdapter extends ArrayAdapter<Quyu_Pptn> {

    private int resourceId;


    public pptnAdapter(@NonNull Context context, int resource, @NonNull List<Quyu_Pptn> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Quyu_Pptn pptn = getItem(position);//获取当前项Quyu_Pptn实例
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.rtu = view.findViewById(R.id.text_pptn_rtu);
            viewHolder.tm = view.findViewById(R.id.text_pptn_tm);
            viewHolder.drp = view.findViewById(R.id.text_pptn_drp);
            view.setTag(viewHolder);//将ViewHolder存储在view中
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        viewHolder.rtu.setText(pptn.getRtunm());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        viewHolder.tm.setText(df.format( pptn.getTm()));
        viewHolder.drp.setText(String.valueOf(pptn.getDrp()));
        return view;
    }

    class ViewHolder{
        TextView rtu;
        TextView tm;
        TextView drp;
    }
}
