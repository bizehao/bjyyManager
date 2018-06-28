package com.example.bjyymanager.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bjyymanager.R;
import com.example.bjyymanager.entity.Quyu_Pptn;
import com.example.bjyymanager.entity.Zhan;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Administrator on 2017\11\15 0015.
 */

public class ZhanAdapter extends ArrayAdapter<Zhan> {

    private int resourceId;


    public ZhanAdapter(@NonNull Context context, int resource, @NonNull List<Zhan> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Zhan zhan = getItem(position);//获取当前项Quyu_Pptn实例
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.zhanImage = view.findViewById(R.id.zhan_image);
            viewHolder.zhanName = view.findViewById(R.id.zhan_name);
            viewHolder.biaoImage = view.findViewById(R.id.biao_image);
            view.setTag(viewHolder);//将ViewHolder存储在view中
        }else{
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();//重新获取ViewHolder
        }
        viewHolder.zhanImage.setImageResource(zhan.getImageId());
        viewHolder.zhanName.setText(zhan.getZhanName());
        viewHolder.biaoImage.setImageResource(zhan.getBiaoId());
        return view;
    }

    class ViewHolder{
        ImageView zhanImage;
        TextView zhanName;
        ImageView biaoImage;
    }
}
