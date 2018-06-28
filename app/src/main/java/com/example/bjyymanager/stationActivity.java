package com.example.bjyymanager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bjyymanager.adapter.pptnAdapter;
import com.example.bjyymanager.adapter.stationAdapter;
import com.example.bjyymanager.entity.Quyu_Pptn;
import com.example.bjyymanager.entity.Station;
import com.example.bjyymanager.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class stationActivity extends AppCompatActivity {
    //提示框
    private ProgressDialog progressDialog = null;
    private int ta;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        //去除头面积
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        TextView title = findViewById(R.id.title_text);
        title.setText("监测站信息");
        Button edit = findViewById(R.id.title_edit);
        edit.setText("查看所有");
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ta == 1){
                    Intent intent = new Intent(view.getContext(), RiverActivity.class);
                    intent.putExtra("extra_id", id);
                    startActivity(intent);
                }else if(ta == 2){
                    Intent intent = new Intent(view.getContext(), RsvrActivity.class);
                    intent.putExtra("extra_id", id);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(view.getContext(), PptnActivity.class);
                    intent.putExtra("extra_id", id);
                    startActivity(intent);
                }
            }
        });
        Intent intent = getIntent();
        id = intent.getIntExtra("extra_id",-1);
        Log.d("stationActivity", "onCreate: id为 "+id);
        ta = intent.getIntExtra("extra_ta",-1);
        String table = null;
        if(ta == 1){
            table = "getRiverStation.do";
        }else if(ta == 2){
            table = "getRsvrStation.do";
        }else{
            table = "getPptnStation.do";
        }
        String address="http://192.168.1.63:8080/bjyyWebService/stbb/"+table+"?id="+ id;
        Log.d("stationActivity", "onClick: 账号"+address);
        //提示框进度条
        progressDialog = new ProgressDialog(stationActivity.this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("正在加载信息......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        HttpUtil.sendOKHttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d("stationActivity", "onFailure: 错误"+e.getMessage());
                shouFailure();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.d("stationActivity", "onResponse: 这个集合"+responseData);
                showResponse(responseData);
            }
        });
    }

    /**
     * 将子线程转移到主线程中
     * @param responseData
     */
    private void showResponse(final String responseData){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                parseJSONWithGSON(responseData);
            }
        });

    }

    /**
     * 用gson解析java web 返回的json数据
     * @param jsonData
     */
    private void parseJSONWithGSON(String jsonData){
        jsonData = jsonData.replace("\\","");
        jsonData= jsonData.substring(1,jsonData.length()-1);
        Gson gson = new Gson();
        List<Station> appList = gson.fromJson(jsonData, new TypeToken<List<Station>>(){}.getType());
        progressDialog.dismiss();
        if(appList.size()==0){
            AlertDialog.Builder dialog = new AlertDialog.Builder(stationActivity.this);
            dialog.setTitle("提示");
            dialog.setMessage("没有数据");
            dialog.setCancelable(false);
            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            dialog.show();
        }
        //另一种显示
        Log.d("stationActivity", "parseJSONWithGSON: gson解析后"+appList);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        //瀑布布局
        //StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        //网格布局
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        //显示和listview一样效果
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        stationAdapter adapter = new stationAdapter(appList, ta, id);
        recyclerView.setAdapter(adapter);

    }
    //发送失败 转入主进程
    private void shouFailure(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                //提示框不带进度条的
                AlertDialog.Builder dialog = new AlertDialog.Builder(stationActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("数据加载失败，请重试");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
            }
        });
    }
}
