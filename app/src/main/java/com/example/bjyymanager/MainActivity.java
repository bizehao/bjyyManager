package com.example.bjyymanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bjyymanager.utils.HttpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private int id;
    private ProgressDialog progressDialog = null;
    private Button login = null;
    private CheckBox checkboxButton = null;
    //记住密码
    SharedPreferences sp = null;
    EditText loginName = null;
    EditText loginPass = null;
    //账号密码
    String lgName = null;
    String lgPass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        checkboxButton = findViewById(R.id.checkBoxLogin);
        init();
    }

    public void init()
    {
        loginName = findViewById(R.id.user_input);
        loginPass = findViewById(R.id.pass_input);
        checkboxButton = findViewById(R.id.checkBoxLogin);
        login =  findViewById(R.id.login);
        //记住密码
        if (sp.getBoolean("checkboxBoolean", false))
        {
            loginName.setText(sp.getString("loginName", null));
            loginPass.setText(sp.getString("loginPass", null));
            checkboxButton.setChecked(true);

        }
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == login){
            loginName = findViewById(R.id.user_input);
            loginPass = findViewById(R.id.pass_input);
            checkboxButton = findViewById(R.id.checkBoxLogin);
            //记住密码
            if (sp.getBoolean("checkboxBoolean", false))
            {
                loginName.setText(sp.getString("loginName", null));
                loginPass.setText(sp.getString("loginPass", null));
                checkboxButton.setChecked(true);

            }
            Log.d("MainActivity", "onClick: 进入到事件中");
            lgName = loginName.getText().toString().trim();
            lgPass = loginPass.getText().toString().trim();
            if(lgName.equals("") || lgName==null || lgPass.equals("") || lgPass==null){
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("请输入账号或密码");
                dialog.setCancelable(false);
                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                dialog.show();
            }else{
                //提示框进度条
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setTitle("提示");
                progressDialog.setMessage("正在登陆中......");
                progressDialog.setCancelable(false);
                progressDialog.show();
                Log.d("上面的账号信息", "onClick: 账号"+lgName+",密码"+lgPass);
                String address="http://192.168.1.63:8080/bjyyWebService/stbb/login.do?lgName="+lgName +"&lgPass="+lgPass;
                Log.d("下面的账号信息", "onClick: 账号"+address);
                HttpUtil.sendOKHttpRequest(address, new okhttp3.Callback() {
                    //访问失败时
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                        Log.d("MainActivity", "onFailure: 错误"+e.getMessage());
                        shouFailure();
                    }
                    //访问成功时
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        id = Integer.parseInt(responseData);
                        if(id == 0){
                            Log.d("MainActivity", "onResponse: 登陆失败");
                        }else{
                            Log.d("MainActivity", "onResponse: 登陆成功");
                        }
                        showResponse(id);
                    }
                });
            }
        }
    }

    /**
     * 用gson解析java web 返回的json数据
     * @param jsonData
     */
   /* private void parseJSONWithGSON(String jsonData){
        Gson gson = new Gson();
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>(){}.getType());
        for (App app : appList) {
            Log.d("MainActivity", "id is " + app.getId());
            Log.d("MainActivity", "name is " + app.getName());
            Log.d("MainActivity", "version is " + app.getVersion());
        }
    }*/

    /**
     * 官方的解析json
     * @param jsonData
     */
    private void parseJSONWithJSONObject(String jsonData){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for(int i = 0; i<jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int state = jsonObject.getInt("state");
                int id = jsonObject.getInt("data");
                String message = jsonObject.getString("message");
                Log.d("MainActivity", "state is " + state);
                Log.d("MainActivity", "id is " + id);
                Log.d("MainActivity", "message is " + message);
                if(id == 0){
                    Toast.makeText(MainActivity.this,"账户或密码不正确",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //发送成功 转入主进程
    private void showResponse(final int id){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(id == 0){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"账户或密码不正确",Toast.LENGTH_SHORT).show();

                }else{
                    progressDialog.dismiss();
                    Log.d("===========", "run: 账号"+lgName);
                    Log.d("===========", "run: 密码"+lgPass);

                    boolean CheckBoxLogin = checkboxButton.isChecked();
                    Log.d("===========", "run: 密码"+CheckBoxLogin);
                    if (CheckBoxLogin)
                    {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("loginName", lgName);
                        editor.putString("loginPass", lgPass);
                        editor.putBoolean("checkboxBoolean", true);
                        editor.commit();
                    }
                    else
                    {
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("loginName", null);
                        editor.putString("loginPass", null);
                        editor.putBoolean("checkboxBoolean", false);
                        editor.commit();
                    }
                    Toast.makeText(MainActivity.this,"登陆成功，欢迎你",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ChooseZhanActivity.class);
                    intent.putExtra("extra_id", id);
                    startActivity(intent);
                }
            }
        });
    }
    //发送失败 转入主进程
    private void shouFailure(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressDialog.dismiss();
                //提示框不带进度条的
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("提示");
                dialog.setMessage("您的网络问题，请检查并重新登录");
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
