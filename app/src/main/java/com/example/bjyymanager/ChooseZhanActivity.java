package com.example.bjyymanager;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bjyymanager.entity.Zhan;
import com.example.bjyymanager.utils.ZhanAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChooseZhanActivity extends AppCompatActivity {

    private List<Zhan> zhanList = new ArrayList<>();
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_zhan);
        //去除头面积
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        TextView title = findViewById(R.id.title_text);
        title.setText("水情信息");
        //获取传过来的id
        Intent intent = getIntent();
        id = intent.getIntExtra("extra_id",-1);
        //加载数据列表
        initZhan();
        ZhanAdapter zhanAdapter = new ZhanAdapter(ChooseZhanActivity.this, R.layout.zhan, zhanList);
        ListView listView = findViewById(R.id.choose_tv);
        listView.setAdapter(zhanAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Zhan zhanClick = zhanList.get(position);
                switch (zhanClick.getId()){
                    case 1:
                        Intent intent1 = new Intent(ChooseZhanActivity.this, stationActivity.class);
                        intent1.putExtra("extra_id", id);
                        intent1.putExtra("extra_ta", 1);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(ChooseZhanActivity.this, stationActivity.class);
                        intent2.putExtra("extra_id", id);
                        intent2.putExtra("extra_ta", 2);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(ChooseZhanActivity.this, stationActivity.class);
                        intent3.putExtra("extra_id", id);
                        intent3.putExtra("extra_ta", 3);
                        startActivity(intent3);
                        break;
                    default:
                        break;
                }
               /* switch (zhanClick.getId()){
                    case 1:
                        Intent intent1 = new Intent(ChooseZhanActivity.this, RiverActivity.class);
                        intent1.putExtra("extra_id", id);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(ChooseZhanActivity.this, RsvrActivity.class);
                        intent2.putExtra("extra_id", id);
                        startActivity(intent2);
                        break;
                    case 3:
                        Intent intent3 = new Intent(ChooseZhanActivity.this, PptnActivity.class);
                        intent3.putExtra("extra_id", id);
                        startActivity(intent3);
                        break;
                    default:
                        break;
                }*/
            }
        });
    }
    private void initZhan(){
        Zhan hD = new Zhan(1,"河道站", R.drawable.hedao, R.drawable.biao);
        zhanList.add(hD);
        Zhan sK = new Zhan(2,"水库站", R.drawable.hedao, R.drawable.biao);
        zhanList.add(sK);
        Zhan yL = new Zhan(3,"雨量站", R.drawable.hedao, R.drawable.biao);
        zhanList.add(yL);
    }
}
