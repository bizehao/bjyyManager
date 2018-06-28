package com.example.bjyymanager.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.bjyymanager.R;

/**
 * Created by Administrator on 2017\11\14 0014.
 */

public class TitleLayout extends LinearLayoutCompat {
    public TitleLayout(Context context) {
        super(context);
    }

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.activity_title, this);
        Button back = findViewById(R.id.title_back);
        Button edit = findViewById(R.id.title_edit);
        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ((Activity) getContext()).finish();
            }
        });
        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "刷新", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public TitleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
