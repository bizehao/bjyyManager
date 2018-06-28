package com.example.bjyymanager.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2017\11\13 0013.
 */

public class HttpUtil {
    /**
     * 网络操作部分
     * @param address
     * @param callback
     */
    public static void sendOKHttpRequest(String address, okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }

}
