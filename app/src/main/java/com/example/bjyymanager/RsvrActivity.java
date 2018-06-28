package com.example.bjyymanager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bjyymanager.adapter.pptnAdapter;
import com.example.bjyymanager.adapter.rsvrAdapter;
import com.example.bjyymanager.entity.Quyu_Pptn;
import com.example.bjyymanager.entity.Quyu_Rsvr;
import com.example.bjyymanager.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class RsvrActivity extends AppCompatActivity {
    //提示框
    private ProgressDialog progressDialog = null;
    private String address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsvr);
        //去除头面积
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.hide();
        }
        TextView title = findViewById(R.id.title_text);
        title.setText("水库水位信息");

        Intent intent = getIntent();
        int id = intent.getIntExtra("extra_id",-1);
        Log.d("RsvrActivity", "onCreate: id为 "+id);
        String stcd = intent.getStringExtra("extra_stcd");

        Log.d("RsvrActivity", "onCreate: id为 "+stcd);
        if(stcd != null){
            address="http://192.168.1.63:8080/bjyyWebService/stbb/getRsvrByStcd.do?id="+id+"&stcd="+stcd;
        }else{
            address="http://192.168.1.63:8080/bjyyWebService/stbb/getRsvr.do?id="+id;
        }
        Log.d("下面的账号信息", "onClick: 账号"+address);
        //提示框进度条
        progressDialog = new ProgressDialog(RsvrActivity.this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("正在加载信息......");
        progressDialog.setCancelable(false);
        progressDialog.show();
        HttpUtil.sendOKHttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.d("MainActivity", "onFailure: 错误"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.d("PptnActivity", "onResponse: 这个集合"+responseData);
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
        List<Quyu_Rsvr> appList = gson.fromJson(jsonData, new TypeToken<List<Quyu_Rsvr>>(){}.getType());
        progressDialog.dismiss();
        if(appList.size()==0){
            AlertDialog.Builder dialog = new AlertDialog.Builder(RsvrActivity.this);
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
        rsvrAdapter arrRtu = new rsvrAdapter(RsvrActivity.this, R.layout.xsrsvr, appList);
        //获取ListView
        ListView listView = findViewById(R.id.list_rsvr_view);
        listView.setAdapter(arrRtu);

    }
}
